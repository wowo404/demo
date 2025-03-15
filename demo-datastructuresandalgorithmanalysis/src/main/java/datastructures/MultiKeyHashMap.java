package datastructures;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

/**
 * @Author lzs
 * @Date 2022/12/5 16:15
 **/
public class MultiKeyHashMap<K1, K2, V> implements MultiKeyMap<K1, K2, V> {

    static final int hash(Object key1, Object key2) {
        int h1;
        h1 = (key1 == null) ? 0 : (h1 = key1.hashCode()) ^ (h1 >>> 16);
        int h2;
        h2 = (key2 == null) ? 0 : (h2 = key2.hashCode()) ^ (h2 >>> 16);
        return h1 + h2;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K1 k1, K2 k2, V v) {
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K1> key1Set() {
        return null;
    }

    @Override
    public Set<K2> key2Set() {
        return null;
    }

    @Override
    public V getByK1(K1 k1) {
        return null;
    }

    @Override
    public V getByK2(K2 k2) {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<MultiKeyEntry<K1, K2, V>> entrySet() {
        return null;
    }

    static class MultiKeyNode<K1, K2, V> implements MultiKeyEntry<K1, K2, V> {
        final int hash;
        final K1 key1;
        final K2 key2;
        V value;
        MultiKeyNode<K1, K2, V> next;

        MultiKeyNode(int hash, K1 key1, K2 key2, V value, MultiKeyNode<K1, K2, V> next) {
            this.hash = hash;
            this.key1 = key1;
            this.key2 = key2;
            this.value = value;
            this.next = next;
        }

        @Override
        public K1 getKey1() {
            return key1;
        }

        @Override
        public K2 getKey2() {
            return key2;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key1) ^ Objects.hashCode(key2) ^ Objects.hashCode(value);
        }

        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof MultiKeyEntry) {
                MultiKeyEntry<?, ?, ?> e = (MultiKeyEntry<?, ?, ?>) o;
                if (Objects.equals(key1, e.getKey1())
                        && Objects.equals(key2, e.getKey2())
                        && Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }
}
