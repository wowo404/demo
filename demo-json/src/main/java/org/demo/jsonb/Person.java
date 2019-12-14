package org.demo.jsonb;

import javax.json.bind.annotation.*;
import javax.json.bind.config.PropertyOrderStrategy;
import java.math.BigDecimal;
import java.time.LocalDate;

@JsonbNillable
@JsonbPropertyOrder(PropertyOrderStrategy.ANY)
public class Person {
    @JsonbTransient
    private Long id;
    @JsonbProperty("person-name")//on field, in this case it will affect serialization and deserialization
    public String name;
    @JsonbProperty(nillable=true)
    public String profession;

    @JsonbDateFormat("dd.MM.yyyy")
    private LocalDate birthDate;

    @JsonbNumberFormat("#0.00")
    private BigDecimal salary;

    @JsonbCreator
    public Person(@JsonbProperty("name") String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonbProperty("name-to-write")//on getter, in this case it will affect serialization only
    public String getProfession() {
        return profession;
    }

    @JsonbProperty("name-to-read")//on setter, in this case it will affect deserialization only
    public void setProfession(String profession) {
        this.profession = profession;
    }
}
