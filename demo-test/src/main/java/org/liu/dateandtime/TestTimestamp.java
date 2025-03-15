package org.liu.dateandtime;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

public class TestTimestamp {

    public static void main(String[] args) {
        Timestamp from = Timestamp.from(Instant.now());
        System.out.println(from);
        Date date = from;
        System.out.println(date);

        Timestamp timestamp = new Timestamp(new Date().getTime());
        System.out.println(timestamp);

        System.out.println(new Date(1734282746000L));
        System.out.println(new Date(1734417157000L));
    }

}
