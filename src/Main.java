import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Create backend (database)
        StudentManager studentManager = new StudentManager();

        // Create window
        JFrame frame = new JFrame("Add Student - Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Add panel to window
        frame.add(new AddStudentPanel(studentManager));

        // Center and show
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}