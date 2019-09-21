package org.liu.service;

import org.liu.pojo.RulePublic;
import org.liu.repository.RulePublicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class RulePublicService {
	
	@Autowired
	private RulePublicRepository rulePublicRepository;
	
	public void save(RulePublic rulePublic) {
		log.debug("save rule public,type:{},content:{}", rulePublic.getContentType(),rulePublic.getContent());
		this.rulePublicRepository.save(rulePublic);
	}

}
