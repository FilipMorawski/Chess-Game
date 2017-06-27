package com.filipmorawski.chess;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JPanel;

public class ChessBoard extends JPanel{
	private JPanel chessBoard;
	
	public static ArrayList<FieldButton> buttonMap = new ArrayList<FieldButton>();
	
//Constructor creating buttons which represent chessfields and adding it to static list	
	public ChessBoard() {
		GridLayout grid = new GridLayout(8,8);
		this.setLayout(grid);
		
		for (int i = 0; i<8 ; i++) {
			for (int j = 0; j<8 ; j++) {
				FieldButton button = new FieldButton(i, j);
				this.add(button);
				buttonMap.add(button);
			}	
		}
		
	}
	


}
