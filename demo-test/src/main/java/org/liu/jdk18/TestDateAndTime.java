package org.liu.jdk18;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author liuzhangsheng
 * @create 2019/6/18
 */
public class TestDateAndTime {

    public static void main(String[] args) {
        System.out.println();
        LocalDateTime dateTime = LocalDateTime.parse(LocalDateTime.now().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(dateTime);

    }

}
