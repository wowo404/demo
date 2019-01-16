package org.liu.numberformat;

import java.text.ChoiceFormat;
import java.text.ParsePosition;

public class NumberFormatUtil {
	
	public static void main(String[] args) {
        choiceFormat();
	}
	
	public static void parseStringToLong(){
		String s = "1000.00";
		if(s.contains(".")){
			s = s.split("\\.")[0];
		}
		System.out.println(Long.valueOf(s));
	}

	public static void choiceFormat(){
        double[] limits = {1,2,3,4,5,6,7};
        String[] dayOfWeekNames = {"Sun","Mon","Tue","Wed","Thur","Fri","Sat"};
        ChoiceFormat form = new ChoiceFormat(limits, dayOfWeekNames);
        ParsePosition status = new ParsePosition(0);
        for (double i = 0.0; i <= 8.0; ++i) {
            status.setIndex(0);
            System.out.println(i + " -> " + form.format(i) + " -> "
                    + form.parse(form.format(i),status));
        }
    }

}
