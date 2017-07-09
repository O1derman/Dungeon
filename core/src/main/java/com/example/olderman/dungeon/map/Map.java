package com.example.olderman.dungeon.map;

public class Map {
	int width = 5;
	int length = 5;
	public int leftEdge = 1;
	public int rightEdge = width;
	public int w = (width + 1) / 2; // if width is odd
	// public int w = (width/2); //if width is even
	public int l = 1;

	public void goLeft() {
		w--;
	}

	public void goRight() {
		w++;
	}

	public void goStraight() {
		l++;
	}

	public void goBackwards() {
		l--;
	}

}