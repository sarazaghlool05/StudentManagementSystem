import javax.swing.*;
import java.awt.*;

public class AddStudentPanel extends JPanel {
    private JLabel nameLabel, ageLabel, genderLabel, idLabel, departmentLabel, GPALabel;
    private JTextField nameField, ageField, idField, departmentField, GPAField;
    private JButton addButton, clearButton;
    private JComboBox<String> genderBox;
    //backend variables
    private StudentManager studentManager;

    public AddStudentPanel(StudentManager studentManager){
        this.studentManager = studentManager;       //connecting to backend (like a constructor)

        // Use GridLayout: 7 rows (6 fields + buttons), 2 columns
        setLayout(new GridLayout(7, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // padding around edges

        nameLabel = new JLabel("Full name: ");
        nameField = new JTextField(15); //15 is how wide the box is

        ageLabel = new JLabel("Age: ");
        ageField = new JTextField(5);

        idLabel = new JLabel("ID: ");
        idField = new JTextField(5);

        departmentLabel = new JLabel("Department: ");
        departmentField = new JTextField(15);

        GPALabel = new JLabel("GPA: ");
        GPAField = new JTextField(5);

        genderLabel = new JLabel("Gender: ");
        genderBox = new JComboBox<>(new String[]{"Select", "Male", "Female"});

        addButton = new JButton("Add");
        clearButton = new JButton("Clear");

        // Add everything to the panel
        add(nameLabel); add(nameField);
        add(ageLabel); add(ageField);
        add(idLabel); add(idField);
        add(genderLabel); add(genderBox);
        add(departmentLabel); add(departmentField);
        add(GPALabel); add(GPAField);
        add(addButton); add(clearButton);

        //buttons functionality
        addButton.addActionListener(e -> addStudent());     //e is an object of action listener class.
        clearButton.addActionListener(e -> clearFields());
    }

    private void addStudent(){
        //reading input from user.
        String name = nameField.getText();
        String id = idField.getText();
        String department = departmentField.getText();
        String gender = (String) genderBox.getSelectedItem();
        int age = Integer.parseInt(ageField.getText());
        float gpa = Float.parseFloat(GPAField.getText());

        //making a new student object
        Student s = new Student(id, name,age, gender, department, gpa);
        //now we need to tell the backend to add to the array and file
        boolean success = studentManager.addStudent(s);

        if(success){
            JOptionPane.showMessageDialog(this, "Student added successfully!");     //this to display the message in this panel
        }
        else{
            JOptionPane.showMessageDialog(this, "Student with this ID already exists!");
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
}
