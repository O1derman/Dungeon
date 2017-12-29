package olderman.dungeon.map;

import olderman.dungeon.Dungeon;
import olderman.dungeon.Style;
import olderman.dungeon.enemies.Plebs;

public class Room {
	private boolean freeRoom = false;

	private int x;
	private int y;
	private Plebs plebs;
	boolean searchedRoom;

	public boolean isSearchedRoom() {
		return searchedRoom;
	}

	public void setSearchedRoom(boolean searchedRoom) {
		this.searchedRoom = searchedRoom;
	}

	public Room(int x, int y, Dungeon dungeon) {
		this.x = x;
		this.y = y;
		searchedRoom = false;
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

	public boolean isFreeRoom() {
		return freeRoom;
	}

	public void setFreeRoom(boolean freeRoom) {
		this.freeRoom = freeRoom;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
