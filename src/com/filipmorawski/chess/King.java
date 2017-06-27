package com.filipmorawski.chess;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class King extends Figure {

	public King( String name, int color, String position, int verticalID, int horizontalID){
		this.name = name;
		this.color = color;
		this.position = position;
		this.verticalID = verticalID;
		this.horizontalID = horizontalID;
		if (this.color == 1) {
			this.icon = new ImageIcon(this.getClass().getResource("whiteKing.png"));
		} else {
			this.icon = new ImageIcon(this.getClass().getResource("blackKing.png"));
		}
		setPossibleMoves();
	}

	// Creates all possible moves in all possible directions, then cut positions away of border of ChessBoard
	@Override
	void setPossibleMoves() {
		this.possibleMoves.clear();
		int[] calculateHorizontalPositions = {this.horizontalID,   this.horizontalID, this.horizontalID + 1, this.horizontalID -1, this.horizontalID + 1, this.horizontalID + 1, this.horizontalID - 1, this.horizontalID - 1}; 
		int[] calculateVerticalPositions = {this.verticalID + 1, this.verticalID - 1,     this.verticalID,      this.verticalID,     this.verticalID + 1,   this.verticalID - 1, this.verticalID + 1,   this.verticalID - 1}; 
		ArrayList<Integer> horizontalPositions = new ArrayList<Integer>(); 
		ArrayList<Integer> verticalPositions = new ArrayList<Integer>(); 

		for(int i = 0; i< calculateHorizontalPositions.length; i++ ) {
			if (calculateHorizontalPositions[i] < 0 || calculateHorizontalPositions[i] > 7 ) {
				calculateHorizontalPositions[i] = 100;
				calculateVerticalPositions[i] = 100;
			} else if (calculateVerticalPositions[i] < 0 || calculateVerticalPositions[i] > 7 ) {
				calculateHorizontalPositions[i] = 100;
				calculateVerticalPositions[i] = 100;	
			}
		}
		for (int position : calculateHorizontalPositions) {
			if (position != 100) {
				horizontalPositions.add(position);
			}
		}
		for (int position : calculateVerticalPositions) {
			if (position != 100) {
				verticalPositions.add(position);
			}
		}
		
		for (int k = 0; k <horizontalPositions.size(); k++) {
			String move = Coordinates.horizontal[horizontalPositions.get(k)] + Coordinates.vertical[verticalPositions.get(k)]; 
			this.possibleMoves.add(move); 
		}
		
// Adding castling moves		
		if (this.color == 1 && this.position.equals("e1") && this.moves == 0) {
			this.possibleMoves.add("g1");
			this.possibleMoves.add("c1");
		}
		if (this.color == 2 && this.position.equals("e8") && this.moves == 0) {
			this.possibleMoves.add("g8");
			this.possibleMoves.add("c8");
		}
		
////////////////////////////Testowy wydruk////////////////////////////	
//		for (int f = 0; f<calculateHorizontalPositions.length; f++) {
//			System.out.print(calculateHorizontalPositions[f] + ", ");
//			System.out.print(calculateVerticalPositions[f] + ", ");
//		}
//		System.out.println("");
//		System.out.println("");

	}


}
