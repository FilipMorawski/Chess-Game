package com.filipmorawski.chess;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.filipmorawski.chess.GameBoard;

// Sending info to server and receive data from it
public class SendAndReceive implements Runnable {

	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private MessageReceivedDialog messageDialog;
	private String color;

	public SendAndReceive(Socket socket, ObjectInputStream ois, String color) {
		this.ois = ois;
		this.socket = socket;
		this.color = color;
	}

	@Override
	public void run() {
		GameBoard board = new GameBoard(color);
		if (board.isAvaible()) {
			JOptionPane.showMessageDialog(board, "It's Your turn!", "Your move", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(board, "It's yours opponent turn", "Please wait",
					JOptionPane.INFORMATION_MESSAGE);
		}

		Thread receiver = new Thread(new Receiver());
		receiver.start();
		while (true) {
			try {
				while (WhoseTurn.sendToServer) {
					oos = new ObjectOutputStream(this.socket.getOutputStream());
					oos.writeObject(ButtonMap.map);
					oos.writeObject(Figures.figures);
					oos.writeObject(Figures.figuresPositionMap);
					oos.writeObject(MovesCount.movesCount);
					oos.writeObject(WhoseTurn.whiteTurn);
					oos.writeObject(WhoseTurn.blackTurn);
					oos.flush();

					System.out.println("Wys³a³em na serwer");
					System.out.println(ButtonMap.map.size());
					WhoseTurn.sendToServer = false;
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
				try {
					Object object;
					ArrayList<FieldButton> object1 = null;
					while ((object = ois.readObject()) != null) {
						object1 = (ArrayList<FieldButton>) object;
						ArrayList<Figure> object2 = (ArrayList<Figure>) ois.readObject();
						ArrayList<String> object3 = (ArrayList<String>) ois.readObject();
						int object4 = (int) ois.readObject();
						boolean object5 = (boolean) ois.readObject();
						boolean object6 = (boolean) ois.readObject();

						Figures.figures = object2;
						Figures.figuresPositionMap = object3;
						ButtonMap.map = object1;
						MovesCount.movesCount = object4;
						WhoseTurn.whiteTurn = object5;
						WhoseTurn.blackTurn = object6;

						GameBoard board = ActualGB.actualGB;
						board.dispose();
						board = new GameBoard(color);
						if (board.isAvaible()) {
							JOptionPane.showMessageDialog(board, "It's Your turn!", "Your move",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(board, "It's yours opponent turn", "Please wait",
									JOptionPane.INFORMATION_MESSAGE);
						}
						new CheckMate().check();

					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
