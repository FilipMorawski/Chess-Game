package com.filipmorawski.chess;

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
	private boolean start;
	private String color;
	private WaitingForPlayerDialog waiting;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
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
	
		Thread receiveStartData = new Thread(new StartReceiver());
		receiveStartData.start();
		if (!start) {
			waiting = new WaitingForPlayerDialog();
		} 
		try {
			receiveStartData.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (start) {
			if (waiting != null) {
				waiting.dispose();
			}
			JOptionPane.showMessageDialog(null, ("You're playing with " + color + " , let's start!") , "Let's start!", JOptionPane.INFORMATION_MESSAGE);
		} 		
		Thread send = new Thread(new SendAndReceive(socket, ois, color));
		send.start();
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

	class StartReceiver extends Thread {

		@Override
		public void run() {
			try {
				while (true) {
					Object obj;
					while ((obj = ois.readObject()) != null) {
						String object1 = String.valueOf(obj);
						boolean object2 = (boolean) ois.readObject();
						color = object1;
						start = object2;
						System.out.println("Receive object " + object1 + "\n Start? : " +  object2);
						break;
					}
					if ((color != null) && start == true) {
						System.out.println("Breaking");
						break;
					}
				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}

