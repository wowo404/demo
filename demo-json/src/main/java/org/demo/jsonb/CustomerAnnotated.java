package org.demo.jsonb;

import lombok.Data;

import javax.json.bind.annotation.JsonbProperty;

@Data
public class CustomerAnnotated {
    @JsonbProperty("customer_id")
    private int id;

    @JsonbProperty("customer_name")
    private String name;
}
