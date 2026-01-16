package org.liu.jdk18;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author liuzhangsheng
 * @create 2019/6/18
 */
public class TestDateAndTime {

    public static void main(String[] args) {
        //Africa/Lusaka
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        ZoneOffset zoneOffset = ZoneOffset.of("+8");
        ZoneId bamako = ZoneId.of("Africa/Bamako");
        ZoneOffset bamakoOffset = ZoneOffset.of("+0");
        ZoneId utc = ZoneId.of("UTC");

        LocalDateTime dateTime = LocalDateTime.parse("2025-12-15 13:55:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(dateTime);
        ZonedDateTime zonedDateTime = dateTime.atZone(zoneId);
        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime.toLocalDateTime());
        System.out.println("-----------bamako----------");
        ZonedDateTime bamakoDateTime = zonedDateTime.withZoneSameInstant(bamako);
        System.out.println(bamakoDateTime);
        System.out.println(bamakoDateTime.toLocalDateTime());

        LocalDateTime now = LocalDateTime.now(bamakoOffset);
        System.out.println(now);

        System.out.println(LocalDateTime.now(utc));

        ZonedDateTime zdt = ZonedDateTime.now(ZoneOffset.of("+8"));
        System.out.println(zdt.toOffsetDateTime());

    }

}
