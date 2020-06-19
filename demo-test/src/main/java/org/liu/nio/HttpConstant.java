package org.liu.nio;

import java.util.ArrayList;
import java.util.List;

public class HttpConstant {

    public static final int PORT = 80;
    public static final List<String> HOSTS = new ArrayList<>();

    static {
        HOSTS.add("www.baidu.com");
    }

}
