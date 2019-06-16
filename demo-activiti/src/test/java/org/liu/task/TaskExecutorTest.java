package org.liu.task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.liu.ActivitiApplication;
import org.liu.pojo.User;
import org.liu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ActivitiApplication.class)
public class TaskExecutorTest {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	@Test
	public void test() throws InterruptedException, ExecutionException {
		for (int i = 1; i < 6; i++) {
			Future<User> future = threadPoolTaskExecutor.submit(new UserTask(userRepository, Long.valueOf(i + "")));
			System.out.println(future.get());
		}
	}
	
	@Test
	public void testExecutor() throws InterruptedException, ExecutionException{
		//虽然设置了60次，但最终只执行了50次，因为threadPoolTaskExecutor设置了最大poolSize为50
		//打印结果并非按顺序排序，说明了是并发执行而不是顺序执行
		for (int i = 0; i < 60; i++) {
			threadPoolTaskExecutor.submit(new CalculateTask(LocalDateTime.now(), i));
		}
	}
	
}
