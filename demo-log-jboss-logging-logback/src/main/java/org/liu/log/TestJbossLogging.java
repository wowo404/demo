package org.liu.log;

import org.jboss.logging.Logger;

/**
 * 测试项目中有多个日志门面框架，只有一个日志实现框架，不引入其他的桥接jar包，会不会有问题
 * 通过测试，commons-logging和java.util.logging必须加入桥接到logback或者slf4j的桥接器
 */
public class TestJbossLogging {

    private static final Logger logger = Logger.getLogger(TestJbossLogging.class);
    private static final Logger logger1 = Logger.getLogger("mytest");
    private static final Logger logger2 = Logger.getLogger("mytest2");

    public static void main(String[] args) {
        TestJbossLogging jbossLogging = new TestJbossLogging();
        jbossLogging.test();

        TestSLF4J slf4J = new TestSLF4J();
        slf4J.test();

        TestJavaUtilLogging javaUtilLogging = new TestJavaUtilLogging();
        javaUtilLogging.test();

        TestCommonsLogging commonsLogging = new TestCommonsLogging();
        commonsLogging.test();

    }

    public void test(){
        logger.trace("trace log");
        logger.debug("debug log");
        logger.info("info log");
        logger.warn("warn log");
        logger.error("error log");

        logger1.info("logger1 info log");
        logger2.info("logger2 info log");
    }

}
