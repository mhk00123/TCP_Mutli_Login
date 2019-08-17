package com.lili.mutltiLogin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
public class TcpMultiServer {
	public static void main(String[] args) throws IOException {
		System.out.println("---------啟動Server---------");
		
		//1.
		ServerSocket server = new ServerSocket(8888);
		
		//封裝多線程
		while(true) {
			//2.
			Socket client = server.accept();
			System.out.println("一個客戶端建立了連接");
			new Thread(new Channel(client)).start();
		}
	}
	
	static class Channel implements Runnable{
		private Socket client;
		private DataInputStream dis;                                    //輸入流
		private DataOutputStream dos;									//輸出流
		
		//Constructor
		public Channel(Socket client) {
			this.client = client;

			try {
				dis = new DataInputStream(client.getInputStream());     //輸入流init
				dos = new DataOutputStream(client.getOutputStream());   //輸出流init
			} catch (IOException e) {
				e.printStackTrace();
				release();
			}
		}
		
		//接收數據
		private String receive() {
			String datas = "";                        					 //如果出了error返回空值
			try {
				datas = dis.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return datas;
		}
		
		//回傳數據
		private void send(String msg) {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//釋放資源
		private void release() {
			try {
				if(dis != null) {
					dis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(dos != null) {
					dos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(client != null) {
					client.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void run() {			
			String uname = "";
			String upwd = "";
			//分析
			/* "uname=uName&upwd=uPwd */
			String[] dataArray = receive().split("&");  //以"&"號分割
			for(String info:dataArray) {
				String[] userInfo = info.split("="); //再以"="分割
				if(userInfo[0].equals("uname")) {
					System.out.println("你的用戶名為:" + userInfo[1]);
					uname = userInfo[1];
				}
				else if(userInfo[0].equals("upwd")){
					System.out.println("你的密碼為:" + userInfo[1]);
					upwd = userInfo[1];
				}
			}
			
			//回傳結果
			if((uname.equals("LiLi"))&&(upwd.equals("123456"))) {
				send("登錄成功");
			}else {
				send("登錄失敗 - 用戶名 / 密碼錯誤");
			}
			//4.
			release();
		}
	}
}
