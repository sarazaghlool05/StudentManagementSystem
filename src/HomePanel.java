import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    private StudentManager manager;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private ViewStudentsPanel viewPanel;
    private JPanel headerPanel;

    public HomePanel(StudentManager manager, CardLayout cardLayout, JPanel mainPanel,
                     ViewStudentsPanel viewPanel, JPanel headerPanel) {
        this.manager = manager;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        this.viewPanel = viewPanel;
        this.headerPanel = headerPanel;

        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250));

        JLabel welcomeLabel = new JLabel("Welcome to the Student Management System", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        welcomeLabel.setForeground(new Color(40, 60, 110));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 30, 30));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 100, 100));
        buttonPanel.setBackground(new Color(245, 247, 250));

        JButton addButton = createStyledButton("Add Student");
        JButton viewButton = createStyledButton("View Students");
        JButton updateButton = createStyledButton("Search / Update");
        JButton deleteButton = createStyledButton("Delete Student");

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.CENTER);
        addButton.addActionListener(e -> {
            headerPanel.setVisible(true);
            cardLayout.show(mainPanel, "Add");
        });

        viewButton.addActionListener(e -> {
            headerPanel.setVisible(true);
            cardLayout.show(mainPanel, "View");
            viewPanel.loadStudents(manager);
        });

        updateButton.addActionListener(e -> {
            headerPanel.setVisible(true);
            cardLayout.show(mainPanel, "Search or Update");
        });

        deleteButton.addActionListener(e -> {
            headerPanel.setVisible(true);
            cardLayout.show(mainPanel, "Delete");
        });
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 149, 237));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));
            }
        });

        return button;
    }
}
