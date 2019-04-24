package org.liu.readproperties;

import java.util.Properties;

public class TestReadProperties {

    public static void main(String[] args) {
        Properties properties = System.getProperties();
        properties.forEach((k,v) -> {
            System.out.println(k + "   ---   " + v);
        });
    }

}
