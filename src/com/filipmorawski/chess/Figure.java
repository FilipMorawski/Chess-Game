package com.filipmorawski.chess;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.Icon;

// Class handles moving of figures and all Figures stuff

public abstract class Figure implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int color; // 1 - white , 2-black
	protected int verticalID;
	protected int horizontalID;
	protected int moves = 0;
	protected int lastMoveCount;
	protected String lastPosition;
	protected String position;
	protected String name;
	protected Icon icon;
	protected ArrayList<String> possibleMoves = new ArrayList<String>();
	protected ArrayList<String> range = new ArrayList<String>();

	protected final int maxRange = 28;
	protected final int maxRangeInOneDirection = 7;
	protected final int outOfBoundFlag = 100;

	abstract void setPossibleMoves();

	public void updateState(String position, int verticalID, int horizontalID) {
		this.position = position;
		this.verticalID = verticalID;
		this.horizontalID = horizontalID;
	}

	public int getColor() {
		return color;
	}

	public int getVerticalID() {
		return verticalID;
	}

	public int getHorizontalID() {
		return horizontalID;
	}

	public String getPosition() {
		return position;
	}

	public Icon getIcon() {
		return icon;
	}

	public String getName() {
		return name;
	}

	public ArrayList<String> getPossibleMoves() {
		return possibleMoves;
	}

	public String getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(String lastPosition) {
		this.lastPosition = lastPosition;
	}

	public int getMoves() {
		return moves;
	}

	public int getLastMoveCount() {
		return lastMoveCount;
	}

	public void setLastMoveCount(int lastMoveCount) {
		this.lastMoveCount = lastMoveCount;
	}

	public ArrayList<String> getRange() {
		return range;
	}
}
