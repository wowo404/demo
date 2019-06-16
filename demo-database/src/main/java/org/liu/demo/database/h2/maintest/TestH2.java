package org.liu.demo.database.h2.maintest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestH2 {

    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/new", "sa", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from test");
            while(rs.next()){
                System.out.println(rs.getInt("ID") + "----" + rs.getString("NAME"));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
