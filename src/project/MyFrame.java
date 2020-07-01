package project;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InterruptedIOException;

public class MyFrame extends JFrame {
    JTextField name;
    ButtonGroup group;
    ControlPanel controlPanel;;
    GamePanel gamePanel;
    JPanel parentPanel;
    WelcomePanel welcomePanel;
    MyMenuBar menubar;
    public MyFrame(String title){
        super(title);
        setLayout(new BorderLayout());
        menubar = new MyMenuBar(this);
        add(menubar,BorderLayout.NORTH);
        JTextField text = new JTextField("hello");
        text.setEditable(false);

        welcomePanel = new WelcomePanel(this);
        parentPanel = new JPanel();

        add(welcomePanel,BorderLayout.CENTER);
        parentPanel.setLayout(new GridLayout(1,2));
        System.out.println("MyFrame");
    }
}
