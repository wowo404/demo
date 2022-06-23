import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.liu.model.ChannelProductReq;
import org.liu.obj.Superior;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Test {

    private final static long DATACENTER_BIT = 8;
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);

    public static void main(String[] args) throws InterruptedException {
        Date date = new Date(1654704000000L);
        System.out.println(date);
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);
        System.out.println(date.getTime());
    }

    public static boolean isNormalized(String path) {
        if (path == null) {
            return true;
        }

        if (path.indexOf("//") > -1) {
            return false;
        }

        for (int j = path.length(); j > 0;) {
            int i = path.lastIndexOf('/', j - 1);
            int gap = j - i;

            if (gap == 2 && path.charAt(i + 1) == '.') {
                // ".", "/./" or "/."
                return false;
            } else if (gap == 3 && path.charAt(i + 1) == '.' && path.charAt(i + 2) == '.') {
                return false;
            }

            j = i;
        }

        return true;
    }

    public static void splitByRNT() {
        String s = "\"BusinessException(resultCode=USERNAME_ALREADY_EXISTS)\r\n\tat com.jmev.ehr.service.impl.UserServiceImpl.check(UserServiceImpl.java:82)\\r\\n\\tat com.jmev.ehr.service.impl.UserServiceImpl.add(UserServiceImpl.java:70)\\r\\n\\tat com.jmev.ehr.service.impl.UserServiceImpl$$FastClassBySpringCGLIB$$2b4eb81.invoke(<generated>)\\r\\n\\tat org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\\r\\n\\tat org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:687)\\r\\n\\tat com.jmev.ehr.service.impl.UserServiceImpl$$EnhancerBySpringCGLIB$$9fe67ee5.add(<generated>)\\r\\n\\tat com.jmev.ehr.api.impl.UserApiImpl.add(UserApiImpl.java:52)\\r\\n\\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\\r\\n\\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\\r\\n\\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\\r\\n\\tat java.lang.reflect.Method.invoke(Method.java:498)\\r\\n\\tat org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:190)\\r\\n\\tat org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:138)\\r\\n\\tat org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:105)\\r\\n\\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:878)\\r\\n\\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:792)\\r\\n\\tat org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\\r\\n\\tat org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1040)\\r\\n\\tat org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:943)\\r\\n\\tat org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\\r\\n\\tat org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:909)\\r\\n\\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:652)\\r\\n\\tat org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\\r\\n\\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:733)\\r\\n\\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)\\r\\n\\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\\r\\n\\tat org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\\r\\n\\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\\r\\n\\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\\r\\n\\tat org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\\r\\n\\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\\r\\n\\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\\r\\n\\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\\r\\n\\tat org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\\r\\n\\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\\r\\n\\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\\r\\n\\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\\r\\n\\tat org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\\r\\n\\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119)\\r\\n\\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\\r\\n\\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\\r\\n\\tat com.jmev.common.spring.filter.SessionContextHolderFilter.doFilter(SessionContextHolderFilter.java:36)\\r\\n\\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)\\r\\n\\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)\\r\\n\\tat org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202)\\r\\n\\tat org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)\\r\\n\\tat org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:541)\\r\\n\\tat org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:143)\\r\\n\\tat org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\\r\\n\\tat org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)\\r\\n\\tat org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\\r\\n\\tat org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:374)\\r\\n\\tat org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\\r\\n\\tat org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:868)\\r\\n\\tat org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1590)\\r\\n\\tat org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\\r\\n\\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\\r\\n\\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)\\r\\n\\tat org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\\r\\n\\tat java.lang.Thread.run(Thread.java:748)\\r\\n\"";
        String[] split = s.split("\r\n\t");
        System.out.println(split[0]);
        String substring = split[0].substring(split[0].indexOf("=") + 1, split[0].length() - 1);
        System.out.println(substring);
    }

    public static void splitURL() {
        String url = "http://msw.0797gzwz.com";
        String replace = url.replace("http://", "");
        int firstSlashIndex = replace.indexOf("/");
        System.out.println(firstSlashIndex);

        String pathNotContainDomain = replace.substring(firstSlashIndex);
        System.out.println(pathNotContainDomain);
    }

    public static String transferWarningDurationTime(Long durationTime) {
        String newTime = "";
        long surplusMillis = durationTime;
        if (surplusMillis >= 86400000) {//1天
            newTime += (surplusMillis / 86400000) + "天";
            surplusMillis = surplusMillis % 86400000;
        }
        if (surplusMillis >= 3600000) {//1小时
            newTime += (surplusMillis / 3600000) + "小时";
            surplusMillis = surplusMillis % 3600000;
        }
        if (surplusMillis >= 60000) {//1分钟
            newTime += (surplusMillis / 60000) + "分钟";
            surplusMillis = surplusMillis % 60000;
        }
        if (durationTime < 3600000) {
            if (surplusMillis >= 1000) {//1秒
                newTime += (surplusMillis / 1000) + "秒";
                surplusMillis = surplusMillis % 1000;
            }
        }
        return newTime;
    }

    public static void replaceAll() {
        String html = "<div><img data-local=\"wxfile://tmp_dd542a6ef31983c463f5bcf6a6986bed.jpg\" src=\"abc\"><p>dsfdf</p><img data-local=\"wxfile://tmp_ab543a6ef31983c463f5bcf6a6986bed.jpg\" src=\"abc\"></div>";
        String s = html.replaceAll("data-local=\"wxfile://([a-z0-9_\\.]*)\"", "");
        System.out.println(s);

        String url = "http://manmanbj-note.oss-accelerate.aliyuncs.com/note-api/20201112/jpg/2304855594082205.jpg?Expires=1920526583&amp;OSSAccessKeyId=LTAI4GDL3L6KKcUP9jgX3d4y&amp;Signature=QdxpedbIkNMuK%2F7OOKdiaIf2o6Q%3D?dd";
        String replaceAll = url.replaceAll("\\?.*", "");
        System.out.println(replaceAll);
        String substring = replaceAll.substring(replaceAll.lastIndexOf("/") + 1);
        System.out.println(substring);
    }

    public static int plus() {
        int j = 0;
        int a = j++ + 10;
        System.out.println(a);//输出10
        int b = 0;
        boolean flag = b++ == 1 ? true : false;
        System.out.println(flag);//输出false

        int c = 0;
        if (c++ == 0) System.out.println(c);//idea有提示，if括号中永远为true这里输出了1，说明这个if虽然没有写大括号，但sout的那行代码跟if不是同一行代码

        int i = 0;
//        return ++i;
        return i++;//这里i++没有起作用，返回的还是0
    }

    public static void f() {
        String[] a = new String[2];
        Object[] b = a;
        a[0] = "hi";
        b[1] = Integer.valueOf(42);
    }

    public static void math() {
        System.out.println(MAX_DATACENTER_NUM);
        System.out.println(4 % 2);
        binaryOperating();
    }

    public static void objectMethod() {
        System.out.println(Test.class.getName());
        System.out.println(clazzEqual(ChannelProductReq.class));
    }

    public static void uuid() {
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
    }

    public static void substring() {
        System.out.println("".split(",").length);
        System.out.println(",".split(",").length);
        System.out.println("1,".split(",").length);
        System.out.println(",1".split(",").length);
        System.out.println("1,1".split(",").length);
        String name = "中国银行杭州支行";
        System.out.println(name.substring(0, name.length() - 1));
        String filePath = "D:/home/manufacture/file/2019\\11\\22\\0000000000000145-8-20191122165539.txt.fft";
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
        System.out.println(fileName);
    }

    public static void index() {
        String img = "sfsd.jpg";
        System.out.println(img.substring(img.lastIndexOf("\\.") + 1));
    }

    public static void date(Date test) {
        Date date = new Date(1480166465631L);
        System.out.println(date);
        test = date;
    }

    public static void startsWith() {
        String str = "/a";
        System.out.println(str.startsWith("/"));
    }

    public static void binaryOperating() {
        int vv = 0;
        vv |= 2;//等同于 vv = vv | 2，将vv和2转为二进制后的与运算，有一真为真，同假为假
        System.out.println(vv);
        int a = 0 & 1;
        System.out.println(a);
    }

    public static void format() {
        String name = "中国银行杭州支行";
        System.out.println(String.format(name));

        String fileName = "130181";
        System.out.println("================  前补零方法一   =================");
        DecimalFormat g1 = new DecimalFormat("0000000");
        String startZeroStr = g1.format(Integer.valueOf(fileName));
        System.out.println("前补零方法一：" + startZeroStr);

        System.out.println("================  前补零方法二   =================");
        startZeroStr = String.format("%07d", Integer.valueOf(fileName));
        System.out.println("前补零方法二：" + startZeroStr);

        System.out.println("================  后补零方法一   =================");
        DecimalFormat g2 = new DecimalFormat("0.000000");
        String endZeroStr = g2.format(Integer.valueOf(fileName));
        System.out.println("后补零：" + endZeroStr);
        System.out.println("虽然后补零出现这种情况,带有小数点");
        System.out.println("比如你要长度要在7位以内，可以这么做");
        System.out.println("后补零转变后：" + endZeroStr.replace(".", "").substring(0, 7));
    }

    public static void deliver() {
        //值传递和引用传递
        Boolean flag = false;
        int a = 1;
        Integer pkgA = 10;
        Superior superior = new Superior();
        superior.setId(100);
        change(flag, 1, pkgA, superior);
        System.out.println(flag + "--" + a + "--" + pkgA + "--" + superior.getId());
    }

    public static void change(Boolean flag, int a, Integer pkgA, Superior superior) {
        flag = true;
        a = 2;
        pkgA = 20;
        superior.setId(200);
    }

    public static boolean clazzEqual(Class clazz) {
        if (clazz.equals(ChannelProductReq.class)) {
            return true;
        }
        return false;
    }

    public static void test1() {

        Map<String, String> map = System.getenv();
        for (Iterator<String> itr = map.keySet().iterator(); itr.hasNext(); ) {
            String key = itr.next();
            System.out.println(key + "=" + map.get(key));
        }

        String a = System.getenv("TNJC_DATACENTID");
        System.out.println(a);

        String machinedId = System.getenv("TNJC_MACHINEDID");
        System.out.println(machinedId);

        BigDecimal rate = null;
        System.out.println(String.valueOf(rate));

        System.out.println(StringUtils.substring("00" + "1", -3));

        List<Integer> list = Arrays.asList(1, 2);
        //this will throw exception
        list.add(3);
    }

    public static void test2() {
        int a = 1;
        int b = ++a + a;
        //int b = 2 + 2;
        System.out.println(b);
        System.out.println(a);
        int c = a++ + a;
        //int c = 2 + 3;
        System.out.println(c);
        System.out.println(a);
    }

    public static void testHashcode() {
        String hashcode = "a";
        System.out.println(hashcode.hashCode());
        Integer hashcodeOfInteger = 10086;
        System.out.println(hashcodeOfInteger.hashCode());
        System.out.println(Integer.toBinaryString(10086));
    }

    public static void testArray() {
        int[] a = {1, 2};
        int[] b = {3, 4};
        int[] c = new int[a.length + b.length];
        for (int i = 0; i < c.length; i++) {
            if (i < a.length) {
                c[i] = a[i];
            } else {
                c[i] = b[i - a.length];
            }
            System.out.println(i);
        }
    }

    public static void binarySearch() {
        String[] ip = {"123", "127.0.0.1", "0.0.0.0"};
        int search = Arrays.binarySearch(ip, "1b1b1b");
        System.out.println(search);
    }

    public static void listContains() {
        String[] ip = {"123", "127.0.0.1", "0.0.0.0"};
        ArrayList<String> list = Lists.newArrayList(ip);
        System.out.println(list.hashCode());
        boolean contains = list.contains("192.168.0.1");
        boolean contains1 = list.contains("127.0.0.1");
        if (!contains && !contains1) {
            System.out.println("invalid ip");
        }
    }

    public static void endWithAny() {
        boolean endsWithAny = StringUtils.endsWithAny("a.JPEG".toLowerCase(), "jpeg");
        System.out.println(endsWithAny);
    }

}
