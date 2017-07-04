package com.filipmorawski.chess;

import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

// Class handles moving of figures and all Figures stuff

public abstract class Figure {
	int color; // 1 - white , 2-black
	int verticalID;
	int horizontalID;
	int moves = 0;
	int lastMoveCount;
	String lastPosition;
	String position;
	String name;
	Icon icon;
	ArrayList<String> possibleMoves = new ArrayList<String>();
	ArrayList<String> range = new ArrayList<String>();
	
	abstract void setPossibleMoves();
	
	 public static void move(FieldButton previousButton, FieldButton chosenButton) {
		 if(previousButton.getIsPiece()) {
			 
			 Figure chosenFigure = previousButton.figure;
			 
			 if (new MoveValidator(chosenFigure, chosenButton).getMoveVerification()) {
				 chosenFigure.updateState(chosenButton.getButtonTitle(), chosenButton.getVerticalID(), chosenButton.getHorizontalID());
				 if(chosenButton.getIsPiece()) {
					 Figures.figures.remove(chosenButton.getFigure());					 
				 }
				 if(chosenFigure.getName().equals("Pawn")) {
					 chosenFigure.setLastPosition(previousButton.getButtonTitle());
					 chosenFigure.setLastMoveCount(MovesCount.movesCount);
				 }
				 chosenButton.setFigure(chosenFigure);
				 chosenButton.setIsPiece(true);
				 chosenButton.setIcon(chosenButton.figure.icon);
				 chosenFigure.moves++;
				 previousButton.setIsPiece(false);
				 previousButton.figure = null;
				 previousButton.setIcon(null);
				 
				 if (new Promotion().check(chosenButton)) {
					 Figure figure = chosenButton.getFigure();
					 
					 int color = figure.getColor();
					 String position = figure.getPosition();
					 int horizontalID = figure.horizontalID;
					 int verticalID = figure.verticalID;
					 Figures.figures.remove(figure);
					 chosenButton.figure = null;
					 
					 String choose = new PromotionOption().getChoose();
					 chosenButton.setFigure(createFigure(choose, color, position, verticalID, horizontalID));
					 chosenButton.setIcon(chosenButton.figure.icon);
				 }
				 
				MovesCount.movesCount ++; 
				new RefreshPositions().refresh();
				new CheckMate().check();
				 
				 WhoseTurn.whiteTurn = !WhoseTurn.whiteTurn;
				 WhoseTurn.blackTurn = !WhoseTurn.blackTurn;
			 }
		 }
		 
	 }
	 
	public static Figure createFigure(String name, int color, String position, int verticalID, int horizontalID) {
		Figure figure = null;
		if (name.equals("Tower")) {
			figure = new Tower(name, color, position, verticalID, horizontalID);
			Figures.figures.add(figure);
		}
		if (name.equals("Jumper")) {
			figure = new Jumper(name, color, position, verticalID, horizontalID);
			Figures.figures.add(figure);
		}
		if (name.equals("Courier")) {
			figure = new Courier(name, color, position, verticalID, horizontalID);
			Figures.figures.add(figure);
		}
		if (name.equals("Queen")) {
			figure = new Queen(name, color, position,  verticalID, horizontalID);
			Figures.figures.add(figure);
		}
		return figure;
	}
	 
	public void updateState (String position, int verticalID, int horizontalID) {
		this.position = position;
		this.verticalID = verticalID;
		this.horizontalID = horizontalID;
	}

	public int getColor() {
		return color;
	}

	public int getVerticalID() {
		return verticalID;
	}

	public int getHorizontalID() {
		return horizontalID;
	}

	public String getPosition() {
		return position;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public void setVerticalID(int verticalID) {
		this.verticalID = verticalID;
	}

	public void setHorizontalID(int horizontalID) {
		this.horizontalID = horizontalID;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public ArrayList<String> getPossibleMoves() {
		return possibleMoves;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPossibleMoves(ArrayList<String> possibleMoves) {
		this.possibleMoves = possibleMoves;
	}

	public String getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(String lastPosition) {
		this.lastPosition = lastPosition;
	}

	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}

	public int getLastMoveCount() {
		return lastMoveCount;
	}

	public void setLastMoveCount(int lastMoveCount) {
		this.lastMoveCount = lastMoveCount;
	}

	public ArrayList<String> getRange() {
		return range;
	}

	public void setRange(ArrayList<String> range) {
		this.range = range;
	}
}
