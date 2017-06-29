package com.filipmorawski.chess;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Queen extends Figure {
	
	public Queen(String name, int color, String position, int verticalID, int horizontalID){
		this.name = name;
		this.color = color;
		this.position = position;
		this.verticalID = verticalID;
		this.horizontalID = horizontalID;
		if (this.color == 1) {
			this.icon = new ImageIcon(this.getClass().getResource("whiteQueen.png"));
		} else {
			this.icon = new ImageIcon(this.getClass().getResource("blackQueen.png"));
		}
//		Figures.figures.add(this);
		setPossibleMoves();
	}

	// Creates all possible moves in all possible directions, then cut positions away of border of ChessBoard	
	@Override
	void setPossibleMoves() {
		this.possibleMoves.clear();
		this.range.clear();
		
//      Queen movements is a sum of Courier and Tower moves - so I create objects of Tower and Courier 
//		with as same as Queen position parameter, and they pass to Queen theirs possible moves 
		
		ArrayList<String> towerPossibleMoves = new ArrayList<String>();
		ArrayList<String> courierPossibleMoves = new ArrayList<String>();
		ArrayList<String> towerRange = new ArrayList<String>();
		ArrayList<String> courierRange = new ArrayList<String>();
		
		
		towerPossibleMoves = new Tower(this.name, this.color, this.position, this.verticalID, this.horizontalID).getPossibleMoves();
		courierPossibleMoves = new Courier(this.name, this.color, this.position, this.verticalID, this.horizontalID).getPossibleMoves();
		towerRange = new Tower(this.name, this.color, this.position, this.verticalID, this.horizontalID).getPossibleMoves();
		courierRange = new Courier(this.name, this.color, this.position, this.verticalID, this.horizontalID).getPossibleMoves();

		this.possibleMoves.addAll(towerPossibleMoves);
		this.possibleMoves.addAll(courierPossibleMoves);
		
		this.range.addAll(towerRange);
		this.range.addAll(courierRange);
		
	}

}
