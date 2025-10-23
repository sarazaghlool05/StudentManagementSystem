import javax.swing.*;
import java.awt.*;

public class AddStudentPanel extends JPanel {
    private JLabel nameLabel, ageLabel, genderLabel, idLabel, departmentLabel, GPALabel;
    private JTextField nameField, ageField, idField, departmentField, GPAField;
    private JButton addButton, clearButton;
    private JComboBox<String> genderBox;

    public AddStudentPanel(){
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
    }


        public static void main(String[] args) {
            JFrame frame = new JFrame("Add Student");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.add(new AddStudentPanel());

            frame.setSize(600, 400);         // make it bigger
            frame.setLocationRelativeTo(null); // center it on screen
            frame.setVisible(true);
        }
}
