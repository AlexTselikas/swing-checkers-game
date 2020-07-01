package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseListener implements MouseListener {
    public MyMouseListener(GamePanel gamePanel,int i, int j,int[] selectedCell,ControlPanel cPanel){
        this.gamePanel = gamePanel;
        this.i = i;
        this.j = j;
        this.selectedCell = selectedCell;
        this.cPanel = cPanel;
        init();
    }
    void init(){
        gameLogic = new GameLogic(gamePanel,i,j,selectedCell,cPanel);
    }
    GamePanel gamePanel;
    int[] selectedCell;
    int i,j;
    GameLogic gameLogic;
    ControlPanel cPanel;
    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                System.out.print(gamePanel.board[i][j]);
            }
            System.out.print("\n");
        }


        if (gamePanel.currentPlayer != gamePanel.board[i][j] && gamePanel.board[i][j] !=0){
            if (gamePanel.currentPlayer==1){
                if (gamePanel.board[i][j]!=3){
                    return;
                }
            } else if(gamePanel.currentPlayer==2){
                if (gamePanel.board[i][j]!=4){
                    return;
                }
            }
        }


        if (gamePanel.board[i][j] == 2 ){
            gameLogic.availableMoves(gamePanel.board);
        } else if (gamePanel.board[i][j] == 1 ){
           gameLogic.availableMoves(gamePanel.board);
        } else {
            System.out.println("else");
            gameLogic.availableMoves(gamePanel.board);

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
