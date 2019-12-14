package org.demo.jsonb;

import javax.json.bind.adapter.JsonbAdapter;

public class CustomerAdapter implements JsonbAdapter<Customer, CustomerAnnotated> {
    @Override
    public CustomerAnnotated adaptToJson(Customer c) throws Exception {
        CustomerAnnotated customer = new CustomerAnnotated();
        customer.setId(c.getId());
        customer.setName(c.getName());
        return customer;
    }

    @Override
    public Customer adaptFromJson(CustomerAnnotated adapted) throws Exception {
        Customer customer = new Customer();
        customer.setId(adapted.getId());
        customer.setName(adapted.getName());
        return customer;
    }
}
