package project;

import javax.print.attribute.standard.JobMediaSheetsCompleted;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Date;
import java.util.Map;

public class ControlPanel extends JPanel {
    JLabel currPlayer;
    JLabel historyLabel;
    JTextArea historyTextArea;
    JScrollPane scrollPane;
    InitializeGame initGame;
    Timer timer;
    ActionListener al;
    long x;
    public ControlPanel(InitializeGame initGame){
        this.initGame = initGame;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JButton btn = new JButton("New Game");
        JLabel timerText = new JLabel("Timer");
        timerText.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(timerText);

        JLabel timerLabel = new JLabel("00 : 00");
        timerLabel.setFont(new Font("Verdana",Font.PLAIN,20));
        final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("mm : ss");
        x = 0;
        al = new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                timerLabel.setText(sdf.format(new Date(x)));
                x += 1000;}};

        timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        timer = new Timer(1000,al);
        timer.start();
        add(timerLabel);
        add(Box.createRigidArea(new Dimension(10,50)));
        historyLabel = new JLabel("History");
        //Kanoume ta grammata megalytera
        historyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(historyLabel);

        historyTextArea = new JTextArea(50,30);
        scrollPane = new JScrollPane(historyTextArea);
        historyTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        historyTextArea.setEditable(false);
        historyTextArea.append("Game Started!\n");
        add(scrollPane);
        add(Box.createRigidArea(new Dimension(10,50)));
        JLabel currPlayerLabel = new JLabel("Current Player");
        currPlayerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(currPlayerLabel);
        currPlayer = new JLabel(initGame.playerName);
        Font font =currPlayer.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE,TextAttribute.UNDERLINE_ON);
        currPlayer.setFont(font.deriveFont(attributes));
        currPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(currPlayer);
        add(Box.createRigidArea(new Dimension(10,100)));

    }
    public void resetControlPanel(){
        //Reset time
        timer.stop();
        Timer newTimer = new Timer(1000,al);
        timer = newTimer;
        x=0;
        timer.start();

        //Reset History
        historyTextArea.setText("Game Started!\n");
        currPlayer.setText(initGame.playerName);

    }

}
