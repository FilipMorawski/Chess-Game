package com.filipmorawski.chess;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ButtonListener implements ActionListener {

	public static ArrayList<FieldButton> pressedButtons = new ArrayList<FieldButton>();

	@Override
	public void actionPerformed(ActionEvent e) {
		new RefreshPositions().refresh();
		FieldButton chosenButton = (FieldButton) e.getSource();

		// printTestingValues(chosenButton);

		detectClicks(chosenButton);
	}

	// Creating two objects useful to further processing - Figure which has
	// chosen to move and place where player wants to move it

	private void detectClicks(FieldButton chosenButton) {
		if (!pressedButtons.isEmpty()) {
			pressedButtons.add(chosenButton);
			FieldButton previousButton = pressedButtons.get(0);
			previousButton.setBackground(previousButton.getOriginalColor());
			new MoveMechanism(previousButton, chosenButton).makeMove();
			pressedButtons.clear();
		} else if (pressedButtons.isEmpty() && chosenButton.getIsPiece()) {
			chosenButton.setBackground(Color.BLUE);
			pressedButtons.add(chosenButton);
		}
	}

	private final void printTestingValues(FieldButton chosenButton) {
		System.out.println(chosenButton.getIsPiece());
		System.out.println("Wspó³rzêdne to : Horizontal - " + chosenButton.getHorizontalID() + " Vertical - "
				+ chosenButton.getVerticalID());
		if (chosenButton.getFigure() != null) {
			System.out.println(chosenButton.getFigure().getName());
			System.out.println(chosenButton.getButtonTitle());
			System.out.println(" Kolor to : " + chosenButton.getFigure().color);
			System.out.println("Miejsca figur to: " + Figures.figuresPositionMap);
			System.out.println("Iloœæ figur to : " + Figures.figures.size());
			System.out.println(" Mo¿liwe ruchy  " + chosenButton.getFigure().possibleMoves);
			System.out.println(" Pozycja to " + chosenButton.getFigure().position);
			System.out.println("Ta figura wykona³a : " + chosenButton.getFigure().getMoves());
			System.out.println("Iloœæ wykonanych ruchów : " + MovesCount.movesCount);
			System.out.println("Czy jest szach? " + CheckMate.check);
			System.out.println("Range of Figure " + chosenButton.getFigure().range);
			System.out.println("Ostatnia pozycja figury : " + chosenButton.getFigure().getLastPosition());
		}
	}

}
