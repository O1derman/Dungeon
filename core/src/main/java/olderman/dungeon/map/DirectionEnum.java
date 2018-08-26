package olderman.dungeon.map;

public enum DirectionEnum {
    EAST("East", 0, 1), NORTH("North", -1, 0), SOUTH("South", 1, 0), WEST("West", 0, -1);

    DirectionEnum(String description, int nextRoomPositionL, int nextRoomPositionW) {
        this.description = description;
        this.nextRoomPositionL = nextRoomPositionL;
        this.nextRoomPositionW = nextRoomPositionW;
    }

    private String description;

    private int nextRoomPositionL;

    private int nextRoomPositionW;

    public String getDescription() {
        return description;
    }

    public int getNextRoomPositionL() {
        return nextRoomPositionL;
    }

    public int getNextRoomPositionW() {
        return nextRoomPositionW;
    }

}
