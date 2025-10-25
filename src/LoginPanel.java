import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "12345";

    private JTextField textField1;
    private JTextField textField2;

    public LoginPanel(StudentManager manager, CardLayout cardLayout, JPanel mainPanel) {
        // Panel setup
        setLayout(new GridBagLayout());
        setBackground(new Color(245, 247, 250));
        setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.WEST;

        // Initialize components
        JLabel usernameLabel = createLabel("Username:");
        JLabel passwordLabel = createLabel("Password:");
        JTextField userField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = createLoginButton();

        // Set component sizes
        userField.setPreferredSize(new Dimension(135, 20));
        passwordField.setPreferredSize(new Dimension(135, 20));
        loginButton.setPreferredSize(new Dimension(135, 30));

        // Add components to panel
        addComponentsToPanel(c, usernameLabel, passwordLabel, userField, passwordField, loginButton);

        // Login button logic
        loginButton.addActionListener(e -> handleLogin(userField, passwordField, cardLayout, mainPanel));
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        return label;
    }

    private JButton createLoginButton() {
        return MainFrame.createStyledButton(
                "Login",
                new Color(63, 235, 251, 61),
                new Color(63, 235, 251),
                Color.WHITE
        );
    }

    private void addComponentsToPanel(GridBagConstraints c, JLabel usernameLabel, JLabel passwordLabel,
                                      JTextField userField, JPasswordField passwordField, JButton loginButton) {

        // Row 0: Username
        c.gridx = 0; c.gridy = 0;
        add(usernameLabel, c);
        c.gridx = 1;
        add(userField, c);

        // Row 1: Password
        c.gridx = 0; c.gridy = 1;
        add(passwordLabel, c);
        c.gridx = 1;
        add(passwordField, c);

        // Row 2: Login Button
        c.gridx = 0; c.gridy = 2;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        add(loginButton, c);
    }

    private void handleLogin(JTextField userField, JPasswordField passwordField, CardLayout cardLayout, JPanel mainPanel) {
        String username = userField.getText();
        String password = new String(passwordField.getPassword());

        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            cardLayout.show(mainPanel, "Home");
        } else {
            JOptionPane.showMessageDialog(this, "Wrong username or password!");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        MainFrame.drawGradientBackground(g, this);
    }
}
