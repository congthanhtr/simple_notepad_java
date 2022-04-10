package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends JFrame implements ActionListener {

    // create text area
    JTextArea textArea = new JTextArea();

    Main() {
        //set title, frame size
        setTitle("Notepad App");
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set icon for application
        ImageIcon icon = new ImageIcon("src/resources/notepad_37173.jpg");
        setIconImage(icon.getImage());

        // add menu bar to parent layout
        MenuBar mnb = new MenuBar();
        mnb.setMenuBar(this);
        mnb.add_actionListener(this);

        // add text area to the notepad
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        textArea.setLineWrap(true);
        add(scrollPane);
    }

    public static void main(String[] args) {
        // write your code here
        new Main().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("New")) {
            textArea.setText(null);
        } else if (e.getActionCommand().equalsIgnoreCase("Save")) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Only .txt file", "txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(textFilter);
            int action = fileChooser.showSaveDialog(null);
            if (action != fileChooser.APPROVE_OPTION) {
                return;
            } else {
                String fileName = fileChooser.getSelectedFile().getAbsolutePath();
                if (!fileName.contains(".txt")) {
                    fileName = fileName + ".txt";
                }
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
                    textArea.write(bw);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Open")) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Only .txt file", "txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(textFilter);
            int action = fileChooser.showSaveDialog(null);
            if (action != fileChooser.APPROVE_OPTION) {
                return;
            } else {
                String fileName = fileChooser.getSelectedFile().getAbsolutePath();
                if (!fileName.contains(".txt")) {
                    fileName = fileName + ".txt";
                }
                try {
                    BufferedReader bw = new BufferedReader(new FileReader(fileName));
                    textArea.read(bw, null);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Print")) {
            try {
                textArea.print();
            } catch (PrinterException prte) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, prte);
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Copy")) {
            textArea.copy();
        } else if (e.getActionCommand().equalsIgnoreCase("Cut")) {
            textArea.cut();
        } else if (e.getActionCommand().equalsIgnoreCase("Select All")) {
            textArea.selectAll();
        } else if (e.getActionCommand().equalsIgnoreCase("About")) {
            new About().setVisible(true);
        }
    }
}
