package org.liu.generator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 序列生成类
 * @author liuzhsh
 */
public class SerialNumGenerator {

	private static Object obj = new Object();
	private String key = "000000";
	private static SerialNumGenerator generator = null;

	private SerialNumGenerator(){}
	
	public static final SerialNumGenerator getInstance() {
		synchronized (obj) {
			if(null == generator){
				generator = new SerialNumGenerator();
			}
		}
		return generator;
	}

	public synchronized String getNextKey() {
		String retStr = "";
		int keyIntValue = Integer.parseInt(key);
		key = String.valueOf(keyIntValue + 1);
		if (keyIntValue < 100000) {
			retStr = key;
			for (int i = 0; i < 6 - key.length(); i++)
				retStr = "0" + retStr;
			key = retStr;
		}
		if (Long.parseLong(key) > 999999)
			key = "000001";
		return key;
	}
	
	public String getNumWithNowDate(){
	    String nowDateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	    return nowDateStr+getNextKey();
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			Runnable thread = new Runnable() {
				
				@Override
				public void run() {
					System.out.println(SerialNumGenerator.getInstance().getNumWithNowDate());
				}
			};
			new Thread(thread).start();
		}
	}
	
}
