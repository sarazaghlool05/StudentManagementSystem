import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;     

public class MainFrame {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private StudentManager manager;

    public MainFrame() {
        manager = new StudentManager();
        SwingUtilities.invokeLater(()
                -> {
            frame = new JFrame("Student Management System");
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    FileHandler.saveToFile(manager.getStudents());
                    System.out.println("Data saved on exit.");
                    frame.dispose();
                    System.exit(0);
                }
            });

            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null);
            cardLayout = new CardLayout();
            mainPanel = new JPanel(cardLayout);
         LoginPanel loginPanel = new LoginPanel(manager, cardLayout, mainPanel);
         ViewStudentsPanel viewPanel=new ViewStudentsPanel (mainPanel);
         HomePanel homePanel = new HomePanel(manager, cardLayout, mainPanel,viewPanel);
         AddStudentPanel addPanel= new AddStudentPanel(manager,cardLayout,mainPanel);
         SearchUpdatePanel searchPanel=new SearchUpdatePanel(manager,cardLayout,mainPanel);
            DeletePanel deletePanel = new DeletePanel(manager, cardLayout, mainPanel);
            JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JButton backButton = new JButton("Back to Home");
            headerPanel.add(backButton);
            backButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
            frame.setLayout(new BorderLayout());
            frame.add(headerPanel, BorderLayout.NORTH);
            frame.add(mainPanel, BorderLayout.CENTER);
            mainPanel.add(deletePanel, "Delete");
            frame.setVisible(true);
            cardLayout.show(mainPanel, "Login");
        mainPanel.add(loginPanel, "Login");
         mainPanel.add(homePanel, "Home");
         mainPanel.add(addPanel,"Add");
         mainPanel.add(viewPanel,"View");
         mainPanel.add(searchPanel,"Search or Update");


            mainPanel.add(deletePanel, "Delete");
            frame.add(mainPanel);
            frame.setVisible(true);
            cardLayout.show(mainPanel, "Login");
        });
    }

    public static JButton createStyledButton(String text, Color bg, Color hoverBg, Color textColor) {
        JButton button = new JButton(text);
        button.setBackground(bg);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // Rounded corners
        button.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 161), 2, true));
        button.setPreferredSize(new Dimension(70,30));
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverBg);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bg);
            }
        });

        return button;
    }


    public static void main(String[] args) {
        new MainFrame();
    }
}
