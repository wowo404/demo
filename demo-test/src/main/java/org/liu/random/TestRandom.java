package org.liu.random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.text.RandomStringGenerator;

public class TestRandom {

	public static void main(String[] args) {
		int nextInt = RandomUtils.nextInt(1000, 9999);
		System.out.println(nextInt);
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', '9').build();
		for (int i = 0; i < 10; i++) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					String randomLetters = generator.generate(5);
					System.out.println(randomLetters);
				}
			};
			new Thread(runnable).start();
		}
	}

	private static void random(){
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
		System.out.println(generator.generate(5, 5));

		System.out.println(RandomStringUtils.random(5, true, true));
	}

}
