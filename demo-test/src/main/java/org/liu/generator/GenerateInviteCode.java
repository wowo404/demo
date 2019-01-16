package org.liu.generator;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * TODO:未完成
 * @author liuzhsh
 *
 */
public class GenerateInviteCode {

	private static Object obj = new Object();
	private static Random random = new Random();
	private static GenerateInviteCode generator = null;
	private char[] charArr = new char[]{};
	// 排除0，1，i，o，共32个数字字母
	private static final char[] pool1 = new char[] { '2', '3', '4', '5', '6', '7', '8', '9', 'a' };
	private static final char[] pool2 = new char[] { 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k' };
	private static final char[] pool3 = new char[] { 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u' };
	private static final char[] pool4 = new char[] { 'v', 'w', 'x', 'y', 'z' };

	private GenerateInviteCode() {
	}

	public static final GenerateInviteCode getInstance() {
		synchronized (obj) {
			if (null == generator) {
				generator = new GenerateInviteCode();
			}
			return generator;
		}
	}

	// 根据yymmdd选择字符，例如171201
	public String generate() {
		StringBuilder sb = new StringBuilder();
		charArr = Arrays.equals(charArr, getYYMMDD()) ? increase() : getYYMMDD();
		for (int i = 0; i < charArr.length; i++) {
			char[] pool = new char[] {};
			int whichPool = random.nextInt(4);
			switch (whichPool) {
			case 0:
				pool = pool1;
				break;
			case 1:
				pool = pool2;
				break;
			case 2:
				pool = pool3;
				break;
			case 3:
				pool = pool4;
				break;
			default:
				// do nothing
			}
			int index = Character.getNumericValue(charArr[i]);
			int idx = index > 4 ? index - 4 : index;
			sb.append(pool[idx]);
		}

		return sb.toString();
	}

	private char[] getYYMMDD() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String yyMMdd = sdf.format(new Date());
		return yyMMdd.toCharArray();
	}
	
	private char[] increase(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String yyMMdd = sdf.format(cal.getTime());
		return yyMMdd.toCharArray();
	}
	
	public static void main(String[] args) {
		Set<String> set = new HashSet<>();
		for (int i = 0; i < 10000; i++) {
			set.add(GenerateInviteCode.getInstance().generate());
		}
		System.out.println(set.size());
	}

}
