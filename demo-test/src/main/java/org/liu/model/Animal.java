package org.liu.model;

import java.io.Serializable;
import java.util.Date;

import org.liu.enums.AnimalType;

/**
 * Created by hello on 2017/11/9.
 */
public class Animal implements Serializable {

    private Integer id;
    private String name;
    private Integer age;
    private Date birthday;
    private AnimalType animalType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }
}
