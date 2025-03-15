package datastructures;

import java.util.function.Function;

/**
 * @Author lzs
 * @Date 2022/12/20 18:21
 **/
public class ArrayRow implements Row {

    private Object[] data;

    public ArrayRow create(){
        return new ArrayRow();
    }

    @Override
    public <V> V get(int index) {
        return null;
    }

    @Override
    public <V> void add(V value) {

    }

    @Override
    public <V> Row append(V value) {
        return null;
    }

    @Override
    public <V1, V2> Row of(V1 value1, V2 value2) {
        return null;
    }

    @Override
    public <V1, V2, V3> Row of(V1 value1, V2 value2, V3 value3) {
        return null;
    }

    @Override
    public <V1, V2, V3, V4> Row of(V1 value1, V2 value2, V3 value3, V4 value4) {
        return null;
    }

    @Override
    public <V> boolean remove(V value) {
        return false;
    }

    @Override
    public <V, T, R> boolean remove(V value, Function<T, R> function) {
        return false;
    }

    @Override
    public boolean remove(int index) {
        return false;
    }
}
