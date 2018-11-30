package HW;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BallPanel extends JPanel {
    // 用一个ArrayList来保存所有的小球
    private ArrayList balls;
    public BallPanel(){
        balls = new ArrayList();
    }

    public void addBall(Ball b) {
        balls.add(b);

    }

    /**
     * 重写父类的paintComponent方法，绘出所有小球。
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < balls.size(); i++) {
            Ball b = (Ball) balls.get(i);
            b.draw(g2);

        }
    }
}