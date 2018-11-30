package HW;

import HW.BallPanel;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball {
    private BallPanel canvas;// 画布，显示小球的面板
    private Color color;// 小球的颜色
    private static final int XSIZE = 30;// 小球的X直径
    private static final int YSIZE = 30;// 小球的Y直径
    private int x = 0; // 小球所处的横坐标
    private int y = 0; // 小球所处的纵坐标
    private int dx = 1; // 小球每次移动的x步长
    private int dy = 1; // 小球每次移动的y步长
    public static int CURRENT_NUM = 1;// 当前小球的编号
    private int number;// 下一个小球的编号

    public Ball(BallPanel panel, Color color2) {
        this.canvas = panel;
        this.color = color2;
        this.number = CURRENT_NUM++;

    }

    public void draw(Graphics2D g2) {
        if (color != null){
            g2.setColor(color);
        }

        g2.fill(new Ellipse2D.Double(x, y, XSIZE, YSIZE));
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("", Font.BOLD, 16));
        g2.drawString(number + "", x + XSIZE / 2 - 5, y + YSIZE / 2 + 5);
    }

    public void move() {
        x += dx;
        y += dy;
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + XSIZE >= canvas.getWidth()) {
            x = canvas.getWidth() - XSIZE;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + YSIZE >= canvas.getHeight()) {
            y = canvas.getHeight() - YSIZE;
            dy = -dy;
        }
        canvas.repaint();
    }

}
