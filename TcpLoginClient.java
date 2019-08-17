package com.lili.netstudy;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**模擬登錄 單向
 * 1. 準備用戶帳號密碼
 * 2. 建立連接 - Socket
 * 3. 操作 - 輸入輸出流
 * 4. 釋放資源
 * @author LiLi-PC
 *
 */
public class TcpLoginClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("---------啟動Client---------");
		
		//1. 準備用戶帳號密碼
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("輸入用戶名");
		String uName = console.readLine();
		System.out.println("輸入密碼");
		String uPwd = console.readLine();
		
		//2.
		Socket client = new Socket("localhost", 8888);
		
		//3.
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		dos.writeUTF("uname="+uName+"&upwd="+uPwd);
		dos.flush();
		
		//接收回傳
		DataInputStream dis = new DataInputStream(client.getInputStream());
		String result = dis.readUTF();
		System.out.println(result);
		
		//4.
		dos.close();
		client.close();
		
		
	}
}
