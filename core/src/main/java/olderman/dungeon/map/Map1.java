package olderman.dungeon.map;

import java.io.Serializable;

import olderman.dungeon.Resources;

public class Map1 implements Serializable {

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
	public String map1 = Resources.getString("/Map1");

	public void initializeRooms() {
		for (int i = 0; i < mapRooms.length; i++) {
			Room[] rooms = mapRooms[i];
			for (int j = 0; j < rooms.length; j++) {
				rooms[j] = new Room(null);
			}
		}
	}

	public void setStartingPosition() {
		l = 1;
		w = (width + 1) / 2;
	}

	public String changeCharInPosition(int position, char ch, String str) {
		char[] charArray = str.toCharArray();
		charArray[position] = ch;
		return new String(charArray);
	}

	int mapPosition;

	public void asciiArtMap() {
		mapPosition = w * 14 + l * 2;
		if (w == 1 && l == 1) {
			map1 = changeCharInPosition(0, 'x', map1);
		}
		if (w == 1 && l == 5) {
			map1 = changeCharInPosition(w * 12, 'x', map1);
		}
		if (w == 5 && l == 1) {
			map1 = changeCharInPosition((w + 1) * 14, 'x', map1);
		}
		if (w == 5 && l == 5) {
			map1 = changeCharInPosition(14 * (w + 2), 'x', map1);
		}
		if (w == 1) {
			map1 = changeCharInPosition(l * 2, 'x', map1);
		}
		if (w == 5) {
			map1 = changeCharInPosition((w + 1) * 14 + l * 2, 'x', map1);
		}
		if (l == 1) {
			map1 = changeCharInPosition(w * 14, 'x', map1);
		}
		if (l == 5) {
			map1 = changeCharInPosition((w + 1) * 14 - 2, 'x', map1);
		}
		map1 = map1.replace('D', 'c');
		map1 = changeCharInPosition(mapPosition, 'D', map1);
	}

	public void mapBack() {
		mapPosition = w * 14 + l * 2;
		if (l == rightEdge && w == w1) {
			map1 = changeCharInPosition(mapPosition, 'A', map1);
		} else {
			map1 = changeCharInPosition(mapPosition, 'o', map1);
		}
	}

	public void goLeft() {
		w--;
		asciiArtMap();
	}

	public void goRight() {
		w++;
		asciiArtMap();
	}

	public void goStraight() {
		l++;
		asciiArtMap();
	}

	public void goBackwards() {
		l--;
		asciiArtMap();
	}

}
