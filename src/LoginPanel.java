import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private static final String USER_FILE = "users.txt";

    private JLabel messageLabel;

    public LoginPanel(CardLayout cardLayout, JPanel mainPanel) {

        setLayout(new GridBagLayout());
        setBackground(new Color(245, 247, 250));
        setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.WEST;


        JLabel usernameLabel = createLabel("Username:");
        JLabel passwordLabel = createLabel("Password:");
        JTextField userField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = createLoginButton();


        userField.setPreferredSize(new Dimension(135, 20));
        passwordField.setPreferredSize(new Dimension(135, 20));
        loginButton.setPreferredSize(new Dimension(135, 30));


        addComponentsToPanel(c, usernameLabel, passwordLabel, userField, passwordField, loginButton);


        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        messageLabel = new JLabel("");
        messageLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(messageLabel, c);


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
                new Color(34, 122, 131, 255),
                new Color(63, 235, 251),
                Color.WHITE
        );
    }

    private void addComponentsToPanel(GridBagConstraints c, JLabel usernameLabel, JLabel passwordLabel,
                                      JTextField userField, JPasswordField passwordField, JButton loginButton) {

        c.gridx = 0; c.gridy = 0;
        add(usernameLabel, c);
        c.gridx = 1;
        add(userField, c);

        c.gridx = 0; c.gridy = 1;
        add(passwordLabel, c);
        c.gridx = 1;
        add(passwordField, c);

        c.gridx = 0; c.gridy = 2;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        add(loginButton, c);
    }

    private void handleLogin(JTextField userField, JPasswordField passwordField,
                             CardLayout cardLayout, JPanel mainPanel) {

        String username = userField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (UserPass.validateLogin(username, password, USER_FILE)) {
            updateMessageLabel("Login successful!", Color.GREEN);
            switchToHomePanel(cardLayout, mainPanel);
        } else {
            updateMessageLabel("Invalid username or password", Color.RED);
        }
    }


    private void updateMessageLabel(String message, Color color) {
        messageLabel.setText(message);
        messageLabel.setForeground(color);
    }


    private void switchToHomePanel(CardLayout cardLayout, JPanel mainPanel) {
        cardLayout.show(mainPanel, "Home");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        MainFrame.drawGradientBackground(g, this);
    }
}
