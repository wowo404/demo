package org.liu.controller;

import org.liu.pojo.Rule;
import org.liu.pojo.RulePublic;
import org.liu.service.RulePublicService;
import org.liu.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiImplicitParam;

@RequestMapping("/rule")
@Controller
public class RuleController {
	
	@Autowired
	private RuleService ruleService;
	@Autowired
	private RulePublicService rulePublicService;
	
	@GetMapping("/toAdd")
	public String toAdd(){
		return "/rule/add";
	}
	
	@ApiImplicitParam(name = "rule", value = "规则实体", required = true, paramType = "Rule")
	@PostMapping(value = "/save")
	public String save(Rule rule){
		this.ruleService.save(rule);
		return "redirect:/toSuccess";
	}
	
	@GetMapping("/toAddPublic")
	public String toAddPublic(){
		return "/rule/addPublic";
	}
	
	@ApiImplicitParam(name = "rulePublic", value = "规则头部", required = true, paramType = "RulePublic")
	@PostMapping(value = "/addPublic")
	public String addPublic(RulePublic rulePublic){
		this.rulePublicService.save(rulePublic);
		return "redirect:/toSuccess";
	}

}
