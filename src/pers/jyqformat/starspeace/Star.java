/**
 * 这是一个Java实训作业
 *
 * @author 蒋应强
 * @Date 24.12 2018
 * @exception Exception
 * @version 1.3
 */

package pers.jyqformat.starspeace;

import java.awt.*;

public class Star {
    int H, V;
    int x, y, z;
    int type;

    /**
     * Star类
     * @param width 获得当前窗口分辨率的宽度（单位：px）
     * @param height 获得当前窗口分辨率的高度 （单位:px）
     * @param depth 获得当前窗口分辨率的景深 便于看出有层次感
     * @param type 获得当前的窗口的类型
     */
    Star(int width, int height, int depth, int type) {
        this.type = type;
        H = width / 2;
        V = height / 2;
        x = (int) (Math.random() * width) - H;
        y = (int) (Math.random() * height) - V;
        if ((x == 0) && (y == 0))
            x = 10;
        z = (int) (Math.random() * depth);
    }

    /**
     * Draw 方法
     * @param g 传入Draw方法的参数 该参数代表 绘图的画布
     * @param rot 传入星星进行坐标运算的数量
     */
    public void Draw(Graphics g, double rot) {
        double X, Y;
        int h, v, hh, vv;
        int d;
        z -= 2;
        if (z < -63)
            z = 100;
        hh = (x * 64) / (64 + z);
        vv = (y * 64) / (64 + z);
        X = (hh * Math.cos(rot)) - (vv * Math.sin(rot));
        Y = (hh * Math.sin(rot)) + (vv * Math.cos(rot));
        h = (int) X + H;
        v = (int) Y + V;
        if ((h < 0) || (h > (2 * H)))
            z = 100;
        if ((v < 0) || (v > (2 * H)))
            z = 100;
        GrayMe(g);
        if (type == 0) {
            d = (100 - z) / 50;
            if (d == 0)
                d = 1;
            g.fillRect(h, v, d, d);
        } else {
            d = (100 - z) / 20;
            g.drawLine(h - d, v, h + d, v);
            g.drawLine(h, v - d, h, v + d);
            if (z < 50) {
                d /= 2;
                g.drawLine(h - d, v - d, h + d, v + d);
                g.drawLine(h + d, v - d, h - d, v + d);
            }
        }
    }

    /**
     *
     * @param g 在画布上面设置星星不同的颜色
     *          形成明暗对比
     */
    public void GrayMe(Graphics g) {
        if (z > 50) {
            g.setColor(Color.gray);
        } else if (z > 25) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(Color.white);
        }
    }
}
