package com.cxp.socketserver;

import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.JOptionPane;

public class SocketServerDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SocketListener().start();
	}
}
