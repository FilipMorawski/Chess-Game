package com.filipmorawski.chess;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class ButtonListener implements ActionListener {

	public static ArrayList<FieldButton> pressedButtons = new ArrayList<FieldButton>();

	@Override
	public void actionPerformed(ActionEvent e) {
		new RefreshPositions().refresh();
		FieldButton chosenButton = (FieldButton) e.getSource();

//////////////////// TESTING PRINTS ////////////////////////		
//		System.out.println(chosenButton.getIsPiece());
//		System.out.println("Wspó³rzêdne to : Horizontal - " + chosenButton.horizontalID + " Vertical - " + chosenButton.verticalID);
//		if (chosenButton.figure != null) {
//			System.out.println(chosenButton.figure.getName());
//			System.out.println(" Kolor to : " + chosenButton.figure.color);
//			System.out.println("Miejsca figur to: " + Figures.figuresPositionMap);
//			System.out.println("Iloœæ figur to : " + Figures.figures.size());
//			System.out.println(" Mo¿liwe ruchy  " +  chosenButton.figure.possibleMoves);
//			System.out.println(" Pozycja to " +  chosenButton.figure.position);
//			System.out.println("Ta figura wykona³a : " + chosenButton.getFigure().getMoves());
//			System.out.println("Czy jest szach? " + CheckMate.check);
//		}
///////////////////////////////////////////////////////////		

		detectClicks(chosenButton);
	}

// Creating two objects useful to further processing - Figure which has chosen to move and place where player wants to move it	
	
	private void detectClicks(FieldButton chosenButton) {
		if (!pressedButtons.isEmpty()) {
			pressedButtons.add(chosenButton);
			FieldButton previousButton = pressedButtons.get(0);
			previousButton.setBackground(previousButton.getOriginalColor());
			Figure.move(previousButton, chosenButton);
			pressedButtons.clear();				
		} else if (pressedButtons.isEmpty() && chosenButton.getIsPiece()) {
			chosenButton.setBackground(Color.BLUE);
			pressedButtons.add(chosenButton);
		}
	}			
	
}
