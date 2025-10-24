import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ViewStudentsPanel extends JPanel {

    private JTable studentTable;
    private DefaultTableModel tableModel;
    private StudentManager manager;
    public ViewStudentsPanel(StudentManager manager) {
        this.manager=manager;
        setLayout(new BorderLayout());

        String[] columns = {"ID", "Name", "Age", "Gender", "Department", "GPA"};
        tableModel = new DefaultTableModel(columns, 0);

        studentTable = new JTable(tableModel);
        studentTable.setAutoCreateRowSorter(true);
        studentTable.setDefaultEditor(Object.class, null);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(studentTable),BorderLayout.CENTER);

    }

    public void loadStudents(StudentManager manager) {
        updateTable(manager.getStudents());
    }

    private void updateTable(java.util.List<Student> list) {
        tableModel.setRowCount(0); // Clear

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
