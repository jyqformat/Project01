package HW;

import javax.swing.*;
import java.awt.*;

public class MeteorFrame extends JFrame {
    double width;
    double height;
    public MeteorFrame(){
        MeteorPanel m = new MeteorPanel();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screensize = tk.getScreenSize();
        width = screensize.getWidth();
        height = screensize.getHeight();
        this.setBounds(0, 0, (int) width, (int) height);
        this.setIconImage(new ImageIcon("image/bar.png").getImage());
        this.add(m);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Thread t = new Thread(m);
        t.start();

    }
    class MeteorPanel extends JPanel implements Runnable{
        int count = 10;
        int[] xx = new int[count];
        int[] yy = new int[count];


        public void paint(Graphics g){
            super.paint(g);
            g.drawImage(new ImageIcon("image/beijing.jpg").getImage(), 0, 0, (int)width, (int)height, this);
            g.drawImage(new ImageIcon("image/bar.png").getImage(), 500, 50, this);
            for(int i= 0;i<count;i++){
                g.drawImage(new ImageIcon("image/bar.png").getImage(),xx[i],yy[i],this);
            }

        }


        public void run() {
            while(true){
                for(int i=0;i<count;i++){
                    xx[i] = (int)(Math.random()*(width-200))+100;
                    yy[i] = (int)(Math.random()*100);
                }
                for(int j = 0;j<100;j++){
                    for(int a = 0;a<count;a++){
                        xx[a]-=10;
                        yy[a]+=10;
                    }
                    repaint();
                    try {
                        Thread.sleep(80);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                count =(int)(Math.random()*10);
                xx = new int[count];
                yy = new int[count];
                int time = (int)(Math.random()*200)+60;
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }


    }
    public static void main(String[] args) {
        MeteorFrame m = new MeteorFrame();


    }
}