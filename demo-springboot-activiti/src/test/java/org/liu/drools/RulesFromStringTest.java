package org.liu.drools;

import org.junit.Test;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;

public class RulesFromStringTest {
	@Test
	public void ruleDaseTestString() throws Exception {
		String myRule = "package org.liu;import org.liu.pojo.User;import org.liu.pojo.UserRuleRecord;rule \"用户年龄小于20或者大于50\"     when         $u:User(age < 20 || age > 50);     then         UserRuleRecord record = new UserRuleRecord();         record.setUserId($u.getUserId());         record.setRulePackage(kcontext.getRule().getPackageName());         record.setRuleName(kcontext.getRule().getName());         record.setScore(1); 		insert(record); end";
		System.out.println(myRule);
		KieHelper helper = new KieHelper();
		helper.addContent(myRule, ResourceType.DRL);
		KieSession ksession = helper.build().newKieSession();
		int i = ksession.fireAllRules();
		System.out.println("执行了" + i + "条规则");
		ksession.dispose();
	}

}
