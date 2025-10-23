import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewStudentsPanel extends JPanel {
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JButton loadButton;

   public ViewStudentsPanel(){
       setLayout(new BorderLayout());

    }
}
