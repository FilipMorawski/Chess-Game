package com.filipmorawski.chess;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

public class CheckMate {
	
	public static boolean check = false;

	private ArrayList<Figure> whites = new ArrayList<Figure>();
	private ArrayList<Figure> blacks = new ArrayList<Figure>();
	
	private ArrayList<String> allWhitesPossibleMoves = new ArrayList<String>();
	private ArrayList<String> allBlacksPossibleMoves = new ArrayList<String>();
	
	private Figure whiteKing;
	private Figure blackKing;
	
	private Boolean whiteCheck = false;
	private Boolean blackCheck = false;
	
	
	public void check() {
		
		BlockPossibility block = new BlockPossibility();
		
// Finding Kings on Map
		for (Figure figure : Figures.figures) {
			if (figure.getColor() == 1) {
				whites.add(figure);
				allWhitesPossibleMoves.addAll(figure.getPossibleMoves());
				if (figure.getName().equals("King")) {
					whiteKing = figure;
					allWhitesPossibleMoves.removeAll(figure.getPossibleMoves());
				}
			}else {
				blacks.add(figure);
				allBlacksPossibleMoves.addAll(figure.getPossibleMoves());
				if (figure.getName().equals("King")) {
					blackKing = figure;
					allBlacksPossibleMoves.removeAll(figure.getPossibleMoves());
				}
			}
		}
		

		for (Figure black : blacks) {			
//Removing own figures positions from possible Movement of blackKing			
			if (blackKing.getPossibleMoves().contains(black.getPosition())) {
				blackKing.possibleMoves.remove(black.getPosition());
			}
			
			ArrayList<String> whPossibleMoves = new ArrayList<String>(whiteKing.getPossibleMoves());
			for (String wkMove : whPossibleMoves) {
				if (black.getPossibleMoves().contains(wkMove)) {
					whiteKing.possibleMoves.remove(wkMove);
				}
			}
		}
		
		//checking if black king is in white movement zone		
				for (Figure white : whites) {
				
					//Removing own figures positions from possible Movement of whiteKing			
					if (whiteKing.getPossibleMoves().contains(white.getPosition())) {
						whiteKing.possibleMoves.remove(white.getPosition());
					}
					
					ArrayList<String> bkPossibleMoves = new ArrayList<String>(blackKing.getPossibleMoves());
					for (String bkMove : bkPossibleMoves) {
						if (white.getPossibleMoves().contains(bkMove)) {
							blackKing.possibleMoves.remove(bkMove);
						}
					}
				}
		
// Checking check and checkMate
		for (Figure black : blacks){
			if (black.getPossibleMoves().contains(whiteKing.getPosition())) {
				if (whiteKing.getPossibleMoves().isEmpty()) {
					if (!allWhitesPossibleMoves.contains(black.getPosition())) {
						if (!block.check(black, whiteKing, allWhitesPossibleMoves)) {
							JOptionPane.showMessageDialog(null, "CheckMate! - Black player wins", "CheckMate", JOptionPane.WARNING_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Check! - White King is in danger", "Check", JOptionPane.WARNING_MESSAGE);
							whiteCheck = true;			
						}
					} else {
						JOptionPane.showMessageDialog(null, "Check! - White King is in danger", "Check", JOptionPane.WARNING_MESSAGE);
						whiteCheck = true;											
					}
				} else {
					JOptionPane.showMessageDialog(null, "Check! - White King is in danger", "Check", JOptionPane.WARNING_MESSAGE);
					whiteCheck = true;					
				}
			}
		}
		

// Checking check and checkMate
		for (Figure white : whites) {
			if (white.getPossibleMoves().contains(blackKing.getPosition())) {
				if (blackKing.getPossibleMoves().isEmpty()) {
					if (!allBlacksPossibleMoves.contains(white.getPosition())) {
						if (!block.check(white, blackKing, allBlacksPossibleMoves)) {
						JOptionPane.showMessageDialog(null, "CheckMate! - White player wins", "CheckMate", JOptionPane.WARNING_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Check! - Black King is in danger", "Check", JOptionPane.WARNING_MESSAGE);
							whiteCheck = true;			
						}	
					} else {
						JOptionPane.showMessageDialog(null, "Check! - Black King is in danger", "Check", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Check! - Black King is in danger", "Check", JOptionPane.WARNING_MESSAGE);
					blackCheck = true;
				}
			} 
/////////////////////////////////// TESTING PRINTS ///////////////////////////////////////
//			System.out.println("First condition " + white.getPossibleMoves().contains(blackKing.getPosition()));
//			System.out.println("Second Condition " + blackKing.getPossibleMoves().isEmpty());
//			System.out.println("Possible black King Moves : " + blackKing.possibleMoves);
//			System.out.println("Third condition " + !allBlacksPossibleMoves.contains(white.getPosition()));
//			System.out.println("Fourth condition " + !block.isPossibility());
//			System.out.println("");
//////////////////////////////////////////////////////////////////////////////////////////
		}
///////////////////////////////////////////////////////////////////////////////////////				
//		System.out.println("Possible black King Moves : " + blackKing.possibleMoves);
//		System.out.println(" Black is empty " + blackKing.getPossibleMoves().isEmpty());
//		System.out.println("Possible white King Moves : " + whiteKing.possibleMoves);
///////////////////////////////////////////////////////////////////////////////////////	
	}
}
