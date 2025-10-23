import javax.swing.*;
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
            /*LoginPanel loginPanel = new LoginPanel(manager, cardLayout, mainPanel);*/
            ViewStudentsPanel viewPanel = new ViewStudentsPanel(mainPanel);
            HomePanel homePanel = new HomePanel(manager, cardLayout, mainPanel,viewPanel);
            AddStudentPanel addPanel= new AddStudentPanel(manager,cardLayout,mainPanel);
            SearchUpdatePanel searchPanel = new SearchUpdatePanel(manager, cardLayout, mainPanel);
            /*DeletePanel deletePanel = new DeletePanel(manager, cardLayout, mainPanel);*/
            /*mainPanel.add(loginPanel, "Login");*/
            mainPanel.add(homePanel, "Home");
            mainPanel.add(addPanel,"Add");
            mainPanel.add(viewPanel,"View");
            mainPanel.add(searchPanel, "Search or Update");
            /*mainPanel.add(deletePanel, "Delete");*/
            frame.add(mainPanel);
            frame.setVisible(true);
            cardLayout.show(mainPanel, "Login");
        });
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
