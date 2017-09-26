package com.filipmorawski.chess;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Pawn extends Figure {

	private final int maxPawnRange = 4;

	public Pawn(String name, int color, String position, int verticalID, int horizontalID) {
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

	// Creates all possible moves in all possible directions, then cut positions
	// away of border of ChessBoard
	@Override
	void setPossibleMoves() {

		this.possibleMoves.clear();
		calculateMaximumRange();
		adjustPossibleMoves();
	}

	private void calculateMaximumRange() {
		int[] calculateVerticalPositions = { -10, -10, -10, -10 };
		int[] calculateHorizontalPositions = { -10, -10, -10, -10 };

		// Checking if this Pawn move is its first
		if (this.color == 1 && this.position.contains("2")) {
			int[] calcVerticalPositions = { this.verticalID - 2, this.verticalID - 1, this.verticalID - 1,
					this.verticalID - 1 };
			int[] calcHorizontalPositions = { this.horizontalID, this.horizontalID, this.horizontalID - 1,
					this.horizontalID + 1 };
			for (int i = 0; i < calcVerticalPositions.length; i++) {
				calculateVerticalPositions[i] = calcVerticalPositions[i];
				calculateHorizontalPositions[i] = calcHorizontalPositions[i];
			}
		} else if (this.color == 1 && !this.position.contains("2")) {
			int[] calcVerticalPositions = { this.verticalID - 1, this.verticalID - 1, this.verticalID - 1 };
			int[] calcHorizontalPositions = { this.horizontalID, this.horizontalID - 1, this.horizontalID + 1 };
			for (int i = 0; i < calcVerticalPositions.length; i++) {
				calculateVerticalPositions[i] = calcVerticalPositions[i];
				calculateHorizontalPositions[i] = calcHorizontalPositions[i];
			}
		}

		if (this.color == 2 && this.position.contains("7")) {
			int[] calcVerticalPositions = { this.verticalID + 2, this.verticalID + 1, this.verticalID + 1,
					this.verticalID + 1 };
			int[] calcHorizontalPositions = { this.horizontalID, this.horizontalID, this.horizontalID - 1,
					this.horizontalID + 1 };
			for (int i = 0; i < calcVerticalPositions.length; i++) {
				calculateVerticalPositions[i] = calcVerticalPositions[i];
				calculateHorizontalPositions[i] = calcHorizontalPositions[i];
			}
		} else if (this.color == 2) {
			int[] calcVerticalPositions = { this.verticalID + 1, this.verticalID + 1, this.verticalID + 1 };
			int[] calcHorizontalPositions = { this.horizontalID, this.horizontalID - 1, this.horizontalID + 1 };
			for (int i = 0; i < calcVerticalPositions.length; i++) {
				calculateVerticalPositions[i] = calcVerticalPositions[i];
				calculateHorizontalPositions[i] = calcHorizontalPositions[i];
			}
		}
		removeOutOfMapPositions(calculateHorizontalPositions, calculateVerticalPositions);
	}

	private void removeOutOfMapPositions(int[] calculateHorizontalPositions, int[] calculateVerticalPositions) {
		ArrayList<Integer> horizontalPositions = new ArrayList<Integer>();
		ArrayList<Integer> verticalPositions = new ArrayList<Integer>();

		// Removing out of bounds possible moves
		for (int i = 0; i < calculateHorizontalPositions.length; i++) {
			if (calculateHorizontalPositions[i] < 0 || calculateHorizontalPositions[i] > maxRangeInOneDirection) {
				calculateHorizontalPositions[i] = outOfBoundFlag;
				calculateVerticalPositions[i] = outOfBoundFlag;
			}
			if (calculateVerticalPositions[i] < 0 || calculateVerticalPositions[i] > maxRangeInOneDirection) {
				calculateHorizontalPositions[i] = outOfBoundFlag;
				calculateVerticalPositions[i] = outOfBoundFlag;
			}
		}
		for (int position : calculateVerticalPositions) {
			if (position != outOfBoundFlag) {
				verticalPositions.add(position);
			}
		}
		for (int position : calculateHorizontalPositions) {
			if (position != outOfBoundFlag) {
				horizontalPositions.add(position);
			}
		}
		convertToStringPositions(horizontalPositions, verticalPositions);
	}

	private void convertToStringPositions(ArrayList<Integer> horizontalPositions,
			ArrayList<Integer> verticalPositions) {
		// Create position strings
		for (int j = 0; j < horizontalPositions.size(); j++) {
			String move = Coordinates.horizontal[horizontalPositions.get(j)]
					+ Coordinates.vertical[verticalPositions.get(j)];
			this.possibleMoves.add(move);
		}
	}

	private void adjustPossibleMoves() {
		this.possibleMoves = new AdjustPawnPossibleMoves(this.name, this.position, this.color,
				this.possibleMoves).possibleMoves;
	}
}
