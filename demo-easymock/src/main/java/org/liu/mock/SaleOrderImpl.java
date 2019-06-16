package org.liu.mock;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleOrderImpl implements SaleOrder {

    private String orderNumber;
    private String region;
    private Double totalPrice;
    
    @Override
    public void loadDataFromDb(ResultSet rs) throws SQLException {
        orderNumber = rs.getString(1);
        region = rs.getString(2);
        totalPrice = rs.getDouble(3);
    }
    
    @Override
    public String getLevel(){
        if(totalPrice < 1000){
            return "A";
        } else if(totalPrice >= 1000 && totalPrice < 10000){
            return "B";
        } else {
            return "C";
        }
    }

}
