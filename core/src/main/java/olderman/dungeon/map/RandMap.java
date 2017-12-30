package olderman.dungeon.map;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
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

	public void initializeRooms() {
		Random r = new Random();
		int rMaxWidth = 15;
		int minWidth = 5;
		int rMaxHight = 15;
		int minHight = 5;
		int barrierOnLine = 0;
		boolean buildBarrier;
		boolean firstTime = true;
		// boolean isEven = rMaxWidth % 2 == 0;
		// if (isEven) {
		// data.w = (rMaxWidth) / 2;
		// data.w1 = (rMaxWidth) / 2;
		// } else {
		// data.w = (rMaxWidth + 1) / 2; // find middle if width is odd
		// data.w1 = (rMaxWidth + 1) / 2;
		// }
		data.leftEdge = 1;
		data.rightEdge = rMaxWidth;
		data.downEdge = rMaxHight;
		data.mapRooms = new Room[rMaxWidth + 2][rMaxHight + 2];
		int iLength = r.nextInt(data.mapRooms.length - minHight) + minHight;
		for (int i = 0; i < iLength; i++) {
			barrierOnLine = 0;
			if (i == 0 || i == data.mapRooms.length - 1) {
				for (int j = 0; j < data.mapRooms.length; j++) {
				}
			} else {
				if (i % 2 == 0) {
					buildBarrier = false;
				} else {
					buildBarrier = true;
				}
				Room[] rooms = data.mapRooms[i];
				int jLength = r.nextInt(rooms.length - minWidth) + minWidth;
				for (int j = 0; j < jLength; j++) {
					if (j == 0 || j == rooms.length - 1) {
					} else {
						if (i != data.w || j != data.l) {
							if (buildBarrier) {
								if (barrierOnLine != jLength - 1) {
									if (dungeon.getRand().nextInt(100) > 20) {
										barrierOnLine++;
									} else {
										rooms[j] = new Room(i, j, dungeon);
									}
								} else {
									rooms[j] = new Room(i, j, dungeon);
								}
							} else {
								if (firstTime) {
									int minWidthCalculator = minHight;
									int possibilities = 0;
									for (int k = 0; k < minHight; k++) {
										boolean isEven = minWidthCalculator % 2 == 0;
										if (isEven) {
											possibilities++;
										}
										minWidthCalculator--;
									}
									int possibility = r.nextInt(possibilities - 1) + 1;
									dungeon.bossX = possibility * 2;
									dungeon.bossY = r.nextInt(minWidth - 1) + 1;
									firstTime = false;
								}
								rooms[j] = new Room(i, j, dungeon);

							}
						} else {
							rooms[j] = new Room(i, j, dungeon);

						}
					}
					jLength = r.nextInt(rooms.length - minWidth) + minWidth;
				}
			}
			iLength = r.nextInt(data.mapRooms.length - minHight) + minHight;
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
		int wayNorth = data.w - 1;
		int waySouth = data.w + 1;
		int wayEast = data.l + 1;
		int wayWest = data.l - 1;
		Room northRoom = data.mapRooms[wayNorth][dungeon.getWay().randMap.data.l];
		Room southRoom = data.mapRooms[waySouth][dungeon.getWay().randMap.data.l];
		Room eastRoom = data.mapRooms[dungeon.getWay().randMap.data.w][wayEast];
		Room westRoom = data.mapRooms[dungeon.getWay().randMap.data.w][wayWest];

		if (eastRoom == null) {
			mapPosition = data.w * (data.rightEdge * 2 + 4) + wayEast * 2;
			data.map = changeCharInPosition(mapPosition, 'x', data.map);
		}
		if (northRoom == null) {
			mapPosition = wayNorth * (data.rightEdge * 2 + 4) + data.l * 2;
			data.map = changeCharInPosition(mapPosition, 'x', data.map);

		}
		if (southRoom == null) {
			mapPosition = waySouth * (data.rightEdge * 2 + 4) + data.l * 2;
			data.map = changeCharInPosition(mapPosition, 'x', data.map);

		}
		if (westRoom == null) {
			mapPosition = data.w * (data.rightEdge * 2 + 4) + wayWest * 2;
			data.map = changeCharInPosition(mapPosition, 'x', data.map);

		}
		mapPosition = data.w * (data.rightEdge * 2 + 4) + data.l * 2;
		if (dungeon.getWay().previousL == dungeon.bossY && dungeon.getWay().previousW == dungeon.bossX) {
			data.map = data.map.replace('D', 'A');
		} else {
			data.map = data.map.replace('D', 'c');
		}
		data.map = changeCharInPosition(mapPosition, 'D', data.map);
	}

	public void mapBack() {
		mapPosition = data.w * (data.rightEdge * 2 + 4) + data.l * 2;
		if (data.l == dungeon.bossY && data.w == dungeon.bossX) {
			data.map = changeCharInPosition(mapPosition, 'A', data.map);
		} else {
			data.map = changeCharInPosition(mapPosition, 'o', data.map);
		}
	}

	/**
	 * 
	 * @param direction
	 * @return Return next room in a given direction to the room character is in
	 */
	public Room getNextRoom(DirectionEnum direction) {
		Room nextRoom = null;
		int mapLength = data.mapRooms.length;
		int mapWidth = data.mapRooms[0].length;

		int nexRoomPosilionW = data.w + direction.getNextRoomPositionW();
		int nextRoomPositionL = data.l + direction.getNextRoomPositionL();

		// if room would be out of map dimensions return null
		if (nextRoomPositionL >= mapLength || nexRoomPosilionW >= mapWidth) {
			nextRoom = null;
		} else {
			nextRoom = data.mapRooms[nexRoomPosilionW][nextRoomPositionL];
		}

		return nextRoom;
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
