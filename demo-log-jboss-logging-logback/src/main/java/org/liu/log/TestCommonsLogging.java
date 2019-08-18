package org.liu.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestCommonsLogging {

    private static final Log log = LogFactory.getLog(TestCommonsLogging.class);

    public void test(){
        log.trace("commons logging, trace");
        log.debug("commons logging, debug");
        log.info("commons logging, info");
        log.warn("commons logging, warn");
        log.error("commons logging, error");
    }

}
