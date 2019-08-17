package com.lili.netstudy;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
  *  ���ƺ�
 * 1. �Ы�
 * 2. �ǳƥ]�q - �r�`�Ʋ�
 * 3. ���]
 * 4. ����ƾ� - ���R
 * 5. ����귽
 * @author LiLi-PC
 *
 */
public class udpServer {
	public static void main(String[] args) throws IOException {
		System.out.println("�����ݱҰ�.....");
		
		//1. �ϥ�DatagramSocket ���w�ݤf
		DatagramSocket server = new DatagramSocket(6666);
		
		//2. �ǳƮe�� -> �e���ʸ˦�DatagramPacket
		byte[] container = new byte[1024*60]; //�̤j����W�L60k
		DatagramPacket packet = new DatagramPacket(container, 0, container.length);
		
		//3. ���]
		server.receive(packet);
		
		//4. ���R�ƾ� = �ѥ]
		byte[] datas = packet.getData();
//		int len = packet.getLength();
		
		DataInputStream dis = new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(datas)));
		String msg = dis.readUTF();
		int age = dis.readInt();
		boolean flag = dis.readBoolean();
		char cha = dis.readChar();
		
		System.out.println(msg +" --> "+ age+" --> "+flag +" --> "+ cha);
		
		//5. ����귽
		server.close();
	}
}
