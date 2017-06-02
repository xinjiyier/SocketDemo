package com.cxp.socketserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class SocketListener extends Thread{
	private ChatSocket cs;
	public void run(){
		//����һ�� socket ����˶���
		try {
			ServerSocket serverSocket = new ServerSocket(12345);
			while(true){
				//�ȴ����ӵ���� �˿�
				Socket socket = serverSocket.accept();
				JOptionPane.showMessageDialog(null,"�пͻ������ӵ��˱���");
				cs = new ChatSocket(socket);
				cs.start();
				ChatSocketManager.getChatSocketManager().addChatSocket(cs);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
