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

import com.filipmorawski.chess.PromotionOption.RadioListener;

public class Castling extends JOptionPane {
	
	private boolean castling = false;
	private boolean doThis = false;

	public Castling (Figure chosenFigure, FieldButton chosenButton) {
		check(chosenFigure, chosenButton);
	}
	
	private void check(Figure chosenFigure, FieldButton chosenButton) {

//Shorts Castlings
			if (chosenFigure.getName().equals("King") && chosenFigure.moves == 0) {
				if (chosenFigure.getColor() == 1 && chosenButton.getButtonTitle().equals("g1") && chosenFigure.getPosition().equals("e1")) {
					for (Figure figure : Figures.figures) {
						if (figure.getPosition().equals("h1") && figure.getName().equals("Tower") && figure.getColor() == 1 && figure.moves == 0) {
							if (!Figures.figuresPositionMap.contains("f1") && !Figures.figuresPositionMap.contains("g1")) {
								setCastling(true);
								showDialog();
								if (doThis) {
									moveShortWhiteTower();
								}
							}
						} 
						if (figure.getPosition().equals("h1") && figure.getName().equals("Tower") && figure.getColor() == 1 && figure.moves != 0) {
							chosenFigure.possibleMoves.remove("g1");
						}
					}
				}
				if (chosenFigure.getColor() == 2 && chosenButton.getButtonTitle().equals("g8")) {
					ArrayList<Figure> temporaryFigures = new ArrayList<Figure>(Figures.figures);
					for (Figure figure : temporaryFigures) {
						if (figure.getPosition().equals("h8") && figure.getName().equals("Tower") && figure.getColor() == 2 && figure.moves == 0) {
							if (!Figures.figuresPositionMap.contains("f8") && !Figures.figuresPositionMap.contains("g8")) {
								setCastling(true);
								showDialog();
								if (doThis) {
									moveShortBlackTower();
								}
							}
						}
						if (figure.getPosition().equals("h8") && figure.getName().equals("Tower") && figure.getColor() == 2 && figure.moves != 0) {
							chosenFigure.possibleMoves.remove("g8");
						}
					}
				}
			}
			
//Long Castlings
			if (chosenFigure.getName().equals("King") && chosenFigure.moves == 0) {
				if (chosenFigure.getColor() == 1 && chosenButton.getButtonTitle().equals("c1") && chosenFigure.getPosition().equals("e1")) {
					ArrayList<Figure> temporaryFigures = new ArrayList<Figure>(Figures.figures);
					for (Figure figure : temporaryFigures) {
						if (figure.getPosition().equals("a1") && figure.getName().equals("Tower") && figure.getColor() == 1 && figure.moves == 0) {
							if (!Figures.figuresPositionMap.contains("b1") && !Figures.figuresPositionMap.contains("c1") && !Figures.figuresPositionMap.contains("d1")) {
								setCastling(true);
								showDialog();
								if (doThis) {
									moveLongWhiteTower();
								}
							}
						} 
						if (figure.getPosition().equals("a1") && figure.getName().equals("Tower") && figure.getColor() == 1 && figure.moves != 0) {
							chosenFigure.possibleMoves.remove("c1");
						}
					}
				}
			}
				if (chosenFigure.getName().equals("King") && chosenFigure.moves == 0) {
					if (chosenFigure.getColor() == 2 && chosenButton.getButtonTitle().equals("c8") && chosenFigure.getPosition().equals("c8")) {
						ArrayList<Figure> temporaryFigures = new ArrayList<Figure>(Figures.figures);
						for (Figure figure : temporaryFigures) {
							if (figure.getPosition().equals("a8") && figure.getName().equals("Tower") && figure.getColor() == 2 && figure.moves == 0) {
								if (!Figures.figuresPositionMap.contains("b8") && !Figures.figuresPositionMap.contains("c8") && !Figures.figuresPositionMap.contains("d8")) {
									setCastling(true);
									showDialog();
									if (doThis) {
										moveLongBlackTower();
									}
								}
							} 
							if (figure.getPosition().equals("a8") && figure.getName().equals("Tower") && figure.getColor() == 2 && figure.moves != 0) {
								chosenFigure.possibleMoves.remove("c8");
							}
						}
					}			
				}				
	}
	
	
// Moving Towers used in castling		
	
