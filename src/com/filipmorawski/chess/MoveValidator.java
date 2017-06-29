package com.filipmorawski.chess;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

// Checking if move is correct and then, pass it to Figure class

public class MoveValidator {

	private Boolean moveVerification = true;
	private Boolean dialog = false;
	
	public MoveValidator(Figure chosenFigure, FieldButton chosenButton) {
		
//Checking whose turn it is		
		if (WhoseTurn.whiteTurn) {
			if(!new WhoseTurn().whoseTurn(chosenFigure.getColor())) {
				if (!dialog) {
					dialog = true;
					JOptionPane.showMessageDialog(null, "It's white turn!", "Wrong move", JOptionPane.WARNING_MESSAGE);
					moveVerification = false;
				}
			}
		}
		
		if (WhoseTurn.blackTurn) {
			if(new WhoseTurn().whoseTurn(chosenFigure.getColor())) {
				if (!dialog) {
					dialog = true;
					JOptionPane.showMessageDialog(null, "It's black turn!", "Wrong move", JOptionPane.WARNING_MESSAGE);
					moveVerification = false;
				}
			}
		}
//Checking if figure you want to move is only the one who defending king from check
		DefendingKing defend = new DefendingKing();
		ArrayList<String> figuresDefendingKing = defend.isDefendingFigures();
		if (figuresDefendingKing.contains(chosenFigure.getPosition())) {
			if (!dialog) {
				dialog = true;
				moveVerification = false;
				JOptionPane.showMessageDialog(null, "You cannot put this figure on this field!", "Wrong move", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		
		
// Checking enPassant possibility and if, its do it
			if (chosenFigure.getName().equals("Pawn")) {
				EnPassant eP = new EnPassant();
				if (eP.check(chosenFigure, chosenButton)) {
					moveVerification = true;
				}
			}
		
// Checking castling possibility and player choose of doing it
			Castling castling = new Castling(chosenFigure, chosenButton);
			if(castling.isCastling() && !castling.isDoThis()) {
				 moveVerification = false;
			}
		
//	Checking if chosen move is in in figure's range
		if (!chosenFigure.getPossibleMoves().contains(chosenButton.getButtonTitle())) {
			if (chosenButton.buttonTitle == chosenFigure.position) {
			moveVerification = false;
			} else {
				if (!dialog) {
					dialog = true;
					moveVerification = false;
					JOptionPane.showMessageDialog(null, "You cannot put this figure on this field!", "Wrong move", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		
// Checking if chosen field is occupied by own figure 
		if (chosenButton.getIsPiece()) {
			if(chosenButton.figure.color == chosenFigure.color) {
				if (chosenButton.buttonTitle != chosenFigure.position) {
					if (!dialog) {
						dialog = true;
						moveVerification = false;
						JOptionPane.showMessageDialog(null, "You cannot take your own figures!", "Wrong move", JOptionPane.WARNING_MESSAGE);
					}
				}
			} 
		}  
	}


	public Boolean getMoveVerification() {
		return moveVerification;
	}

	public void setMoveVerification(Boolean moveVerification) {
		this.moveVerification = moveVerification;
	}

}
