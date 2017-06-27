package com.filipmorawski.chess;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

// Handles all information about chess fields and creating starting setup of Pieces

public class FieldButton extends JButton {

	String buttonTitle;
	int verticalID;
	int horizontalID;
	Icon icon;
	Boolean isPiece = false;
	Color originalColor;
	Figure figure = null;
	
	
//Constructors
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
		this.setName(Coordinates.horizontal[horizontalID] + Coordinates.vertical[verticalID]);
		
///////////////////  Show Coordinates on Fields //////////////////
//		this.setText(Coordinates.horizontal[horizontalID] + Coordinates.vertical[verticalID]);
//////////////////////////////////////////////////////////////////		
		
		buttonTitle = this.getName();
		setIcons();
		this.addActionListener(new ButtonListener());
	}

// Methods
	private void setIcons() {
	//Kings	
		if (this.getName().contains("e")) {
			if (this.getName().contains("8")) {
				this.figure = new King("King", 2, this.getName(), this.getVerticalID(), this.getHorizontalID());
				Figures.figures.add(this.figure);
				this.icon = figure.getIcon();
				this.setIsPiece(true);
				this.setIcon(icon);
			} else if (this.getName().contains("1")){
				this.figure = new King("King", 1, this.getName(), this.getVerticalID(), this.getHorizontalID());
				Figures.figures.add(this.figure);
				this.icon = figure.getIcon();
				this.setIsPiece(true);
				this.setIcon(icon);
			}
		}	
	//Queens	
		if (this.getName().contains("d")) {
			if (this.getName().contains("8")) {
				this.figure = new Queen("Queen", 2, this.getName(), this.getVerticalID(), this.getHorizontalID());
				Figures.figures.add(this.figure);
				this.icon = figure.getIcon();
				this.setIsPiece(true);
				this.setIcon(icon);
			} else if (this.getName().contains("1")){
				this.figure = new Queen("Queen", 1, this.getName(), this.getVerticalID(), this.getHorizontalID());
				Figures.figures.add(this.figure);
				this.icon = figure.getIcon();
				this.setIsPiece(true);
				this.setIcon(icon);
			}
		}
	//Couriers	
		if (this.getName().contains("f") || this.getName().contains("c")) {
			if (this.getName().contains("8")) {
				this.figure = new Courier("Courier", 2, this.getName(), this.getVerticalID(), this.getHorizontalID());
				Figures.figures.add(this.figure);
				this.icon = figure.getIcon();
				this.setIsPiece(true);
				this.setIcon(icon);
			} else if (this.getName().contains("1")){
				this.figure = new Courier("Courier", 1, this.getName(), this.getVerticalID(), this.getHorizontalID());
				Figures.figures.add(this.figure);
				this.icon = figure.getIcon();
				this.setIsPiece(true);
				this.setIcon(icon);
			}

		}
	//Jumpers	
		if (this.getName().contains("g") || this.getName().contains("b")) {
			if (this.getName().contains("8")) {
				this.figure = new Jumper("Jumper", 2, this.getName(), this.getVerticalID(), this.getHorizontalID());
				Figures.figures.add(this.figure);
				this.icon = figure.getIcon();
				this.setIsPiece(true);
				this.setIcon(icon);
			} else if (this.getName().contains("1")){
				this.figure = new Jumper("Jumper",1, this.getName(), this.getVerticalID(), this.getHorizontalID());
				Figures.figures.add(this.figure);
				this.icon = figure.getIcon();
				this.setIsPiece(true);
				this.setIcon(icon);
			}
		}
		
	//towers	
		if (this.getName().contains("h") || this.getName().contains("a")) {
			if (this.getName().contains("8")) {
				this.figure = new Tower("Tower", 2, this.getName(), this.getVerticalID(), this.getHorizontalID());
				Figures.figures.add(this.figure);
				this.icon = figure.getIcon();
				this.setIsPiece(true);
				this.setIcon(icon);
			} else if (this.getName().contains("1")){
				this.figure = new Tower("Tower", 1, this.getName(), this.getVerticalID(), this.getHorizontalID());
				Figures.figures.add(this.figure);
				this.icon = figure.getIcon();
				this.setIsPiece(true);
				this.setIcon(icon);
				}
		}
	//pawns	
		if (this.getName().contains("2") || this.getName().contains("7") ) {
			if (this.getName().contains("7")) {
				this.figure = new Pawn("Pawn", 2, this.getName(), this.getVerticalID(), this.getHorizontalID());
				Figures.figures.add(this.figure);
				this.icon = figure.getIcon();
				this.setIsPiece(true);
				this.setIcon(icon);
			} else {
				this.figure = new Pawn("Pawn", 1, this.getName(), this.getVerticalID(), this.getHorizontalID());
				Figures.figures.add(this.figure);
				this.icon = figure.getIcon();
				this.setIsPiece(true);
				this.setIcon(icon);
			}
		}
	}

//Getters and Setters


	public String getButtonTitle() {
		return buttonTitle;
	}

	public int getVerticalID() {
		return verticalID;
	}

	public int getHorizontalID() {
		return horizontalID;
	}

	public void setButtonTitle(String buttonTitle) {
		this.buttonTitle = buttonTitle;
	}

	public void setVerticalID(int verticalID) {
		this.verticalID = verticalID;
	}

	public void setHorizontalID(int horizontalID) {
		this.horizontalID = horizontalID;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
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


}
