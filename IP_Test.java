package com.lili.netstudy;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IP_Test {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress addr = InetAddress.getLocalHost();
		
		System.out.println(addr.getHostAddress());
		System.out.println(addr.getHostName());
		
		
		InetAddress addr2  = InetAddress.getByName("www.google.com");
		System.out.println(addr2.getHostAddress());
		System.out.println(addr2.getHostName());
	}
}
