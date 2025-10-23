import javax.swing.*;  // for JFrame, JPanel, JButton, etc.
import java.awt.*;      // for layouts like CardLayout

public class MainFrame {
  private  JFrame frame;
  private CardLayout cardLayout ;
  private JPanel maimPanel;
  private StudentManager manager;

  public MainFrame() {
      manager=new StudentManager();
     SwingUtilities.invokeLater(()
     ->{
        frame = new JFrame("Student Management System");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setSize(900, 600);
         frame.setLocationRelativeTo(null);
         cardLayout = new CardLayout();
         mainPanel = new JPanel(cardLayout);
         LoginPanel loginPanel = new LoginPanel(manager, cardLayout, mainPanel);
         HomePanel homePanel = new HomePanel(manager, cardLayout, mainPanel);
         AddPanel addPanel= new AddPanel(manager,cardLayout,mainPanel);
         ViewPanel viewPanel=new ViewPanel (manager,cardLayout,mainPanel);
         Searchpanel searchPanel=new SearchPanel(manager,cardLayout,mainPanel);
         DeletePanel deletePanel = new DeletePanel(manager, cardLayout, mainPanel);
         mainPanel.add(loginPanel, "Login");
         mainPanel.add(homePanel, "Home");
         mainPanel.add(addPanel,"Add");
         mainPanel.add(viewPanel,"View");
         mainPanel.add(searchPanel,"Search or Update");
         mainPanel.add(deletePanel, "Delete");
         frame.add(mainPanel);
         frame.setVisible(true);
         cardLayout.show(mainPanel,"Login");
     })
  }
}
