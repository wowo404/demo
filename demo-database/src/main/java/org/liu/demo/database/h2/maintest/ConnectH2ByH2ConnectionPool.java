package org.liu.demo.database.h2.maintest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.jdbcx.JdbcConnectionPool;

public class ConnectH2ByH2ConnectionPool {

    public static void main(String[] args) {
        JdbcConnectionPool cp = JdbcConnectionPool.create("jdbc:h2:tcp://localhost/~/test", "sa", "");
        try {
            Connection conn = cp.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from test");
            while(rs.next()){
                System.out.println(rs.getInt("ID") + "----" + rs.getString("NAME"));
            }
            rs.close();
            stmt.close();
            conn.close();
            cp.dispose();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
