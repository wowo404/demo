package org.liu.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("hello listener is running!");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("hello listener is destroy!");
	}

}
