package org.liu.config;

import org.liu.controller.servlet.HelloServlet;
import org.liu.filter.HelloFilter;
import org.liu.listener.HelloListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 有时我们需要配置原生的servlet，filter，listener，
 * 有两种配置方式：1.使用原生的servlet3的注解，@WebServlet,@WebListener,@WebFilter，然后在springboot入口类处配置@ServletComponentScan
 * 2.采用spring bean的配置方式，即本类展示的方式
 * 还有可能在DispatcherServlet中增加一些自定义的配置
 * 具体教程见：http://blog.csdn.net/king_is_everyone/article/details/53116744
 * @author liuzhsh
 */
@Configuration
public class ServletConfig {

	/**
	 * 添加原始的servlet
	 * @return
	 */
	@Bean
    public ServletRegistrationBean indexServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new HelloServlet());
        registration.addUrlMappings("/hello");
        return registration;
    }

	/**
	 * 添加自定义的filter
	 * @return
	 */
    @Bean
    public FilterRegistrationBean indexFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new HelloFilter());
        registration.addUrlPatterns("/");
        return registration;
    }
    
    /**
     * 添加自定义的ServletContextListener
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean<HelloListener> servletListenerRegistrationBean(){
        ServletListenerRegistrationBean<HelloListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<HelloListener>();
        servletListenerRegistrationBean.setListener(new HelloListener());
        return servletListenerRegistrationBean;
    }
    
    /**
     * 这里在原来的默认配置上增加了.do和.json配置
     * @param dispatcherServlet
     */
    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
//        registration.addUrlMappings("*.do");
//        registration.addUrlMappings("*.json");
        return registration;
    }
	
}
