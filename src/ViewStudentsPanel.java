import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ViewStudentsPanel extends JPanel {

    private JTable studentTable;
    private StudentTableModel studentModel;

    private JButton loadButton;
    private JButton searchButton;
    private JButton deleteButton;
    private JTextField searchField;

    private List<Student> students = new ArrayList<>();

    public ViewStudentsPanel() {
        setLayout(new BorderLayout());

        // Table model starts empty
        studentModel = new StudentTableModel(new ArrayList<>());
        studentTable = new JTable(studentModel);
        studentTable.setAutoCreateRowSorter(true);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(studentTable);

        // BUTTONS + SEARCH FIELD
        JPanel bottomPanel = new JPanel(new FlowLayout());

        loadButton = new JButton("Load");
        searchButton = new JButton("Search");
        deleteButton = new JButton("Delete");
        searchField = new JTextField(15);

        bottomPanel.add(new JLabel("Search by ID or Name:"));
        bottomPanel.add(searchField);
        bottomPanel.add(searchButton);
        bottomPanel.add(deleteButton);
        bottomPanel.add(loadButton);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // ACTIONS
        loadButton.addActionListener(e -> loadStudents());
        searchButton.addActionListener(e -> searchStudent());
        deleteButton.addActionListener(e -> deleteStudent());
    }

    // ✅ LOAD from File
    private void loadStudents() {
        students = FileHandler.loadFromFile();
        updateTable(students);

        if (!students.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Students loaded successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "No student data found!",
                    "Info",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // ✅ SEARCH in table list
    private void searchStudent() {
        String key = searchField.getText().trim();
        if (key.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Enter search text first!",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getStudentID().equalsIgnoreCase(key) ||
                    s.getStudentName().equalsIgnoreCase(key)) {
                result.add(s);
            }
        }

        if (result.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No matching student found!",
                    "Not Found",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        updateTable(result);
    }

    // ✅ DELETE from table + memory + file
    private void deleteStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Select a student to delete!",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Handle sorting index issues
        int modelRow = studentTable.convertRowIndexToModel(selectedRow);
        Student s = students.get(modelRow);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Delete Student: " + s.getStudentName() + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            students.remove(modelRow);
            FileHandler.saveToFile(students); // rewrite file
            updateTable(students);
            JOptionPane.showMessageDialog(this,
                    "Student deleted!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // ✅ Refresh table view
    private void updateTable(List<Student> list) {
        studentModel.setStudents(list);
    }
}
