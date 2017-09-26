package com.filipmorawski.chess;

import java.util.ArrayList;

public class BlockPossibility {

	private boolean possibility;
	private ArrayList<Figure> atackingFigures;
	private ArrayList<Integer> vertical, horizontal;
	private ArrayList<String> atackingRoute;
	private int xAtack, yAtack, xKing, yKing, yDist, xDist;

	public BlockPossibility() {
		this.possibility = false;
		this.atackingFigures = new ArrayList<Figure>();
	}

	public boolean blockCheck(Figure atackingFigure, Figure king, ArrayList<String> defensiveMoves) {

		this.atackingFigures.add(atackingFigure);
		ArrayList<String> atackingRoute = blockCheck(atackingFigure, king);

		// Checking if any Figure of player who defense can block check so there can't be checkmate
		for (String field : atackingRoute) {
			if (defensiveMoves.contains(field)) {
				this.possibility = true;
			}
		}

		// Jumper cannot be blocked
		if (atackingFigure.getName().equals("Jumper")) {
			this.possibility = false;
		}

		// If there is more than one attacking figure, they cannot be blocked in one move
		if (atackingFigures.size() > 1) {
			this.possibility = false;
		}

		return this.possibility;
	}

	public ArrayList<String> blockCheck(Figure atackingFigure, Figure king) {

		vertical = new ArrayList<Integer>();
		horizontal = new ArrayList<Integer>();
		atackingRoute = new ArrayList<String>();

		xAtack = atackingFigure.getHorizontalID();
		yAtack = atackingFigure.getVerticalID();

		xKing = king.getHorizontalID();
		yKing = king.getVerticalID();

		xDist = xAtack - xKing;
		yDist = yAtack - yKing;

		createVerticalRoute();
		createHorizontalRoute();
		createCrossRoutes();
		convertToPositionStrings();
		return atackingRoute;
	}

	private void createVerticalRoute() {

		// Checking if route is Vertical
		if (xDist == 0 && yDist != 0) {

			if (xDist == 0 && yDist > 0) {
				for (int i = 1; i < yDist; i++) {
					horizontal.add(xKing);
					vertical.add(yKing + i);
				}
			}

			if (xDist == 0 && yDist < 0) {
				yDist = yDist * (-1);
				for (int i = 1; i < yDist; i++) {
					horizontal.add(xKing);
					vertical.add(yKing - i);
				}
			}
		}
	}

	private void createHorizontalRoute() {

		// Checking if route is Horizontal
		if (xDist != 0 && yDist == 0) {

			if (xDist > 0 && yDist == 0) {
				for (int i = 1; i < xDist; i++) {
					horizontal.add(xKing + i);
					vertical.add(yKing);
				}
			}

			if (xDist < 0 && yDist == 0) {
				xDist = xDist * (-1);
				for (int i = 1; i < xDist; i++) {
					horizontal.add(xKing - i);
					vertical.add(yKing);
				}
			}
		}
	}

	private void createCrossRoutes() {

		// Checking if route is crossway
		if (xDist > 0 && yDist > 0) {
			for (int i = 1; i < yDist; i++) {
				horizontal.add(xKing + i);
				vertical.add(yKing + i);
			}
		}

		if (xDist > 0 && yDist < 0) {
			yDist = yDist * (-1);
			for (int i = 1; i < yDist; i++) {
				horizontal.add(xKing + i);
				vertical.add(yKing - i);
			}
		}

		if (xDist < 0 && yDist < 0) {
			xDist = xDist * (-1);
			yDist = yDist * (-1);
			for (int i = 1; i < yDist; i++) {
				horizontal.add(xKing - i);
				vertical.add(yKing - i);
			}
		}

		if (xDist < 0 && yDist > 0) {
			xDist = xDist * (-1);
			for (int i = 1; i < yDist; i++) {
				horizontal.add(xKing - i);
				vertical.add(yKing + i);
			}
		}
	}

	private void convertToPositionStrings() {

		// Creates position coordinates of attacking route
		for (int i = 0; i < vertical.size(); i++) {
			String move = Coordinates.horizontal[horizontal.get(i)] + Coordinates.vertical[vertical.get(i)];
			atackingRoute.add(move);
		}
	}

//	private void testPrints1(ArrayList<String> atackingRoute, ArrayList<String> defensiveMoves) {
//		System.out.println("");
//		System.out.println("Atacking Route " + atackingRoute);
//		System.out.println("Defensive Moves " + defensiveMoves);
//	}
//
//	private void testPrints2(Figure atackingFigure, ArrayList<String> atackingRoute) {
//		System.out.println("");
//		System.out
//				.println("Atacking Route " + atackingFigure.name + " " + atackingFigure.position + " " + atackingRoute);
//	}

	public boolean isPossibility() {
		return possibility;
	}

	public void setPossibility(boolean possibility) {
		this.possibility = possibility;
	}

}
