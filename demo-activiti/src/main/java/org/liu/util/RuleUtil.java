package org.liu.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class RuleUtil {
	
	private static String tplFile;
	private static String tplLoaderPath;
	
	static{
		tplFile = File.separator + "templates" + File.separator + "rule" + File.separator + "drl.ftl";
		String classPath = Thread.currentThread().getContextClassLoader().getResource("/").toString();
		tplLoaderPath = classPath + File.separator + "templates" + File.separator + "rule";
	}
	
	public static String fillTemplate(String packageName, String imports, String declares, String globals,
			String functions, String querys, String rules){
		StringWriter writer = new StringWriter();
		try {
			Configuration cfg = new Configuration(Configuration.getVersion());
			cfg.setDirectoryForTemplateLoading(new File(tplLoaderPath));
			Template tpl = cfg.getTemplate(tplFile);
			Map<String, Object> params = new HashMap<>();
			params.put("packageName", packageName);
			params.put("imports", imports);
			params.put("declares", declares);
			params.put("globals", globals);
			params.put("functions", functions);
			params.put("querys", querys);
			params.put("rules", rules);
			tpl.process(params, writer);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}
		return writer.toString();
	}

}
