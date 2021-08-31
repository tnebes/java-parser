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
      // select only .log files and directories
      fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
         @Override
         public boolean accept(java.io.File file) {
            String filename = file.getName();
            return filename.endsWith(".log") || file.isDirectory();
         }

         @Override
         public String getDescription() {
               return null;
         }
      });
      fileChooser.setAcceptAllFileFilterUsed(false);
      if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
      {
         fileChooser.getSelectedFile().getAbsolutePath();
      }
   }
}
