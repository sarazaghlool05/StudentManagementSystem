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
    //search

    private JTextField nameField, ageField, deptField, gpaField;
    private JComboBox<String> genderBox;
    private JButton updateButton;
    private int selectedRow = -1;
    //update


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
        studentTable.setDefaultEditor(Object.class, null);
        add(new JScrollPane(studentTable),BorderLayout.CENTER);
        searchButton.addActionListener(e -> searchStudent());

        JPanel updatePanel = new JPanel(new GridLayout(6,2,5,5));
        updatePanel.setBorder(BorderFactory.createTitledBorder("Update Student"));

        updatePanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        updatePanel.add(nameField);

        updatePanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        updatePanel.add(ageField);

        updatePanel.add(new JLabel("Gender:"));
        genderBox = new JComboBox<>(new String[]{"Male", "Female"});
        updatePanel.add(genderBox);

        updatePanel.add(new JLabel("Department:"));
        deptField = new JTextField();
        updatePanel.add(deptField);

        updatePanel.add(new JLabel("GPA:"));
        gpaField = new JTextField();
        updatePanel.add(gpaField);

        updateButton = new JButton("Update");
        updatePanel.add(new JLabel("")); // empty space
        updatePanel.add(updateButton);

        add(updatePanel, BorderLayout.SOUTH);

        studentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedRow = studentTable.getSelectedRow();
                if (selectedRow >= 0) {
                    // Fill text fields with selected student's data
                    nameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    ageField.setText(tableModel.getValueAt(selectedRow, 2).toString());
                    genderBox.setSelectedItem(tableModel.getValueAt(selectedRow, 3).toString());
                    deptField.setText(tableModel.getValueAt(selectedRow, 4).toString());
                    gpaField.setText(tableModel.getValueAt(selectedRow, 5).toString());
                }
            }
        });

        updateButton.addActionListener(e -> updateStudent());

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

    private void updateStudent(){
        if (selectedRow < 0){
            JOptionPane.showMessageDialog(this, "Please select a student to update!");
            return;
        }

        String id = tableModel.getValueAt(selectedRow, 0).toString();

        String name = nameField.getText().trim();
        int age = Integer.parseInt(ageField.getText().trim());

        String gender = genderBox.getSelectedItem().toString();
        String dept = deptField.getText().trim();
        float gpa = Float.parseFloat(gpaField.getText().trim());

        manager.updateStudent(new Student(id, name, age, gender, dept, gpa));

        searchStudent(); //to refresh the table after updates
        JOptionPane.showMessageDialog(this, "Student updated successfully!");
    }
}
