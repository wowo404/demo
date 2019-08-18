package org.liu.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSLF4J {

    private static final Logger logger = LoggerFactory.getLogger(TestSLF4J.class);

    public void test(){
        logger.trace("slf4j,trace");
        logger.debug("slf4j,debug");
        logger.info("slf4j,info");
        logger.warn("slf4j,warn");
        logger.error("slf4j,error");
    }

}
