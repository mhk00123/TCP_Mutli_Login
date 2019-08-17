package com.lili.netstudy;

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
public class TcpLoginServer {
	public static void main(String[] args) throws IOException {
		System.out.println("---------啟動Server---------");
		
		//1.
		ServerSocket server = new ServerSocket(8888);
		
		//2.
		Socket client = server.accept();
		System.out.println("一個客戶端建立了連接");
		
		//3.
		DataInputStream dis = new DataInputStream(client.getInputStream());
		String datas = dis.readUTF();
		
		String uname = "";
		String upwd = "";
		//分析
		/* "uname=uName&upwd=uPwd */
		String[] dataArray = datas.split("&");  //以"&"號分割
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
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		if((uname.equals("LiLi"))&&(upwd.equals("123456"))) {
			dos.writeUTF("登錄成功");
		}else {
			dos.writeUTF("登錄失敗 - 用戶名 / 密碼錯誤");
		}
		dos.flush();
		
		//4.
		dis.close();
		dos.close();
		client.close();
		
	}
}
