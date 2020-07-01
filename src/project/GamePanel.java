package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel {
    int[][] board = new int[8][8];
    int[][] availableCells = new int[8][8];
    int[] selectedCell = new int[2];
    int[] enemyCell = new int[2];
    int currentPlayer =0;
    int selectedPlayer;
    int selectedPlayerBackup;
    String  playerName;
    ControlPanel cPanel;
    InitializeGame initGame;
    int[][] moves;

    JPanel[][] list = new JPanel[8][8];
    //O arithmos 1 einai to aspro pouli
    //O arithmos 2 einai to kokkino pouli
    //O arithmos 0 einai kenh thesh
    public GamePanel(ControlPanel cPanel,InitializeGame initGame){

        this.cPanel = cPanel;
        this.initGame = initGame;
        currentPlayer =initGame.selectedPlayer;
        selectedPlayer = initGame.selectedPlayer;
        selectedPlayerBackup =initGame.selectedPlayer;
        playerName = initGame.playerName;
        moves = new int[8][8];
        System.out.println("test");
        for (int i=0;i<8;i++){
            for(int j =0;j<8;j++){
                int finalI = i;
                int finalJ = j;
                JPanel panel = new JPanel();
                if (i %2 != j %2 && i <3) {
                    JLabel j1 = new JLabel(new ImageIcon(getClass().getResource("/icons/pw.png")));
                    panel.add(j1);
                } else if (i %2 != j %2 && i >4){
                    JLabel j1 = new JLabel(new ImageIcon(getClass().getResource("/icons/pr.png")));
                    panel.add(j1);
                }
                panel.addMouseListener( new MyMouseListener(this,i,j,selectedCell,cPanel));
                //Eprepe na to bgalo apo to paintComponent to board epeidh den apothikeue tis times
                //den ksero an ftaiei kanena scope
                if (finalI %2 != finalJ %2 && finalI <3) {
                    board[finalI][finalJ]= 1;
                } else if (finalI %2 != finalJ %2 && finalI >4){
                    board[finalI][finalJ]= 2;
                }



                panel.setSize(50,50);
                if (i %2 != j%2) {
                    panel.setBackground(Color.DARK_GRAY);
                } else {
                    panel.setBackground(Color.lightGray);
                }

                add(panel);
                list[finalI][finalJ] = panel;
            }
        }
//        board[5][2]=4;
//        Component[] componentList = list[5][2].getComponents();
//        for (Component c : componentList){
//            if (c instanceof JLabel){
//                list[5][2].remove(c);
//            }
//        }
//        JLabel j1;
//        j1 = new JLabel(new ImageIcon(getClass().getResource("/icons/kr.png")));
//        list[5][2].add(j1);
//        System.out.println(getClass());
    }
    public void resetGame(){
        for (int i=0;i<8;i++){
            for(int j =0;j<8;j++){
                int finalI = i;
                int finalJ = j;
                Component[] componentList = list[finalI][finalJ].getComponents();
                for (Component c : componentList){
                    if (c instanceof JLabel){
                        list[finalI][finalJ].remove(c);
                    }
                }
                if (i %2 != j %2 && i <3) {

                    JLabel j1 = new JLabel(new ImageIcon(getClass().getResource("/icons/pw.png")));
                    j1.setSize(list[finalI][finalJ].getSize());
                    list[finalI][finalJ].add(j1);

                } else if (i %2 != j %2 && i >4){
                    JLabel j1 = new JLabel(new ImageIcon(getClass().getResource("/icons/pr.png")));
                    j1.setSize(list[finalI][finalJ].getSize());

                    list[finalI][finalJ].add(j1);
                }

                board[finalI][finalJ]=0;

                selectedCell[0]=0;
                selectedCell[1]=0;
                selectedPlayer = selectedPlayerBackup;
                currentPlayer = selectedPlayer;
                if (finalI %2 != finalJ %2 && finalI <3) {
                    board[finalI][finalJ]= 1;
                } else if (finalI %2 != finalJ %2 && finalI >4){
                    board[finalI][finalJ]= 2;
                }
                list[finalI][finalJ].setSize(50,50);
                if (i %2 != j%2) {
                    list[finalI][finalJ].setBackground(Color.DARK_GRAY);
                } else {
                    list[finalI][finalJ].setBackground(Color.lightGray);
                }
            }
        }
    }

    public void fixBoardIDError(){
        for (int i=0;i<8;i++){
            for(int j =0;j<8;j++){
                int finalI = i;
                int finalJ = j;
                boolean foundJLabel = false;
                Component[] componentList = list[finalI][finalJ].getComponents();
                for (Component c : componentList){
                    if (c instanceof JLabel){
                        foundJLabel = true;
                        System.out.println("["+finalI+"]["+finalJ+"] has JLabel");
                    }
                }
                if (foundJLabel == false && board[finalI][finalJ]!=0){
                    board[finalI][finalJ]=0;
                    System.out.println("found JLabel with id1 and  false");
                }

            }
        }
        System.out.println("ran fix id error");
    }
}
