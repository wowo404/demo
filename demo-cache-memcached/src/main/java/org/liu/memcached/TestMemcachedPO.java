package org.liu.memcached;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TestMemcachedPO implements Serializable {
    private int id;
    private String name;
    private Date birthday;
}
