package com.filipmorawski.chess;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChessBoard extends JPanel implements Serializable{
	private JPanel chessBoard;
	private boolean enabled;
	
//Constructor creating buttons which represent chessfields and adding it to static list	
	public ChessBoard() {
		GridLayout grid = new GridLayout(8,8);
		this.setLayout(grid);
		
			for (int i = 0; i<8 ; i++) {
				for (int j = 0; j<8 ; j++) {
					FieldButton button = new FieldButton(i, j);
					this.add(button);
					ButtonMap.map.add(button);
				}	
			}	
	}
	

	public ChessBoard(String color, boolean enabled) {
		
		GridLayout grid = new GridLayout(8,8);
		this.setLayout(grid);
		this.enabled = enabled;
		
		if (MovesCount.movesCount == 0) {
			for (int i = 0; i<8 ; i++) {
				for (int j = 0; j<8 ; j++) {
					FieldButton button = new FieldButton(i, j);
					button.setEnabled(enabled);
					this.add(button);
					ButtonMap.map.add(button);
				}	
			}	
		}
		
		if (MovesCount.movesCount != 0) {
			for (FieldButton button : ButtonMap.map) {
				button.setEnabled(enabled);
				this.add(button);
				button.revalidate();
				button.repaint();
				button.addActionListener(new ButtonListener());
			}
		}	
		

	}
	

}
