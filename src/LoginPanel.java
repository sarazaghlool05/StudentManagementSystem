import javax.swing.*;
import java.awt.*;
public class LoginPanel extends JPanel {
    public LoginPanel(StudentManager manager, CardLayout cardLayout, JPanel mainPanel) {
       setLayout(new GridBagLayout());
       GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        c.gridx = 0;
        c.gridy = 0;
    }
}
