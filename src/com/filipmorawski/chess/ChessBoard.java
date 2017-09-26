package com.filipmorawski.chess;

import java.awt.GridLayout;
import java.io.Serializable;
import javax.swing.JPanel;

public class ChessBoard extends JPanel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean enabled;

	// Constructor creating buttons which represent chessfields and adding it to static list
	public ChessBoard() {
		GridLayout grid = new GridLayout(8, 8);
		this.setLayout(grid);

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				FieldButton button = new FieldButton(i, j);
				this.add(button);
				ButtonMap.map.add(button);
			}
		}
	}

	public ChessBoard(String color, boolean enabled) {

		GridLayout grid = new GridLayout(8, 8);
		this.setLayout(grid);
		this.enabled = enabled;

		if (MovesCount.movesCount == 0) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					FieldButton button = new FieldButton(i, j);
					button.setEnabled(this.enabled);
					this.add(button);
					ButtonMap.map.add(button);
				}
			}
		}

		if (MovesCount.movesCount != 0) {
			for (FieldButton button : ButtonMap.map) {
				button.setEnabled(this.enabled);
				this.add(button);
				button.revalidate();
				button.repaint();
				button.addActionListener(new ButtonListener());
			}
		}
	}
}
