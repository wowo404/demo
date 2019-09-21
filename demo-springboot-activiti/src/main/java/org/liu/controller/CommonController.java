package org.liu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
	
	@GetMapping("/toSuccess")
	public String toSuccess(){
		return "success";
	}

}
