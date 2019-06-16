package org.liu.demo.database.h2.ctrl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloCtrl {

	@GetMapping("/hello")
	public String hello(){
		return "hello";
	}
	
}
