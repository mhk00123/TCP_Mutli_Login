package com.lili.netstudy;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
  *  接數端
 * 1. 創建
 * 2. 準備包裹 - 字節數組
 * 3. 收包
 * 4. 拿到數據 - 分析
 * 5. 釋放資源
 * @author LiLi-PC
 *
 */
public class udpServer {
	public static void main(String[] args) throws IOException {
		System.out.println("接收端啟動.....");
		
		//1. 使用DatagramSocket 指定端口
		DatagramSocket server = new DatagramSocket(6666);
		
		//2. 準備容器 -> 容器封裝成DatagramPacket
		byte[] container = new byte[1024*60]; //最大不能超過60k
		DatagramPacket packet = new DatagramPacket(container, 0, container.length);
		
		//3. 收包
		server.receive(packet);
		
		//4. 分析數據 = 解包
		byte[] datas = packet.getData();
//		int len = packet.getLength();
		
		DataInputStream dis = new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(datas)));
		String msg = dis.readUTF();
		int age = dis.readInt();
		boolean flag = dis.readBoolean();
		char cha = dis.readChar();
		
		System.out.println(msg +" --> "+ age+" --> "+flag +" --> "+ cha);
		
		//5. 釋放資源
		server.close();
	}
}
