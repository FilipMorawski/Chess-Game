package com.filipmorawski.chess;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Courier extends Figure {

	public Courier(String name, int color, String position, int verticalID, int horizontalID) {
		this.name = name;
		this.color = color;
		this.position = position;
		this.verticalID = verticalID;
		this.horizontalID = horizontalID;
		if (this.color == 1) {
			this.icon = new ImageIcon(this.getClass().getResource("whiteCourier.png"));
		} else {
			this.icon = new ImageIcon(this.getClass().getResource("blackCourier.png"));
		}
		setPossibleMoves();
	}

	// Creates all possible moves in all possible directions, then cut positions
	// away of border of ChessBoard
	
	@Override
	void setPossibleMoves() {
		this.possibleMoves.clear();
		this.range.clear();
		calculateMaximumRange();
		adjustPossibleMoves();
	}

	private void calculateMaximumRange() {

		int[] calculateHorizontalPositions = new int[maxRange];
		int[] calculateVerticalPositions = new int[maxRange];

		int j = 0;
		for (int i = 0; i < maxRangeInOneDirection; i++) {
			calculateHorizontalPositions[j] = (this.horizontalID + (i + 1));
			calculateVerticalPositions[j] = (this.verticalID + (i + 1));
			j++;
		}
		for (int i = 0; i < maxRangeInOneDirection; i++) {
			calculateHorizontalPositions[j] = (this.horizontalID + (i + 1));
			calculateVerticalPositions[j] = (this.verticalID - (i + 1));
			j++;
		}
		for (int i = 0; i < maxRangeInOneDirection; i++) {
			calculateHorizontalPositions[j] = (this.horizontalID - (i + 1));
			calculateVerticalPositions[j] = (this.verticalID + (i + 1));
			j++;
		}
		for (int i = 0; i < maxRangeInOneDirection; i++) {
			calculateHorizontalPositions[j] = (this.horizontalID - (i + 1));
			calculateVerticalPositions[j] = (this.verticalID - (i + 1));
			j++;
		}
		j = 0;

		removeOutOfMapPositions(calculateHorizontalPositions, calculateVerticalPositions);

	}

	private void removeOutOfMapPositions(int[] calculateHorizontalPositions, int[] calculateVerticalPositions) {

		ArrayList<Integer> horizontalPositions = new ArrayList<Integer>();
		ArrayList<Integer> verticalPositions = new ArrayList<Integer>();

		for (int i = 0; i < calculateHorizontalPositions.length; i++) {
			if (calculateHorizontalPositions[i] < 0 || calculateHorizontalPositions[i] > maxRangeInOneDirection) {
				calculateHorizontalPositions[i] = outOfBoundFlag;
				calculateVerticalPositions[i] = outOfBoundFlag;
			} else if (calculateVerticalPositions[i] < 0 || calculateVerticalPositions[i] > maxRangeInOneDirection) {
				calculateHorizontalPositions[i] = outOfBoundFlag;
				calculateVerticalPositions[i] = outOfBoundFlag;
			}
		}
		for (int position : calculateHorizontalPositions) {
			if (position != outOfBoundFlag) {
				horizontalPositions.add(position);
			}
		}
		for (int position : calculateVerticalPositions) {
			if (position != outOfBoundFlag) {
				verticalPositions.add(position);
			}
		}

		convertToStringPositions(horizontalPositions, verticalPositions);
	}

	private void convertToStringPositions(ArrayList<Integer> horizontalPositions,
			ArrayList<Integer> verticalPositions) {

		for (int k = 0; k < horizontalPositions.size(); k++) {
			String move = Coordinates.horizontal[horizontalPositions.get(k)]
					+ Coordinates.vertical[verticalPositions.get(k)];
			this.possibleMoves.add(move);
			this.range.add(move);
		}
	}

	private void adjustPossibleMoves() {
		this.possibleMoves = new AdjustCourierPossibleMoves(this.name, this.position, this.color,
				this.possibleMoves).possibleMoves;
	}

}
