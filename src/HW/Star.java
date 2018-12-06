package HW;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Star extends Frame {
    public static  void  main(String[] arge){
        Frame w = new Frame();
        MyPanel p = new MyPanel();
        w.add(p);
        w.setSize(1366,768);
        w.setBackground(Color.black);
        w.show();
        w.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(1);
            }
        });
    }
}
class MyPanel extends Panel{
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.white);
        for (int i = 0;i<300;i++){
            g.drawString("ght", (int) (Math.random() * 1366), (int) (Math.random() * 768));
        }
    }

}