package org.liu.io;

import org.liu.enums.AnimalType;
import org.liu.model.Animal;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

public class OutputStreamTest {

    public static void main(String[] args) {
        objectOutputStream();
    }

    public static void objectOutputStream() {
        Animal animal = new Animal();
        animal.setId(1);
        animal.setAnimalType(AnimalType.LAND);
        animal.setAge(20);
        animal.setName("monkey");

        String target = "D:\\downloads\\monkey.tmp";

        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(new File(target).toPath()))) {
            oos.writeObject(animal);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
