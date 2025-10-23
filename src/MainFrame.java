import javax.swing.*;  // for JFrame, JPanel, JButton, etc.
import java.awt.*;      // for layouts like CardLayout

public class MainFrame {
  private  JFrame frame;
  private CardLayout cardLayout ;
  private JPanel maimPanel;
  private StudentManager manager;

  public MainFrame() {
      manager=new StudentManager();
      
  }
}
