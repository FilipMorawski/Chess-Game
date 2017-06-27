package com.filipmorawski.chess;

import java.util.ArrayList;

public class BlockPossibility {

	private boolean possibility = false;
	
	public boolean check(Figure atackingFigure, Figure king, ArrayList<String> defensiveMoves) {
	
		ArrayList<Integer> vertical = new ArrayList<Integer>();
		ArrayList<Integer> horizontal = new ArrayList<Integer>();
		
		ArrayList<String> atackingRoute = new ArrayList<String>();
		
		
		
		int xAtack = atackingFigure.getHorizontalID();
		int yAtack = atackingFigure.getVerticalID();
		
		int xKing = king.getHorizontalID();
		int yKing = king.getVerticalID();

		int xDist = xAtack - xKing;
		int yDist = yAtack - yKing;
		
		
// Checking if route is Vertical		
		if (xDist == 0 && yDist != 0 ) {
		
			if (xDist == 0 && yDist > 0 ) {
				for (int i = 1; i < yDist; i++) {
					horizontal.add(xKing);
					vertical.add(yKing + i);
				}
			}
			
			if(xDist == 0 && yDist < 0) {
				yDist = yDist*(-1);
				for (int i = 1; i < yDist; i++) {
					horizontal.add(xKing);
					vertical.add(yKing - i);
				}
			}	
		}

//Checking if route is Horizontal
		if (xDist != 0 && yDist == 0 ) {
			
			if (xDist > 0 && yDist == 0 ) {
				for (int i = 1; i < xDist; i++) {
					horizontal.add(xKing + i);
					vertical.add(yKing);
				}
			}
			
			if(xDist < 0 && yDist == 0) {
				xDist = xDist*(-1);
				for (int i = 1; i < xDist; i++) {
					horizontal.add(xKing - i);
					vertical.add(yKing);
				}
			}	
		}
		
//Checking if route is crossway		
		if (xDist > 0 && yDist > 0 ) {	
			for (int i = 1; i < yDist; i++) {
				horizontal.add(xKing + i);
				vertical.add(yKing + i);
				}
			}
			
		if(xDist > 0 && yDist < 0) {
			yDist = yDist*(-1);
			for (int i = 1; i < yDist; i++) {
				horizontal.add(xKing + i);
				vertical.add(yKing - i);
				}
		}	
		

		if (xDist < 0 && yDist < 0 ) {
			xDist = xDist*(-1);
			yDist = yDist*(-1);
			for (int i = 1; i < yDist; i++) {
				horizontal.add(xKing - i);
				vertical.add(yKing - i);
				}
			}
			
		if(xDist < 0 && yDist > 0) {
			xDist = xDist*(-1);
			for (int i = 1; i < yDist; i++) {
				horizontal.add(xKing - i);
				vertical.add(yKing + i);
				}
		}

// Creates position coordinates of attackig route		
		
		for (int i = 0 ; i<vertical.size(); i++) {
			String move = Coordinates.horizontal[horizontal.get(i)] + Coordinates.vertical[vertical.get(i)];
			atackingRoute.add(move);
		}

// Checking if any Figure of player who defense can block check		
		for (String field : atackingRoute) {
			if (defensiveMoves.contains(field)) {
				possibility = true;
			}
		}

/////////////////////////////// TESTING PRINTS ///////////////////////		
//		System.out.println("");
//		System.out.println("Atacking Route " + atackingRoute);
//		System.out.println("Defensive Moves " + defensiveMoves);
//////////////////////////////////////////////////////////////////////

		
// Jumper cannot be blocked		
		if (atackingFigure.getName().equals("Jumper")) {
		possibility = false;
	}
	
	return possibility;
	}

	public boolean isPossibility() {
		return possibility;
	}

	public void setPossibility(boolean possibility) {
		this.possibility = possibility;
	}

}
