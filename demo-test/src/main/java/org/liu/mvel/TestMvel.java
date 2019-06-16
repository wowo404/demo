package org.liu.mvel;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.liu.jdk18.Person;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;

public class TestMvel {
    public static void resovleScript() throws URISyntaxException, IOException{
        File script = new File(TestMvel.class.getResource("/test.el").toURI());
        VariableResolverFactory resolverFactory = new MapVariableResolverFactory();
        MVEL.evalFile(script, ParserContext.create(), resolverFactory);
        resolverFactory.createVariable("a", 12);
        resolverFactory.createVariable("b", 2);
        Integer result = (Integer) MVEL.eval("add(a,b)", resolverFactory);
        System.out.println(result);
        MVEL.eval("hello()", resolverFactory);
    }

    public static void main(String[] args) {
        Person p = new Person();
        p.setFirstName("a");
        p.setAge(21);

        //判断相等
        Boolean eq = MVEL.evalToBoolean("name == 'a'", p);
        System.out.println(eq);

        //取值
        String age = MVEL.evalToString("age", p);
        System.out.println(age);

        //计算
        Map<String,Integer> map = new HashMap<>();
        map.put("x", 11);
        map.put("y", 12);

        //方式一
        Integer result = (Integer) MVEL.eval("x*y", map);
        System.out.println(result);
        //方式二
        Serializable seri = MVEL.compileExpression("x * y");
        Integer result2 = (Integer) MVEL.executeExpression(seri, map);
        System.out.println(result2);

        //read script
        try {
            resovleScript();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

}
