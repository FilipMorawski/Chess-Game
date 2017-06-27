package com.filipmorawski.chess;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Tower extends Figure{
	
	public Tower(String name, int color, String position, int verticalID, int horizontalID){
		this.name = name;
		this.color = color;
		this.position = position;
		this.verticalID = verticalID;
		this.horizontalID = horizontalID;
		if (this.color == 1) {
			this.icon = new ImageIcon(this.getClass().getResource("whiteTower.png"));
		} else {
			this.icon = new ImageIcon(this.getClass().getResource("blackTower.png"));
		}
		setPossibleMoves();
	}

	// Creates all possible moves in all possible directions, then cut positions away of border of ChessBoard
	@Override
	void setPossibleMoves() {
		this.possibleMoves.clear();
		int[] calculateHorizontalPositions = new int[28];		
		int[] calculateVerticalPositions = new int[28]; 
		ArrayList<Integer> horizontalPositions = new ArrayList<Integer>(); 
		ArrayList<Integer> verticalPositions = new ArrayList<Integer>(); 
		
		
		int j = 0;
		for (int i = 0; i<7; i++) {
				calculateHorizontalPositions[j] = (this.horizontalID + (i+1));
				calculateVerticalPositions[j] = this.verticalID;
				j++;
		}
		for (int i = 0; i<7; i++) {
				calculateHorizontalPositions[j] =( this.horizontalID - (i+1) );
				calculateVerticalPositions[j] = this.verticalID;
				j++;
			}
		for (int i = 0; i<7; i++) {
				calculateHorizontalPositions[j] = this.horizontalID;
				calculateVerticalPositions[j] =( this.verticalID + (i+1) );
				j++;
		} 
		for (int i = 0; i<7; i++) {
				calculateHorizontalPositions[j] = this.horizontalID;
				calculateVerticalPositions[j] = (this.verticalID - (i + 1) ) ;
				j++;
		}
		j = 0;
		
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
		
		this.possibleMoves = new AdjustTowerPossibleMoves(this.name, this.position, this.color, this.possibleMoves).possibleMoves;
		
////////////////////////////Testowy wydruk////////////////////////////	
//		for (int f = 0; f<calculateHorizontalPositions.length; f++) {
//			System.out.print(calculateHorizontalPositions[f] + ", ");
//			System.out.print(calculateVerticalPositions[f] + ", ");
//		}
//		System.out.println("");
//		System.out.println("");

	}



}

	
