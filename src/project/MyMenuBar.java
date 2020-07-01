package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyMenuBar extends JMenuBar {
    JMenuItem newGameItem;
    JMenuItem restartItem;
    JMenu menu2;
    JMenu menu3;
    MyFrame parentFrame;
    JMenuItem hideHistoryItem;
    JMenuItem showHistoryItem;

    public MyMenuBar(MyFrame parentFrame){
        this.parentFrame = parentFrame;
        JMenu menu1 = new JMenu("Game");
        newGameItem = new JMenuItem("New Game");
        newGameItem.setEnabled(false);
        newGameItem.addActionListener(new MyActionListener(this,"newgame"));
        restartItem =  new JMenuItem("Restart");

        restartItem.addActionListener(new MyActionListener(this,"restart"));
        restartItem.setEnabled(false);
        JMenuItem exitItem =  new JMenuItem("Exit");
        exitItem.addActionListener(new MyActionListener(this,"exit"));
        menu1.add(newGameItem);
        menu1.add(restartItem);
        menu1.add(exitItem);

        add(menu1);

        menu2 = new JMenu("Options");
        menu2.setEnabled(false);
        JMenu historyMenu = new JMenu("History");
        showHistoryItem = new JMenuItem("Show History");
        hideHistoryItem = new JMenuItem("Hide History");

        showHistoryItem.addActionListener(new MyActionListener(this,"hidehistory"));
        hideHistoryItem.addActionListener(new MyActionListener(this,"showhistory"));
        historyMenu.add(showHistoryItem);
        historyMenu.add(hideHistoryItem);

        menu2.add(historyMenu);
        add(menu2);

        menu3 = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(new MyActionListener(this,"about"));
        menu3.add(aboutItem);
        add(menu3);
    }
}
