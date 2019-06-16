package org.liu.drools;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public abstract class BaseTest {
	
	protected KieSession kSession;
	
	@Before
	public void before(){
		System.setProperty("drools.dateformat", "yyyy-MM-dd");
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	kSession = kContainer.newKieSession("ksession-rules");
	}
	
	@Test
	public void test(){
		yourTest();
	}
	
	public abstract void yourTest();
	
	@After
	public void after(){
		kSession.dispose();
	}

}
