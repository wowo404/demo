package org.liu.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuzhangsheng
 * @create 2019/6/14
 */
public class TestFreemarker {

    public static void main(String[] args) throws IOException, TemplateException {
        createHtmlFromTpl();
    }

    public static void createHtmlFromTpl() throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        String path = TestFreemarker.class.getResource("").getPath();
        System.out.println(path);
        cfg.setDirectoryForTemplateLoading(new File("src/main/resources/ftl"));
        Template template = cfg.getTemplate("/test.ftl");
        Map<String, String> map = new HashMap<>();
        map.put("test_msg", "ok");
        template.process(map, new PrintWriter(System.out));
    }

}
