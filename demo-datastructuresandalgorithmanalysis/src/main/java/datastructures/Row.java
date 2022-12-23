package datastructures;

import java.util.function.Function;

/**
 * data structure:line
 * like array,but offer more OOP method
 *
 * @Author lzs
 * @Date 2022/12/20 17:57
 **/
public interface Row {
    /**
     * get data according to index
     *
     * @param index
     * @param <V>
     * @return
     */
    <V> V get(int index);

    /**
     * add data
     *
     * @param value
     * @param <V>
     */
    <V> void add(V value);

    /**
     * add data
     * offer chain operation
     *
     * @param value
     * @param <V>
     * @return
     */
    <V> Row append(V value);

    /**
     * add data
     *
     * @param value1
     * @param value2
     * @param <V1>
     * @param <V2>
     * @return
     */
    <V1, V2> Row of(V1 value1, V2 value2);

    /**
     * add data
     *
     * @param value1
     * @param value2
     * @param value3
     * @param <V1>
     * @param <V2>
     * @param <V3>
     * @return
     */
    <V1, V2, V3> Row of(V1 value1, V2 value2, V3 value3);

    /**
     * add data
     *
     * @param value1
     * @param value2
     * @param value3
     * @param value4
     * @param <V1>
     * @param <V2>
     * @param <V3>
     * @param <V4>
     * @return
     */
    <V1, V2, V3, V4> Row of(V1 value1, V2 value2, V3 value3, V4 value4);

    /**
     * remove data
     * this method depends on value's equals method
     *
     * @param value
     * @param <V>
     */
    <V> boolean remove(V value);

    /**
     * remove data depends on function result
     *
     * @param value
     * @param function
     * @param <V>
     * @param <T>
     * @param <R>
     * @return
     */
    <V, T, R> boolean remove(V value, Function<T, R> function);

    /**
     * remove data by index
     *
     * @param index
     */
    boolean remove(int index);
}