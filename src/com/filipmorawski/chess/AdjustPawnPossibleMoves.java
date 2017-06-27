package com.filipmorawski.chess;

import java.util.ArrayList;

public class AdjustPawnPossibleMoves extends AdjustPossibleMoves{

	public AdjustPawnPossibleMoves(String name, String position, int color, ArrayList<String> possibleMoves) {
		    this.color = color;
		    this.possibleMoves = possibleMoves;
		    adjustMoves(name, position);
	}
	
// Removes from Pawn possible moves fields where  something blocks its path 
	@Override
	void adjustMoves(String name, String position) {
		String firstLetter = position.substring(0,1);
		String secLetter = position.substring(1, 2);
		int secondLetter = Integer.parseInt(secLetter);
		
		ArrayList<String> crossPositions = new ArrayList<String>();
		
		for (String pos : this.possibleMoves) {
			if (!pos.contains(firstLetter)) {
				crossPositions.add(pos);
			}
		}
		
		for (String cPos : crossPositions) {
			if (!Figures.figuresPositionMap.contains(cPos)) {
				this.possibleMoves.remove(cPos);
			}
		}
		
		if (this.color == 1) {
			if((Figures.figuresPositionMap.contains(firstLetter + (secondLetter + 1)))) {
				this.possibleMoves.remove(firstLetter + (secondLetter + 1));
			}
			
			if ((position.equals(firstLetter + 2)) && (Figures.figuresPositionMap.contains(firstLetter + "3") || Figures.figuresPositionMap.contains(firstLetter + "4"))) {
				this.possibleMoves.remove((firstLetter + "4"));
			}
		}
		if (this.color == 2) {
			if((Figures.figuresPositionMap.contains(firstLetter + (secondLetter - 1)))) {
				this.possibleMoves.remove(firstLetter + (secondLetter - 1));
			}
			if ((position.equals(firstLetter + 7)) &&  (Figures.figuresPositionMap.contains(firstLetter + "6") || Figures.figuresPositionMap.contains(firstLetter + "5")) ) {
				this.possibleMoves.remove((firstLetter + "5"));
			}
		}
	}

}
