package com.example.olderman.dungeon.map;

import com.example.olderman.dungeon.Resources;

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
	public String map = Resources.getString("/map");

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

	public String changeCharInPosition(int position, char ch, String str) {
		char[] charArray = str.toCharArray();
		charArray[position] = ch;
		return new String(charArray);
	}

	public void asciiArtMap() {
		int mapPosition = w * 14 + l * 2;
		map.replace('B', 'x');
		map = changeCharInPosition(mapPosition, 'B', map);
		System.out.println(map);
	}

}