package org.liu.google.guava.basicutilities;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ComparisonChain;

/**
 * 1. 基本工具 [Basic utilities]
让使用Java语言变得更舒适

1.1 使用和避免null：null是模棱两可的，会引起令人困惑的错误，有些时候它让人很不舒服。很多Guava工具类用快速失败拒绝null值，而不是盲目地接受

1.2 前置条件: 让方法中的条件检查更简单

1.3 常见Object方法: 简化Object方法实现，如hashCode()和toString()

1.4 排序: Guava强大的”流畅风格比较器”

1.5 Throwables：简化了异常和错误的传播与检查
 * @author liuzhsh
 */
public class BasicUtilities {
	
	private static void test1(){
		Optional<Integer> possiable = Optional.of(5);
		System.out.println(possiable.isPresent());
		System.out.println(possiable.get());
	}
	private static void test2(){
		Demo demo = new Demo();
		Optional<Demo> demoPos = Optional.of(demo);
		System.out.println(demoPos.isPresent());
		System.out.println(demoPos.get());
		checkNotNull(demoPos);
	}
	private static void test3(){
		System.out.println(Objects.equal("a", "a"));
		System.out.println(Objects.equal(null, "a"));
		System.out.println(Objects.equal("a", null));
		System.out.println(Objects.equal(null, null));
		Demo demo = new Demo();
		System.out.println(Objects.hashCode("a", 1, 0.5f, demo));
	}
	private static void test4(){
		
		
	}
	private static void test5(){}
	
	public static void main(String[] args) {
		int processors = Runtime.getRuntime().availableProcessors();
		System.out.println(processors);
		test1();
	}
	
	static class Demo {
		private Integer id;
		private String name;
		private Integer age;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		public int compareTo(Demo that){
			//guaua提供的compareChain
			return ComparisonChain.start().compare(this.age, that.age).result();
		}
	}

}
