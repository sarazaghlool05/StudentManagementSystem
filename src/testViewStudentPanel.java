import javax.swing.*;

public class testViewStudentPanel {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("View Students");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 500);
            frame.add(new ViewStudentsPanel());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
