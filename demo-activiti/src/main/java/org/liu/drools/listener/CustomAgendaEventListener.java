package org.liu.drools.listener;

import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.AgendaGroupPoppedEvent;
import org.kie.api.event.rule.AgendaGroupPushedEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.event.rule.RuleFlowGroupActivatedEvent;
import org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAgendaEventListener implements AgendaEventListener {

	@Override
	public void matchCreated(MatchCreatedEvent event) {
		String name = event.getMatch().getRule().getName();
		log.info("matchCreated,rule name:" + name);
	}

	@Override
	public void matchCancelled(MatchCancelledEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeMatchFired(BeforeMatchFiredEvent event) {
		String name = event.getMatch().getRule().getName();
		log.info("beforeMatchFired,rule name:" + name);
	}

	@Override
	public void afterMatchFired(AfterMatchFiredEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agendaGroupPopped(AgendaGroupPoppedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void agendaGroupPushed(AgendaGroupPushedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
		// TODO Auto-generated method stub

	}

}
