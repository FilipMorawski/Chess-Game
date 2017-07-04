package com.filipmorawski.chess.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionData implements Runnable{

	private Socket clientSocket;
	private ObjectOutputStream oos; 
	private ArrayList<ObjectOutputStream> outStreams;
	private ArrayList<String> avaibleColours;
	private boolean start;
	
	public ConnectionData(Socket clientSocket, ArrayList<ObjectOutputStream> outStreams, ArrayList<String> avaibleColours, boolean start) {
		this.clientSocket = clientSocket;
		this.outStreams = outStreams;
		this.start = start;
		this.avaibleColours = avaibleColours;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < outStreams.size(); i++) {
				oos = outStreams.get(i);
				oos.writeObject(avaibleColours.get(i));
				oos.writeObject(start);
				oos.flush();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
