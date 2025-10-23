import javax.swing.*;
import java.awt.*;
public class LoginPanel extends JPanel {
    private JTextField textField1;
    private JTextField textField2;

    public LoginPanel(StudentManager manager, CardLayout cardLayout, JPanel mainPanel) {
       setLayout(new GridBagLayout());
       GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        JLabel usernameLabel = new JLabel("Username:");
        c.gridx = 0;
        c.gridy = 0;
        add(usernameLabel, c);
        add(usernameLabel,c);
        JTextField userField = new JTextField(15);
        c.gridx = 1;
        add(userField, c);
        JLabel passwordLabel = new JLabel("Password:");
        c.gridx = 0;
        c.gridy = 1;
        add(passwordLabel, c);
        JPasswordField passwordField = new JPasswordField(15);
        c.gridx = 1;
        add(passwordField, c);
        JButton loginButton = new JButton("Login");
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        add(loginButton, c);
        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = passwordField.getText();
            if(username.equals("admin") && password.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Login successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Wrong username or password!");
            }
        });

    }
}
/*
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        // 1️⃣ Create a frame
        JFrame frame = new JFrame("Login Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200); // width x height
        frame.setLocationRelativeTo(null); // center the window

        // 2️⃣ Create the LoginPanel
        LoginPanel loginPanel = new LoginPanel();

        // 3️⃣ Add panel to frame
        frame.add(loginPanel);

        // 4️⃣ Make it visible
        frame.setVisible(true);
    });
}*/
