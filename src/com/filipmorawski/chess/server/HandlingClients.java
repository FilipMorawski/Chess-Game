package com.filipmorawski.chess.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import com.filipmorawski.chess.client.TestingObject;

public class HandlingClients implements Runnable {

	private Socket clientSocket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private ArrayList<ObjectOutputStream> out;
	
	public HandlingClients (Socket clientSocket, ArrayList<ObjectOutputStream> out) {
		this.clientSocket = clientSocket;
		this.out = out;
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				this.ois = new ObjectInputStream(clientSocket.getInputStream());
				System.out.println("Waiting for action");
				Object obj = null;
				while ((obj = ois.readObject()) == null) {}
					Object obj2 = ois.readObject();
					Object obj3 = ois.readObject();
					Object obj4 = ois.readObject();
					Object obj5 = ois.readObject();
					Object obj6 = ois.readObject();
					spreadToClients(obj, obj2, obj3, obj4, obj5, obj6);
					
					System.out.println("Odebra³em od Klienta");
					System.out.println(obj.hashCode());
				
			}
		} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public synchronized void spreadToClients(Object obj,Object obj2,Object obj3,Object obj4,Object obj5,Object obj6) {
		try {
			System.out.println("Iloœæ streamów : "+ out.size());
			
			for(int i = 0; i<out.size(); i++)  {
				out.get(i).writeObject(obj);
				out.get(i).writeObject(obj2);
				out.get(i).writeObject(obj3);
				out.get(i).writeObject(obj4);
				out.get(i).writeObject(obj5);
				out.get(i).writeObject(obj6);
				out.get(i).flush();
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}			
}



