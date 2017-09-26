package com.filipmorawski.chess;

import java.util.ArrayList;

public class EnPassant {
	private boolean enPassant;
	private Figure chosenFigure;
	private FieldButton chosenButton;

	public EnPassant(Figure chosenFigure, FieldButton chosenButton) {
		this.chosenFigure = chosenFigure;
		this.chosenButton = chosenButton;
		this.enPassant = false;
	}

	// Checking EnPassant move possibility
	public boolean check() {
		String firstLetter = chosenButton.getButtonTitle().substring(0, 1);

		checkForWhites(firstLetter);
		checkForBlacks(firstLetter);

		return enPassant;
	}

	private void checkForWhites(String firstLetter) {

		// Checking EnPassant Possibility for Whites
		ArrayList<Figure> temporaryFigures = new ArrayList<Figure>(Figures.figures);
		boolean checkColorAndPosition = (chosenFigure.getColor() == 1 && chosenFigure.getPosition().contains("5")
				&& chosenButton.getButtonTitle().contains("6"));

		if (checkColorAndPosition) {
			for (Figure figure : temporaryFigures) {

				boolean checkEnemyPawnPosition = (figure.getPosition().equals(firstLetter + "5")
						&& figure.getColor() == 2 && figure.getName().equals("Pawn"));
				boolean checkEnemyPawnLastMove = (figure.getLastPosition().contains("7") && figure.getMoves() == 1
						&& (figure.getLastMoveCount() == (MovesCount.movesCount - 1)));

				if (checkEnemyPawnPosition) {
					if (checkEnemyPawnLastMove) {
						enPassant = true;
						chosenFigure.getPossibleMoves().add(firstLetter + "6");
						removeBlackPawn(firstLetter);
					}
				}
			}
		}
	}

	private void checkForBlacks(String firstLetter) {

		// Checking EnPassant Possibility for Blacks
		ArrayList<Figure> temporaryFigures = new ArrayList<Figure>(Figures.figures);
		boolean checkColorAndPosition = (chosenFigure.getColor() == 2 && chosenFigure.getPosition().contains("4")
				&& chosenButton.getButtonTitle().contains("3"));

		if (checkColorAndPosition) {
			for (Figure figure : temporaryFigures) {

				boolean checkEnemyPawnPosition = (figure.getPosition().equals(firstLetter + "4")
						&& figure.getColor() == 1 && figure.getName().equals("Pawn"));
				boolean checkEnemyPawnLastMove = (figure.getLastPosition().contains("2") && figure.getMoves() == 1
						&& (figure.getLastMoveCount() == (MovesCount.movesCount - 1)));

				if (checkEnemyPawnPosition) {
					if (checkEnemyPawnLastMove) {
						enPassant = true;
						chosenFigure.getPossibleMoves().add(firstLetter + "3");
						removeWhitePawn(firstLetter);
					}
				}
			}
		}
	}

	private void removeWhitePawn(String firstLetter) {

		// Remove enemy pawn after EnPassant move
		ArrayList<Figure> temporaryFigures = new ArrayList<Figure>(Figures.figures);
		for (Figure figure : temporaryFigures) {

			boolean selectEnemyPawnFigure = (figure.getPosition().equals(firstLetter + "4") && figure.getColor() == 1
					&& figure.getName().equals("Pawn"));

			if (selectEnemyPawnFigure) {
				Figures.figures.remove(figure);
			}

			for (FieldButton button : ButtonMap.map) {
				boolean selectEnemyPawnButton = (button.getButtonTitle().equals(firstLetter + 4));
				if (selectEnemyPawnButton) {
					button.setIsPiece(false);
					button.setFigure(null);
					button.setIcon(null);
				}
				button.repaint();
			}

		}

	}

	private void removeBlackPawn(String firstLetter) {

		// Remove enemy pawn after EnPassant move
		ArrayList<Figure> temporaryFigures = new ArrayList<Figure>(Figures.figures);
		for (Figure figure : temporaryFigures) {
			boolean selectEnemyPawnFigure = (figure.getPosition().equals(firstLetter + "5") && figure.getColor() == 2
					&& figure.getName().equals("Pawn"));
			if (selectEnemyPawnFigure) {
				Figures.figures.remove(figure);
			}

			for (FieldButton button : ButtonMap.map) {
				boolean selectEnemyPawnButton = (button.getButtonTitle().equals(firstLetter + 5));
				if (selectEnemyPawnButton) {
					button.setIsPiece(false);
					button.setFigure(null);
					button.setIcon(null);
				}
				button.repaint();
			}

		}

	}
}
