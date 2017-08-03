package com.example.olderman.dungeon.map;

public class Map {

	public boolean[][] rooms = new boolean[6][6];

	public Room[][] mapRooms = new Room[6][6];

	int width = 5;
	int length = 5;
	public int leftEdge = 1;
	public int rightEdge = width;
	public int w1 = (width + 1) / 2; // starting w
	public int w = (width + 1) / 2; // find middle if width is odd
	// public int w = (width/2); // find middle if width is even
	public int l = 1;

	public void initializeRooms() {
		for (int i = 0; i < mapRooms.length; i++) {
			Room[] rooms = mapRooms[i];
			for (int j = 0; j < rooms.length; j++) {
				rooms[j] = new Room(null);
			}
		}
	}

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