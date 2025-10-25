import javax.swing.*;
import java.awt.*;

public class DeletePanel extends JPanel {

    private StudentManager manager;

    public DeletePanel(StudentManager manager, CardLayout cardLayout, JPanel mainPanel) {
        this.manager = manager;

        // Panel setup
        setLayout(new GridBagLayout());
        setBackground(new Color(245, 247, 250)); // optional consistency with other panels
        setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.WEST;

        // Initialize components
        JLabel idLabel = createLabel("Enter Student ID to delete:");
        JTextField idField = new JTextField(15);
        JButton deleteButton = MainFrame.createStyledButton(
                "Delete",
                new Color(63, 235, 251, 61),
                new Color(63, 235, 251),
                Color.WHITE
        );

        // Add components to panel
        addComponentsToPanel(c, idLabel, idField, deleteButton);

        // Button functionality
        deleteButton.addActionListener(e -> handleDeleteAction(idField));
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label.setForeground(Color.WHITE);
        return label;
    }

    private void addComponentsToPanel(GridBagConstraints c, JLabel idLabel, JTextField idField, JButton deleteButton) {
        // Row 0: Label + TextField
        c.gridx = 0;
        c.gridy = 0;
        add(idLabel, c);

        c.gridx = 1;
        add(idField, c);

        // Row 1: Delete button
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        add(deleteButton, c);
    }

    private void handleDeleteAction(JTextField idField) {
        String id = idField.getText().trim();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Student ID");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this student?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            boolean deleted = manager.deleteStudent(id);

            if (deleted) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully!");
                idField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Student ID not found!");
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        MainFrame.drawGradientBackground(g, this);
    }
}
