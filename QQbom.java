package test1;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class QQbom extends JFrame {


    public void GUI(){
        String [] info =null;
        setTitle("MESSAGE BOMB");
        setSize(500,250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container cr =getContentPane();
        setLayout(new GridLayout(4,1,0,5));
        JPanel jp1 = new JPanel(new GridLayout(1, 2,5,0));
        JPanel jp2 = new JPanel(new GridLayout(1, 2));
        JPanel jp3 = new JPanel(new GridLayout(1, 2));

        JLabel label1 = new JLabel("输入QQ：");
        label1.setFont(new Font("宋体",Font.BOLD,20));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField text1 =new JTextField();
        text1.setFont(new Font("宋体",Font.BOLD,20));

        JLabel label2 = new JLabel("输入发送信息：");
        label2.setFont(new Font("宋体",Font.BOLD,20));
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField text2 =new JTextField();
        text2.setFont(new Font("宋体",Font.BOLD,20));



        JLabel label3 = new JLabel("输入发送的次数：");
        label3.setFont(new Font("宋体",Font.BOLD,20));
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField text3 =new JTextField();
        text3.setFont(new Font("宋体",Font.BOLD,20));



        jp1.add(label1);
        jp1.add(text1);

        jp2.add(label2);
        jp2.add(text2);

        jp3.add(label3);
        jp3.add(text3);
        JButton jb=new JButton("开始");

        cr.add(jp1);
        cr.add(jp2);
        cr.add(jp3);
        cr.add(jb);

        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = text1.getText();
                String sentence = text2.getText();
                int numebr =Integer.valueOf(text3.getText());
                try {
                    start(id, sentence, numebr);
                } catch (AWTException ex) {
                    ex.printStackTrace();
                }
            }
        });

        setVisible(true);

    }




    public void start(String id, String sentence,int number) throws AWTException {


        //以下三行为将上述字符串放到剪切板内，相当于做了一次复制操作


        Robot robot = new Robot();//创建Robot对象

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_Z);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_Z);

        Clipboard clip1 = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText1 = new StringSelection(id);
        clip1.setContents(tText1, null);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_CONTROL);//释放ctrl按键，像ctrl，退格键，删除键这样的功能性按键，在按下后一定要释放，不然会出问题。crtl如果按住没有释放，在按其他字母按键是，敲出来的回事ctrl的快捷键。
        robot.keyRelease(KeyEvent.VK_V);

        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);//回车
        robot.keyRelease(KeyEvent.VK_ENTER);


        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(sentence);
        clip.setContents(tText, null);


        for (int i = 1; i <= number; i++) {//循环十次，当然，如果爱得深，你死循环也没问题

            //以下两行按下了ctrl+v，完成粘贴功能
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);

            robot.keyRelease(KeyEvent.VK_CONTROL);//释放ctrl按键，像ctrl，退格键，删除键这样的功能性按键，在按下后一定要释放，不然会出问题。crtl如果按住没有释放，在按其他字母按键是，敲出来的回事ctrl的快捷键。
            robot.keyRelease(KeyEvent.VK_V);//释放ctrl按键，像ctrl，退格键，删除键这样的功能性按键，在按下后一定要释放，不然会出问题。crtl如果按住没有释放，在按其他字母按键是，敲出来的回事ctrl的快捷键。

            robot.delay(1000);//延迟一秒再发送，不然会一次性全发布出去，因为电脑的处理速度很快，每次粘贴发送的速度几乎是一瞬间，所以给人的感觉就是一次性发送了全部。这个时间可以自己改，想几秒发送一条都可以
            robot.keyPress(KeyEvent.VK_ENTER);//回车
            robot.keyRelease(KeyEvent.VK_ENTER);

        }
    }
}

