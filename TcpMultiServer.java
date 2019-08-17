package com.lili.mutltiLogin;

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
public class TcpMultiServer {
	public static void main(String[] args) throws IOException {
		System.out.println("---------�Ұ�Server---------");
		
		//1.
		ServerSocket server = new ServerSocket(8888);
		
		//�ʸ˦h�u�{
		while(true) {
			//2.
			Socket client = server.accept();
			System.out.println("�@�ӫȤ�ݫإߤF�s��");
			new Thread(new Channel(client)).start();
		}
	}
	
	static class Channel implements Runnable{
		private Socket client;
		private DataInputStream dis;                                    //��J�y
		private DataOutputStream dos;									//��X�y
		
		//Constructor
		public Channel(Socket client) {
			this.client = client;

			try {
				dis = new DataInputStream(client.getInputStream());     //��J�yinit
				dos = new DataOutputStream(client.getOutputStream());   //��X�yinit
			} catch (IOException e) {
				e.printStackTrace();
				release();
			}
		}
		
		//�����ƾ�
		private String receive() {
			String datas = "";                        					 //�p�G�X�Ferror��^�ŭ�
			try {
				datas = dis.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return datas;
		}
		
		//�^�Ǽƾ�
		private void send(String msg) {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//����귽
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
			//���R
			/* "uname=uName&upwd=uPwd */
			String[] dataArray = receive().split("&");  //�H"&"������
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
			if((uname.equals("LiLi"))&&(upwd.equals("123456"))) {
				send("�n�����\");
			}else {
				send("�n������ - �Τ�W / �K�X���~");
			}
			//4.
			release();
		}
	}
}
