import javax.swing.*;
import java.awt.*;

public class AddStudentPanel extends JPanel {
    private JLabel nameLabel, ageLabel, genderLabel, idLabel, departmentLabel, GPALabel;
    private JTextField nameField, ageField, idField, departmentField, GPAField;
    private JButton addButton, clearButton;
    private JComboBox<String> genderBox;
    private StudentManager studentManager;

    public AddStudentPanel(StudentManager studentManager, CardLayout cardLayout, JPanel mainPanel) {
        this.studentManager = studentManager;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 15, 10, 15);
        c.anchor = GridBagConstraints.WEST;
        setBackground(new Color(245, 247, 250)); // light gray background
        setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // Initialize components
        nameLabel = new JLabel("Full Name:");
        ageLabel = new JLabel("Age:");
        idLabel = new JLabel("ID:");
        genderLabel = new JLabel("Gender:");
        departmentLabel = new JLabel("Department:");
        GPALabel = new JLabel("GPA:");

        Font labelFont = new Font("Segoe UI", Font.BOLD, 18);
        nameLabel.setFont(labelFont);
        ageLabel.setFont(labelFont);
        idLabel.setFont(labelFont);
        genderLabel.setFont(labelFont);
        departmentLabel.setFont(labelFont);
        GPALabel.setFont(labelFont);

        Color labelColor = Color.WHITE;
        nameLabel.setForeground(labelColor);
        ageLabel.setForeground(labelColor);
        idLabel.setForeground(labelColor);
        genderLabel.setForeground(labelColor);
        departmentLabel.setForeground(labelColor);
        GPALabel.setForeground(labelColor);

        nameField = new JTextField(20);
        ageField = new JTextField(20);
        idField = new JTextField(20);
        departmentField = new JTextField(20);
        GPAField = new JTextField(20);
        genderBox = new JComboBox<>(new String[]{"Select", "Male", "Female"});
        genderBox.setPreferredSize(new Dimension(200, 35));
        clearButton = MainFrame.createStyledButton("Clear",new Color(63, 235, 251, 61),new Color(63, 235, 251),Color.WHITE);
        addButton = MainFrame.createStyledButton("Add",new Color(63, 235, 251, 61),new Color(63, 235, 251),Color.WHITE);

        // Now add them in order
        c.gridx = 0;
        c.gridy = 0;
        add(nameLabel, c);
        c.gridx = 1;
        add(nameField, c);

        c.gridx = 0;
        c.gridy = 1;
        add(ageLabel, c);
        c.gridx = 1;
        add(ageField, c);

        Dimension fieldSize = new Dimension(200, 35);
        nameField.setPreferredSize(fieldSize);
        ageField.setPreferredSize(fieldSize);
        idField.setPreferredSize(fieldSize);
        departmentField.setPreferredSize(fieldSize);
        GPAField.setPreferredSize(fieldSize);

        c.gridx = 0;
        c.gridy = 2;
        add(idLabel, c);
        c.gridx = 1;
        add(idField, c);

        c.gridx = 0;
        c.gridy = 3;
        add(genderLabel, c);
        c.gridx = 1;
        add(genderBox, c);

        c.gridx = 0;
        c.gridy = 4;
        add(departmentLabel, c);
        c.gridx = 1;
        add(departmentField, c);

        c.gridx = 0;
        c.gridy = 5;
        add(GPALabel, c);
        c.gridx = 1;
        add(GPAField, c);

        // Buttons at the bottom
        c.gridx = 0;
        c.gridy = 6;
        add(clearButton, c);
        c.gridx = 1;
        add(addButton, c);

        // Button functionality
        addButton.addActionListener(e -> addStudent());
        clearButton.addActionListener(e -> clearFields());
    }

    private void addStudent() {
        try {
            String name = nameField.getText();
            String id = idField.getText();
            String department = departmentField.getText();
            String gender = (String) genderBox.getSelectedItem();
            int age = Integer.parseInt(ageField.getText());
            float gpa = Float.parseFloat(GPAField.getText());

            Student s = new Student(id, name, age, gender, department, gpa);

            boolean success = studentManager.addStudent(s);
            if (success) {
                JOptionPane.showMessageDialog(this, "Student added successfully!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Student with this ID already exists!", "Duplicate ID", JOptionPane.WARNING_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for Age and GPA.", "Invalid Number", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "<html>" + ex.getMessage().replace("\n", "<br>") + "</html>", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }


    //for the clear button
    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        idField.setText("");
        departmentField.setText("");
        GPAField.setText("");
        genderBox.setSelectedIndex(0); // reset combo box to "Select"
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        MainFrame.drawGradientBackground(g, this);
    }
}
