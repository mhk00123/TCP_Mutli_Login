package com.lili.netstudy;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**�����n�� ��V
 * 1. �ǳƥΤ�b���K�X
 * 2. �إ߳s�� - Socket
 * 3. �ާ@ - ��J��X�y
 * 4. ����귽
 * @author LiLi-PC
 *
 */
public class TcpLoginClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("---------�Ұ�Client---------");
		
		//1. �ǳƥΤ�b���K�X
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("��J�Τ�W");
		String uName = console.readLine();
		System.out.println("��J�K�X");
		String uPwd = console.readLine();
		
		//2.
		Socket client = new Socket("localhost", 8888);
		
		//3.
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		dos.writeUTF("uname="+uName+"&upwd="+uPwd);
		dos.flush();
		
		//�����^��
		DataInputStream dis = new DataInputStream(client.getInputStream());
		String result = dis.readUTF();
		System.out.println(result);
		
		//4.
		dos.close();
		client.close();
		
		
	}
}
