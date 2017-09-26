package com.filipmorawski.chess;

public class FigureFactory {

	private Figure figure;

	public Figure createFigure(String type, int color, String position, int verticalID, int horizontalID) {
		if (type.equals("Tower")) {
			figure = new Tower(type, color, position, verticalID, horizontalID);
			figure.setLastPosition(position);
		}
		if (type.equals("Jumper")) {
			figure = new Jumper(type, color, position, verticalID, horizontalID);
			figure.setLastPosition(position);
		}
		if (type.equals("Courier")) {
			figure = new Courier(type, color, position, verticalID, horizontalID);
			figure.setLastPosition(position);
		}
		if (type.equals("Queen")) {
			figure = new Queen(type, color, position, verticalID, horizontalID);
			figure.setLastPosition(position);
		}
		if (type.equals("Pawn")) {
			figure = new Pawn(type, color, position, verticalID, horizontalID);
			figure.setLastPosition(position);
		}
		if (type.equals("King")) {
			figure = new King(type, color, position, verticalID, horizontalID);
			figure.setLastPosition(position);
		}
		Figures.figures.add(this.figure);
		return this.figure;
	}
}
