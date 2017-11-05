package olderman.dungeon.map;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

import olderman.dungeon.Dungeon;
import olderman.dungeon.Resources;

public class RandMap implements Serializable {
	public RandMapData data = new RandMapData();
	private static final long serialVersionUID = 1L;

	private final Dungeon dungeon;

	public RandMap(Dungeon dungeon) {
		this.dungeon = dungeon;
		initializeRooms();
	}

	private void initializeRooms() {
		Random r = new Random();
		int rWidth = r.nextInt(10) + 5;
		int rHight = r.nextInt(10) + 5;
		boolean isEven = rWidth % 2 == 0;
		if (isEven) {
			data.w = (rWidth) / 2;
			data.w1 = (rWidth) / 2;
		} else {
			data.w = (rWidth + 1) / 2; // find middle if width is odd
			data.w1 = (rWidth + 1) / 2;
		}
		data.l = 1;
		data.leftEdge = 1;
		data.rightEdge = rWidth;
		data.downEdge = rHight;
		data.mapRooms = new Room[rWidth][rHight];
		for (int i = 0; i < data.mapRooms.length; i++) {
			Room[] rooms = data.mapRooms[i];
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
		for (int i = 0; i < data.downEdge; i++) {
			for (int j = 0; j < data.rightEdge * 2 + 3; j++) {
				data.map += content1;
			}
			data.map += content2;
		}
	}

	public void asciiArtMap() {
		mapPosition = data.w * (data.rightEdge * 2 + 4) + data.l * 2;
		if (data.w == 1 && data.l == 1) {
			data.map = changeCharInPosition(0, 'x', data.map);
		}
		if (data.w == 1 && data.l == data.downEdge) {
			data.map = changeCharInPosition(data.w * (data.rightEdge * 2 + 2), 'x', data.map);
		}
		if (data.w == data.rightEdge && data.l == 1) {
			data.map = changeCharInPosition((data.w + 1) * (data.rightEdge * 2 + 4), 'x', data.map);
		}
		if (data.w == data.rightEdge && data.l == data.downEdge) {
			data.map = changeCharInPosition((data.rightEdge * 2 + 4) * (data.w + 2), 'x', data.map);
		}
		if (data.w == 1) {
			data.map = changeCharInPosition(data.l * 2, 'x', data.map);
		}
		if (data.w == data.rightEdge) {
			data.map = changeCharInPosition((data.w + 1) * (data.rightEdge * 2 + 4) + data.l * 2, 'x', data.map);
		}
		if (data.l == 1) {
			data.map = changeCharInPosition(data.w * (data.rightEdge * 2 + 4), 'x', data.map);
		}
		if (data.l == data.downEdge) {
			data.map = changeCharInPosition((data.w + 1) * (data.rightEdge * 2 + 4) - 2, 'x', data.map);
		}
		data.map = data.map.replace('D', 'c');
		data.map = changeCharInPosition(mapPosition, 'D', data.map);
	}

	public void mapBack() {
		mapPosition = data.w * (data.rightEdge * 2 + 4) + data.l * 2;
		if (data.l == data.rightEdge && data.w == data.w1) {
			data.map = changeCharInPosition(mapPosition, 'A', data.map);
		} else {
			data.map = changeCharInPosition(mapPosition, 'o', data.map);
		}
	}

	public void goLeft() {
		data.w--;
		asciiArtMap();
	}

	public void goRight() {
		data.w++;
		asciiArtMap();
	}

	public void goStraight() {
		data.l++;
		asciiArtMap();
	}

	public void goBackwards() {
		data.l--;
		asciiArtMap();
	}

}
