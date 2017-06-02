package com.cxp.socketserver;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

public class ChatSocketManager {
	public static final ChatSocketManager csm = new ChatSocketManager();
	private Vector<ChatSocket> vector = new Vector<ChatSocket>();
	private  ChatSocket cs;
	
	public static ChatSocketManager getChatSocketManager(){
		return csm;
	}
	/**
	 * 写一个私有的空的构造方法，实现单例模式
	 */
	private ChatSocketManager(){}
	
	public void addChatSocket(ChatSocket s){
		vector.add(s);
	}
	
	public void publishMessages(ChatSocket chatSocket,String message){
		System.out.println("message := "+message+"vector.size():= "+vector.size());
		for(int i = 0;i<vector.size();i++){
			cs = vector.get(i);
			if(chatSocket!=null&&(!chatSocket.equals(cs))){
				try {
					System.out.println("message := "+message+"  i := "+i);
					cs.socket.getOutputStream().write(message.getBytes("UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
