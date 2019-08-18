package org.liu.log;

import java.util.logging.Logger;

public class TestJavaUtilLogging {

    private static final Logger logger = Logger.getLogger("TestJavaUtilLogging");

    /**
     * <ul>
     *  * <li>SEVERE (highest value)
     *  * <li>WARNING
     *  * <li>INFO
     *  * <li>CONFIG
     *  * <li>FINE
     *  * <li>FINER
     *  * <li>FINEST  (lowest value)
     *  * </ul>
     */
    public void test(){
        logger.finest("java.util.logging,finest");
        logger.finer("java.util.logging,finer");
        logger.fine("java.util.logging,fine");
        logger.config("java.util.logging,config");
        logger.info("java.util.logging,info");
        logger.warning("java.util.logging,warning");
        logger.severe("java.util.logging,severe");
    }

}
