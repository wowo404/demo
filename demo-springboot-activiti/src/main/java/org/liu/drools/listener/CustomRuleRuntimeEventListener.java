package org.liu.drools.listener;

import org.kie.api.definition.KieDefinition.KnowledgeType;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.rule.FactHandle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomRuleRuntimeEventListener implements RuleRuntimeEventListener {

	@Override
	public void objectInserted(ObjectInsertedEvent event) {
		//event.getRule(),居然是空的
		FactHandle fh = event.getFactHandle();
		log.info("FactHandle:" + fh);
	}

	@Override
	public void objectUpdated(ObjectUpdatedEvent event) {
		String name = event.getRule().getName();
		String id = event.getRule().getId();
		String namespace = event.getRule().getNamespace();
		String packageName = event.getRule().getPackageName();
		KnowledgeType knowledgeType = event.getRule().getKnowledgeType();
		log.info(name + "-" + id + "-" + namespace + "-" + packageName + knowledgeType);
	}

	@Override
	public void objectDeleted(ObjectDeletedEvent event) {
		String name = event.getRule().getName();
		String id = event.getRule().getId();
		String namespace = event.getRule().getNamespace();
		String packageName = event.getRule().getPackageName();
		KnowledgeType knowledgeType = event.getRule().getKnowledgeType();
		log.info(name + "-" + id + "-" + namespace + "-" + packageName + knowledgeType);
	}

}
