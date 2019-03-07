
import java.math.BigDecimal;
import java.util.*;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.liu.enums.ServiceName;
import org.liu.model.ChannelProductReq;

public class Test {
	public static void main(String[] args) {

        test2();

		System.out.println(Test.class.getName());

		System.out.println(4 % 2);
		
		System.out.println(clazzEqual(ChannelProductReq.class));
		Boolean flag = false;
		change(flag);
		System.out.println(flag);
	}
	
	public static void change(Boolean flag) {
		flag = true;
	}
	
	public static boolean clazzEqual(Class clazz) {
		if(clazz.equals(ChannelProductReq.class)) {
			return true;
		}
		return false;
	}
	
	public void test1() {
		System.out.println(ServiceName.directSignClient.name());
		
		Map<String, String> map = System.getenv();
		for (Iterator<String> itr = map.keySet().iterator(); itr.hasNext();) {
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
		
		List<Integer> list = Arrays.asList(1,2);
		//this will throw exception
		list.add(3);
	}

	public static void test2(){
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

    public static void testHashcode(){
        String hashcode = "a";
        System.out.println(hashcode.hashCode());
        Integer hashcodeOfInteger = 10086;
        System.out.println(hashcodeOfInteger.hashCode());
        System.out.println(Integer.toBinaryString(10086));
    }

    public static void testArray(){
        int[] a = {1,2};
        int[] b = {3,4};
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

    public static void binarySearch(){
        String[] ip = {"123", "127.0.0.1", "0.0.0.0"};
        int search = Arrays.binarySearch(ip, "1b1b1b");
        System.out.println(search);
    }

    public static void listContains(){
        String[] ip = {"123", "127.0.0.1", "0.0.0.0"};
        ArrayList<String> list = Lists.newArrayList(ip);
        System.out.println(list.hashCode());
        boolean contains = list.contains("192.168.0.1");
        boolean contains1 = list.contains("127.0.0.1");
        if (!contains && !contains1) {
            System.out.println("invalid ip");
        }
    }

    public static void endWithAny(){
        boolean endsWithAny = StringUtils.endsWithAny("a.JPEG".toLowerCase(), "jpeg");
        System.out.println(endsWithAny);
    }

}