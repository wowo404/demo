package org.liu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * spring boot 启动类
 * 设置Session失效时间，使用Redis Session之后，原Boot的server.session.timeout属性不再生效
 */
@SpringBootApplication
@ComponentScan(basePackages = {"org.liu"})
@EntityScan(basePackages = {"org.liu.pojo"})
@EnableJpaRepositories(basePackages = {"org.liu.repository"})
@EnableScheduling
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400 * 30)
public class ActivitiApplication 
{
    public static void main( String[] args )
    {
    	ConfigurableApplicationContext context = SpringApplication.run(ActivitiApplication.class, args);
    	String[] beans = context.getBeanDefinitionNames();
    	StringBuilder sb = new StringBuilder();
    	for (String beanName : beans) {
    		sb.append(beanName + ",");
		}
    	System.out.println("受spring托管的类：" + sb.toString());
    }
}
