package project;

import javax.swing.*;

public class Project {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MyFrame window = new MyFrame("Checkers Final Project 19-20 Alexandros Tselikas TP4886");
                window.setBounds(50,100,900,500);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setVisible(true);
            }
        });
    }
}
