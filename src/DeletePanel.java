import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class DeletePanel extends JPanel {

    private StudentManager manager;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JButton deleteButton;
    private int selectedRow = -1;

    public DeletePanel(StudentManager manager, CardLayout cardLayout, JPanel mainPanel) {
        this.manager = manager;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel title = new JLabel("Delete Student");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        // Table setup (same look as View panel)
        initializeTable();
        add(new JScrollPane(studentTable), BorderLayout.CENTER);

        // Delete button
        deleteButton = new JButton("delete");
        deleteButton.setPreferredSize(new Dimension(220, 35));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Listeners
        studentTable.getSelectionModel().addListSelectionListener(e -> handleRowSelection());
        deleteButton.addActionListener(e -> handleDeleteAction());

        // Reload students every time the panel is shown
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                loadStudents();
            }
        });

        // Initial load
        loadStudents();
    }

    private void initializeTable() {
        String[] columns = {"ID", "Name", "Age", "Gender", "Department", "GPA"};
        tableModel = new DefaultTableModel(columns, 0);
        studentTable = new JTable(tableModel);

        // Match View panel’s design
        studentTable.setDefaultEditor(Object.class, null);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentTable.setRowHeight(28);
        studentTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        studentTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        studentTable.setAutoCreateRowSorter(true);
    }

    // ✅ This is the key — same logic as the old text-based delete
    private void handleDeleteAction() {
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a student to delete.");
            return;
        }

        // convert view row to model row
        int modelRow = studentTable.convertRowIndexToModel(selectedRow);
        String id = tableModel.getValueAt(modelRow, 0).toString();
        String name = tableModel.getValueAt(modelRow, 1).toString();

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete " + name + " (ID: " + id + ")?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            // Call same backend delete method as before
            boolean deleted = manager.deleteStudent(id);

            if (deleted) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully!");
                FileHandler.saveToFile(manager.getStudents()); // preserve consistency
                loadStudents(); // refresh table
                selectedRow = -1;
            } else {
                JOptionPane.showMessageDialog(this, "Student ID not found!");
            }
        }
    }

    private void handleRowSelection() {
        selectedRow = studentTable.getSelectedRow();
    }

    private void loadStudents() {
        tableModel.setRowCount(0);
        List<Student> students = manager.getStudents();

        for (Student s : students) {
            Object[] row = {
                    s.getStudentID(),
                    s.getStudentName(),
                    s.getStudentAge(),
                    s.getGender(),
                    s.getDepartment(),
                    s.getGPA()
            };
            tableModel.addRow(row);
        }
    }
}

