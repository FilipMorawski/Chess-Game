package com.filipmorawski.chess.server;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class ChessServer {

	private ServerSocket serverSocket;
	private ArrayList<Thread> threads = new ArrayList<Thread>();
	private ArrayList<Socket> connectedClients = new ArrayList<Socket>();
	private ArrayList<ObjectOutputStream> outStreams = new ArrayList<ObjectOutputStream>();
	private ArrayList<String> avaibleColours = new ArrayList<String>(); 
	private boolean start;
	
	public static void main(String[] args) {
		new ChessServer().createServer();	
	}


	public void createServer() {
		try {
			serverSocket = new ServerSocket(5353);
			System.out.println("server is ready");
			avaibleColours.add("White");
			avaibleColours.add("Black");
			
			while (connectedClients.size() < 2) {
				try {
					Socket clientSocket = serverSocket.accept();
					connectedClients.add(clientSocket);
					System.out.println("Client Connected!");
					
					ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
					outStreams.add(out);

					if (connectedClients.size() == 2) {
						start = true;
					}
					
					Thread sendStartingData = new Thread(new ConnectionData(clientSocket, outStreams, avaibleColours, start));
//					avaibleColours.remove(0);
					sendStartingData.start();
					
					Thread clientsService = new Thread(new HandlingClients(clientSocket, outStreams));
					threads.add(clientsService);
					clientsService.start();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
