package org.liu.drools.listener;

import org.kie.api.event.process.ProcessCompletedEvent;
import org.kie.api.event.process.ProcessEventListener;
import org.kie.api.event.process.ProcessNodeLeftEvent;
import org.kie.api.event.process.ProcessNodeTriggeredEvent;
import org.kie.api.event.process.ProcessStartedEvent;
import org.kie.api.event.process.ProcessVariableChangedEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomProcessEventListener implements ProcessEventListener {

	@Override
	public void beforeProcessStarted(ProcessStartedEvent event) {
		String processName = event.getProcessInstance().getProcessName();
		log.info("CustomProcessEventListener-beforeProcessStarted,process name:" + processName);
	}

	@Override
	public void afterProcessStarted(ProcessStartedEvent event) {

	}

	@Override
	public void beforeProcessCompleted(ProcessCompletedEvent event) {
	}

	@Override
	public void afterProcessCompleted(ProcessCompletedEvent event) {
		String processName = event.getProcessInstance().getProcessName();
		log.info("CustomProcessEventListener-afterProcessCompleted,process name:" + processName);
	}

	@Override
	public void beforeNodeTriggered(ProcessNodeTriggeredEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNodeTriggered(ProcessNodeTriggeredEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNodeLeft(ProcessNodeLeftEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNodeLeft(ProcessNodeLeftEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeVariableChanged(ProcessVariableChangedEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterVariableChanged(ProcessVariableChangedEvent event) {
		// TODO Auto-generated method stub

	}

}
