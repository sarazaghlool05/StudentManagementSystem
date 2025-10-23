import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class SearchUpdatePanel extends JPanel {
    private JTextField searchField;
    private JButton searchButton;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private StudentManager manager;

    public SearchUpdatePanel(StudentManager manager){
        this.manager = manager;
        setLayout(new BorderLayout());
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search by ID or Name: "));
        searchField = new JTextField(15);
        searchButton = new JButton("Search");
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        String[] columns = {"ID", "Name", "Age", "Gender", "Department", "GPA"};
        tableModel = new DefaultTableModel(columns,0);
        studentTable = new JTable(tableModel);
        add(new JScrollPane(studentTable),BorderLayout.CENTER);
        searchButton.addActionListener(e -> searchStudent());
    }

    private void searchStudent(){
        String searched = searchField.getText().trim();
        tableModel.setRowCount(0);
        ArrayList<Student> results = manager.searchStudent(searched);

        if(results.isEmpty()){
            JOptionPane.showMessageDialog(this,"No search matches!");
            return;
        }
        for(int i = 0; i < results.size(); i++) {
            Student s = results.get(i);
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
