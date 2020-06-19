package org.demo.json;

import lombok.Data;

import java.util.List;

@Data
public class Student {
    private String name;
    private Integer age;
    private Address address;
    private List<String> teachers;
    private String[] courses;
    private BaseReq baseReq;
}
