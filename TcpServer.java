package com.lili.netstudy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP�y�{
 * 1. ���w�ݤf �ϥ�ServerSocket
 * 2. ���릡���ݳs��
 * 3. �ާ@: ��J��X�y
 * 4. ����귽 
 * @author LiLi-PC
 *
 */
public class TcpServer {
	public static void main(String[] args) throws IOException {
		System.out.println("---------�Ұ�Server---------");
		// 1. ���w�ݤf �ϥ�ServerSocket
		ServerSocket server = new ServerSocket(8888);
		
		// 2. ���릡���ݳs��
		Socket client = server.accept();  //return Socket���
		System.out.println("�@�ӫȤ�ݫإߤF�s��");
		
		// 3. �ާ@: ��J��X�y
		DataInputStream dis = new DataInputStream(client.getInputStream());
		String data = dis.readUTF();
		System.out.println(data);
		
		// 4. ����귽  //Server�ݤ��|��
		dis.close();
		client.close();
		
	}
}
