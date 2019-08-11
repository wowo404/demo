package org.liu.log;

import org.jboss.logging.Logger;

public class TestJbossLogging {

    private static final Logger logger = Logger.getLogger(TestJbossLogging.class);
    private static final Logger logger1 = Logger.getLogger("mytest");
    private static final Logger logger2 = Logger.getLogger("mytest2");

    public static void main(String[] args) {
        logger.trace("trace log");
        logger.debug("debug log");
        logger.info("info log");
        logger.warn("warn log");
        logger.error("error log");

        logger1.info("logger1 info log");
        logger2.info("logger2 info log");
    }

}
