import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ViewStudentsPanel extends JPanel {

    private JTable studentTable;
    private DefaultTableModel tableModel;
    private List<Student> students = new ArrayList<>();

    public ViewStudentsPanel(JPanel mainPanel) {
        setLayout(new BorderLayout());

        String[] columns = {"ID", "Name", "Age", "Gender", "Department", "GPA"};
        tableModel = new DefaultTableModel(columns, 0);

        studentTable = new JTable(tableModel);
        studentTable.setAutoCreateRowSorter(true);
        studentTable.setDefaultEditor(Object.class, null);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        add(scrollPane, BorderLayout.CENTER);

    }

    public void loadStudents() {
        students = FileHandler.loadFromFile();
        updateTable(students);

        if (students.isEmpty())
            JOptionPane.showMessageDialog(this, "No data!", "Info", JOptionPane.WARNING_MESSAGE);
        else
            JOptionPane.showMessageDialog(this, "Loaded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateTable(List<Student> list) {
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
