package com.filipmorawski.chess;

import java.util.ArrayList;

//Setting all possible way to threat King

public class AttackingRoute {

	ArrayList<String> possibleAttackingRoute = new ArrayList<String>();
	King king;
	
	public AttackingRoute(King king) {
		this.king = king;
		addTowerMoves(this.king);
		addCourierMoves(this.king);
	}
	
	public void addTowerMoves(King king) {
		
		this.possibleAttackingRoute.clear();
		int[] calculateHorizontalPositions = new int[28];		
		int[] calculateVerticalPositions = new int[28]; 
		ArrayList<Integer> horizontalPositions = new ArrayList<Integer>(); 
		ArrayList<Integer> verticalPositions = new ArrayList<Integer>(); 
		
		
		int j = 0;
		for (int i = 0; i<7; i++) {
				calculateHorizontalPositions[j] = (king.horizontalID + (i+1));
				calculateVerticalPositions[j] = king.verticalID;
				j++;
		}
		for (int i = 0; i<7; i++) {
				calculateHorizontalPositions[j] =( king.horizontalID - (i+1) );
				calculateVerticalPositions[j] = king.verticalID;
				j++;
			}
		for (int i = 0; i<7; i++) {
				calculateHorizontalPositions[j] = king.horizontalID;
				calculateVerticalPositions[j] =( king.verticalID + (i+1) );
				j++;
		} 
		for (int i = 0; i<7; i++) {
				calculateHorizontalPositions[j] = king.horizontalID;
				calculateVerticalPositions[j] = (king.verticalID - (i + 1) ) ;
				j++;
		}
		j = 0;
		
		for(int i = 0; i< calculateHorizontalPositions.length; i++ ) {
			if (calculateHorizontalPositions[i] < 0 || calculateHorizontalPositions[i] > 7 ) {
				calculateHorizontalPositions[i] = 100;
				calculateVerticalPositions[i] = 100;
			} else if (calculateVerticalPositions[i] < 0 || calculateVerticalPositions[i] > 7 ) {
				calculateHorizontalPositions[i] = 100;
				calculateVerticalPositions[i] = 100;	
			}
		}
		for (int position : calculateHorizontalPositions) {
			if (position != 100) {
				horizontalPositions.add(position);
			}
		}
		for (int position : calculateVerticalPositions) {
			if (position != 100) {
				verticalPositions.add(position);
			}
		}
		
		for (int k = 0; k <horizontalPositions.size(); k++) {
			String move = Coordinates.horizontal[horizontalPositions.get(k)] + Coordinates.vertical[verticalPositions.get(k)]; 
			this.possibleAttackingRoute.add(move); 
		}
		
	}
	
	public void addCourierMoves(King king) {
		this.possibleAttackingRoute.clear();
		int[] calculateHorizontalPositions = new int[28];		
		int[] calculateVerticalPositions = new int[28]; 
		ArrayList<Integer> horizontalPositions = new ArrayList<Integer>(); 
		ArrayList<Integer> verticalPositions = new ArrayList<Integer>(); 
		
		
		int j = 0;
		for (int i = 0; i<7; i++) {
				calculateHorizontalPositions[j] = (king.horizontalID + (i+1));
				calculateVerticalPositions[j] = (king.verticalID + (i+1));
				j++;
		}
		for (int i = 0; i<7; i++) {
				calculateHorizontalPositions[j] =( king.horizontalID + (i+1) );
				calculateVerticalPositions[j] = (king.verticalID - (i+1));
				j++;
			}
		for (int i = 0; i<7; i++) {
				calculateHorizontalPositions[j] = (king.horizontalID - (i + 1));
				calculateVerticalPositions[j] =( king.verticalID + (i+1) );
				j++;
		} 
		for (int i = 0; i<7; i++) {
				calculateHorizontalPositions[j] = (king.horizontalID - (i + 1));
				calculateVerticalPositions[j] = (king.verticalID - (i + 1) ) ;
				j++;
		}
		j = 0;
		
		for(int i = 0; i< calculateHorizontalPositions.length; i++ ) {
			if (calculateHorizontalPositions[i] < 0 || calculateHorizontalPositions[i] > 7 ) {
				calculateHorizontalPositions[i] = 100;
				calculateVerticalPositions[i] = 100;
			} else if (calculateVerticalPositions[i] < 0 || calculateVerticalPositions[i] > 7 ) {
				calculateHorizontalPositions[i] = 100;
				calculateVerticalPositions[i] = 100;	
			}
		}
		for (int position : calculateHorizontalPositions) {
			if (position != 100) {
				horizontalPositions.add(position);
			}
		}
		for (int position : calculateVerticalPositions) {
			if (position != 100) {
				verticalPositions.add(position);
			}
		}
		
		for (int k = 0; k <horizontalPositions.size(); k++) {
			String move = Coordinates.horizontal[horizontalPositions.get(k)] + Coordinates.vertical[verticalPositions.get(k)]; 
			this.possibleAttackingRoute.add(move); 
		}
	}
	
	
	public ArrayList<String> getPossibleAttackingRoute() {
		return possibleAttackingRoute;
	}

	public void setPossibleAttackingRoute(ArrayList<String> possibleAttackingRoute) {
		this.possibleAttackingRoute = possibleAttackingRoute;
	}
	
}
