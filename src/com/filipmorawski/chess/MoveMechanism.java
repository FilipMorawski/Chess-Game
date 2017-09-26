package com.filipmorawski.chess;

public class MoveMechanism {

	private FieldButton previousButton, chosenButton;
	private Figure chosenFigure;
	private boolean moveVerification;

	// This class is responsible of executing move request from player
	public MoveMechanism(FieldButton previousButton, FieldButton chosenButton) {
		this.previousButton = previousButton;
		this.chosenButton = chosenButton;
		if (previousButton.getIsPiece()) {
			this.chosenFigure = previousButton.getFigure();
		}
	}

	public void makeMove() {
		new RefreshPositions().refresh();
		getMoveVerification();
		if (moveVerification) {
			move();
			changeTurnAndSend();
		}
	}

	// Checking if move is correct
	private void getMoveVerification() {
		moveVerification = new MoveValidator(chosenFigure, chosenButton).getMoveVerification();
	}

	// Repainting buttons participating in move and set necessary variables
	public void move() {
		chosenFigure.updateState(chosenButton.getButtonTitle(), chosenButton.getVerticalID(),
				chosenButton.getHorizontalID());
		if (chosenButton.getIsPiece()) {
			Figures.figures.remove(chosenButton.getFigure());
		}
		if (chosenFigure.getName().equals("Pawn")) {

		}
		chosenButton.setFigure(chosenFigure);
		chosenButton.setIsPiece(true);
		chosenButton.setIcon(chosenButton.getFigure().getIcon());
		chosenButton.setDisabledIcon(chosenButton.getFigure().getIcon());
		chosenFigure.setLastMoveCount(MovesCount.movesCount);
		chosenFigure.setLastPosition(previousButton.getButtonTitle());
		chosenFigure.moves++;
		previousButton.setIsPiece(false);
		previousButton.setFigure(null);
		previousButton.setIcon(null);

		if (new Promotion().check(chosenButton)) {
			Figure figure = chosenButton.getFigure();

			int color = figure.getColor();
			String position = figure.getPosition();
			int horizontalID = figure.getHorizontalID();
			int verticalID = figure.getVerticalID();
			Figures.figures.remove(figure);
			chosenButton.setFigure(null);

			String choose = new PromotionOption().getChoose();

			chosenButton.setFigure(new FigureFactory().createFigure(choose, color, position, verticalID, horizontalID));
			chosenButton.setIcon(chosenButton.getFigure().getIcon());
		}
	}

	// Changing variables responsible of changing active player and send them to server
	private void changeTurnAndSend() {
		MovesCount.movesCount++;
		new RefreshPositions().refresh();
		new CheckMate().check();

		WhoseTurn.whiteTurn = !WhoseTurn.whiteTurn;
		WhoseTurn.blackTurn = !WhoseTurn.blackTurn;
		WhoseTurn.sendToServer = true;
	}
}