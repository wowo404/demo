package org.liu.id;

import de.huxhorn.sulky.ulid.ULID;

/**
 * ULID的规范文档：https://github.com/ulid/spec?tab=readme-ov-file
 * java实现：https://github.com/huxi/sulky/tree/master/sulky-ulid
 *
 * @author lzs
 * @Date 2024/5/14 10:49
 **/
public class TestUlid {

    static ULID ulid = new ULID();

    public static void main(String[] args) {
        System.out.println(ulid.nextULID());
        System.out.println(ulid.nextULID());
        System.out.println(ulid.nextULID());
        System.out.println(ulid.nextULID());
        System.out.println(ulid.nextULID());
    }

}
