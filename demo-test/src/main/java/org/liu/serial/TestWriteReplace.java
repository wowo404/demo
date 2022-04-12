package org.liu.serial;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestWriteReplace {

    public static void main(String[] args) throws Exception {
        TestWriteReplace test = new TestWriteReplace();
        test.testOut03();
        System.out.println("-------------");
        test.testIn03();
    }

    /**
     * 序列号People对象，由于有重写writeReplace方法，则不会调用writeObject方法
     * writeReplace方法中返回的是一个Kong对象，则继续调用Kong类的writeObject方法
     * @throws Exception
     */
    public void testOut03() throws Exception {
        People p = new People(2, "小白");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\home\\temp01.txt"));
        out.writeObject(p);
        out.flush();
        out.close();
    }

    /**
     * temp01.txt文件中写入的是一个Kong对象，则反序列化时调用的就是Kong类的readObject方法
     * @throws Exception
     */
    public void testIn03() throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\home\\temp01.txt"));
        Kong k = (Kong) in.readObject();
        in.close();
        System.out.println(k.s);
    }
}
