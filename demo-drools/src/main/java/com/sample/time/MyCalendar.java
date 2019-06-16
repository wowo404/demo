package com.sample.time;

import org.kie.api.time.Calendar;

public class MyCalendar implements Calendar {

	@Override
	public boolean isTimeIncluded(long timestamp) {
		return false;
	}

}
