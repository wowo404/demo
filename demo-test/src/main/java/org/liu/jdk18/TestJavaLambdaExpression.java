package org.liu.jdk18;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 测试Java Lambda Expression
 * 教程：http://blog.csdn.net/renfufei/article/details/24600507
 * @author liuzhsh
 */
public class TestJavaLambdaExpression {

	public void test1() {
		String[] atp = { "Rafael Nadal", "Novak Djokovic",
				"Stanislas Wawrinka", "David Ferrer", "Roger Federer",
				"Andy Murray", "Tomas Berdych", "Juan Martin Del Potro" };
		List<String> players = Arrays.asList(atp);

		// 以前的循环方式
		for (String player : players) {
			System.out.print(player + "; ");
		}

		System.out.println();
		System.out.println("---------------------");

		// 使用 lambda 表达式以及函数操作(functional operation)
		players.forEach((player) -> System.out.print(player + "; "));

		System.out.println();
		System.out.println("---------------------");

		// 在 Java 8 中使用双冒号操作符(double colon operator)
		players.forEach(System.out::println);
	}

	/**
	 * 在图形用户界面程序中,匿名类可以使用lambda表达式来代替
	 */
	public void test2() {
		// 使用匿名内部类
		//		btn.setOnAction(new EventHandler<ActionEvent>() {
		//		          @Override
		//		          public void handle(ActionEvent event) {
		//		              System.out.println("Hello World!"); 
		//		          }
		//		    });
		//		// 或者使用 lambda expression
		//		btn.setOnAction(event -> System.out.println("Hello World!"));
	}

	/**
	 * 使用lambda来实现 Runnable接口
	 */
	public void test3() {
		// 1.1使用匿名内部类
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Hello world !");
			}
		}).start();

		// 1.2使用 lambda expression
		new Thread(() -> System.out.println("Hello world !")).start();

		// 2.1使用匿名内部类
		Runnable race1 = new Runnable() {
			@Override
			public void run() {
				System.out.println("Hello world !");
			}
		};

		// 2.2使用 lambda expression
		Runnable race2 = () -> System.out.println("Hello world !");

		// 直接调用 run 方法(没开新线程哦!)
		race1.run();
		race2.run();
	}

	public void test4() {
		String[] players = { "Rafael Nadal", "Novak Djokovic",
				"Stanislas Wawrinka", "David Ferrer", "Roger Federer",
				"Andy Murray", "Tomas Berdych", "Juan Martin Del Potro",
				"Richard Gasquet", "John Isner" };

		// 1.1 使用匿名内部类根据 name 排序 players
		Arrays.sort(players, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return (s1.compareTo(s2));
			}
		});

		// 1.2 使用 lambda expression 排序 players
		Comparator<String> sortByName = (String s1, String s2) -> (s1
				.compareTo(s2));
		Arrays.sort(players, sortByName);

		// 1.3 也可以采用如下形式:
		Arrays.sort(players, (String s1, String s2) -> (s1.compareTo(s2)));
	}

	public void test5() {
		String[] players = { "Rafael Nadal", "Novak Djokovic",
				"Stanislas Wawrinka", "David Ferrer", "Roger Federer",
				"Andy Murray", "Tomas Berdych", "Juan Martin Del Potro",
				"Richard Gasquet", "John Isner" };
		// 1.1 使用匿名内部类根据 surname 排序 players
		Arrays.sort(players, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return (s1.substring(s1.indexOf(" ")).compareTo(s2.substring(s2
						.indexOf(" "))));
			}
		});

		// 1.2 使用 lambda expression 排序,根据 surname
		Comparator<String> sortBySurname = (String s1, String s2) -> (s1
				.substring(s1.indexOf(" ")).compareTo(s2.substring(s2
				.indexOf(" "))));
		Arrays.sort(players, sortBySurname);

		// 1.3 或者这样,怀疑原作者是不是想错了,括号好多...
		Arrays.sort(players, (String s1, String s2) -> (s1.substring(s1
				.indexOf(" ")).compareTo(s2.substring(s2.indexOf(" ")))));

		// 2.1 使用匿名内部类根据 name lenght 排序 players
		Arrays.sort(players, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return (s1.length() - s2.length());
			}
		});

		// 2.2 使用 lambda expression 排序,根据 name lenght
		Comparator<String> sortByNameLenght = (String s1, String s2) -> (s1
				.length() - s2.length());
		Arrays.sort(players, sortByNameLenght);

		// 2.3 or this
		Arrays.sort(players,
				(String s1, String s2) -> (s1.length() - s2.length()));

		// 3.1 使用匿名内部类排序 players, 根据最后一个字母
		Arrays.sort(players, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return (s1.charAt(s1.length() - 1) - s2.charAt(s2.length() - 1));
			}
		});

		// 3.2 使用 lambda expression 排序,根据最后一个字母
		Comparator<String> sortByLastLetter = (String s1, String s2) -> (s1
				.charAt(s1.length() - 1) - s2.charAt(s2.length() - 1));
		Arrays.sort(players, sortByLastLetter);

		// 3.3 or this
		Arrays.sort(players,
				(String s1, String s2) -> (s1.charAt(s1.length() - 1) - s2
						.charAt(s2.length() - 1)));

		List<String> friends = Arrays.asList("CSS", "HTML", "Oracle", "Dart");

		//same but reverse
		System.out.println("\nsort reverse:");
		Comparator<String> comp = (aName, bName) -> aName.compareTo(bName);

		friends.stream().sorted(comp.reversed())
				.forEach(System.out::println);
	}

	public static void main(String[] args) {
		TestJavaLambdaExpression test = new TestJavaLambdaExpression();
		test.test5();
	}

}
