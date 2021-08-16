package org.liu.random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.text.RandomStringGenerator;

public class TestRandom {

	public static void main(String[] args) {
		for(int i = 0; i < 100; i++){
			randomChinese();
		}
	}

	/**
	 * https://www.qqxiuzi.cn/zh/hanzi-unicode-bianma.php
	 */
	public static void randomChinese(){
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange(0x4e00, 0x9fa5).build();
		System.out.println(generator.generate(5, 5));
	}

	public static char getRandomChar() {
		return (char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1)));
	}

	private static void random(){
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
		System.out.println(generator.generate(5, 5));

		System.out.println(RandomStringUtils.random(5, true, true));
	}

}
