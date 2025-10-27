import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class SearchUpdatePanel extends JPanel {

    private JTextField searchField;
    private JButton searchButton;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JTextField nameField, ageField, deptField, gpaField;
    private JComboBox<String> genderBox;
    private JButton updateButton;
    private int selectedRow = -1;
    private StudentManager manager;

    public SearchUpdatePanel(StudentManager manager) {
        this.manager = manager;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top search panel
        JPanel searchPanel = createSearchPanel();
        add(searchPanel, BorderLayout.NORTH);

        // Center table
        initializeTable();
        add(new JScrollPane(studentTable), BorderLayout.CENTER);

        // Bottom update panel
        JPanel updatePanel = createUpdatePanel();
        add(updatePanel, BorderLayout.SOUTH);

        // Listeners
        searchButton.addActionListener(e -> searchStudent());
        studentTable.getSelectionModel().addListSelectionListener(e -> handleRowSelection());
        updateButton.addActionListener(e -> updateStudent());
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Search by ID or Name: "));

        searchField = new JTextField(15);
        searchButton = MainFrame.createStyledButton("Search", new Color(34, 122, 131, 255), new Color(63, 235, 251), Color.WHITE);

        panel.add(searchField);
        panel.add(searchButton);

        return panel;
    }

    private void initializeTable() {
        String[] columns = {"ID", "Name", "Age", "Gender", "Department", "GPA"};
        tableModel = new DefaultTableModel(columns, 0);
        studentTable = new JTable(tableModel);
        studentTable.setDefaultEditor(Object.class, null);
        studentTable.setRowHeight(28);
    }

    private JPanel createUpdatePanel() {
        JPanel updatePanel = new JPanel(new GridLayout(6, 2, 5, 5));
        updatePanel.setBorder(BorderFactory.createTitledBorder("Update Student"));

        // Fields
        nameField = new JTextField();
        ageField = new JTextField();
        deptField = new JTextField();
        gpaField = new JTextField();
        genderBox = new JComboBox<>(new String[]{"Select", "Male", "Female"});
        updateButton = MainFrame.createStyledButton("Update", new Color(34, 122, 131, 255), new Color(63, 235, 251), Color.WHITE);

        // Layout
        updatePanel.add(new JLabel("Name:"));
        updatePanel.add(nameField);

        updatePanel.add(new JLabel("Age:"));
        updatePanel.add(ageField);

        updatePanel.add(new JLabel("Gender:"));
        updatePanel.add(genderBox);

        updatePanel.add(new JLabel("Department:"));
        updatePanel.add(deptField);

        updatePanel.add(new JLabel("GPA:"));
        updatePanel.add(gpaField);

        updatePanel.add(new JLabel("")); // spacer
        updatePanel.add(updateButton);

        return updatePanel;
    }

    private void searchStudent() {
        String searched = searchField.getText().trim();
        tableModel.setRowCount(0);

        ArrayList<Student> results = manager.searchStudent(searched);

        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No search matches!");
            return;
        }

        for (Student s : results) {
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

    private void handleRowSelection() {
        if (studentTable.getSelectionModel().isSelectionEmpty()) return;

        selectedRow = studentTable.getSelectedRow();
        if (selectedRow >= 0) {
            nameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
            ageField.setText(tableModel.getValueAt(selectedRow, 2).toString());
            genderBox.setSelectedItem(tableModel.getValueAt(selectedRow, 3).toString());
            deptField.setText(tableModel.getValueAt(selectedRow, 4).toString());
            gpaField.setText(tableModel.getValueAt(selectedRow, 5).toString());
        }
    }

    private void updateStudent() {
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a student to update!");
            return;
        }

        try {
            String id = tableModel.getValueAt(selectedRow, 0).toString();
            String name = nameField.getText().trim();
            String department = deptField.getText().trim();
            String gender = (String) genderBox.getSelectedItem();

            if (gender.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select a valid gender!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (name.isEmpty() || department.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and Department fields cannot be empty.", "Missing Data", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int age = Integer.parseInt(ageField.getText().trim());
            float gpa = Float.parseFloat(gpaField.getText().trim());

            Student updatedStudent = new Student(id, name, age, gender, department, gpa);
            boolean success = manager.updateStudent(updatedStudent);

            if (success) {
                JOptionPane.showMessageDialog(this, "Student updated successfully!");
                tableModel.setRowCount(0);
                clearAll();
            } else {
                JOptionPane.showMessageDialog(this, "Student not found or update failed!", "Update Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for Age and GPA.", "Invalid Number", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "<html>" + ex.getMessage().replace("\n", "<br>") + "</html>", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void clearAll() {
        tableModel.setRowCount(0);

        searchField.setText("");
        nameField.setText("");
        ageField.setText("");
        deptField.setText("");
        gpaField.setText("");
        genderBox.setSelectedIndex(0);
        selectedRow = -1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        MainFrame.drawGradientBackground(g, this);
    }
}
