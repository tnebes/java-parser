package view;

import controller.ParseController;
import core.Config;
import model.Page;

import javax.swing.*;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParserWindow {
    private JFrame frame;
    private JPanel panel;
    private JTable table;
    private String filePath;
    private Page[] fileContents;
    private String[] tableHeaders;
    private Object[][] tableData;

    public ParserWindow() {
        initComponents();
    }

    private void initComponents() {
        frame = new JFrame(Config.APP_NAME + " by " + Config.APP_AUTHOR + " " + Config.YEAR);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Config.WINDOW_X, Config.WINDOW_Y);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));
        frame.add(panel);

        table = new JTable(null, tableHeaders);


        table.setEnabled(false);

        frame.add(new JScrollPane(table));

        JButton uploadButton = new JButton("Select");
        JLabel uploadLabel = new JLabel("Select a file");

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    filePath = fileChooser.getSelectedFile().getAbsolutePath();
                }
            }
        });

        JButton parseButton = new JButton("Parse");
        JLabel parseLabel = new JLabel("Parse the file");

        parseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (filePath != null) {
                    System.out.println("Parsing file: " + filePath);
                    ParseController parseController = ParseController.getInstance();
                    fileContents = parseController.parse(filePath);
                } else {
                    System.out.println("No file selected");
                }
            }
        });

        // add everything to the grid
        panel.add(table);
        panel.add(uploadButton);
        panel.add(uploadLabel);
        panel.add(parseButton);
        panel.add(parseLabel);

    }

    public void show() {
        frame.setVisible(true);
    }

    public void updateTable()
    {
        
    }

    public interface PathGetter {
        void run(String path);
    }
}