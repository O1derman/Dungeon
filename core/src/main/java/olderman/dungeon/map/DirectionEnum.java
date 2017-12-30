package olderman.dungeon.map;

public enum DirectionEnum {
	EAST("East", 1, 0), NORTH("North", 0, -1), SOUTH("South", 0, 1), WEST("West", -1, 0);

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
