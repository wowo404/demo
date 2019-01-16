package org.liu.computer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;

public class ProgramGetComputerInfo {

	public static void main(String[] args) throws IOException {
		String computerName = System.getenv().get("COMPUTERNAME");
		System.out.println(computerName);
//		System.out.println(getMyIP());
		System.out.println(getMyIPLocal());
		System.out.println(getLocalIp());
	}

	private static String getMyIP() throws IOException {
		InputStream ins = null;
		try {
			URL url = new URL("http://iframe.ip138.com/ic.asp");
			URLConnection con = url.openConnection();
			ins = con.getInputStream();
			InputStreamReader isReader = new InputStreamReader(ins, "GB2312");
			BufferedReader bReader = new BufferedReader(isReader);
			StringBuffer webContent = new StringBuffer();
			String str = null;
			while ((str = bReader.readLine()) != null) {
				webContent.append(str);
			}
			int start = webContent.indexOf("[") + 1;
			int end = webContent.indexOf("]");
			return webContent.substring(start, end);
		} finally {
			if (ins != null) {
				ins.close();
			}
		}
	}

	private static String getMyIPLocal() throws IOException {
		InetAddress ia = InetAddress.getLocalHost();
		return ia.getHostAddress();
	}
	
	private static String getLocalIp() throws IOException{
		Process process = Runtime.getRuntime().exec("ipconfig -all");
		BufferedReader bufReader = new BufferedReader(new InputStreamReader(process.getInputStream(),"GBK"));
		StringBuffer sb = new StringBuffer();
		String line = null;
		while ((line = bufReader.readLine()) != null){
			sb.append(line).append("\n");
		}
		return sb.toString();
	}

}
