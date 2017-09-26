package com.filipmorawski.chess;

import java.awt.Color;
import java.io.Serializable;

import javax.swing.Icon;
import javax.swing.JButton;

// Handles all information about chess fields and creating starting setup of Pieces

public class FieldButton extends JButton implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String buttonTitle;
	private int verticalID;
	private int horizontalID;
	private	Icon buttonIcon;
	private Boolean isPiece = false;
	private Color originalColor;
	private Figure figure = null;
	
	
	public FieldButton(int verticalID, int horizontalID) {
		this.verticalID = verticalID;
		this.horizontalID = horizontalID;
		
		if ( ((verticalID % 2 > 0) && (horizontalID % 2 > 0)) || ((verticalID % 2 == 0) && (horizontalID % 2 == 0)) ) {
			this.setBackground(Color.WHITE);
			this.originalColor = Color.WHITE;
		} else {
			this.setBackground(Color.LIGHT_GRAY);
			this.originalColor = Color.LIGHT_GRAY;
		}
		this.setButtonTitle(Coordinates.horizontal[horizontalID] + Coordinates.vertical[verticalID]);
		
///////////////////  Show Coordinates on Fields //////////////////
//		this.setText(Coordinates.horizontal[horizontalID] + Coordinates.vertical[verticalID]);
//////////////////////////////////////////////////////////////////		
		
		if (MovesCount.movesCount == 0) {
			setIcons();			
		}	
		this.addActionListener(new ButtonListener());
	}

	private void setIcons() {
		new StartingFiguresLayout(this).setLayout();
	}

	public void setButtonTitle(String string) {
		this.buttonTitle = string;	
	}
	
	public String getButtonTitle() {
		return buttonTitle;
	}

	public int getVerticalID() {
		return verticalID;
	}

	public int getHorizontalID() {
		return horizontalID;
	}

	public void setVerticalID(int verticalID) {
		this.verticalID = verticalID;
	}

	public void setHorizontalID(int horizontalID) {
		this.horizontalID = horizontalID;
	}

	public Boolean getIsPiece() {
		return isPiece;
	}

	public void setIsPiece(Boolean isPiece) {
		this.isPiece = isPiece;
	}

	public Color getOriginalColor() {
		return originalColor;
	}

	public void setOriginalColor(Color originalColor) {
		this.originalColor = originalColor;
	}

	public Figure getFigure() {
		return figure;
	}

	public void setFigure(Figure figure) {
		this.figure = figure;
	}

	public Icon getButtonIcon() {
		return buttonIcon;
	}

	public void setButtonIcon(Icon buttonIcon) {
		this.buttonIcon = buttonIcon;
	}


}
