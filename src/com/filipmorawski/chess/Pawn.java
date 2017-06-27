package com.filipmorawski.chess;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Pawn extends Figure {
	
	
	public Pawn(String name, int color, String position, int verticalID, int horizontalID){
		this.name = name;
		this.color = color;
		this.position = position;
		this.verticalID = verticalID;
		this.horizontalID = horizontalID;
		if (this.color == 1) {
			this.icon = new ImageIcon(this.getClass().getResource("whitePawn.png"));
		} else {
			this.icon = new ImageIcon(this.getClass().getResource("blackPawn.png"));
		}
		setPossibleMoves();
	}

	// Creates all possible moves in all possible directions, then cut positions away of border of ChessBoard
	@Override
	void setPossibleMoves() {
		
		this.possibleMoves.clear();
		int[] calculateVerticalPositions = {-10,-10,-10,-10};
		int[] calculateHorizontalPositions  =  {-10,-10,-10,-10};
		ArrayList<Integer> horizontalPositions = new ArrayList<Integer>(); 
		ArrayList<Integer> verticalPositions = new ArrayList<Integer>();

		
// Checking if this Pawn move is its first		
		if(this.color == 1 && this.position.contains("2")) {
			int[] calcVerticalPositions = {this.verticalID - 2, this.verticalID - 1, this.verticalID - 1, this.verticalID - 1};
			int[] calcHorizontalPositions = {this.horizontalID, this.horizontalID, this.horizontalID - 1, this.horizontalID +1};
			for (int i = 0; i<calcVerticalPositions.length; i++) {
				calculateVerticalPositions[i] = calcVerticalPositions[i];
				calculateHorizontalPositions[i] = calcHorizontalPositions[i];
			}
		} else  if (this.color == 1 && !this.position.contains("2")) {
			int[] calcVerticalPositions = {this.verticalID - 1, this.verticalID - 1, this.verticalID - 1};
			int[] calcHorizontalPositions = {this.horizontalID, this.horizontalID - 1, this.horizontalID +1};
			for (int i = 0; i<calcVerticalPositions.length; i++) {
				calculateVerticalPositions[i] = calcVerticalPositions[i];
				calculateHorizontalPositions[i] = calcHorizontalPositions[i];
			}
		}
		
		if(this.color == 2 && this.position.contains("7")) {
			int[] calcVerticalPositions = {this.verticalID + 2, this.verticalID + 1, this.verticalID + 1, this.verticalID + 1};
			int[] calcHorizontalPositions = {this.horizontalID, this.horizontalID, this.horizontalID - 1, this.horizontalID +1};
			for (int i = 0; i<calcVerticalPositions.length; i++) {
				calculateVerticalPositions[i] = calcVerticalPositions[i];
				calculateHorizontalPositions[i] = calcHorizontalPositions[i];
			}		
			} else if (this.color == 2) {
			int[] calcVerticalPositions = {this.verticalID + 1, this.verticalID + 1, this.verticalID + 1};
			int[] calcHorizontalPositions = {this.horizontalID, this.horizontalID - 1, this.horizontalID +1};		
			for (int i = 0; i<calcVerticalPositions.length; i++) {
				calculateVerticalPositions[i] = calcVerticalPositions[i];
				calculateHorizontalPositions[i] = calcHorizontalPositions[i];
			}
		}		
		
//Removig out of bounds possible moves
		for(int i = 0; i< calculateHorizontalPositions.length; i++ ) {
			if (calculateHorizontalPositions[i] < 0 || calculateHorizontalPositions[i] > 7 ) {
				calculateHorizontalPositions[i] = 100;
				calculateVerticalPositions[i] = 100;
			}
			if (calculateVerticalPositions[i] < 0 || calculateVerticalPositions[i] > 7 ) {
				calculateHorizontalPositions[i] = 100;
				calculateVerticalPositions[i] = 100;	
			}
		}
		
		for (int position : calculateVerticalPositions) {
			if (position != 100) {
				verticalPositions.add(position);
			}
		}
		
		for (int position : calculateHorizontalPositions) {
			if (position != 100) {
				horizontalPositions.add(position);
			}
		}
		
//Create position strings	
		for (int j = 0; j <horizontalPositions.size(); j++) {
			String move = Coordinates.horizontal[horizontalPositions.get(j)] + Coordinates.vertical[verticalPositions.get(j)]; 
			this.possibleMoves.add(move); 
		}
		
		this.possibleMoves = new AdjustPawnPossibleMoves(this.name, this.position, this.color, this.possibleMoves).possibleMoves;

	}
}
