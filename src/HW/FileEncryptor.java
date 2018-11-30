package HW;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class FileEncryptor extends Frame {
    JPanel jp1;
    JPanel jp2;
    JLabel jlb1;
    JLabel jlb2;
    JFileChooser jfc;
    JCheckBox jcb;
    JTextField jtf;
    JButton jb1;
    JButton jb2;
    public FileEncryptor() {
        super("加密解密器 v1.0");
        jp1 = new JPanel();
        jp2 = new JPanel();
        jlb1 = new JLabel("请选择加密或解密的文件");
        jlb2 = new JLabel("选择加密或解密算子（0-255）");
        jfc = new JFileChooser();
        jtf = new JTextField(10);
        jcb = new JCheckBox("覆盖源文件");
        jb1 = new JButton("加密/解密");
        jb2 = new JButton("取消˚");

    }
    public void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(jp1, BorderLayout.CENTER);
        this.add(jp2, BorderLayout.SOUTH);

        jp1.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        jp1.setLayout(new FlowLayout(FlowLayout.LEFT));
        jfc.setControlButtonsAreShown(false);
        jp1.add(jlb1);
        jp1.add(jfc);
        jp1.add(jlb2);
        jp1.add(jtf);
        jp1.add(jcb);

        jp2.add(jb1);
        jp2.add(jb2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(520, 480);
        this.setResizable(false);
        setEvent();
    }

    private void setDefaultCloseOperation(int exitOnClose) {
    }

    public void setEvent(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File f = jfc.getSelectedFile();
                int code = Integer.parseInt(jtf.getText());
                new Encrypter(code,f,jcb.isSelected()).encode();
                final JDialog jd = new JDialog(FileEncryptor.this,"加密成功");
                jd.add(new JLabel("文件加密成功"),BorderLayout.NORTH);
                JButton jbok;
                jd.add(jbok = new JButton("确定"),BorderLayout.SOUTH);
                jbok.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        jd.dispose();
                    }
                });
                jd.setLocationRelativeTo(FileEncryptor.this);
                jd.setSize(300, 200);
                jd.setVisible(true);
            }
        });
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    class Encrypter {
        int code;
        boolean rewrite;
        File sourceFile;

        FileInputStream fin;
        FileOutputStream fout;
        String sourceFileName;
        String objFileName;

        public Encrypter(int code2, File f, boolean selected) {
            code = code2;
            sourceFile = f;
            rewrite = selected;
            sourceFileName = f.getName();
            objFileName = "Encrypt_" + sourceFileName;
        }

        public void encode() {
            if(rewrite){
                RandomAccessFile fra=null;
                try {
                    fra = new RandomAccessFile(sourceFile, "rw");
                    int temp;
                    while((temp = fra.read())!=-1){
                        fra.seek(fra.getFilePointer()-1);
                        fra.write(temp^code);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally{
                    if(fra!=null){
                        try {
                            fra.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }else{
                FileInputStream fis = null;
                FileOutputStream fos = null;
                try {
                    fis = new FileInputStream(sourceFile);
                    fos = new FileOutputStream(objFileName);
                    while(fis.available()>0){
                        fos.write(fis.read()^code);
                    }
                    fos.flush();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }finally{
                    if(fis!=null){
                        try {
                            fis.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    if(fos!=null){
                        try {
                            fos.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
    }
    public static void main(String[] args) {
        FileEncryptor f = new FileEncryptor();
        f.init();
    }
}