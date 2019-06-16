package com.sample;

import org.junit.Test;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

public abstract class SingleDrlBaseTest {

	protected KieSession kSession;
	protected String drlPath;
	
	@Test
	public void test(){
		System.setProperty("drools.dateformat", "yyyy-MM-dd");
	    Resource resource =  ResourceFactory.newClassPathResource(drlPath, SingleDrlBaseTest.class);
	    KieHelper helper = new KieHelper();
	    helper.addResource(resource, ResourceType.DRL);
	    kSession = helper.build().newKieSession();
	    //custom operation
		yourTest();
		
		kSession.dispose();
	}
	
	public abstract void yourTest();
	
}
