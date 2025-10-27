import javax.swing.*;
import java.awt.*;

public class MainFrame{

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JButton backButton;
    private StudentManager manager;

    //the constructor
    public MainFrame() {
        manager = new StudentManager();     //this connects the backend and the frontend

        //instead of implementing runnable
        SwingUtilities.invokeLater(() -> {      //we run the gui on a different thread(event dispatch thread)
            initializeFrame();
            setupLayout();
            setupHeaderPanel();
            setupPanels();
            frame.setVisible(true);
        });
    }

    private void initializeFrame() {
        frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);     //to stop java from closing the window without saving data
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);      //open the app in the center of the screen
        frame.setLayout(new BorderLayout());        /*we use borderlayout to set the layout of the frame
                                                        (north, south, east, west and center)*/
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
        //to extend window adapter so we override the method in it
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
        cardLayout = new CardLayout();      //a variable to call when we want to move between the panels
        mainPanel = new JPanel(cardLayout);     //creates the main panel as a deck of cards
        frame.add(mainPanel, BorderLayout.CENTER);      //the frame will have this panel which will contains everything
    }

    //a panel for the back button to be able to hide it in login and home panels
    private void setupHeaderPanel() {
        headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                MainFrame.drawGradientBackground(g, this);
            }
        };
        backButton = MainFrame.createStyledButton("Back", new Color(34, 122, 131, 255), new Color(63, 235, 251), Color.WHITE);
        headerPanel.add(backButton);
        headerPanel.setVisible(false);

        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "Home");
            headerPanel.setVisible(false);
        });

        frame.add(headerPanel, BorderLayout.NORTH);
    }

    private void setupPanels() {
        //creating each panel as a JPanel and adding them to the mainPanel

        ViewStudentsPanel viewPanel = new ViewStudentsPanel(manager);
        SearchUpdatePanel searchPanel = new SearchUpdatePanel(manager);
        HomePanel homePanel = new HomePanel(manager, cardLayout, mainPanel, viewPanel, headerPanel, searchPanel);
        LoginPanel loginPanel = new LoginPanel(cardLayout, mainPanel);
        AddStudentPanel addPanel = new AddStudentPanel(manager);
        DeletePanel deletePanel = new DeletePanel(manager);

        mainPanel.add(loginPanel, "Login");
        mainPanel.add(homePanel, "Home");
        mainPanel.add(addPanel, "Add");
        mainPanel.add(viewPanel, "View");
        mainPanel.add(searchPanel, "Search or Update");
        mainPanel.add(deletePanel, "Delete");

        cardLayout.show(mainPanel, "Login");
    }

    public static JButton createStyledButton(String text, Color bg, Color hoverBg, Color textColor) {
        JButton button = new JButton(text);
        button.setBackground(bg);
        button.setForeground(textColor);
        button.setFocusPainted(false);
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
        Graphics2D g2d = (Graphics2D) g.create();       //the painting brush to repaint the panels
       //g.create to creat a separate paint brush for the background.
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
        new MainFrame();        //the one that begins everything
    }
}
