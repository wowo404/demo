package org.liu.task;

import java.util.concurrent.Callable;

import org.liu.pojo.User;
import org.liu.repository.UserRepository;

public class UserTask implements Callable<User> {

	private UserRepository userRepository;
	private Long userId;
	
	public UserTask(UserRepository userRepository, Long userId){
		this.userRepository = userRepository;
		this.userId = userId;
	}
	
	@Override
	public User call() throws Exception {
		return this.userRepository.findOne(userId);
	}

}
