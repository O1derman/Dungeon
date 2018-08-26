package olderman.dungeon.map;

import java.io.Serializable;

import olderman.dungeon.Dungeon;
import olderman.dungeon.Style;
import olderman.dungeon.enemies.Plebs;

public class Room implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean freeRoom = false;

    private int x;
    private int y;

    private Plebs plebs;
    private boolean searchedRoom;
    private boolean visitedRoom;
    private boolean stone;
    private boolean yourPosition;
    private boolean rightSideBorder;

    private boolean bossRoom;
    private boolean roomWasNextToYou;
    private boolean border;
    private String str;

    public Room(int x, int y, Dungeon dungeon) {
        this.x = x;
        this.y = y;
        searchedRoom = false;
        visitedRoom = false;
        stone = false;
        bossRoom = false;
        yourPosition = false;
        rightSideBorder = false;
        roomWasNextToYou = false;
        border = false;
        plebs = new Plebs(dungeon);
        if (y == dungeon.getWay().randMap.data.mapRooms.length - 1) {
            rightSideBorder = true;
        } else if (x == dungeon.getWay().randMap.data.mapRooms[0].length - 1 || x == 0 || y == 0) {
            border = true;
        }
    }

    public Plebs getPlebs() {
        return plebs;
    }

    public void normalRoom(Dungeon dungeon) {
        int chestGoldFound = dungeon.getRand().nextInt(600) + 550;
        boolean emptyRoom = true;
        if (dungeon.getRand().nextInt(100) < dungeon.getForAll().chestChance) {
            dungeon.println(Style.CENTER, ">>>You found a Chest!<<<");
            dungeon.println(Style.CENTER, "You earned " + chestGoldFound + " Gold!");
            dungeon.getForAll().gold += chestGoldFound;
            emptyRoom = false;
        }
        if (dungeon.getRand().nextInt(100) < dungeon.getForAll().snakeChance) {
            if (dungeon.getForAll().snakeHeft + dungeon.getForAll().lCapacity > dungeon.getCharacter()
                    .getLoadCapacity()) {
                dungeon.println(Style.CENTER, "You don't have enough load capacity!");
            } else {
                dungeon.getForAll().snake++;
                dungeon.getForAll().lCapacity += dungeon.getForAll().snakeHeft;
                dungeon.println(Style.CENTER, "You found a Snake.");
            }
            emptyRoom = false;

        }
        if (dungeon.getRand().nextInt(100) < dungeon.getForAll().frogChance) {
            if (dungeon.getForAll().frogHeft + dungeon.getForAll().lCapacity > dungeon.getCharacter()
                    .getLoadCapacity()) {
                dungeon.println(Style.CENTER, "You don't have enough load capacity!");
            } else {
                dungeon.getForAll().frog++;
                dungeon.getForAll().lCapacity += dungeon.getForAll().frogHeft;
                dungeon.println(Style.CENTER, "You found a Frog.");
            }
            emptyRoom = false;

        }
        if (dungeon.getRand().nextInt(100) < dungeon.getForAll().wormChance) {
            if (dungeon.getForAll().wormHeft + dungeon.getForAll().lCapacity > dungeon.getCharacter()
                    .getLoadCapacity()) {
                dungeon.println(Style.CENTER, "You don't have enough load capacity!");
            } else {
                dungeon.getForAll().worm++;
                dungeon.getForAll().lCapacity += dungeon.getForAll().wormHeft;
                dungeon.println(Style.CENTER, "You found a Worm.");
            }
            emptyRoom = false;
        }
        if (emptyRoom) {
            dungeon.println(Style.CENTER, "This is just an empty room.");
        }
    }

    public void drawCorrectSymbol(Room room, Dungeon dungeon) {
        if ((room.getX() == (dungeon.getWay().randMap.data.yourPositionx - 1) && room.getY() == dungeon.getWay().randMap.data.yourPositiony) || (room.getX() == dungeon.getWay().randMap.data.yourPositionx && room.getY() == (dungeon.getWay().randMap.data.yourPositiony - 1) || (room.getX() == (dungeon.getWay().randMap.data.yourPositionx + 1) && room.getY() == dungeon.getWay().randMap.data.yourPositiony)) || (room.getX() == dungeon.getWay().randMap.data.yourPositionx && room.getY() == (dungeon.getWay().randMap.data.yourPositiony + 1))) {
            room.setRoomWasNextToYou(true);
        }
        if (room.isYourPosition()) {
            dungeon.getWay().randMap.data.map.append(MapConstants.playerChar);
            dungeon.getWay().randMap.data.map.append(" ");
            dungeon.getWay().randMap.data.yourPositionx = room.getX();
            dungeon.getWay().randMap.data.yourPositiony = room.getY();
        } else if (room.isRightSideBorder() && room.isRoomWasNextToYou()) {
            dungeon.getWay().randMap.data.map.append(MapConstants.wallChar);
            dungeon.getWay().randMap.data.map.append("\n");
        } else if ((room.isStone() && room.isRoomWasNextToYou()) || (room.isBorder() && room.isRoomWasNextToYou())) {
            dungeon.getWay().randMap.data.map.append(MapConstants.wallChar);
            dungeon.getWay().randMap.data.map.append(" ");
        } else if (room.isSearchedRoom() && room.isVisitedRoom()) {
            dungeon.getWay().randMap.data.map.append(MapConstants.clearRoomChar);
            dungeon.getWay().randMap.data.map.append(" ");
        } else if (!room.isSearchedRoom() && room.isVisitedRoom()) {
            dungeon.getWay().randMap.data.map.append(MapConstants.fullRoomChar);
            dungeon.getWay().randMap.data.map.append(" ");
        } else if (room.isBossRoom() && room.isVisitedRoom()) {
            dungeon.getWay().randMap.data.map.append(MapConstants.bossChar);
            dungeon.getWay().randMap.data.map.append(" ");
        } else if (room.isRightSideBorder()) {
            dungeon.getWay().randMap.data.map.append(" ");
            dungeon.getWay().randMap.data.map.append("\n");
        } else {
            dungeon.getWay().randMap.data.map.append("  ");
        }
    }


    public boolean isFreeRoom() {
        return freeRoom;
    }

    public void setFreeRoom(boolean freeRoom) {
        this.freeRoom = freeRoom;
    }

    public void setStone(boolean stone) {
        this.stone = stone;
    }

    public void setRightSideBorder(boolean rightSideBorder) {
        this.rightSideBorder = rightSideBorder;
    }

    public void setBorder(boolean border) {
        this.border = border;
    }

    public void setVisitedRoom(boolean visitedRoom) {
        this.visitedRoom = visitedRoom;
    }

    public boolean isSearchedRoom() {
        return searchedRoom;
    }

    public boolean isBorder() {
        return border;
    }

    public void setBossRoom(boolean bossRoom) {
        this.bossRoom = bossRoom;
    }

    public void setSearchedRoom(boolean searchedRoom) {
        this.searchedRoom = searchedRoom;
    }

    public void setYourPosition(boolean yourPosition) {
        this.yourPosition = yourPosition;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getStr() {
        return str;
    }

    public boolean isRightSideBorder() {
        return rightSideBorder;
    }

    public boolean isRoomWasNextToYou() {
        return roomWasNextToYou;
    }

    public boolean isStone() {
        return stone;
    }

    public boolean isVisitedRoom() {
        return visitedRoom;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public boolean isYourPosition() {
        return yourPosition;
    }

    public void setRoomWasNextToYou(boolean roomWasNextToYou) {
        this.roomWasNextToYou = roomWasNextToYou;
    }

    public boolean isBossRoom() {
        return bossRoom;
    }
}
