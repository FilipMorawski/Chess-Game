package com.filipmorawski.chess;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Jumper extends Figure {

	public Jumper(String name, int color, String position, int verticalID, int horizontalID) {
		this.name = name;
		this.color = color;
		this.position = position;
		this.verticalID = verticalID;
		this.horizontalID = horizontalID;
		if (this.color == 1) {
			this.icon = new ImageIcon(this.getClass().getResource("whiteJumper.png"));
		} else {
			this.icon = new ImageIcon(this.getClass().getResource("blackJumper.png"));
		}
		setPossibleMoves();
	}

	// Creates all possible moves in all possible directions, then cut positions
	// away of border of ChessBoard
	void setPossibleMoves() {
		this.possibleMoves.clear();
		this.range.clear();
		calculateMaximumRange();
	}

	private void calculateMaximumRange() {
		int[] calculateHorizontalPositions = { this.horizontalID - 1, this.horizontalID - 1, this.horizontalID + 1,
				this.horizontalID + 1, this.horizontalID - 2, this.horizontalID - 2, this.horizontalID + 2,
				this.horizontalID + 2 };
		int[] calculateVerticalPositions = { this.verticalID - 2, this.verticalID + 2, this.verticalID - 2,
				this.verticalID + 2, this.verticalID - 1, this.verticalID + 1, this.verticalID - 1,
				this.verticalID + 1 };

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
		for (int j = 0; j < horizontalPositions.size(); j++) {
			String move = Coordinates.horizontal[horizontalPositions.get(j)]
					+ Coordinates.vertical[verticalPositions.get(j)];
			this.possibleMoves.add(move);
		}
	}

}
