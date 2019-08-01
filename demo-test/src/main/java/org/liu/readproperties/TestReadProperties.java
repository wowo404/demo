package org.liu.readproperties;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestReadProperties {

    public static void main(String[] args) throws IOException {
        readFromJar();
    }

    public static void readSystemProperties(){
        Properties properties = System.getProperties();
        properties.forEach((k,v) -> {
            System.out.println(k + "   ---   " + v);
        });
    }

    public static void readFromClassPath() throws IOException {
        InputStream stream = TestReadProperties.class.getResourceAsStream("/bank/P0046000/sm2PublicKey.key");
        String content = IOUtils.toString(stream, "utf-8");
        System.out.println(content);
    }

    //只有web工程才有webapp目录，并且打成war包才有会webapp目录下的文件
//    public static void readFromWebappPath(HttpServletRequest request){
//        String realPath = request.getSession().getServletContext().getRealPath("/");
//        System.out.println("servlet context real path:" + realPath);
//        String filePath = realPath + "js/testjson.js";
//        System.out.println(filePath);
//    }

    //jar中的文件就是classpath文件
    public static void readFromJar() throws IOException {
        InputStream stream = TestReadProperties.class.getResourceAsStream("/META-INF/NOTICE.txt");
        String content = IOUtils.toString(stream, "utf-8");
        System.out.println(content);

        //1.在commons-codec-1.6.jar!\org\apache\commons\codec\language\bm目录下
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("org/apache/commons/codec/language/bm/ash_approx_any.txt");

        byte[] b = new byte[10240];
        is.read(b);

        System.out.println(new String(b));

        System.out.println("-----------------------------------------------------");
        //2.就在mvel2-2.2.8.Final.jar包的根目录下
//        InputStream is2 = TestReadProperties.class.getResourceAsStream("/build.properties");
        InputStream is2 = Thread.currentThread().getContextClassLoader().getResourceAsStream("build.properties");

        byte[] b2 = new byte[20480];
        is2.read(b2);

        System.out.println(new String(b2));

    }

}
