package olderman.dungeon.map;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

import olderman.dungeon.Dungeon;
import olderman.dungeon.Resources;

public class Map1 implements Serializable {

	public Room[][] mapRooms;

	public int leftEdge;
	public int downEdge;
	public int rightEdge;
	public int w;
	public int w1;
	// public int w1 = (width + 1) / 2; // starting w
	public int l;
	public String map1 = "";

	private final Dungeon dungeon;

	public Map1(Dungeon dungeon) {
		this.dungeon = dungeon;
		initializeRooms();
	}

	private void initializeRooms() {
		Random r = new Random();
		int rWidth = r.nextInt(10) + 5;
		int rHight = r.nextInt(10) + 5;
		boolean isEven = rWidth % 2 == 0;
		if (isEven) {
			w = (rWidth) / 2;
			w1 = (rWidth) / 2;
		} else {
			w = (rWidth + 1) / 2; // find middle if width is odd
			w1 = (rWidth + 1) / 2;
		}
		l = 1;
		leftEdge = 1;
		rightEdge = rWidth;
		downEdge = rHight;
		mapRooms = new Room[rWidth][rHight];
		for (int i = 0; i < mapRooms.length; i++) {
			Room[] rooms = mapRooms[i];
			for (int j = 0; j < rooms.length; j++) {
				rooms[j] = new Room(dungeon, i, j);
			}
		}
	}

	public String changeCharInPosition(int position, char ch, String str) {
		char[] charArray = str.toCharArray();
		charArray[position] = ch;
		return new String(charArray);
	}

	int mapPosition;
	BufferedWriter bw = null;
	FileWriter fw = null;

	public void createAsciiArtMap() {
		String content1 = " ";
		String content2 = "\n";
		for (int i = 0; i < downEdge; i++) {
			for (int j = 0; j < rightEdge * 2 + 3; j++) {
				map1 += content1;
			}
			map1 += content2;
		}
	}

	public void asciiArtMap() {
		mapPosition = w * (rightEdge * 2 + 4) + l * 2;
		if (w == 1 && l == 1) {
			map1 = changeCharInPosition(0, 'x', map1);
		}
		if (w == 1 && l == downEdge) {
			map1 = changeCharInPosition(w * (rightEdge * 2 + 2), 'x', map1);
		}
		if (w == rightEdge && l == 1) {
			map1 = changeCharInPosition((w + 1) * (rightEdge * 2 + 4), 'x', map1);
		}
		if (w == rightEdge && l == downEdge) {
			map1 = changeCharInPosition((rightEdge * 2 + 4) * (w + 2), 'x', map1);
		}
		if (w == 1) {
			map1 = changeCharInPosition(l * 2, 'x', map1);
		}
		if (w == rightEdge) {
			map1 = changeCharInPosition((w + 1) * (rightEdge * 2 + 4) + l * 2, 'x', map1);
		}
		if (l == 1) {
			map1 = changeCharInPosition(w * (rightEdge * 2 + 4), 'x', map1);
		}
		if (l == downEdge) {
			map1 = changeCharInPosition((w + 1) * (rightEdge * 2 + 4) - 2, 'x', map1);
		}
		map1 = map1.replace('D', 'c');
		map1 = changeCharInPosition(mapPosition, 'D', map1);
	}

	public void mapBack() {
		mapPosition = w * (rightEdge * 2 + 4) + l * 2;
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
