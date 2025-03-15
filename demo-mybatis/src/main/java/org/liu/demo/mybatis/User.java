package org.liu.demo.mybatis;

import lombok.Data;
import org.locationtech.jts.geom.Geometry;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private Integer age;
    private Integer enabled;
    private Geometry gis;
}
