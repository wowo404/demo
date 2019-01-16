package org.liu.random;

import org.apache.commons.text.RandomStringGenerator;

public class TestRandom {

	public static void main(String[] args) {
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

}
