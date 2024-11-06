package org.liu.jdk18;

import lombok.Data;

import java.util.List;

@Data
public class Person {

    private String firstName, lastName, job, gender, hobbies;
    private int salary, age;
    private List<String> sonNames;

    public Person() {
    }

    public Person(String hobbies) {
        this.hobbies = hobbies;
    }

    public Person(String firstName, String lastName, String job,
                  String gender, int age, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.job = job;
        this.salary = salary;
    }

}
