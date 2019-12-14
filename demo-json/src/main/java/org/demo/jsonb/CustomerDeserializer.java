package org.demo.jsonb;

import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;
import java.lang.reflect.Type;

public class CustomerDeserializer implements JsonbDeserializer<Customer> {
    @Override
    public Customer deserialize(JsonParser parser, DeserializationContext ctx, Type rtType) {
        Customer customer = new Customer();
        JsonParser.Event next;

        // Moving parser by hand looking for customer_id and customer_name properties
        while ((next = parser.next()) != JsonParser.Event.END_OBJECT) {
            if (next == JsonParser.Event.KEY_NAME) {
                String jsonKeyName = parser.getString();

                // Move to json value
                parser.next();

                if ("customer_id".equals(jsonKeyName)) {
                    customer.setId(parser.getInt());
                } else if ("customer_name".equals(jsonKeyName)) {
                    customer.setName(parser.getString());
                }
            }
        }
        return customer;
    }
}
