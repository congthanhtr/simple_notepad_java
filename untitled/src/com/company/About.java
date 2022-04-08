package com.company;

import javax.swing.*;

public class About extends JFrame {

    About() {
        setBounds(100, 100, 500, 400);
        setTitle("About Notepad App");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel iconLabel = new JLabel(new ImageIcon("src/resources/notepad_37173.jpg"));
        iconLabel.setBounds(100, 50, 239, 222);
        add(iconLabel);

        JLabel textLabel = new JLabel("<html>Welcome to NotePad App<br>I love u all</html>");
        textLabel.setBounds(100, 200, 400, 300);
        add(textLabel);
    }

    public static void main(String[] args) {
        new About().setVisible(true);
    }
}
