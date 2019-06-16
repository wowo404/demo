package org.liu.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public interface DBUtility {
    
    public abstract Connection getConnection();
    
    public abstract void closeDbResource(Connection conn);
    
    public abstract void closeDbResource(Statement stmt);
    
    public abstract void closeDbResource(ResultSet rs);

}
