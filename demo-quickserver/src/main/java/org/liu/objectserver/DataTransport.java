package org.liu.objectserver;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DataTransport implements Serializable {
    private int id;
    private String name;
    private Date birthday;
    private String callback;
}
