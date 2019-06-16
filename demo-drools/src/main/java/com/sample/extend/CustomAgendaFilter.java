package com.sample.extend;

import java.util.Set;

import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;

/**
 * �Զ����������ִ��
 * @author liuzhsh
 */
public class CustomAgendaFilter implements AgendaFilter {

	private Set<String> ruleNameSet;
	
	public CustomAgendaFilter(Set<String> ruleNameSet){
		this.ruleNameSet = ruleNameSet;
	}
	
	@Override
	public boolean accept(Match match) {
		if(ruleNameSet.contains(match.getRule().getName())){
			return true;
		}
		return false;
	}

}
