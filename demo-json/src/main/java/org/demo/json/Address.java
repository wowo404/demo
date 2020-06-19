package org.demo.json;

import lombok.Data;

@Data
public class Address {
    private String province;
    private String city;
    private String county;
    private String village;
    private String detail;
}
