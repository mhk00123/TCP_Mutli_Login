package com.lili.netstudy;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
  * �o�e��
 * 1. �Ы�
 * 2. �ǳƦn�r�`�Ʋ�
 * 3. ���] - �a�}
 * 4. �o�e
 * 5. ����귽
 * @author LiLi-PC
 *
 */
public class udpSend {
	public static void main(String[] args) throws IOException {
		System.out.println("�o�e��Ұʤ�");
		
		//1.  �Ыصo�e�� - �ϥ�DatagramSocket ���w�ݤf
		DatagramSocket client = new DatagramSocket(9999);
		
		//2. �ǳƼƾ� - �ন�r�`�Ʋ�
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(baos));
		//2.1�ާ@�ƾ����� + �ƾ�����
		//�U���write�|�g�J�@������Array������
		dos.writeUTF("�s�X����");
		dos.write(18);
		dos.writeBoolean(true);
		dos.writeChar('c');
		dos.flush();
		byte[] datas = baos.toByteArray();
		
		//3. �ʸ�DatagramPacket(�]�q) + �ت��a
		DatagramPacket packet = new DatagramPacket(datas, 0, datas.length,
				                                  new InetSocketAddress("localhost", 6666));
		
		//4. �o�e�]�q
		client.send(packet);
		
		//5. ����귽
		client.close();
	}
}
