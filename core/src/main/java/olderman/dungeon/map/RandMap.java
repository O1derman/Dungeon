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
        int rMaxWidth = 12;
        int minWidth = 7;
        int rMaxHight = 12;
        int minHight = 7;
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
        int iLength = r.nextInt(rMaxHight - minHight + 1) + minHight - 1;
        int jLength = r.nextInt(rMaxWidth - minWidth + 1) + minWidth - 1;
        data.rightEdge = jLength;
        data.downEdge = iLength;
        for (int i = 0; i < iLength; i++) {
            barrierOnLine = 0;
            if (i % 2 == 0) {
                buildBarrier = false;
            } else {
                buildBarrier = true;
            }
            Room[] rooms = data.mapRooms[i];
            if (firstTimejLenght) {
                previousjLength = jLength;
                firstTimejLenght = false;
            }
            for (int j = 0; j < jLength; j++) {
                if (i != data.yourPositionx || j != data.yourPositiony) {
                    if (buildBarrier) {
                        if (barrierOnLine < jLength - 3) {
                            if (dungeon.getRand().nextInt(100) < 70) { //70% chance for stone
                                rooms[j] = new Room(i, j, dungeon);
                                Room room = data.mapRooms[i][j];
                                room.setStone(true);
                                barrierOnLine++;
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

                previousjLength = jLength;
            }
        }

    }

    public void drawAsciiArtMap() {
        data.map = new StringBuilder();
        for (int i = 0; i < data.downEdge; i++) {
            for (int j = 0; j < data.rightEdge; j++) {
                Room room = data.mapRooms[i][j];
                room.drawCorrectSymbol(room, dungeon, dungeon.getMapConstants());

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

        int nexRoomPositionX = data.yourPositionx + direction.getNextRoomPositionW();
        int nextRoomPositionY = data.yourPositiony + direction.getNextRoomPositionL();
        nextRoom = data.mapRooms[nexRoomPositionX][nextRoomPositionY];

        //border
        if (nextRoom == null) {
            return null;

        } else {
            return nextRoom;
        }

    }


    private void visitedRoom() {
        Room room = data.mapRooms[data.yourPositionx][data.yourPositiony];
        room.setVisitedRoom(true);
        room.setYourPosition(false);
    }

    public void goLeft() {
        visitedRoom();
        data.yourPositionx--;
        Room room = data.mapRooms[data.yourPositionx][data.yourPositiony];
        room.setYourPosition(true);
        drawAsciiArtMap();
    }

    public void goRight() {
        visitedRoom();
        data.yourPositionx++;
        Room room = data.mapRooms[data.yourPositionx][data.yourPositiony];
        room.setYourPosition(true);
        drawAsciiArtMap();
    }

    public void goStraight() {
        visitedRoom();
        data.yourPositiony++;
        Room room = data.mapRooms[data.yourPositionx][data.yourPositiony];
        room.setYourPosition(true);
        drawAsciiArtMap();
    }

    public void goBackwards() {
        visitedRoom();
        data.yourPositiony--;
        Room room = data.mapRooms[data.yourPositionx][data.yourPositiony];
        room.setYourPosition(true);
        drawAsciiArtMap();
    }

}
