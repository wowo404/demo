package org.liu.serial;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @Author lzs
 * @Date 2022/4/9 10:14
 **/
public class Kong implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -7144694309484327560L;

    public String s;

    Kong(String s) {
        this.s = s;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        System.out.println("Kong.writeObject");
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        System.out.println("Kong.readObject");
    }
}
