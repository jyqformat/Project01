package HW;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

//import com.sun.awt.AWTUtilities;

//抽奖器
public class Lottery extends JFrame {
    int id = 0;//data集合当中的数据个数
    int n = 0;//控制切换的次数
    int step = 10;
    // 初始用户名及电话号码
    String name = "张三";
    String phone = "1234556";
    // 抽奖的次数
    int count = 5;
    boolean isStart = false;
    static List<String> data = new ArrayList<String>();
    List<String> result = new ArrayList<String>();
    Lottery() {
        read();
        this.setBounds(150, 100, 868, 552);
        // 设置无标题
        this.setUndecorated(true);
        // 设置窗体透明
        //AWTUtilities.setWindowOpaque(this, false);
        // 给窗体添加图标
        this.setIconImage(new ImageIcon("./beij/icon.jpg").getImage());
        // 创建自定义面板对象并实例化
        LotteryPanel panel = new LotteryPanel();
        // 将面板添加到窗体
        this.add(panel);
        // 窗体显示
        this.setVisible(true);
        this.addMouseListener(panel);

    }

    // 自定义面板，实现接口，添加鼠标监听
    class LotteryPanel extends JPanel implements MouseListener,Runnable{
        // 姓名提示信息的Y位置
        int y = 222;
        boolean isMove = true;
        public LotteryPanel(){
            Thread t = new Thread(this);
//            t.start();
        }
        // 开起画笔
        public void paint(Graphics g) {
            super.paint(g);
            // 画背景图片
            g.drawImage(new ImageIcon("./beij/back2.jpg").getImage(), 0, 0, this);
            // 画关闭图片
            g.drawImage(new ImageIcon("./beij/close.png").getImage(), 840, 12, this);
            // 画最小图片
            g.drawImage(new ImageIcon("./beij/min.png").getImage(), 795, 0, this);
            // 如果isStart为true
            if (isStart) {
                // 画开始抽奖图片
                g.drawImage(new ImageIcon("./beij/start.png").getImage(), 204, 311,
                        this);
            } else {
                // 画暂停图片
                g.drawImage(new ImageIcon("./beij/zanting1.png").getImage(), 204, 311,
                        this);
            }
            // 画重新抽奖的照片
            g.drawImage(new ImageIcon("./beij/again.png").getImage(), 725, 426, this);
            // 设置字体
            g.setFont(new Font("微软雅黑", Font.BOLD, 30));
            // 设置颜色
            g.setColor(new Color(156, 11, 3));
            // 画姓名
            g.drawString(name, 105, y);
            // 画电话
            g.drawString(phone, 283, y);
            // 画统计数
            g.drawString(count + "", 310, 410);
            // 设置字段用于结果显示
            g.setFont(new Font("微软雅黑", Font.BOLD, 20));
            for(int i=0;i<result.size();i++){
                g.drawString(result.get(i), 580,130+30*i);
            }

        }

        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            Thread t = new Thread(this);
            if(x>204&&x<204+200&&y>311&&y<311+55){
                //开始抽奖
                if(count==0){
                    return;
                }else{
                    if(isStart){
                        //对应的图片是：开始抽奖
                        isMove = true;
                        n=0;
                        step = 500;
                        isStart = false;
                        repaint();
                        t.start();

                    }else{
                        //对应的图片是：暂停抽奖
                        count--;
                        String str = data.get(n%id);
                        result.add(str);
                        data.remove(n%id);
                        id--;
                        isMove = false;
                        isStart = true;
                        y = 230;
                        repaint();
                    }

                }

            }else if(x>840&&x<840+17&&y>12&&y<12+18){
                //关闭程序
                System.exit(0);
            }else if(x>725&&x<725+116&&y>426&&y<426+42){
                //重新开始
                data.clear();
                result.clear();
                isStart = true;
                isMove=false;
                n = 0;
                id = 0;
                count = 5;
                read();
                repaint();

            }else if(x>795&&x<795+35&&y>0&&y<35){
                //最小化
                Lottery.this.setState(JFrame.ICONIFIED);
            }


        }

        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        public void run() {
            while(true){
                if(isMove){
                    //实现滚动
                    String[] str = data.get(n%id).split(":");
                    name = str[0];
                    phone = str[1];
                    //repaint();
                    while(y<245){
                        y+=5;
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        repaint();
                    }
                    y=222;
                    n++;
                    this.repaint();
                }
            }

        }

    }
    public void read(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./beij/lottery.txt"))) ;
            String str = "";
            while((str = br.readLine())!=null){
                data.add(str);
                id++;
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Lottery l = new Lottery();
		for(int i = 0;i<data.size();i++){
			System.out.println(data.get(i));
		}
    }
}
