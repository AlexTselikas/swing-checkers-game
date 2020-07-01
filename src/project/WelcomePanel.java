package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePanel extends JPanel {
    MyFrame parentFrame;
    public WelcomePanel(MyFrame parentFrame){
        this.parentFrame = parentFrame;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        JButton btn = new JButton("Start");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        WelcomePanel ctx = this;
        btn.addActionListener(new MyActionListener(parentFrame,"welcomepanelstart"));
        parentFrame.name = new JTextField();
        parentFrame.name.setMaximumSize(new Dimension(200,30));
        parentFrame.name.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel nameLabel = new JLabel("Player Name");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel choosePlayerLabel = new JLabel("Choose Player");

        choosePlayerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JRadioButton whitePlayer = new JRadioButton("White Player");
        whitePlayer.setActionCommand("white");
        JRadioButton redPlayer = new JRadioButton("Red Player");
        redPlayer.setActionCommand("red");
        whitePlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
        redPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);

        parentFrame.group = new ButtonGroup();
        parentFrame.group.add(whitePlayer);
        parentFrame.group.add(redPlayer);
        add(nameLabel);
        add(parentFrame.name);
        add(choosePlayerLabel);
        add(whitePlayer);
        add(redPlayer);
        add(btn);

    }
}
