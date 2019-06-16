package org.liu.mock;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SaleOrder {
    
    void loadDataFromDb(ResultSet rs) throws SQLException;

    String getLevel();

}
