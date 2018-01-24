package com.qafs.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
public static void main(String[] args) {
	 try {
		String addr = InetAddress.getLocalHost().getHostAddress();
		System.out.println(addr);
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
