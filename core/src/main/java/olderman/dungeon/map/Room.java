package olderman.dungeon.map;

import olderman.dungeon.Dungeon;

public class Room {

	public Room(Dungeon dungeon) {
		this.dungeon = dungeon;

	}

	private final Dungeon dungeon;

	public void normalRoom() {
		int chestGoldFound = dungeon.getRand().nextInt(600) + 550;
		boolean emptyRoom = true;
		if (dungeon.getRand().nextInt(100) < dungeon.getForAll().chestChance) {
			dungeon.printlnMiddle(">>>You found a Chest!<<<");
			dungeon.printlnMiddle("You earned " + chestGoldFound + " Gold!");
			dungeon.getForAll().gold += chestGoldFound;
			emptyRoom = false;
		}
		if (dungeon.getRand().nextInt(100) < dungeon.getForAll().sneakChance) {
			if (dungeon.getForAll().sneakHeft + dungeon.getForAll().lCapacity > dungeon.getCharacter()
					.getLoadCapacity()) {
				dungeon.printlnMiddle("You dont have enough load capacity!");
			} else {
				dungeon.getForAll().sneak++;
				dungeon.getForAll().lCapacity += dungeon.getForAll().sneakHeft;
				dungeon.printlnMiddle("You found a Sneak.");
			}
			emptyRoom = false;

		}
		if (dungeon.getRand().nextInt(100) < dungeon.getForAll().frogChance) {
			if (dungeon.getForAll().frogHeft + dungeon.getForAll().lCapacity > dungeon.getCharacter()
					.getLoadCapacity()) {
				dungeon.printlnMiddle("You dont have enough load capacity!");
			} else {
				dungeon.getForAll().frog++;
				dungeon.getForAll().lCapacity += dungeon.getForAll().frogHeft;
				dungeon.printlnMiddle("You found a Frog.");
			}
			emptyRoom = false;

		}
		if (dungeon.getRand().nextInt(100) < dungeon.getForAll().wormChance) {
			if (dungeon.getForAll().wormHeft + dungeon.getForAll().lCapacity > dungeon.getCharacter()
					.getLoadCapacity()) {
				dungeon.printlnMiddle("You dont have enough load capacity!");
			} else {
				dungeon.getForAll().worm++;
				dungeon.getForAll().lCapacity += dungeon.getForAll().wormHeft;
				dungeon.printlnMiddle("You found a Worm.");
			}
			emptyRoom = false;
		}
		if (emptyRoom) {
			dungeon.printlnMiddle("This is just an empty room.");
		}
	}

}
