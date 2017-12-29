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
		int rMaxWidth = r.nextInt(10) + 5;
		int rMaxHight = r.nextInt(10) + 5;
		int barrierOnLine = 0;
		boolean buildBarrier;
		// boolean isEven = rMaxWidth % 2 == 0;
		data.w = 1;
		data.w1 = 1;
		// if (isEven) {
		// data.w = (rMaxWidth) / 2;
		// data.w1 = (rMaxWidth) / 2;
		// } else {
		// data.w = (rMaxWidth + 1) / 2; // find middle if width is odd
		// data.w1 = (rMaxWidth + 1) / 2;
		// }
		data.l = 1;
		data.leftEdge = 1;
		data.rightEdge = rMaxWidth;
		data.downEdge = rMaxHight;
		data.mapRooms = new Room[rMaxWidth + 2][rMaxHight + 2];
		int iLength = r.nextInt(data.mapRooms.length - 1) + 1;
		for (int i = 0; i < iLength; i++) {
			barrierOnLine = 0;
			if (i == 0 ^ i == data.mapRooms.length - 1) {
				for (int j = 0; j < data.mapRooms.length; j++) {
				}
			} else {
				if (i % 2 == 0) {
					buildBarrier = false;
				} else {
					buildBarrier = true;
				}
				Room[] rooms = data.mapRooms[i];
				int jLength = r.nextInt(rooms.length - 1) + 1;
				for (int j = 0; j < jLength; j++) {
					if (j == 0 ^ j == rooms.length - 1) {
					} else {
						if (buildBarrier && (i != 1 ^ j != 1)) {
							if (barrierOnLine != iLength - 1) {
								if (dungeon.getRand().nextInt(100) > 20) {
									barrierOnLine++;
								} else {
									rooms[j] = new Room(i, j, dungeon);
								}
							} else {
								rooms[j] = new Room(i, j, dungeon);
							}
						} else {
							rooms[j] = new Room(i, j, dungeon);

						}
					}
					jLength = r.nextInt(rooms.length - 1) + 1;
				}
			}
			iLength = r.nextInt(data.mapRooms.length - 1) + 1;
		}

	}

	public String changeCharInPosition(int position, char ch, String str) {
		char[] charArray = str.toCharArray();
		charArray[position] = ch;
		return new String(charArray);
	}

	public int previousPosition;
	public int mapPosition;
	BufferedWriter bw = null;
	FileWriter fw = null;

	public void createAsciiArtMap() {
		String content1 = " ";
		String content2 = "\n";
		for (int i = 0; i <= data.downEdge + 1; i++) {
			for (int j = 0; j < data.rightEdge * 2 + 3; j++) {
				data.map += content1;
			}
			data.map += content2;
		}
	}

	public void mapBarrier() {
		mapPosition = data.w * (data.rightEdge * 2 + 4) + data.l * 2;
		data.map = changeCharInPosition(mapPosition, 'x', data.map);
	}

	public void asciiArtMap() {
		mapPosition = data.w * (data.rightEdge * 2 + 4) + data.l * 2;
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
