package org.liu.demo.database.h2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot与h2的集成，1.h2的jar在classpath，2，引入spring-boot-devtools
 * 默认访问方式是/h2-console，可以通过spring.h2.console.path配置
 * @author liuzhsh 2017年6月1日
 */
@SpringBootApplication
public class H2Application {
	
	public static void main(String[] args) {
		SpringApplication.run(H2Application.class, args);
	}

}
