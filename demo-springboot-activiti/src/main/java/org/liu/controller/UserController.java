package org.liu.controller;

import java.util.List;

import org.liu.pojo.User;
import org.liu.pojo.vo.ListUserReq;
import org.liu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 用户control
 * @author liuzhsh
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "hello接口", notes = "详细注释")
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

	@ApiOperation(value = "根据email获取用户")
	@ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String")
	@GetMapping("/getByEmail")
	public User getgeByEmail(@RequestParam("email")String email) {
		return this.userService.getByEmail(email);
	}

	@ApiOperation(value = "新增用户")
	@ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User")
	@PutMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody User user) {
		this.userService.save(user);
	}
	
	@ApiOperation(value = "分页排序查询所有用户")
	@ApiImplicitParams({@ApiImplicitParam(name = "pageNumber", value = "页号", required = true, dataType = "Integer"),
			@ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, dataType = "Integer")})
	@PostMapping(value = "/listAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<User> listAll(@RequestParam("pageNumber")int pageNumber, @RequestParam("pageSize")int pageSize,
			@RequestParam("direction")String direction){
		return this.userService.listAll(pageNumber - 1, pageSize, direction, "userId");
	}
	
	@ApiOperation(value = "用Specification按条件分页排序查询")
	@ApiImplicitParam(name = "req", value = "请求实体", required = true, dataType = "ListUserReq")
	@PostMapping(value = "/listByEmailAndDateRange", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<User> listByEmailAndDateRange(@RequestBody ListUserReq req){
		return this.userService.listByEmailAndDateRange(req);
	}
	
	@ApiOperation(value = "用Example按条件分页排序查询")
	@ApiImplicitParam(name = "req", value = "请求实体", required = true, dataType = "ListUserReq")
	@PostMapping(value = "/listByExample", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<User> listByExample(@RequestBody ListUserReq req){
		return this.userService.listByExample(req);
	}
	
	@ApiOperation(value = "用自定义sql查询")
	@ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String")
	@GetMapping(value = "/listByCustomSql/{userName}")
	public List<User> listByCustomSql(@PathVariable("userName") String userName){
		return this.userService.listByCustomSql(userName);
	}
	
	@ApiOperation(value = "用hql查询")
	@ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String")
	@GetMapping(value = "/listByHql/{userName}")
	public List<User> listByHql(@PathVariable("userName") String userName){
		return this.userService.listByHql(userName);
	}
	
}
