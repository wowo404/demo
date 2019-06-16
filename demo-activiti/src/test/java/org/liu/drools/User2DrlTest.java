package org.liu.drools;

import org.junit.Before;
import org.liu.pojo.User;

public class User2DrlTest extends SingleDrlBaseTest {

	@Before()
	public void before(){
		drlPath = "rules/user2.drl";
	}
	
	@Override
	public void operate() {
		User u = new User();
		u.setAge(0);
		u.setUserId(4L);
		kSession.insert(u);
		int i = kSession.fireAllRules();
		System.out.println("执行了" + i + "条规则");
	}

}
