package org.liu.gsonextension;

public interface GsonEnum<E> {

    String serialize();

    E deserialize(String jsonEnum);

}
