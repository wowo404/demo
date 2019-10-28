package org.liu.multithread;

import com.google.common.primitives.Bytes;

public class TestThreadLocal {

    private static ThreadLocal<byte[]> localData = new ThreadLocal<>();

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            byte[] bytes = localData.get();
            if (null == bytes) {
                bytes = new byte[0];
            }
            byte[] newBytes = new byte[]{0x09,0x33,0x48};
            byte[] concat = Bytes.concat(bytes, newBytes);
            localData.set(concat);
        }
        System.out.println(localData.get().length);
    }

}
