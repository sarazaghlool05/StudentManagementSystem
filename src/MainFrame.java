import javax.swing.*;

import java.awt.*;


public class MainFrame {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private StudentManager manager;
    private JPanel headerPanel;
    private JButton backButton;

    public MainFrame() {
        manager = new StudentManager();

        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Student Management System");
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());

            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    FileHandler.saveToFile(manager.getStudents());
                    System.out.println("Data saved on exit.");
                    frame.dispose();
                    System.exit(0);
                }
            });


            cardLayout = new CardLayout();
            mainPanel = new JPanel(cardLayout);


            headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            backButton = new JButton("Back to Home");
            headerPanel.add(backButton);
            headerPanel.setVisible(false);

            backButton.addActionListener(e -> {
                cardLayout.show(mainPanel, "Home");
                headerPanel.setVisible(false);
            });

            ViewStudentsPanel viewPanel = new ViewStudentsPanel(manager);
            HomePanel homePanel = new HomePanel(manager, cardLayout, mainPanel, viewPanel, headerPanel);
            LoginPanel loginPanel = new LoginPanel(manager, cardLayout, mainPanel);
            AddStudentPanel addPanel = new AddStudentPanel(manager, cardLayout, mainPanel);
            SearchUpdatePanel searchPanel = new SearchUpdatePanel(manager, cardLayout, mainPanel);
            DeletePanel deletePanel = new DeletePanel(manager, cardLayout, mainPanel);
            mainPanel.add(loginPanel, "Login");
            mainPanel.add(homePanel, "Home");
            mainPanel.add(addPanel, "Add");
            mainPanel.add(viewPanel, "View");
            mainPanel.add(searchPanel, "Search or Update");
            mainPanel.add(deletePanel, "Delete");
            frame.add(headerPanel, BorderLayout.NORTH);
            frame.add(mainPanel, BorderLayout.CENTER);
            cardLayout.show(mainPanel, "Login");
            frame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
