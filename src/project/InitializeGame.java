package project;

import javax.swing.*;
import java.awt.*;

public class InitializeGame {
    public InitializeGame(ControlPanel controlPanel, GamePanel gamePanel, JPanel parentPanel,MyFrame parentFrame){
        this.controlPanel = controlPanel;
        this.gamePanel = gamePanel;
        this.parentPanel = parentPanel;
        this.parentFrame = parentFrame;
        init();
    }
    ControlPanel controlPanel;
    GamePanel gamePanel;
    JPanel parentPanel;
    MyFrame parentFrame;
    int selectedPlayer;
    String playerName;
    void init(){
        playerName = parentFrame.name.getText();
        if(parentFrame.group.getSelection().getActionCommand() == "white"){
            selectedPlayer = 1;
        } else {
            selectedPlayer =2;
        }
        GridLayout grid = new GridLayout(8,8);
        controlPanel = new ControlPanel(this);
        gamePanel = new GamePanel(controlPanel,this);
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                System.out.print(gamePanel.board[i][j]);
            }
            System.out.print("\n");
        }

        gamePanel.setLayout(grid);
        parentPanel.add(gamePanel);
        parentPanel.add(controlPanel);

        //Xreiazetai allios den mporo na to vro apo to menubar
        parentFrame.controlPanel = controlPanel;

        enableMenus();
    }
    void enableMenus(){
        parentFrame.menubar.menu2.setEnabled(true);
        parentFrame.menubar.menu3.setEnabled(true);
        parentFrame.menubar.newGameItem.setEnabled(true);
        parentFrame.menubar.restartItem.setEnabled(true);
        parentFrame.menubar.showHistoryItem.setEnabled(false);
    }

}
