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

/**
 * 主程序入口
 */
public class Main {
    public static void main(String[] args) {
        // 创建容器
        final JFrame jf = new JFrame();
        final StarField sf = new StarField();
        // 将 StarField 的对象 sf 添加到了面板jf中
        jf.add(sf);
        // 去掉特效和拖拽过渡效果（实现完全全屏 不受其他影响）
        jf.setUndecorated(true);
        // 设置窗口可见性
        jf.setVisible(true);
        // 获取可是窗口的可大小
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        // 这里设置大小（和屏幕一样大）
        jf.setSize(d.width, d.height);
        sf.init();
        sf.start();
        // 设置默认关闭操作栏
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
