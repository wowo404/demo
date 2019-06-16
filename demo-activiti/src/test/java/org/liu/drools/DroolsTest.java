package org.liu.drools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.drools.core.command.impl.GenericCommand;
import org.drools.core.command.runtime.BatchExecutionCommandImpl;
import org.drools.core.command.runtime.rule.FireAllRulesCommand;
import org.drools.core.command.runtime.rule.GetObjectsCommand;
import org.drools.core.command.runtime.rule.InsertObjectCommand;
import org.drools.core.runtime.impl.ExecutionResultImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.ExecutionResults;
import org.kie.internal.runtime.helper.BatchExecutionHelper;
import org.kie.server.api.commands.CallContainerCommand;
import org.kie.server.api.commands.CommandScript;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.KieServerCommand;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.api.model.ServiceResponsesList;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.RuleServicesClient;
import org.liu.ActivitiApplication;
import org.liu.pojo.User;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ActivitiApplication.class)
public class DroolsTest {
	
	@Test
	public void testKieServerByXml(){
		User user = new User();
		user.setAge(12);
		user.setUserName("a");
		user.setEmail("aa@11.com");
		String url = "http://192.168.199.128:8080/kie-server/services/rest/server";
		String username = "kieserver";
		String password = "kieserver1!";
		InsertObjectCommand insertObjectCommand = new InsertObjectCommand(user, "user");
		GetObjectsCommand getObjectsCommand = new GetObjectsCommand();
		getObjectsCommand.setOutIdentifier("objects");
		FireAllRulesCommand fireAllRulesCommand = new FireAllRulesCommand("runAllRules");
		
		List<GenericCommand<?>> commands = new ArrayList<>();
		commands.add(insertObjectCommand);
		commands.add(getObjectsCommand);
		commands.add(fireAllRulesCommand);
		
		BatchExecutionCommand command = new BatchExecutionCommandImpl(commands);
		String xmlString = BatchExecutionHelper.newXStreamMarshaller().toXML(command);
		KieServicesConfiguration config = KieServicesFactory.newRestConfiguration(url, username, password);
		config.setMarshallingFormat(MarshallingFormat.XSTREAM);
		KieServicesClient client = KieServicesFactory.newKieServicesClient(config);//获取请求
		String containerId = "testKieServer";//请求容器的名称
		KieServerCommand call = new CallContainerCommand(containerId, xmlString);//拼接kie-server命名
		List<KieServerCommand> cmds = Arrays.asList(call);//命名集合
        CommandScript script = new CommandScript(cmds);
        ServiceResponsesList reply = client.executeScript(script);//服务响应列表 请求服务
        for (ServiceResponse<? extends Object> r : reply.getResponses()) {
            System.out.println(r.getResult());

            if (r.getResult() != null) {
                ExecutionResultImpl result = (ExecutionResultImpl) BatchExecutionHelper.newXStreamMarshaller().fromXML((String) r.getResult());
                user = (User) result.getResults().get("user");  //和web  results功能是一样的
                // Objects From insert(fact0) in rule. The problem is that they are staying and multiplying there in Drools, don't know yet how to manage it. ToDo.
                ArrayList<Object> objects = (ArrayList<Object>) result.getResults().get("objects");//和web  results功能是一样的
                System.out.println(objects+ user.getUserName());
            }
            else{
            	System.out.println("Empty result...?");
            }
        }
	}
	
	@Test
	public void testKieServerByJson(){
		User user = new User();
		user.setAge(12);
		user.setUserName("a");
		user.setEmail("aa@11.com");
		String url = "http://192.168.199.128:8080/kie-server/services/rest/server";
		String username = "kieserver";
		String password = "kieserver1!";

        KieServicesConfiguration config = KieServicesFactory.newRestConfiguration(url, username, password);
        config.setMarshallingFormat(MarshallingFormat.JSON);//请求方式
        config.setTimeout(30000L);//如果请求失败，再次请求的间隔时间

        KieServicesClient client = KieServicesFactory.newKieServicesClient(config);//创建kie-server客户端
        RuleServicesClient rules = client.getServicesClient(RuleServicesClient.class);//创建访问规则的客户端

        KieCommands cmdFactory = KieServices.Factory.get().getCommands();

        List<Command<?>> commands = new LinkedList<Command<?>>();
        commands.add(cmdFactory.newInsert(user, "user"));   //输入事务，在请求获取请求时，和web请求获取一样
        commands.add(cmdFactory.newFireAllRules());
        ServiceResponse<ExecutionResults> response = rules.executeCommandsWithResults("testKieServer",cmdFactory.newBatchExecution(commands, "kiesession01"));
        //第一个参数，容器名称，第二个参数将传放的值放到容器中 kiesession01  表示kiesession 第二个参数可有可无，如果没有则使用的是kiesession默认值
        System.out.println(response.getMsg());
        ExecutionResults result = response.getResult(); //获取请求
        ServiceResponse.ResponseType type = response.getType();  //请求状态
        System.out.println(type.name());
        user = (User) result.getValue("person"); //和web 获取前端传值很像吧
        System.out.println(user.getUserName());
	}
	
}
