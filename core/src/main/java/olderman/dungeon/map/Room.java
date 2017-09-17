package olderman.dungeon.map;

import olderman.dungeon.Dungeon;
import olderman.dungeon.Style;

public class Room {

	public Room(Dungeon dungeon) {
		this.dungeon = dungeon;

	}

	private final Dungeon dungeon;

	public void normalRoom() {
		int chestGoldFound = dungeon.getRand().nextInt(600) + 550;
		boolean emptyRoom = true;
		if (dungeon.getRand().nextInt(100) < dungeon.getForAll().chestChance) {
            dungeon.println(Style.CENTER, ">>>You found a Chest!<<<");
            dungeon.println(Style.CENTER, "You earned " + chestGoldFound + " Gold!");
            dungeon.getForAll().gold += chestGoldFound;
			emptyRoom = false;
		}
		if (dungeon.getRand().nextInt(100) < dungeon.getForAll().sneakChance) {
			if (dungeon.getForAll().sneakHeft + dungeon.getForAll().lCapacity > dungeon.getCharacter()
					.getLoadCapacity()) {
                dungeon.println(Style.CENTER, "You don't have enough load capacity!");
            } else {
				dungeon.getForAll().sneak++;
				dungeon.getForAll().lCapacity += dungeon.getForAll().sneakHeft;
                dungeon.println(Style.CENTER, "You found a Sneak.");
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

}
