package olderman.dungeon.map;

import java.io.Serializable;
import java.util.Random;

import olderman.dungeon.Dungeon;

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
        int rMaxWidth = 10;
        int minWidth = 5;
        int rMaxHight = 10;
        int minHight = 5;
        int barrierOnLine;
        data.mapRooms = new Room[rMaxWidth][rMaxHight];
        if (data.yourPositionx > minHight && data.yourPositionx > data.yourPositiony) {
            minHight = data.yourPositionx;
            minWidth = data.yourPositiony;
        }
        if (data.yourPositiony > minWidth && data.yourPositiony > data.yourPositionx) {
            minHight = data.yourPositiony;
            minWidth = data.yourPositionx;
        }

        boolean buildBarrier;
        boolean firstTime = true;
        boolean firstTimejLenght = true;
        int previousjLength = 0;
        int iLength = r.nextInt(data.mapRooms.length - minHight - 1) + minHight + 1;
        data.rightEdge = rMaxWidth;
        data.downEdge = iLength;
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
                int jLength = r.nextInt(rooms.length - minWidth - 1) + minWidth + 1;
                if (firstTimejLenght) {
                    previousjLength = jLength;
                    firstTimejLenght = false;
                }
                for (int j = 0; j < jLength; j++) {
                    if (j == 0 || j == rooms.length - 1) {
                    } else {
                        if (i != data.yourPositionx || j != data.yourPositiony) {
                            if (buildBarrier) {
                                if (barrierOnLine != previousjLength - 2) {
                                    if (dungeon.getRand().nextInt(100) > 20) {
                                        rooms[j] = new Room(i, j, dungeon);
                                        Room room = data.mapRooms[i][j];
                                        room.setStone(true);
                                    } else {
                                        rooms[j] = new Room(i, j, dungeon);
                                    }
                                } else {
                                    rooms[j] = new Room(i, j, dungeon);
                                }
                            } else {
                                if (firstTime) {
                                    int iLengthCalculator = iLength;
                                    int possibilities = 0;
                                    for (int k = 0; k < iLength; k++) {
                                        boolean isEven = iLengthCalculator % 2 == 0;
                                        if (isEven) {
                                            possibilities++;
                                        }
                                        iLengthCalculator--;
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
                    previousjLength = jLength;
                }
            }
        }

    }

    public void drawAsciiArtMap() {
        data.map = "";
        for (int i = 0; i <= data.downEdge; i++) {
            for (int j = 0; j < data.rightEdge; j++) {
                Room room = data.mapRooms[i][j];
                room.drawCorrectSymbol();
            }
        }
    }

    public void mapBack() {
        Room room = data.mapRooms[data.yourPositionx][data.yourPositiony];
        room.setVisitedRoom(true);
    }

    /**
     * @param direction
     * @return Return next room in a given direction to the room character is in
     */
    public Room getNextRoom(DirectionEnum direction) {
        Room nextRoom;
        int mapLength = data.mapRooms.length;
        int mapWidth = data.mapRooms[0].length;

        int nexRoomPositionX = data.yourPositionx + direction.getNextRoomPositionW();
        int nextRoomPositionY = data.yourPositiony + direction.getNextRoomPositionL();
        Room room = data.mapRooms[nexRoomPositionX][nextRoomPositionY];

        //border
        if (nextRoomPositionY == mapLength) {
            nextRoom = null;
            room.setRightSideRoom(true);
        } else if (nexRoomPositionX == mapWidth || nexRoomPositionX == 0 || nextRoomPositionY == 0) {
            nextRoom = null;
            room.setBorder(true);
        } else {
            nextRoom = data.mapRooms[nexRoomPositionX][nextRoomPositionY];
        }

        return nextRoom;
    }

    private void visitedRoom() {
        Room room = data.mapRooms[data.yourPositionx][data.yourPositiony];
        room.setVisitedRoom(true);
        room.setYourPosition(true);
    }

    public void goLeft() {
        data.yourPositiony--;
        visitedRoom();
        drawAsciiArtMap();
    }

    public void goRight() {
        data.yourPositiony++;
        visitedRoom();
        drawAsciiArtMap();
    }

    public void goStraight() {
        data.yourPositionx++;
        visitedRoom();
        drawAsciiArtMap();
    }

    public void goBackwards() {
        data.yourPositionx--;
        visitedRoom();
        drawAsciiArtMap();
    }

}
