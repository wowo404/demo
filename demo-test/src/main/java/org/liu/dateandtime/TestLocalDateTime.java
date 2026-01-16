package org.liu.dateandtime;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class TestLocalDateTime {

    public static void main(String[] args) {
        beginEnd();
    }

    public static void beginEnd() {
        LocalDateTime localDateTime = LocalDateTime.now();
        
        System.out.println(localDateTime.toLocalDate().atStartOfDay());
        LocalDateTime min = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MIN);
        LocalDateTime max = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MAX);
        System.out.println(min);
        System.out.println(max);
    }

}
