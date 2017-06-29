package com.filipmorawski.chess;

import java.util.ArrayList;

public class DefendingKing {
	
	private ArrayList<String> figuresDefendingKing = new ArrayList<String>();
	boolean defendingFigures = false;
	
	public ArrayList<String> isDefendingFigures() {
		
		figuresDefendingKing.clear();
		ArrayList<Figure> whites = new ArrayList<Figure>();
		ArrayList<Figure> blacks = new ArrayList<Figure>();
		
		ArrayList<String> allWhitesPositions = new ArrayList<String>();
		ArrayList<String> allBlacksPositions = new ArrayList<String>();
		
		ArrayList<String> allWhitesPossibleMoves = new ArrayList<String>();
		ArrayList<String> allBlacksPossibleMoves = new ArrayList<String>();
		
		Figure whiteKing = null;
		Figure blackKing = null;
		
		ArrayList<String> atackingRoute = new ArrayList<String>();
		
				for (Figure figure : Figures.figures) {
					if (figure.getColor() == 1) {
						whites.add(figure);
						allWhitesPositions.add(figure.getPosition());
						allWhitesPossibleMoves.addAll(figure.getPossibleMoves());
						if (figure.getName().equals("King")) {
							whiteKing = (King) figure;
							allWhitesPositions.remove(figure.getPosition());
							allWhitesPossibleMoves.removeAll(figure.getPossibleMoves());
						}
					}else {
						blacks.add(figure);
						allBlacksPositions.add(figure.getPosition());
						allBlacksPossibleMoves.addAll(figure.getPossibleMoves());
						if (figure.getName().equals("King")) {
							blackKing = (King) figure;
							allBlacksPositions.remove(figure.getPosition());
							allBlacksPossibleMoves.removeAll(figure.getPossibleMoves());
						}
					}
				}		
		
				for (Figure white : whites) {
					if(white.getName().equals("Queen") || white.getName().equals("Tower") || white.getName().equals("Courier")) {
						if (white.range.contains(blackKing.position)) {
							ArrayList<String> obstacles = new ArrayList<String>();
							atackingRoute = new BlockPossibility().blockCheck(white, blackKing);
							for (String field : atackingRoute) {
								if (Figures.figuresPositionMap.contains(field)) {
									obstacles.add(field);
								}
							}
							if (obstacles.size() == 1 && allBlacksPositions.contains(obstacles.get(0))) {
								figuresDefendingKing.add(obstacles.get(0));
							}
						}
					}	
				}
				for (Figure black : blacks) {
					if(black.getName().equals("Queen") || black.getName().equals("Tower") || black.getName().equals("Courier")) {
						if (black.range.contains(whiteKing.position)) {
							ArrayList<String> obstacles = new ArrayList<String>();
							atackingRoute = new BlockPossibility().blockCheck(black, whiteKing);
							for (String field : atackingRoute) {
								if (Figures.figuresPositionMap.contains(field)) {
									obstacles.add(field);
								}
							}
							if (obstacles.size() == 1 && allWhitesPositions.contains(obstacles.get(0))) {
								figuresDefendingKing.add(obstacles.get(0));
							}
						}
					}	
				}
				
				return figuresDefendingKing;
				
	}// End of isDefendingFigures
		
	
}
