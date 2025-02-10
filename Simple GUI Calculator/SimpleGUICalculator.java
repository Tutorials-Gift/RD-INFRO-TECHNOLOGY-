import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGUICalculator extends JFrame implements ActionListener {
    JTextField display;
    double num1=0,num2=0,result=0;
    char operator;

    public SimpleGUICalculator () {

        setTitle("SimpleCalculator ");
        setSize(500, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("Times New Roman", Font.BOLD, 25));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(Color.white);
        display.setForeground(Color.black);
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));
        buttonPanel.setBackground(Color.white);

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "x",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Times New Roman", Font.BOLD, 25));
            button.setFocusPainted(false);

            if (text.equals("C")) {
                button.setBackground(Color.white);
                button.setForeground(Color.red);
            } else if ("+-x/=".contains(text)) {
                button.setBackground(Color.white);
                button.setForeground(Color.green);
            } else {
                button.setForeground(Color.BLACK);
            }

            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]")) {
            display.setText(display.getText() + command);
        }
        
        else if (command.matches("[+\\-x/]")) {
            num1 = Double.parseDouble(display.getText());
            operator = command.charAt(0);
            display.setText("");
        }

        else if (command.equals("C")) {
            display.setText("");
            num1 = num2 = result = 0;
        }

        else if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case 'x': result = num1 * num2; break;
                case '/': 
                    if (num2 != 0) result = num1 / num2; 
                    else JOptionPane.showMessageDialog(this, "Number can't be divided by!");
                    break;
            }
            display.setText(String.valueOf(result));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleGUICalculator calculator = new SimpleGUICalculator();
            calculator.setVisible(true);
        });
    }
}
