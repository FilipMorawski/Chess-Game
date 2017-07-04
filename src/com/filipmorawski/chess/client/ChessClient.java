package com.filipmorawski.chess.client;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.net.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

import java.io.*;

public class ChessClient {
	
	private String url;
	private int portNumber;
	private ChooseServerDialog dialog;
	private Socket socket;
	private Scanner scanner;
	
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	private TestingObject object1;
	private TestingObject object2;
	private TestingObject object3;
	
	private MessageReceivedDialog messageDialog;
	
// Main Thread	
	public static void main(String[] args) {
		new ChessClient().start();
	}

	public void start() {
		dialog = new ChooseServerDialog();
		getAdressToConnect();
		connectToServer(url, portNumber);
		if(this.socket.isConnected()) {
			System.out.println("Connection estabilished");
			JOptionPane.showMessageDialog(null, "Succesfully connected to server", "Connection estabilished", JOptionPane.INFORMATION_MESSAGE);
		} else {
			System.out.println("Can't connect to server. Server is full or you entered wrong adress");	
		}
		createTestingObjects();
		Thread send = new Thread(new SendAndReceive(object1, socket, ois));
		send.start();
	}


	public void createTestingObjects() {
		object1 = new TestingObject("Filip", 27, "Pistolet");
		object2 = new TestingObject("Jacek", 28, "Knaga");
		object3 = new TestingObject("Guzy", 21, "Wielki Brzuch");	
	}
	
	public void connectToServer(String url, int portNumber) {
		try {
			this.socket = new Socket(url, portNumber);
			this.ois = new ObjectInputStream(this.socket.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Server is full");
			e.printStackTrace();
		} 
		
	}

	public void getAdressToConnect() {
		boolean isUrl = false;
		while (!isUrl) {
			url = dialog.getUrl();
			portNumber = dialog.getPortNumber();
			if(url != null && portNumber != 0) {
				isUrl = true;
			}
		}
		System.out.println("Connecting to " + url + ":" + portNumber);		
	}	
}
