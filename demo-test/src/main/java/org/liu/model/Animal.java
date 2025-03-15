package org.liu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.liu.enums.AnimalType;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by hello on 2017/11/9.
 */
@NoArgsConstructor
@Table
@Data
public class Animal implements Serializable {

    private Integer id;
    private String name;
    private Integer age;
    private Date birthday;
    private AnimalType animalType;

    public Animal(Integer id){
        this.id = id;
    }
}
