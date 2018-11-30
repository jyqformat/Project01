package HW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BallFrame extends JFrame {
    public static final int WIDTH = 450;
    public static final int HIGHT = 450;
    private BallPanel panel;
    private JButton start, close;
    private Color[] colors = {Color.blue,Color.orange,Color.red,Color.yellow,Color.pink,Color.green};
    public BallFrame(){
        panel = new BallPanel();
        start=new JButton("Start");
        close=new JButton("Close");
        this.add(panel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(start);
        buttonPanel.add(close);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setSize(WIDTH, HIGHT);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addEventHandle();

    }
    public void addEventHandle(){
        start.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                addBall();
            }
        });
        close.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    public void addBall(){
        Ball b = new Ball(panel,colors[(int)(Math.random()*colors.length)]);
        panel.addBall(b);

        BallThread t = new BallThread(b);
        t.start();

    }
    public static void main(String[] args) {
        BallFrame f = new BallFrame();

    }
}
