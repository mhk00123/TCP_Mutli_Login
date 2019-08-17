package com.lili.netstudy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP�y�{
 * 1. ���w�ݤf �ϥ�ServerSocket
 * 2. ���릡���ݳs��
 * 3. �ާ@: ��J��X�y
 * +  ���R
 * 4. ����귽 
 * @author LiLi-PC
 *
 */
public class TcpLoginServer {
	public static void main(String[] args) throws IOException {
		System.out.println("---------�Ұ�Server---------");
		
		//1.
		ServerSocket server = new ServerSocket(8888);
		
		//2.
		Socket client = server.accept();
		System.out.println("�@�ӫȤ�ݫإߤF�s��");
		
		//3.
		DataInputStream dis = new DataInputStream(client.getInputStream());
		String datas = dis.readUTF();
		
		String uname = "";
		String upwd = "";
		//���R
		/* "uname=uName&upwd=uPwd */
		String[] dataArray = datas.split("&");  //�H"&"������
		for(String info:dataArray) {
			String[] userInfo = info.split("="); //�A�H"="����
			if(userInfo[0].equals("uname")) {
				System.out.println("�A���Τ�W��:" + userInfo[1]);
				uname = userInfo[1];
			}
			else if(userInfo[0].equals("upwd")){
				System.out.println("�A���K�X��:" + userInfo[1]);
				upwd = userInfo[1];
			}
		}
		
		//�^�ǵ��G
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		if((uname.equals("LiLi"))&&(upwd.equals("123456"))) {
			dos.writeUTF("�n�����\");
		}else {
			dos.writeUTF("�n������ - �Τ�W / �K�X���~");
		}
		dos.flush();
		
		//4.
		dis.close();
		dos.close();
		client.close();
		
	}
}
