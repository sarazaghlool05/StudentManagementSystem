import javax.swing.*;
import java.awt.*;

public class AddStudentPanel extends JPanel {

    private JLabel nameLabel, ageLabel, genderLabel,departmentLabel, GPALabel;
    private JTextField nameField, ageField,GPAField;
    private JButton addButton, clearButton;
    private JComboBox<String> genderBox;
    private JComboBox<String> departmentBox;
    private StudentManager studentManager;

    public AddStudentPanel(StudentManager studentManager, CardLayout cardLayout, JPanel mainPanel) {
        this.studentManager = studentManager;

        // Panel setup
        setLayout(new GridBagLayout());
        setBackground(new Color(245, 247, 250)); // light gray background
        setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 15, 10, 15);
        c.anchor = GridBagConstraints.WEST;

        initializeLabels();
        initializeFields();
        initializeButtons();

        Dimension fieldSize = new Dimension(200, 35);
        setFieldSizes(fieldSize);

        // Add components to panel
        addComponentsToPanel(c);

        // Button functionality
        addButton.addActionListener(e -> addStudent());
        clearButton.addActionListener(e -> clearFields());
    }


    private void initializeLabels() {
        nameLabel = new JLabel("Full Name:");
        ageLabel = new JLabel("Age:");
        genderLabel = new JLabel("Gender:");
        departmentLabel = new JLabel("Department:");
        GPALabel = new JLabel("GPA:");

        Font labelFont = new Font("Segoe UI", Font.BOLD, 18);
        Color labelColor = Color.WHITE;

        JLabel[] labels = { nameLabel, ageLabel,genderLabel, departmentLabel, GPALabel };
        for (JLabel label : labels) {
            label.setFont(labelFont);
            label.setForeground(labelColor);
        }
    }

    private void initializeFields() {
        nameField = new JTextField(20);
        ageField = new JTextField(20);
        GPAField = new JTextField(20);
        String[] departments = {
                "Select",
                "Computer and Communication",
                "Biomedical",
                "Mechatronics and Robotics",
                "Electromechanical",
                "Gas and Petrochemical",
                "General program",
                "Architectural and Constructural",
                "Civil Environmental"
        };

        departmentBox = new JComboBox<>(departments);
        departmentBox.setPreferredSize(new Dimension(200, 35));


        genderBox = new JComboBox<>(new String[]{"Select", "Male", "Female"});
        genderBox.setPreferredSize(new Dimension(200, 35));
    }

    private void initializeButtons() {
        Color base = new Color(63, 235, 251, 61);
        Color hover = new Color(63, 235, 251);
        Color text = Color.WHITE;

        clearButton = MainFrame.createStyledButton("Clear", base, hover, text);
        addButton = MainFrame.createStyledButton("Add", base, hover, text);
    }

    private void setFieldSizes(Dimension size) {
        JTextField[] fields = { nameField, ageField, GPAField };
        for (JTextField field : fields) {
            field.setPreferredSize(size);
        }
    }

    private void addComponentsToPanel(GridBagConstraints c) {
        // Row 0: Name
        c.gridx = 0; c.gridy = 0;
        add(nameLabel, c);
        c.gridx = 1;
        add(nameField, c);

        // Row 1: Age
        c.gridx = 0; c.gridy = 1;
        add(ageLabel, c);
        c.gridx = 1;
        add(ageField, c);

        // Row 2: Gender
        c.gridx = 0; c.gridy = 2;
        add(genderLabel, c);
        c.gridx = 1;
        add(genderBox, c);

        // Row 3: Department
        c.gridx = 0; c.gridy = 3;
        add(departmentLabel, c);
        c.gridx = 1;
        add(departmentBox, c);


        // Row 4: GPA
        c.gridx = 0; c.gridy = 4;
        add(GPALabel, c);
        c.gridx = 1;
        add(GPAField, c);

        // Row 5: Buttons
        c.gridx = 0; c.gridy = 5;
        add(clearButton, c);
        c.gridx = 1;
        add(addButton, c);
    }

    private void addStudent() {
        try {
            String newId = studentManager.generateNextID();
            String name = nameField.getText();
            String department = (String) departmentBox.getSelectedItem();
            String gender = (String) genderBox.getSelectedItem();
            int age = Integer.parseInt(ageField.getText());
            float gpa = Float.parseFloat(GPAField.getText());

            Student s = new Student(newId, name, age, gender, department, gpa);
            boolean success = studentManager.addStudent(s);

            if (success) {
                JOptionPane.showMessageDialog(this, "Student added successfully!\nGenerated ID: " + newId);
                clearFields();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numeric values for Age and GPA.",
                    "Invalid Number", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this,
                    "<html>" + ex.getMessage().replace("\n", "<br>") + "</html>",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        departmentBox.setSelectedIndex(0);
        GPAField.setText("");
        genderBox.setSelectedIndex(0); // reset combo box to "Select"
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        MainFrame.drawGradientBackground(g, this);
    }
}
