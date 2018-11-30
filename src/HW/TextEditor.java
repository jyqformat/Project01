package HW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextEditor extends JFrame implements ActionListener {
    JPanel jp;
    JTextArea jta;
    JScrollPane jsp;
    JMenuBar jmb;
    JMenu file, edit, view;
    File currentFile = null;
    boolean wasSaved = true;

    public TextEditor() {
        super("My Text Editor v1.0");
        jp = new JPanel();
        jmb = new JMenuBar();
        jta = new JTextArea(50, 50);
        jta.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent arg0) {
                wasSaved = false;
            }

            public void insertUpdate(DocumentEvent arg0) {
                wasSaved = false;
            }

            public void removeUpdate(DocumentEvent arg0) {
                wasSaved = false;
            }

        });

        jsp = new JScrollPane(jta);
        file = new JMenu("File");
        JMenuItem item;
        file.add(item = new JMenuItem("New"));
        item.addActionListener(this);
        file.add(item = new JMenuItem("Open..."));
        item.addActionListener(this);
        file.addSeparator();
        file.add(item = new JMenuItem("Save"));
        item.addActionListener(this);
        file.add(item = new JMenuItem("Save As..."));
        item.addActionListener(this);
        file.addSeparator();
        file.add(item = new JMenuItem("Quit"));
        item.addActionListener(this);

        edit = new JMenu("Edit");
        edit.add(item = new JMenuItem("Find..."));
        item.addActionListener(this);
        edit.add(item = new JMenuItem("Replace..."));
        item.addActionListener(this);

        view = new JMenu("View");
        view.add(item = new JMenuItem("Font..."));
        item.addActionListener(this);
        view.add(item = new JMenuItem("Color..."));
        item.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Open...")) {
            // open...菜单处理
            if (askSave() == 0) {
                return;
            }
            JFileChooser jfc = new JFileChooser();
            jfc.showOpenDialog(this);
            File f = jfc.getSelectedFile();
            if (f != null) {
                openFile(f);
                currentFile = f;
            }
            return;
        }
        if (command.equals("Color...")) {
            // Color 菜单处理
            Color c = JColorChooser.showDialog(this, "请选择文字颜色", Color.BLACK);
            jta.setForeground(c);
            return;
        }
        if (command.equals("Quit")) {// Quit
            if (askSave() != 0) {
                return;
            }else {
                System.exit(0);
            }
        }
        if (command.equals("Save As...")) {
            JFileChooser jfc = new JFileChooser();
            jfc.showSaveDialog(this);
            File f = jfc.getSelectedFile();
            if (f != null) {
                saveFile(f);
                currentFile = f;
            }
            return;
        }
        if (command.equals("Save")) {
            if (!wasSaved) {
                if (currentFile == null) {
                    JFileChooser jfc = new JFileChooser();
                    jfc.showSaveDialog(this);
                    File f = jfc.getSelectedFile();
                    if (f != null) {
                        saveFile(f);
                        currentFile = f;
                    }
                } else
                    saveFile(currentFile);
            }
            return;
        }
    }

    private void saveFile(File f) {
        // TODO Auto-generated method stub

    }

    private void openFile(File f) {
        // TODO Auto-generated method stub

    }

    private int askSave() {
        // TODO Auto-generated method stub
        return 0;
    }

    public void init() {
        jmb.add(file);
        jmb.add(edit);
        jmb.add(view);
        this.setJMenuBar(jmb);
        this.add(jsp, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                if (askSave() != 0)
                    return;
                else
                    System.exit(0);
            }

            private int askSave() {
                // TODO Auto-generated method stub
                return 0;
            }
        });
        setSize(800, 600);
        jta.setFont(new Font("宋体", Font.PLAIN, 18));
        setVisible(true);
    }
    public static void main (String [] arge){
        TextEditor textEditor = new TextEditor();
        textEditor.init();

    }
}
