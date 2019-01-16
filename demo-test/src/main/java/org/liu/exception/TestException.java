package org.liu.exception;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author liuzhangsheng
 * @create 2018/10/29
 */
public class TestException {

    public static void main(String[] args) {
        try {
            transfer();
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getCause() instanceof FileNotFoundException) {
                System.out.println("FileNotFoundException");
            }
        }
    }

    public static void transfer(){
        File file = new File("/Users/liuzhangsheng/Documents/a.txt");
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
