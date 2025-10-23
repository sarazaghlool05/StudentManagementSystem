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
       // JLabel passwordLabel = new JLabel("Password:");
        c.gridx = 0;
        c.gridy = 0;
        add(userLabel, c);
        add(userLabel, gbc);
        JTextField userField = new JTextField(15);
        c.gridx = 1;
        add(userField, c);
    }
}
