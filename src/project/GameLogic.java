package project;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;

public class GameLogic {
    int[][] moves;

    public GameLogic(GamePanel gamePanel,int i, int j,int[] selectedCell,ControlPanel cPanel){
        this.i = i;
        this.j = j;
        this.gamePanel = gamePanel;
        this.selectedCell = selectedCell;
        this.cPanel = cPanel;
        this.moves = gamePanel.moves;

    }

    GamePanel gamePanel;
    int i,j;
    int[] selectedCell;
    int[][] legalMoves = new int[8][8];
    ControlPanel cPanel;
    int count1=0;
    int count2=0;
    public void availableMoves(int[][] board){

        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                if (board[i][j]==0){
                    JPanel panel = gamePanel.list[i][j];
                    if (i %2 != j%2) {
                        panel.setBackground(Color.DARK_GRAY);
                    } else {
                        panel.setBackground(Color.lightGray);
                    }
                }
            }
        }

        int index = 0;
        if (board[i][j]==1){
            index = 1;
        }else if (board[i][j]==2){
            index = -1;
        }
        if (gamePanel.initGame.selectedPlayer==0){
            return;
        }
        if (board[i][j] == 1 || board[i][j] == 2 ||board[i][j] == 3||board[i][j] == 4){
            selectedCell[0] = i;
            selectedCell[1] = j;
            gamePanel.moves = legalMoves(board);
            for (int i=0;i<8;i++){
                for (int j=0;j<8;j++){
                    if(gamePanel.moves[i][j]==1){
                        JPanel panel = gamePanel.list[i][j];
                        panel.setBackground(Color.green);
                    }
                    if (gamePanel.moves[i][j]==2){
                        JPanel panel = gamePanel.list[i][j];
                        panel.setBackground(Color.red);
                    }
                }
            }
        } else {

            if (gamePanel.moves[i][j]==1){
                JLabel j1;
                if (board[selectedCell[0]][selectedCell[1]]==1){
                    if (i==7){
                        j1 = new JLabel(new ImageIcon(getClass().getResource("/icons/kw.png")));
                        board[i][j]=3;
                    } else {
                        j1 = new JLabel(new ImageIcon(getClass().getResource("/icons/pw.png")));
                        board[i][j]=1;
                    }
                } else if (board[selectedCell[0]][selectedCell[1]]==2){
                    if (i ==0){
                        j1 = new JLabel(new ImageIcon(getClass().getResource("/icons/kr.png")));
                        board[i][j]=4;
                    } else {
                        j1 = new JLabel(new ImageIcon(getClass().getResource("/icons/pr.png")));
                        board[i][j]=2;
                    }
                }else if (board[selectedCell[0]][selectedCell[1]]==3){
                    j1 = new JLabel(new ImageIcon(getClass().getResource("/icons/kw.png")));
                    board[i][j]=3;
                } else {
                    j1 = new JLabel(new ImageIcon(getClass().getResource("/icons/kr.png")));
                    board[i][j]=4;
                }
                Component[] componentList = gamePanel.list[selectedCell[0]][selectedCell[1]].getComponents();
                for (Component c : componentList){
                    if (c instanceof JLabel){
                        gamePanel.list[selectedCell[0]][selectedCell[1]].remove(c);
                    }
                }
                board[selectedCell[0]][selectedCell[1]] =0;
                resetAvailableCells();

                updateHistory();

                switchPlayer();
                j1.setSize(gamePanel.list[i][j].getSize());
                gamePanel.list[i][j].add(j1);
                //Gia na afairesei to JLabel apo to JPanel
                gamePanel.repaint();

            } else if(gamePanel.moves[i][j]==2){
                JLabel j1;
                if (board[selectedCell[0]][selectedCell[1]]==1){
                    if (i==7){
                        j1 = new JLabel(new ImageIcon(getClass().getResource("/icons/kw.png")));
                        board[i][j]=3;
                    } else {
                        j1 = new JLabel(new ImageIcon(getClass().getResource("/icons/pw.png")));
                        board[i][j]=1;
                    }
                } else if (board[selectedCell[0]][selectedCell[1]]==2){
                    if (i ==0){
                        j1 = new JLabel(new ImageIcon(getClass().getResource("/icons/kr.png")));
                        board[i][j]=4;
                    } else {
                        j1 = new JLabel(new ImageIcon(getClass().getResource("/icons/pr.png")));
                        board[i][j]=2;
                    }

                }else if (board[selectedCell[0]][selectedCell[1]]==3){
                    j1 = new JLabel(new ImageIcon(getClass().getResource("/icons/kw.png")));
                    board[i][j]=3;
                } else {
                    j1 = new JLabel(new ImageIcon(getClass().getResource("/icons/kr.png")));
                    board[i][j]=4;
                }
                Component[] componentList = gamePanel.list[gamePanel.enemyCell[0]][gamePanel.enemyCell[1]].getComponents();
                for (Component c : componentList){
                    if (c instanceof JLabel){
                        gamePanel.list[gamePanel.enemyCell[0]][gamePanel.enemyCell[1]].remove(c);
                    }
                }
                componentList = gamePanel.list[selectedCell[0]][selectedCell[1]].getComponents();
                for (Component c : componentList){
                    if (c instanceof JLabel){
                        gamePanel.list[selectedCell[0]][selectedCell[1]].remove(c);
                    }
                }
                board[gamePanel.enemyCell[0]][gamePanel.enemyCell[1]] =0;
                board[selectedCell[0]][selectedCell[1]] =0;
                resetAvailableCells();

                updateHistory();
                switchPlayer();
                j1.setSize(gamePanel.list[i][j].getSize());
                gamePanel.list[i][j].add(j1);
                //Gia na afairesei to JLabel apo to JPanel
                gamePanel.repaint();

            }
        }

    }
    public void resetAvailableCells(){
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                gamePanel.availableCells[i][j]=0;
            }
        }
        count1=0;
        count2=0;
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                if (gamePanel.board[i][j]==1 ||gamePanel.board[i][j]==3){
                    count1++;
                }
                if (gamePanel.board[i][j]==2 || gamePanel.board[i][j]==4){
                    count2++;
                }
            }
        }
    }
    public void switchPlayer(){
        if (gamePanel.currentPlayer == 1) {
            gamePanel.currentPlayer = 2;
        } else {
            gamePanel.currentPlayer =1;
        }
    }

    public void updateHistory(){
        cPanel.historyTextArea.append(cPanel.currPlayer.getText()+" moved to "+i+","+j+"\n");
        if(gamePanel.selectedPlayer == gamePanel.currentPlayer){
            cPanel.currPlayer.setText("Computer");
            Font font =cPanel.currPlayer.getFont();
            Map attributes = font.getAttributes();
            attributes.put(TextAttribute.UNDERLINE,TextAttribute.UNDERLINE_ON);
            cPanel.currPlayer.setFont(font.deriveFont(attributes));
        } else {
            cPanel.currPlayer.setText(gamePanel.playerName);
            Font font =cPanel.currPlayer.getFont();
            Map attributes = font.getAttributes();
            attributes.put(TextAttribute.UNDERLINE,TextAttribute.UNDERLINE_ON);
            cPanel.currPlayer.setFont(font.deriveFont(attributes));
        }
        if (count1==0){
            if (gamePanel.initGame.selectedPlayer ==2){
                cPanel.historyTextArea.append(gamePanel.initGame.playerName+" won the match!"+"\n");
                gamePanel.initGame.selectedPlayer=0;
                cPanel.timer.stop();
            }else {
                cPanel.historyTextArea.append("Computer won the match!"+"\n");
                gamePanel.initGame.selectedPlayer=0;
                cPanel.timer.stop();

            }
        } else if(count2==0) {
            if (gamePanel.initGame.selectedPlayer ==1){
                cPanel.historyTextArea.append(gamePanel.initGame.playerName+" won the match!"+"\n");
                gamePanel.initGame.selectedPlayer=0;
                cPanel.timer.stop();
            }else {
                cPanel.historyTextArea.append("Computer won the match!"+"\n");
                gamePanel.initGame.selectedPlayer=0;
                cPanel.timer.stop();
            }
        }
    }
    public int[][] legalMoves(int[][] board){
        int[][] moves = new int[8][8];
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                moves[i][j]=0;
            }
        }
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                if (i==selectedCell[0] && j ==selectedCell[1]){
                    int index = 0;
                    if (board[i][j]==1){
                        index = 1;
                    }else if (board[i][j]==2){
                        index = -1;
                    }


                    //Gia to aspro pouli
                    if (board[i][j]==1){

                        if (j==0 && i<7){
                            if (board[i+1][j+1]==0){
                                moves[i+1][j+1]=1;
                            }
                            if (board[i+1][j+1] != gamePanel.currentPlayer &&board[i+1][j+1] != gamePanel.currentPlayer+2  && board[i+1][j+1]!=0 ){
                                if (board[i+2][j+2]== 0 ){
                                    moves[i+2][j+2]=2;
                                    gamePanel.enemyCell[0] = i+1;
                                    gamePanel.enemyCell[1]=j+1;
                                }
                            }
                        } else if(j==7){
                            if (board[i+1][j-1]==0){
                                moves[i+1][j-1]=1;
                            }
                            if (i<6){
                                if (board[i+1][j-1] != gamePanel.currentPlayer && board[i+1][j-1] != gamePanel.currentPlayer+2 && board[i+1][j-1]!=0 ){
                                    if (board[i+2][j-2]== 0 ){
                                        moves[i+2][j-2]=2;
                                        gamePanel.enemyCell[0] = i+1;
                                        gamePanel.enemyCell[1]=j-1;
                                    }
                                }
                            }
                        } else {
                            if (board[i+1][j+1]==0){
                                moves[i+1][j+1]=1;
                            }
                            if (board[i+1][j-1]==0){
                                moves[i+1][j-1]=1;
                            }
                            if (j<6 && j>1){
                                if (i!=6){
                                    if (board[i+1][j+1] != gamePanel.currentPlayer && board[i+1][j+1] != gamePanel.currentPlayer +2 &&board[i+1][j+1]!=0 ){
                                        if (board[i+2][j+2]== 0 ){
                                            gamePanel.enemyCell[0] = i+1;
                                            gamePanel.enemyCell[1]=j+1;
                                            moves[i+2][j+2]=2;
                                        }
                                    }
                                    if (board[i+1][j-1] != gamePanel.currentPlayer &&board[i+1][j-1] != gamePanel.currentPlayer+2 && board[i+1][j-1]!=0 ){
                                        if (board[i+2][j-2]== 0 ){
                                            gamePanel.enemyCell[0] = i+1;
                                            gamePanel.enemyCell[1]=j-1;
                                            moves[i+2][j-2]=2;
                                        }
                                    }
                                }

                            } else if(j == 1){
                                if (board[i+1][j+1] != gamePanel.currentPlayer && board[i+1][j+1] != gamePanel.currentPlayer +2 && board[i+1][j+1]!=0 ){
                                    if (board[i+2][j+2]== 0 ){
                                        gamePanel.enemyCell[0] = i+1;
                                        gamePanel.enemyCell[1]=j+1;
                                        moves[i+2][j+2]=2;
                                    }
                                }
                            }
                            if (j==6){
                                if (board[i+1][j-1] != gamePanel.currentPlayer &&board[i+1][j-1] != gamePanel.currentPlayer+2 && board[i+1][j-1]!=0 ){
                                    if (board[i+2][j-2]== 0 ){
                                        gamePanel.enemyCell[0] = i+1;
                                        gamePanel.enemyCell[1]=j-1;
                                        moves[i+2][j-2]=2;
                                    }
                                }
                            }

                        }

                    }


                    //Gia to kokkino pouli
                    if (board[i][j]==2){
                        if (j==0){
                            if (board[i-1][j+1]==0){
                                moves[i-1][j+1]=1;
                            }
                            if (board[i-1][j+1] != gamePanel.currentPlayer && board[i-1][j+1]!=0 ){
                                if (board[i-2][j+2]== 0 ){
                                    moves[i-2][j+2]=2;
                                    gamePanel.enemyCell[0] = i-1;
                                    gamePanel.enemyCell[1]=j+1;
                                }
                            }
                        } else if(j==7){
                            if (board[i-1][j-1]==0){
                                moves[i-1][j-1]=1;
                            }
                            if (board[i-1][j-1] != gamePanel.currentPlayer && board[i-1][j-1]!=0 ){
                                if (board[i-2][j-2]== 0 ){
                                    moves[i-2][j-2]=2;
                                    gamePanel.enemyCell[0] = i-1;
                                    gamePanel.enemyCell[1]=j-1;
                                }
                            }

                        } else {
                            if (board[i-1][j+1]==0){
                                moves[i-1][j+1]=1;
                            }
                            if (board[i-1][j-1]==0){
                                moves[i-1][j-1]=1;
                            }

                            if (i!=1){
                                if (j <6){
                                    if (board[i-1][j+1] != gamePanel.currentPlayer && board[i-1][j+1]!=0 ){
                                        if (board[i-2][j+2]== 0 ){
                                            gamePanel.enemyCell[0] = i-1;
                                            gamePanel.enemyCell[1]=j+1;
                                            moves[i-2][j+2]=2;
                                        }
                                    }

                                }
                                if (j>1){
                                    if (board[i-1][j-1] != gamePanel.currentPlayer && board[i-1][j-1]!=0 ){
                                        if (board[i-2][j-2]== 0 ){
                                            gamePanel.enemyCell[0] = i-1;
                                            gamePanel.enemyCell[1]=j-1;
                                            moves[i-2][j-2]=2;
                                        }
                                    }

                                }


                            }

                        }
                    }
                    //Gia otan ginetai ntama to aspro
                    //to exw kanei pio periploko ap'oso prepei
                    if (board[i][j]==3){
                        //Ama einai sthn teleytaia seira
                        if (i==7){
                            if (j==0){
                                if (board[i-1][j+1]==0){
                                    moves[i-1][j+1]=1;
                                }
                                if (board[i-2][j+2]==0 && (board[i-1][j+1] == 2 || board[i-1][j+1]==4)){
                                    gamePanel.enemyCell[0]= i-1;
                                    gamePanel.enemyCell[1]=j+1;
                                    moves[i-2][j+2]=2;
                                }
                            }else if(j==1){
                                if (board[i-1][j+1]==0){
                                    moves[i-1][j+1]=1;
                                }
                                if (board[i-1][j-1]==0){
                                    moves[i-1][j-1]=1;
                                }
                                if (board[i-2][j+2]==0 && (board[i-1][j+1] == 2 || board[i-1][j+1]==4)){
                                    gamePanel.enemyCell[0]= i-1;
                                    gamePanel.enemyCell[1]=j+1;
                                    moves[i-2][j+2]=2;
                                }
                            } else if (j==6){
                                if (board[i-1][j+1]==0){
                                    moves[i-1][j+1]=1;
                                }
                                if (board[i-1][j-1]==0){
                                    moves[i-1][j-1]=1;
                                }
                                if (board[i-2][j-2]==0 && (board[i-1][j-1] == 2 || board[i-1][j-1]==4)){
                                    gamePanel.enemyCell[0]= i-1;
                                    gamePanel.enemyCell[1]=j-1;
                                    moves[i-2][j-2]=2;
                                }
                            } else if(j==7){
                                if (board[i-1][j-1]==0){
                                    moves[i-1][j-1]=1;
                                }
                                if (board[i-2][j-2]==0 && (board[i-1][j-1] == 2 || board[i-1][j-1]==4)){
                                    gamePanel.enemyCell[0]= i-1;
                                    gamePanel.enemyCell[1]=j-1;
                                    moves[i-2][j-2]=2;
                                }

                            } else {
                                if (board[i-1][j-1]==0){
                                    moves[i-1][j-1]=1;
                                }
                                if (board[i-1][j+1]==0){
                                    moves[i-1][j+1]=1;
                                }
                                if (board[i-2][j-2]==0 && (board[i-1][j-1]==2 || board[i-1][j-1]==4)){
                                    gamePanel.enemyCell[0]= i-1;
                                    gamePanel.enemyCell[1]=j-1;
                                    moves[i-2][j-2]=2;
                                }
                                if (board[i-2][j+2]==0 && (board[i-1][j+1]==2 || board[i-1][j+1]==4)){
                                    gamePanel.enemyCell[0]= i-1;
                                    gamePanel.enemyCell[1]=j+1;
                                    moves[i-2][j+2]=2;
                                }
                            }

                        } else if(i==6){
                            if (j==0){
                                if (board[i+1][j+1]==0){
                                    moves[i+1][j+1]=1;
                                }
                                if (board[i-1][j+1]==0){
                                    moves[i-1][j+1]=1;
                                }

                            } else if (j==1){
                                if (board[i-1][j-1]==0){
                                    moves[i-1][j-1]=1;
                                }
                                if (board[i+1][j-1]==0){
                                    moves[i+1][j-1]=1;
                                }
                                if (board[i+1][j+1]==0){
                                    moves[i+1][j+1]=1;
                                }
                                if (board[i-1][j+1]==0){
                                    moves[i-1][j+1]=1;
                                }
                                if (board[i-2][j+2]==0 &&  board[i-1][j+1]!= 3 && board[i-1][j+1]!=0){
                                    gamePanel.enemyCell[0]= i-1;
                                    gamePanel.enemyCell[1]=j+1;
                                    moves[i-2][j+2]=2;
                                }

                            } else if (j==6){
                                if (board[i-1][j-1]==0){
                                    moves[i-1][j-1]=1;
                                }
                                if (board[i+1][j-1]==0){
                                    moves[i+1][j-1]=1;
                                }
                                if (board[i+1][j+1]==0){
                                    moves[i+1][j+1]=1;
                                }
                                if (board[i-1][j+1]==0){
                                    moves[i-1][j+1]=1;
                                }
                                if (board[i-2][j-2]==0 &&  board[i-1][j-1]!= 3 && board[i-1][j-1]!=0){
                                    gamePanel.enemyCell[0]= i-1;
                                    gamePanel.enemyCell[1]=j-1;
                                    moves[i-2][j-2]=2;
                                }

                            } else if (j==7){
                                if (board[i-1][j-1]==0){
                                    moves[i-1][j-1]=1;
                                }
                                if (board[i+1][j-1]==0){
                                    moves[i+1][j-1]=1;
                                }
                                if (board[i-2][j-2]==0 && (board[i-1][j-1] == 2 || board[i-1][j-1]==4)){
                                    gamePanel.enemyCell[0]= i-1;
                                    gamePanel.enemyCell[1]=j-1;
                                    moves[i-2][j-2]=2;
                                }

                            }
                        } else if (j<6 && j>1 && i<6 && i>1){
                            if (board[i-1][j-1]==0){
                                moves[i-1][j-1]=1;
                            }
                            if (board[i-1][j+1]==0){
                                moves[i-1][j+1]=1;
                            }
                            if (board[i+1][j-1]==0){
                                moves[i+1][j-1]=1;
                            }

                            if (board[i+1][j+1]==0){
                                moves[i+1][j+1]=1;
                            }
                            if (board[i-2][j-2]==0 && (board[i-1][j-1] == 2 || board[i-1][j-1]==4)){
                                gamePanel.enemyCell[0]= i-1;
                                gamePanel.enemyCell[1]=j-1;
                                moves[i-2][j-2]=2;
                            }
                            if (board[i-2][j+2]==0 && (board[i-1][j+1] == 2 || board[i-1][j+1]==4)){
                                gamePanel.enemyCell[0]= i-1;
                                gamePanel.enemyCell[1]=j+1;
                                moves[i-2][j+2]=2;
                            }
                            if (board[i+2][j+2]==0 && (board[i+1][j+1] == 2 || board[i+1][j+1]==4)){
                                gamePanel.enemyCell[0]= i+1;
                                gamePanel.enemyCell[1]=j+1;
                                moves[i+2][j+2]=2;
                            }
                            if (board[i+2][j-2]==0 && (board[i+1][j-1] == 2 || board[i+1][j-1]==4)){
                                gamePanel.enemyCell[0]= i+1;
                                gamePanel.enemyCell[1]=j-1;
                                moves[i+2][j-2]=2;
                            }

                        } else if (j==1 && i>=1){
                            if (board[i+1][j-1]==0){
                                moves[i+1][j-1]=1;
                            }
                            if (board[i+1][j+1]==0){
                                moves[i+1][j+1]=1;
                            }
                            if (board[i-1][j-1]==0){
                                moves[i-1][j-1]=1;
                            }
                            if (board[i-1][j+1]==0){
                                moves[i-1][j+1]=1;
                            }
                        }
                        if (i>0 && i<7){
                            if (j==0){
                                if (board[i-1][j+1]==0){
                                    moves[i-1][j+1]=1;
                                }
                                if (board[i+1][j+1]==0){
                                    moves[i+1][j+1]=1;
                                }
                                if (i>1 && i<6){
                                    if (board[i-2][j+2]==0 && (board[i-1][j+1] == 2 || board[i-1][j+1]==4)){
                                        gamePanel.enemyCell[0]= i-1;
                                        gamePanel.enemyCell[1]=j+1;
                                        moves[i-2][j+2]=2;
                                    }
                                    if (board[i+2][j+2]==0 && (board[i+1][j+1] == 2 || board[i+1][j+1]==4)){
                                        gamePanel.enemyCell[0]= i+1;
                                        gamePanel.enemyCell[1]=j+1;
                                        moves[i+2][j+2]=2;
                                    }
                                }
                            } else if (j==7){
                                if (board[i-1][j-1]==0){
                                    moves[i-1][j-1]=1;
                                }
                                if (board[i+1][j-1]==0){
                                    moves[i+1][j-1]=1;
                                }
                                if (i>1 && i<6){
                                    if (board[i-2][j-2]==0 && (board[i-1][j-1] == 2 || board[i-1][j-1]==4)){
                                        gamePanel.enemyCell[0]= i-1;
                                        gamePanel.enemyCell[1]=j-1;
                                        moves[i-2][j-2]=2;
                                    }
                                    if (board[i+2][j-2]==0 && (board[i+1][j-1] == 2 || board[i+1][j-1]==4)){
                                        gamePanel.enemyCell[0]= i+1;
                                        gamePanel.enemyCell[1]=j-1;
                                        moves[i+2][j-2]=2;
                                    }
                                }
                                if (i<6){

                                }
                            } else {
                                if (board[i-1][j+1]==0){
                                    moves[i-1][j+1]=1;
                                }
                                if (board[i+1][j+1]==0){
                                    moves[i+1][j+1]=1;
                                }
                                if (board[i-1][j-1]==0){
                                    moves[i-1][j-1]=1;
                                }
                                if (board[i+1][j-1]==0){
                                    moves[i+1][j-1]=1;
                                }
                                if (j==6 && i<6){
                                    if (board[i+2][j-2]==0 && (board[i+1][j-1] == 2 || board[i+1][j-1]==4)){
                                        gamePanel.enemyCell[0]= i+1;
                                        gamePanel.enemyCell[1]=j-1;
                                        moves[i+2][j-2]=2;
                                    }
                                }
                            }

                            if (i>2 && i<6 && j>1 && j<6){
                                if (board[i-2][j+2]==0 && (board[i-1][j+1] == 2 || board[i-1][j+1]==4)){
                                    gamePanel.enemyCell[0]= i-1;
                                    gamePanel.enemyCell[1]=j+1;
                                    moves[i-2][j+2]=2;
                                }
                                if (board[i+2][j+2]==0 && (board[i+1][j+1] == 2 || board[i+1][j+1]==4)){
                                    gamePanel.enemyCell[0]= i+1;
                                    gamePanel.enemyCell[1]=j+1;
                                    moves[i+2][j+2]=2;
                                }
                            }
                        }
                    }


                    //Gia otan ginetai ntama to kokkino
                    if (board[i][j]==4){
                        //
                        //Ama einai sthn teleytaia seira
                        if (i==7){
                            if (j==0){
                                if (board[i-1][j+1]==0){
                                    moves[i-1][j+1]=1;
                                }
                                if (board[i-2][j+2]==0 && (board[i-1][j+1] == 1 || board[i-1][j+1]==3)){
                                    gamePanel.enemyCell[0]= i-1;
                                    gamePanel.enemyCell[1]=j+1;
                                    moves[i-2][j+2]=2;
                                }
                            }else if(j==1){
                                if (board[i-1][j+1]==0){
                                    moves[i-1][j+1]=1;
                                }
                                if (board[i-1][j-1]==0){
                                    moves[i-1][j-1]=1;
                                }
                                if (board[i-2][j+2]==0 && (board[i-1][j+1] == 1 || board[i-1][j+1]==3)){
                                    gamePanel.enemyCell[0]= i-1;
                                    gamePanel.enemyCell[1]=j+1;
                                    moves[i-2][j+2]=2;
                                }

                            } else if (j==6){
                                if (board[i-1][j+1]==0){
                                    moves[i-1][j+1]=1;
                                }
                                if (board[i-1][j-1]==0){
                                    moves[i-1][j-1]=1;
                                }
                                if (board[i-2][j-2]==0 && (board[i-1][j-1] ==1 || board[i-1][j-1]==3)){
                                    gamePanel.enemyCell[0]= i-1;
                                    gamePanel.enemyCell[1]=j-1;
                                    moves[i-2][j-2]=2;
                                }

                            } else if(j==7){
                                if (board[i-1][j-1]==0){
                                    moves[i-1][j-1]=1;
                                }
                                if (board[i-2][j-2]==0 && (board[i-1][j-1] == 1 || board[i-1][j-1]==3)){
                                    gamePanel.enemyCell[0]= i-1;
                                    gamePanel.enemyCell[1]=j-1;
                                    moves[i-2][j-2]=2;
                                }

                            } else {
                                if (board[i-1][j-1]==0){
                                    board[i-1][j-1]=1;
                                }
                                if (board[i-1][j+1]==0){
                                    board[i-1][j+1]=1;
                                }
                                if (board[i-2][j-2]==0 && (board[i-1][j-1]==1 || board[i-1][j-1]==3)){
                                    gamePanel.enemyCell[0]= i-1;
                                    gamePanel.enemyCell[1]=j-1;
                                    moves[i-2][j-2]=2;
                                }
                                if (board[i-2][j+2]==0 && (board[i-1][j+1]==1 || board[i-1][j+1]==3)){
                                    gamePanel.enemyCell[0]= i-1;
                                    gamePanel.enemyCell[1]=j+1;
                                    moves[i-2][j+2]=2;
                                }
                            }

                        } else if(i==6){
                            if (j==0){
                                if (board[i+1][j+1]==0){
                                    moves[i+1][j+1]=1;
                                }
                                if (board[i-1][j+1]==0){
                                    moves[i-1][j+1]=1;
                                }

                            } else if (j==1){
                                if (board[i-1][j-1]==0){
                                    moves[i-1][j-1]=1;
                                }
                                if (board[i+1][j-1]==0){
                                    moves[i+1][j-1]=1;
                                }
                                if (board[i+1][j+1]==0){
                                    moves[i+1][j+1]=1;
                                }
                                if (board[i-1][j+1]==0){
                                    moves[i-1][j+1]=1;
                                }
                                if (board[i-2][j+2]==0 &&  board[i-1][j+1]!= 4 && board[i-1][j+1]!=0){
                                    gamePanel.enemyCell[0]= i-1;
                                    gamePanel.enemyCell[1]=j+1;
                                    moves[i-2][j+2]=2;
                                }

                            } else if (j==6){
                                if (board[i-1][j-1]==0){
                                    moves[i-1][j-1]=1;
                                }
                                if (board[i+1][j-1]==0){
                                    moves[i+1][j-1]=1;
                                }
                                if (board[i+1][j+1]==0){
                                    moves[i+1][j+1]=1;
                                }
                                if (board[i-1][j+1]==0){
                                    moves[i-1][j+1]=1;
                                }
                                if (board[i-2][j-2]==0 &&  board[i-1][j-1]!= 4 && board[i-1][j-1]!=0){
                                    gamePanel.enemyCell[0]= i-1;
                                    gamePanel.enemyCell[1]=j-1;
                                    moves[i-2][j-2]=2;
                                }

                            } else if (j==7){
                                if (board[i-1][j-1]==0){
                                    moves[i-1][j-1]=1;
                                }
                                if (board[i+1][j-1]==0){
                                    moves[i+1][j-1]=1;
                                }
                                if (board[i-2][j-2]==0 && (board[i-1][j-1] == 1 || board[i-1][j-1]==3)){
                                    gamePanel.enemyCell[0]= i-1;
                                    gamePanel.enemyCell[1]=j-1;
                                    moves[i-2][j-2]=2;
                                }

                            }
                        } else if (j<6 && j>1 && i<6 && i>1){
                            if (board[i-1][j-1]==0){
                                moves[i-1][j-1]=1;
                            }
                            if (board[i-1][j+1]==0){
                                moves[i-1][j+1]=1;
                            }
                            if (board[i+1][j-1]==0){
                                moves[i+1][j-1]=1;
                            }

                            if (board[i+1][j+1]==0){
                                moves[i+1][j+1]=1;
                            }
                            if (board[i-2][j-2]==0 && (board[i-1][j-1] == 1 || board[i-1][j-1]==3)){
                                gamePanel.enemyCell[0]= i-1;
                                gamePanel.enemyCell[1]=j-1;
                                moves[i-2][j-2]=2;
                            }
                            if (board[i-2][j+2]==0 && (board[i-1][j+1] == 1 || board[i-1][j+1]==3)){
                                gamePanel.enemyCell[0]= i-1;
                                gamePanel.enemyCell[1]=j+1;
                                moves[i-2][j+2]=2;
                            }
                            if (board[i+2][j+2]==0 && (board[i+1][j+1] == 1 || board[i+1][j+1]==3)){
                                gamePanel.enemyCell[0]= i+1;
                                gamePanel.enemyCell[1]=j+1;
                                moves[i+2][j+2]=2;
                            }
                            if (board[i+2][j-2]==0 && (board[i+1][j-1] == 1 || board[i+1][j-1]==3)){
                                gamePanel.enemyCell[0]= i+1;
                                gamePanel.enemyCell[1]=j-1;
                                moves[i+2][j-2]=2;
                            }

                        } else if (j==1 && i>=1){
                            if (board[i+1][j-1]==0){
                                moves[i+1][j-1]=1;
                            }
                            if (board[i+1][j+1]==0){
                                moves[i+1][j+1]=1;
                            }
                            if (board[i-1][j-1]==0){
                                moves[i-1][j-1]=1;
                            }
                            if (board[i-1][j+1]==0){
                                moves[i-1][j+1]=1;
                            }
                        }
                        if (i>0 && i<7){
                            if (j==0){
                                if (board[i-1][j+1]==0){
                                    moves[i-1][j+1]=1;
                                }
                                if (board[i+1][j+1]==0){
                                    moves[i+1][j+1]=1;
                                }
                                if (i>1 && i<6){
                                    if (board[i-2][j+2]==0 && (board[i-1][j+1] == 1 || board[i-1][j+1]==3)){
                                        gamePanel.enemyCell[0]= i-1;
                                        gamePanel.enemyCell[1]=j+1;
                                        moves[i-2][j+2]=2;
                                    }
                                    if (board[i+2][j+2]==0 && (board[i+1][j+1] == 1 || board[i+1][j+1]==3)){
                                        gamePanel.enemyCell[0]= i+1;
                                        gamePanel.enemyCell[1]=j+1;
                                        moves[i+2][j+2]=2;
                                    }
                                }
                            } else if (j==7){
                                if (board[i-1][j-1]==0){
                                    moves[i-1][j-1]=1;
                                }
                                if (board[i+1][j-1]==0){
                                    moves[i+1][j-1]=1;
                                }
                                if (i>1 && i<6){
                                    if (board[i-2][j-2]==0 && (board[i-1][j-1] == 1 || board[i-1][j-1]==3)){
                                        gamePanel.enemyCell[0]= i-1;
                                        gamePanel.enemyCell[1]=j-1;
                                        moves[i-2][j-2]=2;
                                    }
                                    if (board[i+2][j-2]==0 && (board[i+1][j-1] == 1 || board[i+1][j-1]==3)){
                                        gamePanel.enemyCell[0]= i+1;
                                        gamePanel.enemyCell[1]=j-1;
                                        moves[i+2][j-2]=2;
                                    }
                                }
                                if (i<6){

                                }
                            } else {
                                if (board[i-1][j+1]==0){
                                    moves[i-1][j+1]=1;
                                }
                                if (board[i+1][j+1]==0){
                                    moves[i+1][j+1]=1;
                                }
                                if (board[i-1][j-1]==0){
                                    moves[i-1][j-1]=1;
                                }
                                if (board[i+1][j-1]==0){
                                    moves[i+1][j-1]=1;
                                }
                            }
                            if (i==1 && j>1 && j<6){
                                if (board[i+2][j-2]==0 && (board[i+1][j-1] == 1 || board[i+1][j-1]==3)){
                                    gamePanel.enemyCell[0]= i+1;
                                    gamePanel.enemyCell[1]=j-1;
                                    moves[i+2][j-2]=2;
                                }
                                if (board[i+2][j+2]==0 && (board[i+1][j+1] == 1 || board[i+1][j+1]==3)){
                                    gamePanel.enemyCell[0]= i+1;
                                    gamePanel.enemyCell[1]=j+1;
                                    moves[i+2][j+2]=2;
                                }
                            }
                            if (i>2 && i<6 && j>1 && j<6){
                                if (board[i-2][j+2]==0 && (board[i-1][j+1] == 1 || board[i-1][j+1]==3)){
                                    gamePanel.enemyCell[0]= i-1;
                                    gamePanel.enemyCell[1]=j+1;
                                    moves[i-2][j+2]=2;
                                }
                                if (board[i+2][j+2]==0 && (board[i+1][j+1] == 1 || board[i+1][j+1]==3)){
                                    gamePanel.enemyCell[0]= i+1;
                                    gamePanel.enemyCell[1]=j+1;
                                    moves[i+2][j+2]=2;
                                }
                            }
                        }

                        if (i==0){
                            if (j==0){
                                if (board[i+1][j+1]==0){
                                    moves[i+1][j+1]=1;
                                }
                                if (board[i+2][j+2]==0 && (board[i+1][j+1] == 1 || board[i+1][j+1]==3)){
                                    gamePanel.enemyCell[0]= i+1;
                                    gamePanel.enemyCell[1]=j+1;
                                    moves[i+2][j+2]=2;
                                }
                            }
                            if (j==7){
                                if (board[i+1][j-1]==0){
                                    moves[i+1][j-1]=1;
                                }
                                if (board[i+2][j-2]==0 && (board[i+1][j-1] == 1 || board[i+1][j-1]==3)){
                                    gamePanel.enemyCell[0]= i+1;
                                    gamePanel.enemyCell[1]=j-1;
                                    moves[i+2][j-2]=2;
                                }
                            }
                            if (j>1 && j <6){
                                if (board[i+1][j+1]==0){
                                    moves[i+1][j+1]=1;
                                }
                                if (board[i+1][j-1]==0){
                                    moves[i+1][j-1]=1;
                                }
                                if (board[i+2][j-2]==0 && (board[i+1][j-1] == 1 || board[i+1][j-1]==3)){
                                    gamePanel.enemyCell[0]= i+1;
                                    gamePanel.enemyCell[1]=j-1;
                                    moves[i+2][j-2]=2;
                                }
                                if (board[i+2][j+2]==0 && (board[i+1][j+1] == 1 || board[i+1][j+1]==3)){
                                    gamePanel.enemyCell[0]= i+1;
                                    gamePanel.enemyCell[1]=j+1;
                                    moves[i+2][j+2]=2;
                                }
                            }
                            if(j==1){
                                if(board[i+1][j-1]==0){
                                    moves[i+1][j-1]=1;
                                }
                                if(board[i+1][j+1]==0){
                                    moves[i+1][j+1]=1;
                                }
                                if (board[i+2][j+2]==0 && (board[i+1][j+1] == 1 || board[i+1][j+1]==3)){
                                   gamePanel.enemyCell[0]= i+1;
                                   gamePanel.enemyCell[1]=j+1;
                                   moves[i+2][j+2]=2;
                               }
                            }
                        }
                    }
                }
            }
        }
        gamePanel.moves = moves;
        return moves;
    }
}
