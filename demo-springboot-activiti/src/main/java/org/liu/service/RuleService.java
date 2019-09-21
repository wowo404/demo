package org.liu.service;

import org.liu.pojo.Rule;
import org.liu.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class RuleService {
	
	@Autowired
	private RuleRepository ruleRepository;

	public void save(Rule rule) {
		log.debug("save rule, rule name:" + rule.getRuleName());
		this.ruleRepository.save(rule);
	}

}
