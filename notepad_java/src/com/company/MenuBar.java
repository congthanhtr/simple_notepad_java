package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JFrame implements ActionListener {

    // create menubar
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenu editMenu = new JMenu("Edit");
    JMenu helpMenu = new JMenu("Help");

    // menu item for file menu
    JMenuItem newFile = new JMenuItem("New");
    JMenuItem saveFile = new JMenuItem("Save");
    JMenuItem openFile = new JMenuItem("Open");
    JMenuItem printFile = new JMenuItem("Print");

    // menu item for edit menu
    JMenuItem copy = new JMenuItem("Copy");
    JMenuItem cut = new JMenuItem("Cut");
    JMenuItem selectall = new JMenuItem("Select All");

    // menu item for help menu
    JMenuItem about = new JMenuItem("About");

    // create text area
    JTextArea textArea = new JTextArea();

    MenuBar() {
        // new file action listener
        newFile.addActionListener(this);
        saveFile.addActionListener(this);
        openFile.addActionListener(this);
        printFile.addActionListener(this);
        selectall.addActionListener(this);
        copy.addActionListener(this);
        cut.addActionListener(this);
        about.addActionListener(this);
    }

    public void setMenuBar(Main main) {
        // set menu bar and add menu
        main.setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        // add menu item to File menu
        fileMenu.add(newFile);
        fileMenu.add(saveFile);
        fileMenu.add(openFile);
        fileMenu.add(printFile);

        // add menu item to Edit menu
        editMenu.add(copy);
        editMenu.add(cut);
        editMenu.add(selectall);

        // add menu item to Help menu
        helpMenu.add(about);

        // new file action listener
        newFile.addActionListener(this);
        saveFile.addActionListener(this);
        openFile.addActionListener(this);
        printFile.addActionListener(this);
        selectall.addActionListener(this);
        copy.addActionListener(this);
        cut.addActionListener(this);
        about.addActionListener(this);
    }

    public void add_actionListener(ActionListener e) {
        // action listener
        newFile.addActionListener(e);
        saveFile.addActionListener(e);
        openFile.addActionListener(e);
        printFile.addActionListener(e);
        selectall.addActionListener(e);
        copy.addActionListener(e);
        cut.addActionListener(e);
        about.addActionListener(e);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
