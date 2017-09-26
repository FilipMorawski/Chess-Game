package com.filipmorawski.chess;

import javax.swing.JButton;

// Checking if Pawn reach promotion zone

public class Promotion {

	public boolean check(FieldButton chosenButton) {
		Figure figure = chosenButton.getFigure();
		String name = figure.getName();
		int color = figure.getColor();
		String position = figure.getPosition();
		boolean promotion = false;

		if (name.equals("Pawn")) {
			if (color == 1) {
				if (position.contains("8")) {
					promotion = true;
				}
			}
			if (color == 2) {
				if (position.contains("1")) {
					promotion = true;
				}
			}

		}
		return promotion;
	}

}
