package org.liu.serial;

import org.liu.model.Monkey;

import java.io.*;
import java.text.MessageFormat;

/**
 * @author liuzhangsheng
 * @create 2018/12/7
 */
public class TestObjSerializeAndDeserialize {

    public static void main(String[] args) throws Exception {
        serializePerson();//序列化Person对象
        Monkey p = deserializePerson();//反序列Person对象
        System.out.println(p.getName());
        System.out.println(MessageFormat.format("name={0},age={1}",
                p.getName(), p.getAge()));
    }

    private static void serializePerson() throws FileNotFoundException,
            IOException {
        Monkey person = new Monkey();
        person.setName("gacl");
        person.setAge(25);
        // ObjectOutputStream 对象输出流，将Person对象存储到E盘的Person.txt文件中，完成对Person对象的序列化操作
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
                new File("/Users/liuzhangsheng/Documents/Person.txt")));
        oo.writeObject(person);
        System.out.println("Person对象序列化成功！");
        oo.close();
    }

    private static Monkey deserializePerson() throws Exception, IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                new File("/Users/liuzhangsheng/Documents/Person.txt")));
        Monkey person = (Monkey) ois.readObject();
        System.out.println("Person对象反序列化成功！");
        return person;
    }

}
