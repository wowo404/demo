package org.demo.jsonb;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.BinaryDataStrategy;
import javax.json.bind.config.PropertyNamingStrategy;
import javax.json.bind.config.PropertyOrderStrategy;
import java.util.ArrayList;
import java.util.List;

public class TestJsonb {

    public static void main(String[] args) {
        singleObject();
    }

    private static void singleObject(){
        Jsonb jsonb = JsonbBuilder.create();

        // Create a dog instance
        Dog dog = new Dog();
        dog.name = "Falco";
        dog.age = 4;
        dog.bites = false;

        String result = jsonb.toJson(dog);
        System.out.println(result);

        // Deserialize back
        dog = jsonb.fromJson("{\"name\":\"Falco\",\"age\":4,\"bites\":false}", Dog.class);
    }

    private static void collections(){
        Dog falco = new Dog();
        falco.name = "Falco";
        falco.age = 4;
        falco.bites = false;

        Dog cassidy = new Dog();
        cassidy.name = "Falco";
        cassidy.age = 4;
        cassidy.bites = false;

        // List of dogs
        List dogs = new ArrayList();
        dogs.add(falco);
        dogs.add(cassidy);

// Create Jsonb and serialize
        Jsonb jsonb = JsonbBuilder.create();
        String result = jsonb.toJson(dogs);

// We can also deserialize back into a raw collection, but since there is no way to infer a type here,
// the result will be a list of java.util.Map instances with string keys.
        dogs = jsonb.fromJson(result, ArrayList.class);
    }

    private static void genericCollections(){
        Dog falco = new Dog();
        falco.name = "Falco";
        falco.age = 4;
        falco.bites = false;

        Dog cassidy = new Dog();
        cassidy.name = "Falco";
        cassidy.age = 4;
        cassidy.bites = false;
        // List of dogs
        List<Dog> dogs = new ArrayList<>();
        dogs.add(falco);
        dogs.add(cassidy);

// Create Jsonb and serialize
        Jsonb jsonb = JsonbBuilder.create();
        String result = jsonb.toJson(dogs);

// Deserialize back
        dogs = jsonb.fromJson(result, new ArrayList<Dog>(){}.getClass().getGenericSuperclass());
    }

    private static void customizedMapping(){
        // Create custom configuration with formatted output
        JsonbConfig config = new JsonbConfig()
                .withFormatting(true);

        // Create Jsonb with custom configuration
        Jsonb jsonb = JsonbBuilder.create(config);

        Dog dog = new Dog();
        dog.name = "Falco";
        dog.age = 4;
        dog.bites = false;
        // Use it!
        String result = jsonb.toJson(dog);
    }

    private static void namingStrategies(){
        // Custom configuration
        JsonbConfig config = new JsonbConfig()
                .withPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE_WITH_DASHES);

// Create Jsonb with custom configuration
        Jsonb jsonb = JsonbBuilder.create(config);
    }

    private static void propertiesOrder(){
        // Custom configuration
        JsonbConfig config = new JsonbConfig()
                .withPropertyOrderStrategy(PropertyOrderStrategy.ANY);

// Create Jsonb with custom configuration
        Jsonb jsonb = JsonbBuilder.create(config);
    }

    private static void ignoringProperties(){

    }

    private static void nullHandling(){
        // Create custom configuration
        JsonbConfig nillableConfig = new JsonbConfig()
                .withNullValues(true);

// Create Jsonb with custom configuration
        Jsonb jsonb = JsonbBuilder.create(nillableConfig);
    }

    private static void customInstantiation(){

    }

    private static void dateNumberFormats(){
        // Create custom configuration
        JsonbConfig config = new JsonbConfig()
                .withDateFormat("dd.MM.yyyy", null);

// Create Jsonb with custom configuration
        Jsonb jsonb = JsonbBuilder.create(config);
    }

    private static void binaryEncoding(){
        // Create custom configuration
        JsonbConfig config = new JsonbConfig()
                .withBinaryDataStrategy(BinaryDataStrategy.BASE_64);

// Create Jsonb with custom configuration
        Jsonb jsonb = JsonbBuilder.create(config);
    }

    private static void adapters(){
// Create Jsonb with default configuration
        Jsonb jsonb = JsonbBuilder.create();

// Create customer
        Customer c = new Customer();

// Initialization code is skipped

// Serialize
        jsonb.toJson(c);

        // Create custom configuration
        JsonbConfig config = new JsonbConfig()
                .withAdapters(new CustomerAdapter());

// Create Jsonb with custom configuration
        Jsonb jsonb2 = JsonbBuilder.create(config);

// Create customer
        Customer customer = new Customer();

// Initialization code is skipped

// Serialize
        jsonb.toJson(customer);
    }

    private static void serializersDeserializers(){
// Create pojo
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Freddie");

// Also configurable with @JsonbSerializer / JsonbDeserializer on properties and class.
        JsonbConfig config = new JsonbConfig()
                .withSerializers(new CustomerSerializer())
                .withDeserializers(new CustomerDeserializer());

        Jsonb jsonb = JsonbBuilder.create(config);
        String json = jsonb.toJson(customer);
        Customer result = jsonb.fromJson(json, Customer.class);
    }

    //I-JSON (”Internet JSON”)
    private static void strictIJSONSupport(){
// Create custom configuration
        JsonbConfig config = new JsonbConfig()
                .withStrictIJSON(true);

// Create Jsonb with custom configuration
        Jsonb jsonb = JsonbBuilder.create(config);
    }

}
