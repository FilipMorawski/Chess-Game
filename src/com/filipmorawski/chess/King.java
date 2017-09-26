package com.filipmorawski.chess;

import java.util.ArrayList;
import javax.swing.ImageIcon;

public class King extends Figure {

	private ArrayList<String> possibleAtackingRoutes;

	public King(String name, int color, String position, int verticalID, int horizontalID) {
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

	// Creates all possible moves in all possible directions, then cut positions
	// away of border of ChessBoard
	@Override
	void setPossibleMoves() {
		this.possibleMoves.clear();
		calculateMaximumRange();
	}

	private void calculateMaximumRange() {
		int[] calculateHorizontalPositions = { this.horizontalID, this.horizontalID, this.horizontalID + 1,
				this.horizontalID - 1, this.horizontalID + 1, this.horizontalID + 1, this.horizontalID - 1,
				this.horizontalID - 1 };
		int[] calculateVerticalPositions = { this.verticalID + 1, this.verticalID - 1, this.verticalID, this.verticalID,
				this.verticalID + 1, this.verticalID - 1, this.verticalID + 1, this.verticalID - 1 };
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
		}
	}
}
