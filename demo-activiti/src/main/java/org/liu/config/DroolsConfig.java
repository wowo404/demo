package org.liu.config;

import java.io.IOException;

import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.kie.spring.KModuleBeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
public class DroolsConfig {

	private static final String RULES_PATH = "rules/";
	
	static{
		System.setProperty("drools.dateformat", "yyyy-MM-dd");
	}

	@Bean
	public KieFileSystem kieFileSystem() throws IOException {
		KieFileSystem kieFileSystem = KieServices.Factory.get().newKieFileSystem();
		for (Resource file : getRuleFiles()) {
			kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH + file.getFilename(), "UTF-8"));
		}
		return kieFileSystem;
	}

	private Resource[] getRuleFiles() throws IOException {
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
	}

	@Bean
	public KieContainer kieContainer() throws IOException {
		return KieServices.Factory.get().newKieClasspathContainer();
	}
	
	@Bean
	public KModuleBeanFactoryPostProcessor kiePostProcessor() {
		return new KModuleBeanFactoryPostProcessor();
	}

}
