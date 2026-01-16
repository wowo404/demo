package org.liu.jdk18;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

/**
 * http://blog.csdn.net/wangnan537/article/details/49102877
 *
 * @author liuzhsh
 */
public class TestInstantAndClock {

    public static void main(String[] args) {
        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.getZone() + " ---- " + clock.millis());

        Instant instant = clock.instant();
        Instant now = Instant.now();
        System.out.println(instant);
        System.out.println(now);//UTC时间
        System.out.println(now.atZone(ZoneId.of("Asia/Shanghai")));

        System.out.println(Date.from(now));

        System.out.println(Instant.parse("2007-12-03T10:15:30.33Z"));
        System.out.println(Instant.parse("2007-12-03T10:15:30Z"));

        System.out.println("----------------------");
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        for (String availableZoneId : availableZoneIds) {
            System.out.println(availableZoneId);
        }
    }

}
