package org.liu.mock;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Test;
import org.liu.domain.DBUtility;

public class TestMockConnection {
    
    @Test
    public void testGetConnectionByMock(){
        IMocksControl control = EasyMock.createControl();
        DBUtility mockDbUtility = control.createMock(DBUtility.class);
        Connection mockConn = control.createMock(Connection.class);
        Statement mockStmt = control.createMock(Statement.class);
        ResultSet mockRs = control.createMock(ResultSet.class);
        try {
            expect(mockDbUtility.getConnection()).andStubReturn(mockConn);
            expect(mockConn.createStatement()).andStubReturn(mockStmt);
            expect(mockStmt.executeQuery("select * from sales_order_table")).andReturn(mockRs);
            
            mockRs.next();// 设定调用next方法的返回值和次数
            expectLastCall().andReturn(true).times(3);
            expectLastCall().andReturn(false).times(1);
            
            mockRs.getString(1);// 调用getString(1)方法的返回值和次数
            expectLastCall().andReturn("gun").times(1);
            expectLastCall().andReturn("rose").times(1);
            expectLastCall().andReturn("store").times(1);
            
            mockRs.getString(2);// 调用getString(2)方法的返回值和次数
            expectLastCall().andReturn("China").times(1);
            expectLastCall().andReturn("America").times(1);
            expectLastCall().andReturn("Europe").times(1);
            
            mockRs.getDouble(3);// 调用getString(3)方法的返回值和次数
            expectLastCall().andReturn(621.12).times(1);
            expectLastCall().andReturn(1530.04).times(1);
            expectLastCall().andReturn(98456.51).times(1);
            
            control.replay();
            
            Connection conn = mockDbUtility.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from sales_order_table");
            
            int i=0;
            String[] priceLevels = {"A","B","C"};
            while (rs.next()) {
                SaleOrder order = new SaleOrderImpl();
                order.loadDataFromDb(rs);
                assertEquals(order.getLevel(),priceLevels[i]);
                i++;
            }
            
            control.verify();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
