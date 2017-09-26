package com.filipmorawski.chess;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Representing panel of horizontal coordinates on gameboard
public class HorizontalPanel extends JPanel {

	public HorizontalPanel() {

		Font font = new Font("Calibri", Font.PLAIN, 20);

		Box theBox = Box.createHorizontalBox();

		for (int i = 0; i < 8; i++) {
			JLabel label = new JLabel(Coordinates.horizontal[i]);
			label.setFont(font);
			theBox.add(Box.createRigidArea(new Dimension(42, 5)));
			theBox.add(label);
			theBox.add(Box.createRigidArea(new Dimension(45, 5)));
		}
		this.add(theBox);
	}
}
