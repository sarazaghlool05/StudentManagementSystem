import javax.swing.*;

public class TestSearchGUI {
    public static void main(String[] args) {
        // Make GUI look better (optional)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create the backend manager (loads data)
        StudentManager manager = new StudentManager();

        // Create your custom panel (Search + Update)
        SearchUpdatePanel panel = new SearchUpdatePanel(manager);

        // Create the main window (frame)
        JFrame frame = new JFrame("Student Search & Update");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // center on screen

        // Add your panel to the frame
        frame.add(panel);

        // Show the window
        frame.setVisible(true);
    }
}
