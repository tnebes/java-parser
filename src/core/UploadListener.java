package core;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UploadListener implements ActionListener {

   @Override
   public void actionPerformed(ActionEvent e)
   {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setCurrentDirectory(new java.io.File("."));
      fileChooser.setDialogTitle("Select a file");
      fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
      fileChooser.setAcceptAllFileFilterUsed(false);
      if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
      {
         String fileName = fileChooser.getSelectedFile().getAbsolutePath();
      }
   }
}
