package com.filipmorawski.chess;

import java.util.ArrayList;

public class DefendingKing {

	private ArrayList<String> figuresDefendingKing;
	private boolean defendingFigures;

	// This class checks if there are figures on check routes, so it blocks
	// possibility of moving them
	public DefendingKing() {
		this.figuresDefendingKing = new ArrayList<String>();
		this.setDefendingFigures(false);
		findKingsOnMap();
	}

	private void findKingsOnMap() {
		ArrayList<Figure> whites = new ArrayList<Figure>();
		ArrayList<Figure> blacks = new ArrayList<Figure>();

		ArrayList<String> allWhitesPositions = new ArrayList<String>();
		ArrayList<String> allBlacksPositions = new ArrayList<String>();

		ArrayList<String> allWhitesPossibleMoves = new ArrayList<String>();
		ArrayList<String> allBlacksPossibleMoves = new ArrayList<String>();

		Figure whiteKing = null;
		Figure blackKing = null;

		for (Figure figure : Figures.figures) {
			if (figure.getColor() == 1) {
				whites.add(figure);
				allWhitesPositions.add(figure.getPosition());
				allWhitesPossibleMoves.addAll(figure.getPossibleMoves());
				if (figure.getName().equals("King")) {
					whiteKing = figure;
					allWhitesPositions.remove(figure.getPosition());
					allWhitesPossibleMoves.removeAll(figure.getPossibleMoves());
				}
			}
			if (figure.getColor() == 2) {
				blacks.add(figure);
				allBlacksPositions.add(figure.getPosition());
				allBlacksPossibleMoves.addAll(figure.getPossibleMoves());
				if (figure.getName().equals("King")) {
					blackKing = figure;
					allBlacksPositions.remove(figure.getPosition());
					allBlacksPossibleMoves.removeAll(figure.getPossibleMoves());
				}
			}
		}

		isDefendingFigures(whites, blacks, allWhitesPositions, allBlacksPositions, whiteKing, blackKing);
	}

	private void isDefendingFigures(ArrayList<Figure> whites, ArrayList<Figure> blacks,
			ArrayList<String> allWhitesPositions, ArrayList<String> allBlacksPositions, Figure whiteKing,
			Figure blackKing) {

		ArrayList<String> atackingRoute;

		for (Figure white : whites) {
			boolean atackingFigureIsQueenTowerCourier = (white.getName().equals("Queen")
					|| white.getName().equals("Tower") || white.getName().equals("Courier"));
			boolean kingIsInEnemyRange = (white.range.contains(blackKing.position));

			if (atackingFigureIsQueenTowerCourier) {
				ArrayList<String> obstaclesWhite = new ArrayList<String>();
				if (kingIsInEnemyRange) {
					atackingRoute = new BlockPossibility().blockCheck(white, blackKing);
					for (String field : atackingRoute) {
						if (Figures.figuresPositionMap.contains(field)) {
							obstaclesWhite.add(field);
						}
					}
					boolean thereIsOneObstacle = ((obstaclesWhite.size() == 1)
							&& (allBlacksPositions.contains(obstaclesWhite.get(0))));
					if (thereIsOneObstacle) {
						figuresDefendingKing.add(obstaclesWhite.get(0));
						setDefendingFigures(true);
					}
				}
			}
		}
		for (Figure black : blacks) {
			boolean atackingFigureIsQueenTowerCourier = (black.getName().equals("Queen")
					|| black.getName().equals("Tower") || black.getName().equals("Courier"));
			boolean kingIsInEnemyRange = (black.range.contains(whiteKing.position));

			if (atackingFigureIsQueenTowerCourier) {
				ArrayList<String> obstaclesBlack = new ArrayList<String>();
				if (kingIsInEnemyRange) {
					atackingRoute = new BlockPossibility().blockCheck(black, whiteKing);
					for (String field : atackingRoute) {
						if (Figures.figuresPositionMap.contains(field)) {
							obstaclesBlack.add(field);
						}
					}
					boolean thereIsOneObstacle = (obstaclesBlack.size() == 1
							&& allWhitesPositions.contains(obstaclesBlack.get(0)));

					if (thereIsOneObstacle) {
						figuresDefendingKing.add(obstaclesBlack.get(0));
						setDefendingFigures(true);
					}
				}
			}
		}
	}

	public ArrayList<String> getFiguresDefendingKing() {
		return figuresDefendingKing;
	}

	public boolean isDefendingFigures() {
		return defendingFigures;
	}

	private void setDefendingFigures(boolean defendingFigures) {
		this.defendingFigures = defendingFigures;
	}

}
