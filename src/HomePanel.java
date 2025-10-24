import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    private StudentManager manager;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private ViewStudentsPanel viewPanel;
    private JPanel headerPanel;


    public HomePanel(StudentManager manager, CardLayout cardLayout, JPanel mainPanel,
                     ViewStudentsPanel viewPanel, JPanel headerPanel,SearchUpdatePanel searchPanel) {
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

        JButton addButton = MainFrame.createStyledButton("Add student",new Color(38, 127, 135),new Color(0, 164, 174),Color.WHITE);
        JButton viewButton = MainFrame.createStyledButton("View all students",new Color(38, 127, 135),new Color(0, 164, 174),Color.WHITE);
        JButton updateButton = MainFrame.createStyledButton("Update student/Search for a student",new Color(38, 127, 135),new Color(0, 164, 174),Color.WHITE);
        JButton deleteButton = MainFrame.createStyledButton("Delete student",new Color(38, 127, 135),new Color(0, 164, 174),Color.WHITE);

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
            searchPanel.clearAll();
        });

        deleteButton.addActionListener(e -> {
            headerPanel.setVisible(true);
            cardLayout.show(mainPanel, "Delete");
        });
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        MainFrame.drawGradientBackground(g, this);
    }
}
