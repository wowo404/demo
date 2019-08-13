package org.liu.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

public class MysqlTest {

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.17.230:3306/manufacture?Unicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false", "mft_user", "3NQPzn$h6^VL");
        Statement statement = connection.createStatement();
        int id = 6531;
        int dataAlleywayId = 1150;
        for (int i = 69; i < 169; i++) {
            DecimalFormat format = new DecimalFormat("000");
            String seq = format.format(i);
            String serialNum = "36475" + seq;
            String sql = "INSERT INTO `gateway_cfg` (`id`,`name`,`serial_num`,`password`,`protocol`,`connect_type`,`server_ip`,`server_port`,`mobile_no`,`sim_num`,`operator`,`IMEI`,`client_ip`,`remark`,`labels`,`gps`,`status`,`login_time`,`heartbeat_time`,`data_time`,`factory_id`,`factory_name`,`prod_line_id`,`product_line`,`state`,`create_uid`,`create_uname`,`create_time`,`modify_uid`,`modify_uname`,`modify_time`,`ext1`,`ext2`,`ext3`,`sys_flag`,`spare_password`,`spare_server_ip`,`spare_server_port`,`install_position`,`insert_type`,`internet_type`) VALUES (" + i + ",'测试网关" + i + "','" + serialNum + "','12345678','','','','','','','','','','','',NULL,2,'2019-05-22 15:38:54',NULL,'2019-05-22 22:27:47',6458,'螺丝厂',NULL,NULL,NULL,6519,'lzs','2019-05-29 17:05:35',NULL,NULL,NULL,NULL,NULL,NULL,'999999998','','','','','',NULL)";
            statement.execute(sql);
            for(int j = 0; j < 4; j++){
                String name = "测试监控点-" + i + "-" + j;
                String code = serialNum + "-" + j;
                int dataAlleywayNum = j + 1;
                String sql1 = "insert into `data_alleyway` (id, cfg_id, content, data_alleyway_num, create_uid, create_uname, create_time, sys_flag, ext1, ext2, ext3) VALUES (" + dataAlleywayId + ", " + i + ", '数据1', " + dataAlleywayNum + ", 1, 'lzs', '2019-06-05 10:01:44', '999999998', NULL, NULL, 'N')";
                statement.execute(sql1);
                String sql2 = "INSERT INTO `eqp_variable_relation` (`id`,`name`,`code`,`status`,`control_type`,`eqp_id`,`eqp_name`,`eqp_type`,`data_source`,`category_id`,`category_name`,`variable_id`,`variable_name`,`gateway_id`,`gateway_serial_num`,`data_alleyway_id`,`data_alleyway_num`,`remark`,`ext1`,`ext2`,`ext3`,`up_interval`,`up_interval_unit`,`history_save`,`history_save_unit`,`trend_save`,`trend_save_unit`,`dept_id`,`dept_name`,`prod_line_id`,`product_line`,`system_id`,`system_name`,`main_id`,`main_name`,`son_eqp_id`,`son_eqp_name`,`create_uid`,`create_uname`,`create_time`,`modify_uid`,`modify_uname`,`modify_time`,`sys_flag`,`warn_level`,`gs_type`,`gs_desc`,`range_begin`,`range_end`,`export_begin`,`export_end`,`precision`,`compensation`) VALUES (" + id + ",'" + name + "','" + code + "',1,1,6466,'工艺系统测试',NULL,1,6433,'振动',6427,'测试监测项'," + i + ",'" + serialNum + "'," + dataAlleywayId + ", " + dataAlleywayNum + ",'test','',NULL,NULL,1,2,4,5,5,5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,6408,'admin','2019-04-30 11:23:15',6509,'lzs','2019-05-21 10:45:32','99999',NULL,1,'gs',1.00,1.00,1.00,1.00,NULL,NULL)";
                statement.execute(sql2);

                id++;
                dataAlleywayId++;
            }
        }
        connection.commit();
        statement.close();
        connection.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        connect();
    }

}
