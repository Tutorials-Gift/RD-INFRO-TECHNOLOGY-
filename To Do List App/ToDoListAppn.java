import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ToDoListAppn {
    private JFrame frame;
    private DefaultListModel<String> tasksListModels;
    private JList<String> taskList;
    private JTextArea taskInput;

    public ToDoListAppn() {
        frame = new JFrame("To-Do List Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        tasksListModels = new DefaultListModel<>();
        taskList = new JList<>(tasksListModels);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        taskInput = new JTextArea(1,1);
        JButton addButton = new JButton("ADD TASK");
        addButton.addActionListener(e -> addTask());

        panel.add(taskInput, BorderLayout.CENTER);
        panel.add(addButton, BorderLayout.EAST);

        frame.add(panel, BorderLayout.NORTH);
        addButton.setBackground(Color.GREEN.brighter());
        frame.add(new JScrollPane(taskList), BorderLayout.CENTER);
        frame.add(createButtonPanel(), BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton editButton = new JButton("EDIT TASK");
        editButton.addActionListener(e -> editTask());
        buttonPanel.add(editButton); 
        editButton.setBackground(Color.CYAN);

        JButton deleteButton = new JButton("DELETE TASK");
        deleteButton.addActionListener(e -> deleteTask());
        buttonPanel.add(deleteButton);
        deleteButton.setBackground(Color.PINK);


        return buttonPanel;
    }

    private void addTask() {
        String task = taskInput.getText().trim();
        if (!task.isEmpty()) {
            tasksListModels.addElement(task);
            taskInput.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Task can't be empty!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            String newTask = JOptionPane.showInputDialog(frame, "Edit Task:", tasksListModels.getElementAt(selectedIndex));
            if (newTask != null && !newTask.trim().isEmpty()) {
                tasksListModels.set(selectedIndex, newTask.trim());
            }
        } else {
            JOptionPane.showMessageDialog(frame, " Please Select a task to edit", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            tasksListModels.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(frame, " Please Select a task to delete", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoListAppn::new);
    }
}
