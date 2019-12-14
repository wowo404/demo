package org.liu.commonscollections;

import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.keyvalue.MultiKey;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.collections.map.MultiKeyMap;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.liu.model.Animal;

public class TestCommonsCollections {

    public static void main(String[] args) {
        queue();
    }

    public static void queue(){
        CircularFifoQueue<Animal> queue = new CircularFifoQueue<>(5);
        for(int i = 0; i < 10; i++){
            Animal animal = new Animal();
            animal.setId(i);
            queue.add(animal);
            System.out.println(queue.size() + " -- " + i);
            System.out.println("-----------------");
            queue.forEach(animal1 -> {
                System.out.println(animal1.getId());
            });
            System.out.println("-----------------");
        }
        queue.removeIf(animal -> animal.getId() == 9);
        System.out.println(queue.size());
    }

    public static void multiKeyMap(){
        MultiKeyMap multiKeyMap = MultiKeyMap.decorate(new LinkedMap());
        multiKeyMap.put("1", "a", "liu");
        multiKeyMap.put("2", "a", "zhang");
        multiKeyMap.put("1", "b", "li");
        MapIterator mapIterator = multiKeyMap.mapIterator();
        while (mapIterator.hasNext()) {
            MultiKey next = (MultiKey) mapIterator.next();
            System.out.println(next.getKey(0));
            System.out.println(next.getKey(1));
            System.out.println(mapIterator.getKey() + " --- " + mapIterator.getValue());
        }
        multiKeyMap.forEach((key, value) -> {
            System.out.println(key + " ----- " + value);
        });
    }

}
