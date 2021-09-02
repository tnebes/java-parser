package view;

import controller.ParseController;
import core.Config;
import model.Page;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ParserWindow {
    private JFrame frame;
    private JPanel panel;
    private GridBagConstraints constraints;
    private JTable table;
    private String filePath;
    private JButton uploadButton;
    private JLabel uploadLabel;
    private JButton parseButton;
    private JLabel parseLabel;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    public ParserWindow() {
        initComponents();
    }

    private void initComponents() {
        frame = new JFrame(Config.APP_NAME + " by " + Config.APP_AUTHOR + " " + Config.YEAR);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Config.WINDOW_X, Config.WINDOW_Y);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);

        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();


        tableModel = new DefaultTableModel(new Object[]{"URL", "Views", "Unique views"}, 0);
        table = new JTable(tableModel);

        scrollPane = new JScrollPane(table);

        uploadButton = new JButton("Select");
        uploadLabel = new JLabel("Select a file");

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

        parseButton = new JButton("Parse");
        parseLabel = new JLabel("Parse the file");

        parseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (filePath != null) {
                    ParseController parseController = ParseController.getInstance();
                    ArrayList<Page> pages = parseController.parse(filePath);
                    parseController.sort(pages);
                    updateTable(pages);
                } else {
                    JOptionPane.showMessageDialog(null, "No file selected!");
                }
            }
        });

        // TODO: create and add the panel
        this.design();
        this.show();
    }

    public void design() {
        table.setEnabled(false);
        constraints.gridwidth = 5;
        panel.add(scrollPane, constraints);

        constraints.gridwidth = 1;
        constraints.gridy = 1;
        panel.add(uploadLabel, constraints);

        constraints.gridy = 2;
        panel.add(uploadButton, constraints);

        constraints.gridy = 1;
        panel.add(parseLabel, constraints);

        constraints.gridy = 2;
        panel.add(parseButton, constraints);

        frame.add(panel);
    }

    public void show() {
        frame.setVisible(true);
    }

    public void updateTable(ArrayList<Page> pages) {
        if (pages == null) {
            return;
        }
        tableModel.setRowCount(0);
        for (Page page : pages) {
            tableModel.addRow(new Object[]{page.url, page.views, page.uniqueViews});
        }
    }

}