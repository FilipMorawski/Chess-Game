package com.filipmorawski.chess;

import java.util.ArrayList;

abstract class AdjustPossibleMoves {
	protected int color;
	protected ArrayList<String> possibleMoves;

	abstract void adjustMoves(String name, String position);

}
