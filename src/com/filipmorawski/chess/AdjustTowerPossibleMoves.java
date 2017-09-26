package com.filipmorawski.chess;

import java.util.ArrayList;
import java.util.Collections;

public class AdjustTowerPossibleMoves extends AdjustPossibleMoves {

	public AdjustTowerPossibleMoves(String name, String position, int color, ArrayList<String> possibleMoves) {
		this.color = color;
		this.possibleMoves = possibleMoves;
		adjustMoves(name, position);
	}

	// Removes from Tower possible moves fields after any obstacle in his potential way

	@Override
	void adjustMoves(String name, String position) {

		String firstLetter = position.substring(0, 1);
		String secondLetter = position.substring(1, 2);

		ArrayList<String> horizontal = new ArrayList<String>();
		ArrayList<String> horizontalLeft = new ArrayList<String>();
		ArrayList<String> horizontalRight = new ArrayList<String>();

		ArrayList<String> vertical = new ArrayList<String>();
		ArrayList<String> verticalUp = new ArrayList<String>();
		ArrayList<String> verticalDown = new ArrayList<String>();

		for (String pos : possibleMoves) {
			if (pos.contains(firstLetter)) {
				vertical.add(pos);
			}
			if (pos.contains(secondLetter)) {
				horizontal.add(pos);
			}
		}
		vertical.add(position);
		horizontal.add(position);

		Collections.sort(vertical);
		Collections.sort(horizontal);

		Boolean toRightOrUp = false;
		for (String pos : horizontal) {
			if (toRightOrUp) {
				horizontalRight.add(pos);
			} else {
				horizontalLeft.add(pos);
			}
			if (pos.equals(position)) {
				toRightOrUp = true;
			}
		}

		toRightOrUp = false;
		for (String pos : vertical) {
			if (toRightOrUp) {
				verticalUp.add(pos);
			} else {
				verticalDown.add(pos);
			}
			if (pos.equals(position)) {
				toRightOrUp = true;
			}
		}

		horizontalLeft.remove(position);
		verticalDown.remove(position);

		Collections.reverse(horizontalLeft);
		boolean erase = false;
		for (String yPos : horizontalLeft) {
			if (erase) {
				possibleMoves.remove(yPos);
			}
			if (Figures.figuresPositionMap.contains(yPos)) {
				erase = true;
			}
		}
		erase = false;
		for (String yPos : horizontalRight) {
			if (erase) {
				possibleMoves.remove(yPos);
			}
			if (Figures.figuresPositionMap.contains(yPos)) {
				erase = true;
			}
		}

		Collections.reverse(verticalDown);

		erase = false;
		for (String xPos : verticalDown) {
			if (erase) {
				possibleMoves.remove(xPos);
			}
			if (Figures.figuresPositionMap.contains(xPos)) {
				erase = true;
			}
		}
		erase = false;
		for (String xPos : verticalUp) {
			if (erase) {
				possibleMoves.remove(xPos);
			}
			if (Figures.figuresPositionMap.contains(xPos)) {
				erase = true;
			}
		}
		erase = false;
	}
}
