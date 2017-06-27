package com.filipmorawski.chess;

// This class defining variables that are informing about which player has to move

public class WhoseTurn {	
	
	public static Boolean whiteTurn = true;
	public static Boolean blackTurn = false;
	
	public boolean whoseTurn(int color) {
		if (color == 1) {
			return true;
		} else {
			return false;
		}
	}
}
