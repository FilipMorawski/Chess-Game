package com.filipmorawski.chess;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CheckMate {

	public static boolean check = false;

	private ArrayList<Figure> whites;
	private ArrayList<Figure> blacks;

	private ArrayList<String> allWhitesPossibleMoves;
	private ArrayList<String> allBlacksPossibleMoves;

	private Figure whiteKing;
	private Figure blackKing;

	private Boolean whiteCheck;
	private Boolean blackCheck;

	private BlockPossibility block;

	public final void check() {
		this.whites = new ArrayList<Figure>();
		this.blacks = new ArrayList<Figure>();
		this.allWhitesPossibleMoves = new ArrayList<String>();
		this.allBlacksPossibleMoves = new ArrayList<String>();
		this.whiteCheck = false;
		this.blackCheck = false;
		block = new BlockPossibility();
		check = false;

		findKingsOnMap();
		removeIllegalKingsPossibleMoves();
		detectCheckForWhite();
		detectCheckForBlack();
		// printControlData();
	}

	private void findKingsOnMap() {
		// Finding Kings on Map
		for (Figure figure : Figures.figures) {
			if (figure.getColor() == 1) {
				whites.add(figure);
				allWhitesPossibleMoves.addAll(figure.getPossibleMoves());
				if (figure.getName().equals("King")) {
					whiteKing = figure;
					allWhitesPossibleMoves.removeAll(figure.getPossibleMoves());
				}
			} else {
				blacks.add(figure);
				allBlacksPossibleMoves.addAll(figure.getPossibleMoves());
				if (figure.getName().equals("King")) {
					blackKing = figure;
					allBlacksPossibleMoves.removeAll(figure.getPossibleMoves());
				}
			}
		}
	}

	private void removeIllegalKingsPossibleMoves() {
		for (Figure black : blacks) {
			ArrayList<String> whPossibleMoves = new ArrayList<String>(whiteKing.getPossibleMoves());
			boolean ownFigureIsInBlackKingRange = (blackKing.getPossibleMoves().contains(black.getPosition()));

			if (ownFigureIsInBlackKingRange) {
				blackKing.possibleMoves.remove(black.getPosition());
			}

			for (String wkMove : whPossibleMoves) {
				boolean dangerZoneInWhiteKingRange = (black.getPossibleMoves().contains(wkMove));

				if (dangerZoneInWhiteKingRange) {
					whiteKing.possibleMoves.remove(wkMove);
				}
			}
		}

		for (Figure white : whites) {
			// Removing own figures positions from possible Movement of whiteKing
			ArrayList<String> bkPossibleMoves = new ArrayList<String>(blackKing.getPossibleMoves());
			boolean ownFigureIsInWhiteKingRange = (whiteKing.getPossibleMoves().contains(white.getPosition()));

			if (ownFigureIsInWhiteKingRange) {
				whiteKing.possibleMoves.remove(white.getPosition());
			}

			for (String bkMove : bkPossibleMoves) {
				boolean dangerZoneInBlackKingRange = (white.getPossibleMoves().contains(bkMove));

				if (dangerZoneInBlackKingRange) {
					blackKing.possibleMoves.remove(bkMove);
				}
			}
		}
	}

	private void detectCheckForWhite() {

		// Checking check and checkMate
		for (Figure black : blacks) {

			boolean kingIsInEnemyMovesZone = (black.getPossibleMoves().contains(whiteKing.getPosition()));
			boolean kingHasNoMoves = (whiteKing.getPossibleMoves().isEmpty());
			boolean threatCannotBeTaken = (!allWhitesPossibleMoves.contains(black.getPosition()));

			if (kingIsInEnemyMovesZone) {
				if (kingHasNoMoves) {
					if (threatCannotBeTaken) {
						boolean enemyRangeCannotBeBlocked = (!block.blockCheck(black, whiteKing,
								allWhitesPossibleMoves));
						if (enemyRangeCannotBeBlocked) {
							JOptionPane.showMessageDialog(null, "CheckMate! - Black player wins", "CheckMate",
									JOptionPane.WARNING_MESSAGE);
							check = true;
						} else if (!check) {
							JOptionPane.showMessageDialog(null, "Check! - White King is in danger", "Check",
									JOptionPane.WARNING_MESSAGE);
							whiteCheck = true;
							check = true;
						}
					} else if (!check) {
						JOptionPane.showMessageDialog(null, "Check! - White King is in danger", "Check",
								JOptionPane.WARNING_MESSAGE);
						whiteCheck = true;
						check = true;
					}
				} else if (!check) {
					JOptionPane.showMessageDialog(null, "Check! - White King is in danger", "Check",
							JOptionPane.WARNING_MESSAGE);
					whiteCheck = true;
					check = true;
				}
			}
		}
	}

	private void detectCheckForBlack() {

		// Checking check and checkMate
		for (Figure white : whites) {

			boolean kingIsInEnemyMovesZone = (white.getPossibleMoves().contains(blackKing.getPosition()));
			boolean kingHasNoMoves = (blackKing.getPossibleMoves().isEmpty());
			boolean threatCannotBeTaken = (!allBlacksPossibleMoves.contains(white.getPosition()));

			if (kingIsInEnemyMovesZone) {
				if (kingHasNoMoves) {
					if (threatCannotBeTaken) {
						boolean enemyRangeCannotBeBlocked = (!block.blockCheck(white, blackKing,
								allBlacksPossibleMoves));
						if (enemyRangeCannotBeBlocked) {
							JOptionPane.showMessageDialog(null, "CheckMate! - White player wins", "CheckMate",
									JOptionPane.WARNING_MESSAGE);
							check = true;
						} else if (!check) {
							JOptionPane.showMessageDialog(null, "Check! - Black King is in danger", "Check",
									JOptionPane.WARNING_MESSAGE);
							whiteCheck = true;
							check = true;
						}
					} else if (!check) {
						JOptionPane.showMessageDialog(null, "Check! - Black King is in danger", "Check",
								JOptionPane.WARNING_MESSAGE);
						check = true;
					}
				} else if (!check) {
					JOptionPane.showMessageDialog(null, "Check! - Black King is in danger", "Check",
							JOptionPane.WARNING_MESSAGE);
					blackCheck = true;
					check = true;
				}
			}
		}
	}

	private void printControlData() {
		System.out.println("Second Condition " + blackKing.getPossibleMoves().isEmpty());
		System.out.println("Possible black King Moves : " + blackKing.possibleMoves);
		System.out.println("Fourth condition " + !block.isPossibility());
		System.out.println("");
		System.out.println("Possible black King Moves : " + blackKing.possibleMoves);
		System.out.println(" Black is empty " + blackKing.getPossibleMoves().isEmpty());
		System.out.println("Possible white King Moves : " + whiteKing.possibleMoves);
	}

}
