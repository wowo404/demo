package org.liu.database.searchandsocket;

import org.apache.commons.lang3.RandomUtils;

import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class SearchAndSendTCPTest {

    private Map<String, String> prepareData = new HashMap<>();

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, InterruptedException {
        SearchAndSendTCPTest test = new SearchAndSendTCPTest();
//        test.run();
        test.send("SYAL 1163647588612345678\r\n", "SYAD 1161510101012346541\r\n");
    }

    private void run() throws SQLException, ClassNotFoundException {
        prepareData();

        Set<Map.Entry<String, String>> entries = prepareData.entrySet();
        CyclicBarrier cb = new CyclicBarrier(prepareData.size());
        for (Map.Entry<String, String> entry : entries) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        cb.await();
                        send(entry.getKey(), entry.getValue());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    private void prepareData() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.17.230:3306/manufacture?Unicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false", "mft_user", "3NQPzn$h6^VL");
        Statement statement = connection.createStatement();
        String sql = "select serial_num,password from gateway_cfg where id > 68";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            String serial_num = resultSet.getString("serial_num");
            String password = resultSet.getString("password");
            String account = "SYAL 116" + serial_num + password + "\r\n";
            String sql2 = "select count(*) as count from eqp_variable_relation where gateway_serial_num = '" + serial_num + "'";
            ResultSet resultSet1 = connection.createStatement().executeQuery(sql2);
            while (resultSet1.next()) {
                int count = resultSet1.getInt("count");
                String data = "SYAD 116";
                for (int i = 0; i < count; i++) {
                    data += RandomUtils.nextInt(1000, 9999);
                }
                data += "\r\n";
                prepareData.put(account, data);
            }
        }
        statement.close();
        connection.close();
    }

    private void send(String account, String data) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 28000);

        new Thread(new PrintThread(socket)).start();

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

        //以CRLF结尾，表示一条消息发送完了
        //登录
        pw.write(account);
        pw.flush();

        //心跳
        //SYAH 1011
        //数据类型（4个字节）-空格（1个字节）-数据版本（1个字节）-数据长度（2个字节，小于10补0）-数据
        //pw.write("SYAH 1011\r\n");
        //pw.flush();

        //数据
        String msg = data;

        pw.write(msg);
        pw.flush();
    }

    public class PrintThread implements Runnable {
        private Socket socket;

        public PrintThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            while (true) {
                if (socket.isClosed()) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    br.lines().forEach(msg -> {
                        try {
                            if (msg.contains("Auth failed")) {
                                socket.close();
                            }
                            if (msg.contains("-ERR Timeout")) {
                                socket.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println(msg);
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
