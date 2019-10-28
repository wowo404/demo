package org.demo.json.gsonextension;

public interface GsonEnum<E> {

    String serialize();

    E deserialize(String jsonEnum);

}
