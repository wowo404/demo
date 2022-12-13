package datastructures;

import java.util.Collection;
import java.util.Set;

/**
 * @Author lzs
 * @Date 2022/12/5 15:13
 **/
public interface MultiKeyMap<K1, K2, V> {

    int size();

    boolean isEmpty();

    boolean containsKey(Object key);

    boolean containsValue(Object value);

    V get(Object key);

    V put(K1 k1, K2 k2, V v);

    V remove(Object key);

    void clear();

    Set<K1> key1Set();

    Set<K2> key2Set();

    V getByK1(K1 k1);

    V getByK2(K2 k2);

    Collection<V> values();

    Set<MultiKeyEntry<K1, K2, V>> entrySet();

    default V putIfAbsent(K1 k1, K2 k2, V v) {
        V v1 = getByK1(k1);
        V v2 = getByK2(k2);
        if (v1 == null && v2 == null) {
            put(k1, k2, v);
        }
        return v1;
    }

    interface MultiKeyEntry<K1, K2, V> {
        K1 getKey1();

        K2 getKey2();

        V getValue();

        V setValue(V value);

        boolean equals(Object o);

        int hashCode();
    }
}
