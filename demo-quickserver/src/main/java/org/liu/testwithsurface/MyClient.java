package org.liu.testwithsurface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author huangzjb cblue2013@126.com
 * @version 1.0
 * @Description: 客户端
 * @Company Digital China
 * @date 2014-6-4 下午02:18:24
 */
public class MyClient extends JFrame implements ActionListener {

    JTextArea jta = null;
    JTextField jtf = null;
    JButton jb = null;
    JPanel jp1 = null;
    JScrollPane jsp = null;
    PrintWriter pw = null;

    public static void main(String[] args) {
        new MyClient();
    }

    public MyClient() {
        jta = new JTextArea();
        jsp = new JScrollPane(jta);
        jtf = new JTextField(10);
        // 注册回车事件
        jtf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });

        jb = new JButton("发送");
        jb.addActionListener(this);// 注册一个监听事件

        jp1 = new JPanel();
        jp1.add(jtf);
        jp1.add(jb);
        this.add(jsp, BorderLayout.CENTER);
        this.add(jp1, BorderLayout.SOUTH);
        this.setTitle("chat client");
        this.setSize(300, 200);
        this.setVisible(true);

        // 接收从服务端发送过来的消息
        try {
            Socket s = new Socket("127.0.0.1", 2222);
            BufferedReader in = new BufferedReader(new InputStreamReader(s
                    .getInputStream()));
            pw = new PrintWriter(s.getOutputStream(), true);
            while (true) {
                String info = in.readLine();
                String str = null;
                if (jta.getText() == null || "".equals(jta.getText())) {
                    str = "Server:" + info;
                } else {
                    str = jta.getText() + "\r\nServer:" + info;
                }
                jta.setText(str);
                jta.setCaretPosition(jta.getDocument().getLength());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 把信息给服务器端
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb) {
            sendMessage();

        }
    }


    /**
     * @throws
     * @Description: 发送消息给服务器端
     */
    public void sendMessage() {
        String info = jtf.getText();
        pw.println(info);
        pw.flush();
        jtf.setText("");
        if (jta.getText() == null || "".equals(jta.getText())) {
            jta.append("Client:" + info);
        } else {
            jta.append("\r\nClient:" + info);
        }
        jta.setCaretPosition(jta.getDocument().getLength());// 设置滚动条自动滚动
    }

}
