package com.lili.netstudy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP流程
 * 1. 指定端口 使用ServerSocket
 * 2. 阻塞式等待連接
 * 3. 操作: 輸入輸出流
 * 4. 釋放資源 
 * @author LiLi-PC
 *
 */
public class TcpServer {
	public static void main(String[] args) throws IOException {
		System.out.println("---------啟動Server---------");
		// 1. 指定端口 使用ServerSocket
		ServerSocket server = new ServerSocket(8888);
		
		// 2. 阻塞式等待連接
		Socket client = server.accept();  //return Socket文件
		System.out.println("一個客戶端建立了連接");
		
		// 3. 操作: 輸入輸出流
		DataInputStream dis = new DataInputStream(client.getInputStream());
		String data = dis.readUTF();
		System.out.println(data);
		
		// 4. 釋放資源  //Server端不會關
		dis.close();
		client.close();
		
	}
}
