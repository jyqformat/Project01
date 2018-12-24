/**
 * 这是一个Java实训作业
 *
 * @author 蒋应强
 * @Date 24.12 2018
 * @exception Exception
 * @version 1.3
 */
package pers.jyqformat.starspeace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 对类变量进行定义
 */
public class StarField extends java.applet.Applet implements Runnable {
    // 变量定义
    int Width = 200, Height = 200;
    Thread me = null;
    boolean suspend = false;
    Image im;
    Graphics offscreen;
    double rot, dx, ddx;
    int speed, stars, type;
    double defddx, max;
    Star pol[];

    /**
     * 初始化函数
     */
    public void init() {
        rot = 0;
        dx = 0;
        ddx = 0;
        // 获得可视窗口的大小 对 Width和Height 属性进行初始化
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        Width = d.width;
        Height = d.height;
        // 添加鼠标监听事件 对鼠标的点击事件进行监听 来判断 对画布的旋转（以达到穿越的效果）或是退出屏幕保护程序
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    ddx = (ddx == 0) ? defddx : 0;
                }
                if (e.getClickCount() == 2) {
                    System.exit(0);
                }
            }
        });
        // 规定初始数值
        speed = 20;
        stars = 30;
        type = 0;
        rot = 0.1;
        max = 0.1;
        defddx = 0.005;
        // 创建绘图画布
        try {
            im = createImage(Width, Height);
            offscreen = im.getGraphics();
        } catch (Exception e) {
            offscreen = null;
        }
        pol = new Star[stars];
        for (int i = 0; i < stars; i++)
            pol[i] = new Star(Width, Height, 100, type);
    }

    /**
     *
     * @param g 进行星星的绘制
     */
    public void paint(Graphics g) {
        if (offscreen != null) {
            paintMe(offscreen);
            g.drawImage(im, 0, 0, this);
        } else {
            paintMe(g);
        }
    }

    // 对星星进行着色
    public void paintMe(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Width, Height);

        for (int i = 0; i < stars; i++)
            pol[i].Draw(g, rot);
    }

    // 启动多个线程
    public void start() {
        if (me == null) {
            me = new Thread(this);
            me.start();
        }
    }

    // 停止线程
    public void stop() {
        if (me != null) {
            me.stop();
            me = null;
        }
    }

    // 运行
    public void run() {
        while (me != null) {
            rot += dx;
            dx += ddx;
            if (dx > max)
                ddx = -defddx;
            if (dx < -max)
                ddx = defddx;
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
            }
            repaint();
        }
    }

    // 屏幕重新绘制
    public void update(Graphics g) {
        paint(g);
    }

}
