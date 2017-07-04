package com.filipmorawski.chess.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import com.filipmorawski.chess.GameBoard;

public class SendAndReceive implements Runnable {
	
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private MessageReceivedDialog messageDialog;
	private TestingObject object;
	
	public SendAndReceive(TestingObject object, Socket socket,  ObjectInputStream ois) {

		this.ois = ois;
		this.socket = socket;
		this.object = object;
	}

	@Override
	public void run() {
		this.messageDialog = new MessageReceivedDialog();
		Thread receiver = new Thread(new Receiver());
		receiver.start();
		SendButton button = new SendButton();
		while (true) {
			try {
				while (button.isButtonPressed()) {
					oos = new ObjectOutputStream(this.socket.getOutputStream());
					oos.writeObject(object);
					oos.flush();
					
					System.out.println("Wys³a³em na serwer");
					System.out.println(object.getName());
					button = new SendButton();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}					
	}
	
	class Receiver implements Runnable {

		@Override
		public void run() {
			while (true) {
				try{
					Object object;
					TestingObject object1 = null;
					while ((object = ois.readObject()) != null) {
						object1 = (TestingObject) object;
						
					System.out.println("Odebrana wiadomoœæ : " + object1.getName() + "\n");	

					messageDialog.centralArea.append("Odebrano z serwera \n");
					messageDialog.centralArea.append(object1.getWeapon() + "\n");
					messageDialog.centralArea.append("\n");
					}
				}catch (Exception ex) {
					ex.printStackTrace();
				}	
			}				
		} 
	}
}
