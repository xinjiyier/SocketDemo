package com.cxp.socketserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ChatSocket extends Thread{
	public static Socket socket;
	private ChatSocketManager csManager;
	private boolean isConnet = true;
	ChatSocket(Socket s){
		this.socket = s;
	}
	
	private void out(String out){
		try {
			socket.getOutputStream().write(out.getBytes("UTF-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			isConnet = false;
			e.printStackTrace();
		}
	}
	
	public void run(){
		int count = 0;
		csManager = ChatSocketManager.getChatSocketManager();
		isConnet = true;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			String line = null;
			while((line = br.readLine())!=null){
				csManager.publishMessages(this, line);
/*			try {
				out("count := "+count++);
				sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			//监听客户端的输入
			}
			if(br != null){
				br.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
