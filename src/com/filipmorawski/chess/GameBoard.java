package com.filipmorawski.chess;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;

// Create main window and starting game

public class GameBoard extends JFrame{
	private boolean avaible = true;
	
	
	public boolean isAvaible() {
		return avaible;
	}

	public void setAvaible(boolean avaible) {
		this.avaible = avaible;
	}

	public GameBoard() {
		this.setTitle("Chess");
		this.setSize(800,700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(new ChessBoard(), BorderLayout.CENTER);
		this.add(new VerticalPanel(), BorderLayout.WEST);
		this.add(new HorizontalPanel(), BorderLayout.NORTH);
		this.add(new HorizontalPanel(), BorderLayout.SOUTH);
		this.setResizable(false);
		this.setVisible(true);
		this.addWindowFocusListener(new FrameListener());
		ActualGB.actualGB = this;


	}
	
	public GameBoard(String color) {
		
		if(color.equals("White") && WhoseTurn.whiteTurn == false) {
			this.avaible = false;
		}
		if (color.equals("Black") && WhoseTurn.blackTurn == false){
			this.avaible = false;
		}
		
		if (MovesCount.movesCount == 0) {
			this.setTitle("Chess");
			this.setSize(800,700);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.add(new ChessBoard(color, avaible), BorderLayout.CENTER);
			this.add(new VerticalPanel(), BorderLayout.WEST);
			this.add(new HorizontalPanel(), BorderLayout.NORTH);
			this.add(new HorizontalPanel(), BorderLayout.SOUTH);
			this.setResizable(false);
			this.setVisible(true);
			this.addWindowFocusListener(new FrameListener());
			ActualGB.actualGB = this;
	
		} else {
			this.setTitle("Chess");
			this.setSize(WindowParameters.width,WindowParameters.height);
			this.setLocation(WindowParameters.x, WindowParameters.y);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.add(new ChessBoard(color, avaible), BorderLayout.CENTER);
			this.add(new VerticalPanel(), BorderLayout.WEST);
			this.add(new HorizontalPanel(), BorderLayout.NORTH);
			this.add(new HorizontalPanel(), BorderLayout.SOUTH);
			this.setResizable(false);
			this.setVisible(true);
			this.addWindowFocusListener(new FrameListener());
			ActualGB.actualGB = this;
		}
		
	}

	public static void main(String[] args) {
		GameBoard gameBoard = new GameBoard();
	}
	

	class FrameListener implements WindowFocusListener {

		@Override
		public void windowGainedFocus(WindowEvent e) {
			GameBoard b = (GameBoard) e.getSource();
			WindowParameters.height = b.getHeight();
			WindowParameters.width = b.getWidth();
			WindowParameters.x = b.getX();
			WindowParameters.y = b.getY();			
		}

		@Override
		public void windowLostFocus(WindowEvent e) {
			GameBoard b = (GameBoard) e.getSource();
			WindowParameters.height = b.getHeight();
			WindowParameters.width = b.getWidth();
			WindowParameters.x = b.getX();
			WindowParameters.y = b.getY();
		}
		

		
	}
	
}
