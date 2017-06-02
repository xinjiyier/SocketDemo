package com.cxp.socketserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class SocketListener extends Thread{
	private ChatSocket cs;
	public void run(){
		//创建一个 socket 服务端对象
		try {
			ServerSocket serverSocket = new ServerSocket(12345);
			while(true){
				//等待连接到这个 端口
				Socket socket = serverSocket.accept();
				JOptionPane.showMessageDialog(null,"有客户端连接到了本机");
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