	private void moveLongBlackTower() {
		
		ArrayList<Figure> temporaryFigures = new ArrayList<Figure>(Figures.figures);
		for (Figure figure : temporaryFigures) {

			if (figure.getPosition().equals("a8")) {
				Figures.figures.remove(figure);
						
				FieldButton a8;
				FieldButton d8;
				for (FieldButton button : ChessBoard.buttonMap) {
						if (button.getButtonTitle().equals("a8")) {
						a8 = button;
						a8.setIsPiece(false);
						a8.figure = null;
						a8.setIcon(null);
					} 
					if (button.getButtonTitle().equals("d8")) {
						 d8 = button;
						 d8.setFigure(new Tower("Tower", 1, "d1", 0, 3));
						 Figures.figures.add(d8.figure);
						 d8.setIsPiece(true);
						 d8.setIcon(d8.figure.icon);
					}
					button.repaint();
				}
			}	
		}		
	}

	private void moveLongWhiteTower() {
		ArrayList<Figure> temporaryFigures = new ArrayList<Figure>(Figures.figures);
		for (Figure figure : temporaryFigures) {

			if (figure.getPosition().equals("a1")) {
				Figures.figures.remove(figure);
						
				FieldButton a1;
				FieldButton d1;
				for (FieldButton button : ChessBoard.buttonMap) {
						if (button.getButtonTitle().equals("a1")) {
						a1 = button;
						a1.setIsPiece(false);
						a1.figure = null;
						a1.setIcon(null);
					} 
					if (button.getButtonTitle().equals("d1")) {
						 d1 = button;
						 d1.setFigure(new Tower("Tower", 1, "d1", 7, 3));
						 Figures.figures.add(d1.figure);
						 d1.setIsPiece(true);
						 d1.setIcon(d1.figure.icon);
					}
					button.repaint();
				}
			}	
		}
	}

	private void moveShortBlackTower() {
		ArrayList<Figure> temporaryFigures = new ArrayList<Figure>(Figures.figures);
		for (Figure figure : temporaryFigures) {
			if (figure.getPosition().equals("h8")) {
				Figures.figures.remove(figure);
						
				FieldButton h8;
				FieldButton f8;
				for (FieldButton button : ChessBoard.buttonMap) {
						if (button.getButtonTitle().equals("h8")) {
						h8 = button;
						h8.setIsPiece(false);
						h8.figure = null;
						h8.setIcon(null);
					} 
					if (button.getButtonTitle().equals("f8")) {
						 f8 = button;
						 f8.setFigure(new Tower("Tower", 2, "f8", 0, 5));
						 Figures.figures.add(f8.figure);
						 f8.setIsPiece(true);
						 f8.setIcon(f8.figure.icon);
					}
					button.repaint();
				}
			}	
		}
	}

	private void moveShortWhiteTower() {
		ArrayList<Figure> temporaryFigures = new ArrayList<Figure>(Figures.figures);
		for (Figure figure : temporaryFigures) {
			if (figure.getPosition().equals("h1")) {
				Figures.figures.remove(figure);
			
				FieldButton h1;
				FieldButton f1;
				for (FieldButton button : ChessBoard.buttonMap) {
						if (button.getButtonTitle().equals("h1")) {
						h1 = button;
						h1.setIsPiece(false);
						h1.figure = null;
						h1.setIcon(null);
//						h1.repaint();
					} 
					if (button.getButtonTitle().equals("f1")) {
						 f1 = button;
						 f1.setFigure(new Tower("Tower", 1, "f1", 7, 5));
						 Figures.figures.add(f1.figure);
						 f1.setIsPiece(true);
						 f1.setIcon(f1.figure.icon);
					}
					button.repaint();
				}
			}	
		}
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
		return castling ;
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