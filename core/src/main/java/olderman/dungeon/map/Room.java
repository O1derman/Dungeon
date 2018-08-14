package olderman.dungeon.map;

import java.io.Serializable;

import olderman.dungeon.Dungeon;
import olderman.dungeon.Style;
import olderman.dungeon.enemies.Plebs;

public class Room implements Serializable {
    public RandMapData data = new RandMapData();
    private static final long serialVersionUID = 1L;

    private boolean freeRoom = false;

    private int x;
    private int y;

    private Plebs plebs;
    boolean searchedRoom;
    boolean visitedRoom;
    boolean stone;
    boolean yourPosition;
    boolean rightSideRoom;

    boolean bossRoom;
    boolean roomWasNextToYou;
    boolean border;
    String str;

    public Room(int x, int y, Dungeon dungeon) {
        this.x = x;
        this.y = y;
        searchedRoom = false;
        visitedRoom = false;
        stone = false;
        bossRoom = false;
        yourPosition = false;
        rightSideRoom = false;
        roomWasNextToYou = false;
        border = false;
        plebs = new Plebs(dungeon);
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

    public void drawCorrectSymbol() {
        if ((x == (data.yourPositionx - 1) && y == data.yourPositiony) || (x == data.yourPositionx && y == (data.yourPositiony - 1) || (x == (data.yourPositionx + 1) && y == data.yourPositiony)) || (x == data.yourPositionx && y == (data.yourPositiony + 1))) {
            roomWasNextToYou = true;
        }
        if (yourPosition) {
            str = MapConstants.playerChar + " ";
            data.yourPositionx = x;
            data.yourPositiony = y;
        } else if (rightSideRoom && roomWasNextToYou) {
            str = MapConstants.wallChar + "\n";
        } else if (stone && roomWasNextToYou) {
            str = MapConstants.wallChar + " ";
        } else if (searchedRoom && visitedRoom) {
            str = MapConstants.clearRoomChar + " ";
        } else if (border && roomWasNextToYou) {
            str = MapConstants.wallChar + " ";
        } else if (!searchedRoom && visitedRoom) {
            str = MapConstants.fullRoomChar + " ";
        } else if (bossRoom && visitedRoom) {
            str = MapConstants.bossChar + " ";
        } else {
            str = "  ";
        }
        data.map += str;
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

    public void setRightSideRoom(boolean rightSideRoom) {
        this.rightSideRoom = rightSideRoom;
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

    public void setBossRoom(boolean bossRoom) {
        this.bossRoom = bossRoom;
    }

    public void setSearchedRoom(boolean searchedRoom) {
        this.searchedRoom = searchedRoom;
    }

    public void setYourPosition(boolean yourPosition) {
        this.yourPosition = yourPosition;
    }

}
