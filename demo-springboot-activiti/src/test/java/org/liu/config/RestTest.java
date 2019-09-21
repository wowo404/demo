package org.liu.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.liu.ActivitiApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ActivitiApplication.class})
public class RestTest {

	@Autowired
	private RestTemplate restTemplate;
	
	@Test
	public void test(){
		String baidu = this.restTemplate.getForObject("https://www.baidu.com", String.class);
		System.out.println("response from baidu.com:" + baidu);
	}
	
}
