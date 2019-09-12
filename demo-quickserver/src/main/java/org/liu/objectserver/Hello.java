package org.liu.objectserver;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
public class Hello implements Serializable {
    private String id = UUID.randomUUID().toString();
    private Date serverTime;
    private String serverName;
}
