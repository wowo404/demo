package org.liu.commonspool2;

import lombok.Data;

@Data
public class MyConnection {
    private String host;
    private int port;
    private String username;
    private String password;
}
