package com.filipmorawski.chess;

public class RefreshPositions {

	// Refreshing figures positions and possible moves

	public void refresh() {

		Figures.figuresPositionMap.clear();
		Figures.figuresPositionMap = Figures.createMap();
		for (Figure figure : Figures.figures) {
			figure.setPossibleMoves();
		}
	}

}
