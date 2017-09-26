package com.filipmorawski.chess;

import java.io.Serializable;
import java.util.ArrayList;

// Map of all figures on board and theirs positions

public class Figures implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7863281987432889514L;

	public static ArrayList<Figure> figures = new ArrayList<Figure>();

	public static ArrayList<String> figuresPositionMap = new ArrayList<String>();

	public static ArrayList<String> createMap() {
		ArrayList<String> figuresPositionMap = new ArrayList<String>();
		for (Figure figure : figures) {
			String position = figure.getPosition();
			figuresPositionMap.add(position);
		}
		return figuresPositionMap;
	}
}
