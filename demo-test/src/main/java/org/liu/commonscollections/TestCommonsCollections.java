package org.liu.commonscollections;

import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.keyvalue.MultiKey;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.collections.map.MultiKeyMap;

import java.util.Set;

public class TestCommonsCollections {

    public static void main(String[] args) {
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
