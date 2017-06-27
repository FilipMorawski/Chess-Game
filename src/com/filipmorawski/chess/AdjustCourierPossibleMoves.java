package com.filipmorawski.chess;

import java.util.ArrayList;
import java.util.Collections;

public class AdjustCourierPossibleMoves extends AdjustPossibleMoves {

	 public AdjustCourierPossibleMoves(String name, String position, int color, ArrayList<String> possibleMoves) {
	    this.color = color;
	    this.possibleMoves = possibleMoves;
	    adjustMoves(name, position);
	 }

	 
// Removes from Courier possible moves fields that are behind any figures in his path
	@Override
	void adjustMoves(String name, String position) {
		String secondLetter = position.substring(1,2);
		int verticalPosition = Integer.parseInt(secondLetter);
		
		ArrayList<String> right = new ArrayList<String>();
		ArrayList<String> upperRight = new ArrayList<String>();
		ArrayList<String> bottomRight = new ArrayList<String>();
		
		ArrayList<String> left = new ArrayList<String>();			
		ArrayList<String> upperLeft = new ArrayList<String>();
		ArrayList<String> bottomLeft = new ArrayList<String>();
		
		this.possibleMoves.add(position);
		Collections.sort(this.possibleMoves);
		
		boolean rightSide = false;
		for (String pos : possibleMoves) {
			if (possibleMoves.contains(pos) && !rightSide) {
				left.add(pos);
			}
			if (rightSide) {
				right.add(pos);
			}
			if (pos.equals(position)) {
				rightSide = true;
			}
		}
		this.possibleMoves.remove(position);
		right.add(position);
		
		Collections.sort(right);
		Collections.sort(left);
				
		for (String pos : left) {
			String secondL = pos.substring(1,2);
			int vPos = Integer.parseInt(secondL);
			if (vPos > verticalPosition) {
				upperLeft.add(pos);
			} else {
				bottomLeft.add(pos);
			}
		}
		bottomLeft.remove(position);
		
		for (String pos : right) {
			String secondL = pos.substring(1,2);
			int vPos = Integer.parseInt(secondL);
			if (vPos > verticalPosition) {
				upperRight.add(pos);
			} else {
				bottomRight.add(pos);
			}
		}
		bottomRight.remove(position);
		
		Collections.reverse(bottomLeft);
		Collections.reverse(upperLeft);

		boolean erase = false;
		for (String lPos : upperLeft) {
			if (erase) {
				possibleMoves.remove(lPos);
			}
			if (Figures.figuresPositionMap.contains(lPos)) {
				erase = true;
			}
		}
		erase = false;
		for (String lPos : bottomLeft) {
			if (erase) {
				possibleMoves.remove(lPos);
			}
			if (Figures.figuresPositionMap.contains(lPos)) {
				erase = true;
			}
		}	
		erase = false;
		for (String rPos : upperRight) {
			if (erase) {
				possibleMoves.remove(rPos);
			}
			if (Figures.figuresPositionMap.contains(rPos)) {
				erase = true;
			}
		}
		erase = false;
		for (String rPos : bottomRight) {
			if (erase) {
				possibleMoves.remove(rPos);
			}
			if (Figures.figuresPositionMap.contains(rPos)) {
				erase = true;
			}
		}
		erase = false;
	}

}


