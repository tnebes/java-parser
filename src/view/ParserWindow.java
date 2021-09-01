package view;

import controller.ParseController;
import core.Config;
import model.Page;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class ParserWindow {
    private JFrame frame;
    private JPanel panel;
    private JTable table;
    private String filePath;
    private JButton uploadButton;
    private JLabel uploadLabel;
    private JButton parseButton;
    private JLabel parseLabel;
    private GridBagConstraints constraints;
    private TableModel tableModel;

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

        //set up panel grid
        panel.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();

        String[] columnNames = new String[]{"URL", "Views", "Unique views"};
        tableModel = new DefaultTableModel(new String[][]{{""}}, columnNames);
        table = new JTable(tableModel){
            @Override
            public Dimension getPreferredScrollableViewportSize()
            {
                return new Dimension(100,300);
            }
        };
        table.setEnabled(false);

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
                    System.out.println("Parsing file: " + filePath);
                    ParseController parseController = ParseController.getInstance();
                    ArrayList<Page> pages = parseController.parse(filePath);
                    parseController.sort(pages);
                    updateTable(pages);
                } else {
                    System.out.println("No file selected!");
                }
            }
        });

        // TODO: create and add the panel
        frame.add(panel);
        this.design();
        this.show();
    }

    public void design()
    {
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(new JScrollPane(table));


        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 10;
        constraints.gridheight = 2;
        panel.add(table, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        panel.add(uploadLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(uploadButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(parseLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(parseButton, constraints);


        for (JButton button : new JButton[]{uploadButton, parseButton})
        {
            button.setSize(100, 50);
        }
    }

    public void show() {
        frame.setVisible(true);
        panel.setVisible(true);
    }

    public void updateTable(ArrayList<Page> pages)
    {
//        table.setModel(new DefaultTableModel());
    }

}