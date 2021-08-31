package view;
import javax.swing.*;
import java.awt.*;
import core.*;

public class ParserWindow
{
   public ParserWindow()
   {
      initComponents();
   }

   private void initComponents()
   {
      JFrame frame = new JFrame(Config.APP_NAME + " by " + Config.APP_AUTHOR + " " + Config.YEAR);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(Config.WINDOW_X, Config.WINDOW_Y);
      frame.setLocationRelativeTo(null);
      frame.setResizable(false);
      frame.setVisible(true);

      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(6, 2));
      frame.add(panel);

      JTable table = new JTable(1, 2);
      table.setValueAt("Name", 0, 0);
      table.setValueAt("Visits", 0, 1);
      // table.setValueAt("John", 1, 0);
      // table.setValueAt("10", 1, 1);
      table.setVisible(true);
      table.setEnabled(false);

      JScrollPane scrollPane = new JScrollPane(table);

      // add a button for uploading a file
      JButton uploadButton = new JButton("Upload");
      // add a button for parsing the file
      JButton parseButton = new JButton("Parse");
      // add everything to the grid
      panel.add(scrollPane);
      panel.add(uploadButton);
      panel.add(parseButton);
   }
}