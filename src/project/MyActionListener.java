package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyActionListener implements ActionListener {
    public MyActionListener(MyMenuBar menuBar,String src){
        this.menuBar = menuBar;
        this.src = src;
    }
    public MyActionListener(MyFrame parentFrame,String src){
        this.parentFrame= parentFrame;
        this.src=src;
    }
    MyMenuBar menuBar;
    MyFrame parentFrame;
    String src;

    @Override
    public void actionPerformed(ActionEvent e) {
                if (src =="newgame"){
                    menuBar.parentFrame.welcomePanel.setVisible(true);
                    //Mono to remove den ftanei, mou to bgazei diplo
                    //Ama bgalo to remove tote fainetai to timer sto welcomePanel mexri na ginei overwrite me to new JPanel
                    menuBar.parentFrame.remove(menuBar.parentFrame.parentPanel);

                    menuBar.parentFrame.parentPanel = new JPanel();
                    menuBar.parentFrame.parentPanel.setLayout(new GridLayout(1,2));
                    menuBar.parentFrame.revalidate();
                    menuBar.parentFrame.repaint();
                }
                if (src =="restart"){
                    menuBar.parentFrame.gamePanel.resetGame();
                    menuBar.parentFrame.controlPanel.resetControlPanel();
                }
                if (src == "exit"){
                    System.exit(0);
                }
                if (src == "hidehistory"){
                    menuBar.parentFrame.controlPanel.scrollPane.setVisible(true);
                    menuBar.parentFrame.controlPanel.historyLabel.setVisible(true);
                    menuBar.hideHistoryItem.setEnabled(true);
                    menuBar.showHistoryItem.setEnabled(false);
                }
                if (src=="showhistory"){
                    menuBar.parentFrame.controlPanel.scrollPane.setVisible(false);
                    menuBar.parentFrame.controlPanel.historyLabel.setVisible(false);
                    menuBar.hideHistoryItem.setEnabled(false);
                    menuBar.showHistoryItem.setEnabled(true);
                }
                if (src =="about"){
                    JOptionPane.showMessageDialog(menuBar.parentFrame,"This is a checkers game made as a semester project \nin Principles of Software Engineering\n\n\n Created by Alexandros Tselikas TP4886");
                }
                if (src =="welcomepanelstart"){
                    parentFrame.welcomePanel.setVisible(false);
//                parentFrame.remove(parentFrame.welcomePanel);
                    InitializeGame init = new InitializeGame(parentFrame.controlPanel,parentFrame.gamePanel,parentFrame.parentPanel,parentFrame);
                    //Xreiazetai gia na exoume prosvasi sto gamePanel se alles classes, allios petaei NullPointerException
                    parentFrame.gamePanel = init.gamePanel;
                    parentFrame.add(parentFrame.parentPanel,BorderLayout.CENTER);
                    parentFrame.repaint();
                }
    }
}
