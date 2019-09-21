package org.liu.controller;

import org.liu.pojo.User;
import org.liu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;

/**
 * 对drools的相关测试
 * @author liuzhsh
 */
@RestController
public class DroolsController {

	@Autowired
	private UserService userService;
	
	@ApiImplicitParam(name = "user",value = "用户实体", required = true, dataType = "User")
	@PostMapping("/checkUser")
	public String checkUser(@RequestBody User user){
		this.userService.checkUser(user);
		return "success";
	}
	
	@ApiImplicitParam(name = "user",value = "用户实体", required = true, dataType = "User")
	@PostMapping("/checkUserUseRemoteRule")
	public String checkUserUseRemoteRule(@RequestBody User user){
		this.userService.checkUserUseRemoteRule(user);
		return "success";
	}
	
	@ApiImplicitParam(name = "user",value = "用户实体", required = true, dataType = "User")
	@PostMapping("/checkUserUseRuleString")
	public String checkUserUseRuleString(@RequestBody User user){
		this.userService.checkUserUseRuleString(user);
		return "success";
	}
	
}
