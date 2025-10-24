import javax.swing.*;
import java.awt.*;

public class DeletePanel extends JPanel {
    private StudentManager manager;

    public DeletePanel(StudentManager manager, CardLayout cardLayout, JPanel mainPanel) {
        this.manager = manager;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        JLabel idLabel = new JLabel("Enter Student ID to delete:");
        Color labelColor = Color.WHITE;
        idLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        idLabel.setForeground(labelColor);
        c.gridx = 0;
        c.gridy = 0;
        add(idLabel, c);
        JTextField idField = new JTextField(15);
        c.gridx = 1;
        add(idField, c);
        JButton deleteButton = MainFrame.createStyledButton("Delete",new Color(63, 235, 251, 61),new Color(63, 235, 251),Color.WHITE);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        add(deleteButton, c);
        deleteButton.addActionListener(e -> {
            String id = idField.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Student ID");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this student?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_OPTION) {
                boolean deleted = manager.deleteStudent(id);
                if(deleted) {
                    JOptionPane.showMessageDialog(this, "Student deleted successfully!");
                    idField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Student ID not found!");
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        MainFrame.drawGradientBackground(g, this);
    }
}
