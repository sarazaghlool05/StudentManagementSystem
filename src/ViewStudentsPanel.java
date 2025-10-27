import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewStudentsPanel extends JPanel {

    private JTable studentTable;
    private DefaultTableModel tableModel;
    private StudentManager manager;

    public ViewStudentsPanel(StudentManager manager) {
        this.manager = manager;

        setLayout(new BorderLayout());
        initializeTable();
        add(new JScrollPane(studentTable), BorderLayout.CENTER);
    }

    private void initializeTable() {
        String[] columns = {"ID", "Name", "Age", "Gender", "Department", "GPA"};
        tableModel = new DefaultTableModel(columns, 0);

        studentTable = new JTable(tableModel);
        studentTable.setAutoCreateRowSorter(true);
        studentTable.setDefaultEditor(Object.class, null); // make table read-only
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentTable.setRowHeight(28);
    }

    public void loadStudents(StudentManager manager) {
        updateTable(manager.getStudents());
    }

    private void updateTable(List<Student> list) {
        tableModel.setRowCount(0); // Clear existing rows

        for (Student s : list) {
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
