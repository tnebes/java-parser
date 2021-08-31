package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import core.*;

public class ParserWindow
{
   private JFrame frame;
   private JPanel panel;
   private JTable table;
   private String filePath;
   private HashSet<String> fileContents;
   
   public ParserWindow()
   {
      initComponents();
   }

   private void initComponents()
   {
      frame = new JFrame(Config.APP_NAME + " by " + Config.APP_AUTHOR + " " + Config.YEAR);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(Config.WINDOW_X, Config.WINDOW_Y);
      frame.setLocationRelativeTo(null);
      frame.setResizable(false);

      panel = new JPanel();
      panel.setLayout(new GridLayout(6, 2));
      frame.add(panel);

      table = new JTable(1, 2);
      table.setValueAt("Name", 0, 0);
      table.setValueAt("Visits", 0, 1);
      table.setEnabled(false);

      JScrollPane scrollPane = new JScrollPane(table);

      JButton uploadButton = new JButton("Upload");
      JLabel uploadLabel = new JLabel("Upload a file");
      uploadButton.addActionListener(new UploadListener());

      JButton parseButton = new JButton("Parse");
      JLabel parseLabel = new JLabel("Parse the file");

      // add everything to the grid
      panel.add(table);
      panel.add(uploadButton);
      panel.add(uploadLabel);
      panel.add(parseButton);
      panel.add(parseLabel);
            
   }

   public void show()
   {
      frame.setVisible(true);
   }
}