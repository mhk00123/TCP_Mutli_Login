package com.lili.netstudy;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * �Ȥ��
 * 1. �إ߳s��, �ϥ�Socket + Server�ݪ��a�}�Mport
 * 2. �ާ@, ��J��X�y�ާ@
 * 3. ����귽
 * @author LiLi-PC
 *
 */
public class TcpClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("---------�Ұ�Client---------");
		
		// 1. �إ߳s��, �ϥ�Socket + Server�ݪ��a�}�Mport
		Socket client = new Socket("localhost", 8888);
		
		// 2. �ާ@, ��J��X�y�ާ@
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		String data = "Hello Man";
		dos.writeUTF(data);
		dos.flush();
		
		// 3. ����귽
		dos.close();
		client.close();
	}
}
