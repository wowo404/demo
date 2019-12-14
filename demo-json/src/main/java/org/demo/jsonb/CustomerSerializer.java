package org.demo.jsonb;

import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;

public class CustomerSerializer implements JsonbSerializer<Customer> {

    @Override
    public void serialize(Customer customer, JsonGenerator generator, SerializationContext serializationContext) {
        generator.writeStartObject();
        generator.write("customer_id", customer.getId());
        generator.write("customer_name", customer.getName());
        generator.writeEnd();
    }
}
