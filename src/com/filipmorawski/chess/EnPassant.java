package com.filipmorawski.chess;

import java.util.ArrayList;

public class EnPassant {
	private boolean enPassant = false;
	
	public boolean check(Figure chosenFigure, FieldButton chosenButton) {
		String firstLetter = chosenButton.getButtonTitle().substring(0,1);

// Checking EnPassant Possibility for Whites		
		if(chosenFigure.getColor() == 1 && chosenFigure.getPosition().contains("5") && chosenButton.getButtonTitle().contains("6")) {
			ArrayList<Figure> temporaryFigures = new ArrayList<Figure>(Figures.figures);
			for (Figure figure : temporaryFigures) {
				if(figure.getPosition().equals(firstLetter + "5") && figure.getColor()==2 && figure.getName().equals("Pawn")) {
					if (figure.getLastPosition().contains("7") && figure.getMoves() == 1 && (figure.getLastMoveCount() == (MovesCount.movesCount - 1))) {
						enPassant = true;
						chosenFigure.getPossibleMoves().add(firstLetter + "6"); 
						removeBlackPawn(firstLetter);
					}
				}
			}
		}
// Checking EnPassant Possibility for Blacks				
		if(chosenFigure.getColor() == 2 && chosenFigure.getPosition().contains("4") && chosenButton.getButtonTitle().contains("3")) {
			ArrayList<Figure> temporaryFigures = new ArrayList<Figure>(Figures.figures);
			for (Figure figure : temporaryFigures) {
				if(figure.getPosition().equals(firstLetter + "4") && figure.getColor()==1 && figure.getName().equals("Pawn")) {
					if (figure.getLastPosition().contains("2") && figure.getMoves() == 1 && (figure.getLastMoveCount() == (MovesCount.movesCount - 1))) {
						enPassant = true;
						chosenFigure.getPossibleMoves().add(firstLetter + "3"); 
						removeWhitePawn(firstLetter);
					}
				}
			}
		}
		
		return enPassant;
	}

	private void removeWhitePawn(String firstLetter) {
		ArrayList<Figure> temporaryFigures = new ArrayList<Figure>(Figures.figures);
		for (Figure figure : temporaryFigures) {
			if (figure.getPosition().equals(firstLetter + "4") && figure.getColor()==1 && figure.getName().equals("Pawn")) {
				Figures.figures.remove(figure);
			}
			
			for(FieldButton button : ButtonMap.map) {
				if (button.getButtonTitle().equals(firstLetter + 4)) {
					button.setIsPiece(false);
					button.figure = null;
					button.setIcon(null);
				}
				button.repaint();
			}
			
		}
		
	}

	private void removeBlackPawn(String firstLetter) {
		ArrayList<Figure> temporaryFigures = new ArrayList<Figure>(Figures.figures);
		for (Figure figure : temporaryFigures) {
			if (figure.getPosition().equals(firstLetter + "5") && figure.getColor()==2 && figure.getName().equals("Pawn")) {
				Figures.figures.remove(figure);
			}
			
			for(FieldButton button : ButtonMap.map) {
				if (button.getButtonTitle().equals(firstLetter + 5)) {
					button.setIsPiece(false);
					button.figure = null;
					button.setIcon(null);
				}
				button.repaint();
			}
			
		}
		
	}
}
