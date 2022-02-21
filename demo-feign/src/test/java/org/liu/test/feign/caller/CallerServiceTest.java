package org.liu.test.feign.caller;

import org.junit.Test;

public class CallerServiceTest {

    private CallerService callerService = new CallerService();

    @Test
    public void callRemote() {
        callerService.callRemote();
    }

    @Test
    public void callFileService() {
        callerService.callFileService();
    }
}
