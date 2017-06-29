package com.filipmorawski.chess;

import java.awt.BorderLayout;

import javax.swing.JFrame;

// Create main window and starting game

public class GameBoard extends JFrame{
	
	public GameBoard() {
		this.setTitle("Chess");
		this.setSize(800,700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(new ChessBoard(), BorderLayout.CENTER);
		this.add(new VerticalPanel(), BorderLayout.WEST);
		this.add(new HorizontalPanel(), BorderLayout.NORTH);
		this.add(new HorizontalPanel(), BorderLayout.SOUTH);
		this.setResizable(true);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		GameBoard gameBoard = new GameBoard();
	}
	
}
