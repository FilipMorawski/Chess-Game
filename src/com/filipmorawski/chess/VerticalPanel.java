package com.filipmorawski.chess;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VerticalPanel extends JPanel {

	public VerticalPanel() {

		Font font = new Font("Calibri", Font.PLAIN, 20);

		Box theBox = Box.createVerticalBox();

		for (int i = 0; i < 8; i++) {
			JLabel label = new JLabel(Coordinates.vertical[i]);
			label.setFont(font);
			theBox.add(Box.createRigidArea(new Dimension(5, 24)));
			theBox.add(label);
			theBox.add(Box.createRigidArea(new Dimension(5, 25)));
		}
		this.add(theBox);
	}

}