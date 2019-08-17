package com.lili.netstudy;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 客戶端
 * 1. 建立連接, 使用Socket + Server端的地址和port
 * 2. 操作, 輸入輸出流操作
 * 3. 釋放資源
 * @author LiLi-PC
 *
 */
public class TcpClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("---------啟動Client---------");
		
		// 1. 建立連接, 使用Socket + Server端的地址和port
		Socket client = new Socket("localhost", 8888);
		
		// 2. 操作, 輸入輸出流操作
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		String data = "Hello Man";
		dos.writeUTF(data);
		dos.flush();
		
		// 3. 釋放資源
		dos.close();
		client.close();
	}
}
