import javax.swing.*;
import java.awt.*;

public class MainFrame {        /*in here we used composition where the mainframe class contains the JFrame that
                                    runs the program so if this class is deleted all the program collapses */

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JButton backButton;
    private StudentManager manager;

    public MainFrame() {
        manager = new StudentManager();

        SwingUtilities.invokeLater(() -> {
            initializeFrame();      //initialize the main window
            setupLayout();          //sets the layout
            setupHeaderPanel();     //sets the header
            setupPanels();          //creates all the panels
            frame.setVisible(true);
        });
    }

    private void initializeFrame() {
        frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);      //to open in the middle of the screen
        frame.setLayout(new BorderLayout());        //this divides the window into 5 parts north, south, east, west and center.

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                FileHandler.saveToFile(manager.getStudents());
                System.out.println("Data saved on exit.");
                frame.dispose();
                System.exit(0);
            }
        });
    }

    private void setupLayout() {
        cardLayout = new CardLayout();      //a method that makes switching between the panels possible
        mainPanel = new JPanel(cardLayout);     //we add the new card to the main panel
        frame.add(mainPanel, BorderLayout.CENTER);      //then make sure the mainPanel is in the center of the window
    }

    private void setupHeaderPanel() {
        headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));      //what is flow layout?
        backButton = new JButton("Back to Home");
        headerPanel.add(backButton);
        headerPanel.setVisible(false);      //we start it as false because we don't need it in the login panel or home panel

        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "Home");
            headerPanel.setVisible(false);
        });

        frame.add(headerPanel, BorderLayout.NORTH);     //we add the header at the top of the window
    }

    private void setupPanels() {        //here we creat all the panels
        ViewStudentsPanel viewPanel = new ViewStudentsPanel(manager);
        SearchUpdatePanel searchPanel = new SearchUpdatePanel(manager, cardLayout, mainPanel);
        HomePanel homePanel = new HomePanel(manager, cardLayout, mainPanel, viewPanel, headerPanel, searchPanel);
        LoginPanel loginPanel = new LoginPanel(manager, cardLayout, mainPanel);
        AddStudentPanel addPanel = new AddStudentPanel(manager, cardLayout, mainPanel);
        DeletePanel deletePanel = new DeletePanel(manager, cardLayout, mainPanel);

        //we add all of them to the main panel
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(homePanel, "Home");
        mainPanel.add(addPanel, "Add");
        mainPanel.add(viewPanel, "View");
        mainPanel.add(searchPanel, "Search or Update");
        mainPanel.add(deletePanel, "Delete");

        //which panel we want to show on opening
        cardLayout.show(mainPanel, "Login");
    }

    public static JButton createStyledButton(String text, Color bg, Color hoverBg, Color textColor) {
        JButton button = new JButton(text);
        button.setBackground(bg);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 161), 2, true));
        button.setPreferredSize(new Dimension(70, 30));

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

    public static void drawGradientBackground(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g.create();
        try {
            int width = c.getWidth();
            int height = c.getHeight();

            Color color1 = new Color(63, 235, 251, 173);   // soft blue
            Color color2 = new Color(228, 118, 135, 255);  // soft pink
            GradientPaint gp = new GradientPaint(0, 0, color1, width, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        } finally {
            g2d.dispose();
        }
    }

    public static void main(String[] args) {
        new MainFrame();        //this creates the window
    }
}

