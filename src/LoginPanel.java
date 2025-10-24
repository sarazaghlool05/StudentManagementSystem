import javax.swing.*;
import java.awt.*;
import java.net.CookieHandler;

public class LoginPanel extends JPanel {
    private JTextField textField1;
    private JTextField textField2;

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "12345";


    public LoginPanel(StudentManager manager, CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        c.gridx = 0;
        c.gridy = 0;
        add(usernameLabel, c);
        JTextField userField = new JTextField(15);
        userField.setPreferredSize(new Dimension(135, 20));
        c.gridx = 1;
        add(userField, c);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        c.gridx = 0;
        c.gridy = 1;
        add(passwordLabel, c);
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setPreferredSize(new Dimension(135, 20));
        c.gridx = 1;
        add(passwordField, c);
        JButton loginButton = MainFrame.createStyledButton("Login",new Color(63, 235, 251, 61),new Color(63, 235, 251),Color.WHITE);
        loginButton.setPreferredSize(new Dimension(135,30));
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        add(loginButton, c);
        loginButton.addActionListener(e -> {
            String username = userField.getText();

            String password = new String(passwordField.getPassword());

            if (username.equals(USERNAME) && password.equals(PASSWORD)) {
                cardLayout.show(mainPanel, "Home");
            } else {
                JOptionPane.showMessageDialog(this, "Wrong username or password!");
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        MainFrame.drawGradientBackground(g, this);
    }

}