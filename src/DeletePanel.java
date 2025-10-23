import javax.swing.*;
import java.awt.*;

public class DeletePanel extends JPanel {
    private StudentManager manager;

    public DeletePanel(StudentManager manager, CardLayout cardLayout, JPanel mainPanel) {
        this.manager = manager;
        setLayout(new BorderLayout());
        GridBagConstraints c=new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);
        c.gridx = 0;
        c.gridy = 0;
        JLabel idLabel = new JLabel("Enter Student ID to delete:");
        c.gridx = 0;
        c.gridy = 0;
        add(idLabel, c);
        JTextField idField = new JTextField(15);
        c.gridx = 1;
        add(idField, c);
        JButton deleteButton = new JButton("Delete");
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        add(deleteButton, c);
        

}
