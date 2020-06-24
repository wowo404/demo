package org.demo.tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

public class TomcatMain {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();

        Connector connector = new Connector();
        connector.setPort(8080);

        tomcat.setConnector(connector);

        tomcat.start();
        tomcat.getServer().await();
    }

}
