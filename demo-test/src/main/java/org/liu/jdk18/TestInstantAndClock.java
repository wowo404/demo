package org.liu.jdk18;

import java.time.Clock;
import java.time.Instant;
import java.util.Date;

/**
 * http://blog.csdn.net/wangnan537/article/details/49102877
 * @author liuzhsh
 */
public class TestInstantAndClock {

	public static void main(String[] args) {
		Clock clock = Clock.systemDefaultZone();
		System.out.println(clock.millis());
		
		Instant instant = clock.instant();
		System.out.println(Instant.now());
		
		Date.from(Instant.now());
	}

}
