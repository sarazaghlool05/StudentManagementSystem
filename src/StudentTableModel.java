import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StudentTableModel extends AbstractTableModel {

    private List<Student> students;
    private final String[] columnNames = {
            "ID", "Name", "Age", "Gender", "Department", "GPA"
    };

    public StudentTableModel(List<Student> students) {
        this.students = students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
        fireTableDataChanged(); // Refresh table UI
    }

    @Override
    public int getRowCount() {
        return students == null ? 0 : students.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student s = students.get(rowIndex);

        switch (columnIndex) {
            case 0: return s.getStudentID();
            case 1: return s.getStudentName();
            case 2: return s.getStudentAge();
            case 3: return s.getGender();
            case 4: return s.getDepartment();
            case 5: return s.getGPA();
        }
        return null;
    }
}
