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
  * 發送端
 * 1. 創建
 * 2. 準備好字節數組
 * 3. 打包 - 地址
 * 4. 發送
 * 5. 釋放資源
 * @author LiLi-PC
 *
 */
public class udpSend {
	public static void main(String[] args) throws IOException {
		System.out.println("發送方啟動中");
		
		//1.  創建發送端 - 使用DatagramSocket 指定端口
		DatagramSocket client = new DatagramSocket(9999);
		
		//2. 準備數據 - 轉成字節數組
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(baos));
		//2.1操作數據類型 + 數據類型
		//下方用write會寫入一個類似Array的物件
		dos.writeUTF("編碼辛酸");
		dos.write(18);
		dos.writeBoolean(true);
		dos.writeChar('c');
		dos.flush();
		byte[] datas = baos.toByteArray();
		
		//3. 封裝DatagramPacket(包裹) + 目的地
		DatagramPacket packet = new DatagramPacket(datas, 0, datas.length,
				                                  new InetSocketAddress("localhost", 6666));
		
		//4. 發送包裹
		client.send(packet);
		
		//5. 釋放資源
		client.close();
	}
}
