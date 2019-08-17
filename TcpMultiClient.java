package com.lili.mutltiLogin;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * TCP流程
 * 1. 指定端口 使用ServerSocket
 * 2. 阻塞式等待連接
 * 3. 操作: 輸入輸出流
 * +  分析
 * 4. 釋放資源 
 * @author LiLi-PC
 *
 */
public class TcpMultiClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("---------啟動Client---------");
		
		//2.
		Socket client = new Socket("localhost", 8888);
		
		//3.
		new Send(client).send();
		
		//接收回傳
		new Receive(client).receive();
		
		//4.
		client.close();	
	}
	
	static class Send{
		
		private DataOutputStream dos;
		private BufferedReader console;
		private Socket client;
		private String msg;
		//Constructor
		public Send(Socket client) {
			this.client = client;
			console = new BufferedReader(new InputStreamReader(System.in));
			this.msg = init();
			
			try {
				dos = new DataOutputStream(client.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		private String init() {
			try {
				System.out.println("輸入用戶名");
				String uName = console.readLine();
				System.out.println("輸入密碼");
				String uPwd = console.readLine();
				
				return "uname="+uName+"&upwd="+uPwd;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "";
		}
		
		public void send() {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
		
	}
	
	static class Receive{
		private DataInputStream dis;
		private Socket client;
		public Receive(Socket client) {
			this.client = client;
			try {
				dis = new DataInputStream(client.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void receive() {
			String result;
			try {
				result = dis.readUTF();
				System.out.println(result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
