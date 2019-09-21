package org.liu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	@GetMapping("/toHello")
	public String toHello(Model model){
		model.addAttribute("name", "liu");
		return "hello";
	}
	
}
