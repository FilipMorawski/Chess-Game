package com.filipmorawski.chess;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Castling extends JOptionPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean castling = false;
	private boolean doThis = false;
	private FieldButton a1, d1, a8, d8, h1, f1, h8, f8;
	private ArrayList<Figure> temporaryFigures;
	private boolean noKingMovesAndNoCheck, playerTryingCastlingMove, noFiguresInCastlingWay, towerForCastingWasNotMoved;
	private Figure chosenFigure;
	private FieldButton chosenButton;

	public Castling(Figure chosenFigure, FieldButton chosenButton) {
		this.chosenFigure = chosenFigure;
		this.chosenButton = chosenButton;
		temporaryFigures = new ArrayList<Figure>(Figures.figures);
		findUsefulButtons();
		check();
	}

	// Check if castling is possible
	private void check() {

		noKingMovesAndNoCheck = (chosenFigure.getName().equals("King") && chosenFigure.moves == 0 && !CheckMate.check);

		if (noKingMovesAndNoCheck) {
			checkWhiteShortCastling();
			checkBlackShortCastling();
			checkWhiteLongCastling();
			checkBlackLongCastling();
		}
	}

	private void checkWhiteShortCastling() {
		playerTryingCastlingMove = (chosenFigure.getColor() == 1 && chosenButton.getButtonTitle().equals("g1")
				&& chosenFigure.getPosition().equals("e1"));
		noFiguresInCastlingWay = (!Figures.figuresPositionMap.contains("f1")
				&& !Figures.figuresPositionMap.contains("g1"));

		if (playerTryingCastlingMove) {
			for (Figure figure : temporaryFigures) {

				towerForCastingWasNotMoved = (figure.getPosition().equals("h1") && figure.getName().equals("Tower")
						&& figure.getColor() == 1 && figure.moves == 0);

				if (towerForCastingWasNotMoved) {
					if (noFiguresInCastlingWay) {
						chosenFigure.possibleMoves.add("g1");
						setCastling(true);
						if (WhoseTurn.whiteTurn) {
							showDialog();
							if (doThis) {
								moveShortWhiteTower();
							}
						}
					}
				}
			}
		}
	}

	private void checkBlackShortCastling() {
		playerTryingCastlingMove = (chosenFigure.getColor() == 2 && chosenButton.getButtonTitle().equals("g8")
				&& chosenFigure.getPosition().equals("e8"));
		noFiguresInCastlingWay = (!Figures.figuresPositionMap.contains("f8")
				&& !Figures.figuresPositionMap.contains("g8"));

		if (playerTryingCastlingMove) {
			for (Figure figure : temporaryFigures) {

				towerForCastingWasNotMoved = (figure.getPosition().equals("h8") && figure.getName().equals("Tower")
						&& figure.getColor() == 2 && figure.moves == 0);

				if (towerForCastingWasNotMoved) {
					if (noFiguresInCastlingWay) {
						chosenFigure.possibleMoves.add("g8");
						setCastling(true);
						if (WhoseTurn.blackTurn) {
							showDialog();
							if (doThis) {
								moveShortBlackTower();
							}
						}
					}
				}
			}
		}
	}

	private void checkWhiteLongCastling() {
		playerTryingCastlingMove = (chosenFigure.getColor() == 1 && chosenButton.getButtonTitle().equals("c1")
				&& chosenFigure.getPosition().equals("e1"));
		noFiguresInCastlingWay = (!Figures.figuresPositionMap.contains("b1")
				&& !Figures.figuresPositionMap.contains("c1") && !Figures.figuresPositionMap.contains("d1"));

		if (playerTryingCastlingMove) {
			for (Figure figure : temporaryFigures) {

				towerForCastingWasNotMoved = (figure.getPosition().equals("a1") && figure.getName().equals("Tower")
						&& figure.getColor() == 1 && figure.moves == 0);

				if (towerForCastingWasNotMoved) {
					if (noFiguresInCastlingWay) {
						chosenFigure.possibleMoves.add("c1");
						setCastling(true);
						if (WhoseTurn.whiteTurn) {
							showDialog();
							if (doThis) {
								moveLongWhiteTower();
							}
						}
					}
				}
			}
		}
	}

	private void checkBlackLongCastling() {

		playerTryingCastlingMove = (chosenFigure.getColor() == 2 && chosenButton.getButtonTitle().equals("c8")
				&& chosenFigure.getPosition().equals("e8"));
		noFiguresInCastlingWay = (!Figures.figuresPositionMap.contains("b8")
				&& !Figures.figuresPositionMap.contains("c8") && !Figures.figuresPositionMap.contains("d8"));

		if (playerTryingCastlingMove) {
			for (Figure figure : temporaryFigures) {

				towerForCastingWasNotMoved = (figure.getPosition().equals("a8") && figure.getName().equals("Tower")
						&& figure.getColor() == 2 && figure.moves == 0);

				if (towerForCastingWasNotMoved) {
					if (noFiguresInCastlingWay) {
						chosenFigure.possibleMoves.add("c8");
						setCastling(true);
						if (WhoseTurn.blackTurn) {
							showDialog();
							if (doThis) {
								moveLongBlackTower();
							}
						}
					}
				}
			}
		}
	}

	private void findUsefulButtons() {

		a1 = null;
		d1 = null;
		a8 = null;
		d8 = null;
		h1 = null;
		f1 = null;
		h8 = null;
		f8 = null;

		for (FieldButton button : ButtonMap.map) {
			if (button.getButtonTitle().equals("a1")) {
				a1 = button;
			}
			if (button.getButtonTitle().equals("d1")) {
				d1 = button;
			}
			if (button.getButtonTitle().equals("a8")) {
				a8 = button;
			}
			if (button.getButtonTitle().equals("d8")) {
				d8 = button;
			}
			if (button.getButtonTitle().equals("h1")) {
				h1 = button;
			}
			if (button.getButtonTitle().equals("f1")) {
				f1 = button;
			}
			if (button.getButtonTitle().equals("h8")) {
				h8 = button;
			}
			if (button.getButtonTitle().equals("f8")) {
				f8 = button;
			}
		}

	}

	// Moving Towers used in castling

	private void moveLongBlackTower() {
		new MoveMechanism(a8, d8).move();
		a8.repaint();
		d8.repaint();
	}

	private void moveLongWhiteTower() {
		new MoveMechanism(a1, d1).move();
		a1.repaint();
		d1.repaint();
	}

	private void moveShortBlackTower() {
		new MoveMechanism(h8, f8).move();
		h8.repaint();
		f8.repaint();
	}

	private void moveShortWhiteTower() {
		new MoveMechanism(h1, f1).move();
		h1.repaint();
		f1.repaint();
	}

	// Displaying dialog box when castling is possible
	public void showDialog() {

		JPanel panel = new JPanel();
		BorderLayout layout = new BorderLayout();
		panel.setLayout(layout);
		JLabel label = new JLabel("Castling is possible, do you want to do this?");
		JRadioButton b1 = new JRadioButton("Yes", false);
		JRadioButton b2 = new JRadioButton("No", false);
		Box theBox = Box.createHorizontalBox();

		RadioListener listener = new RadioListener();

		b1.addItemListener(listener);
		b2.addItemListener(listener);

		theBox.add(b1);
		theBox.add(b2);

		theBox.setAlignmentY(CENTER_ALIGNMENT);

		panel.add(label, BorderLayout.NORTH);
		panel.add(theBox, BorderLayout.CENTER);

		JOptionPane.showMessageDialog(null, panel, "Castling", JOptionPane.QUESTION_MESSAGE);
	}

	public boolean isCastling() {
		return castling;
	}

	public void setCastling(boolean castling) {
		this.castling = castling;
	}

	public boolean isDoThis() {
		return doThis;
	}

	public void setDoThis(boolean doThis) {
		this.doThis = doThis;
	}

	class RadioListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == 1) {
				JRadioButton rb = (JRadioButton) e.getSource();
				String choose = rb.getText();
				if (choose.equals("Yes")) {
					setDoThis(true);
				}
				if (choose.equals("No")) {
					setDoThis(false);
				}
			}

		}
	}
}
