package olderman.dungeon.town;

import olderman.dungeon.Dungeon;
import olderman.dungeon.Resources;
import olderman.dungeon.Style;
import olderman.dungeon.inventory.Pot;

public class House {
	public House(Dungeon dungeon) {

		this.dungeon = dungeon;

	}

	private final Dungeon dungeon;

	public void inside() {
		final String HOUSE = Resources.getString("/House");
		BACK: while (true) {
			dungeon.printAsciiArt(HOUSE);
			boolean hasPot = dungeon.getInventory().has(Pot.POT);
			if (dungeon.getForAll().bed > 0 && hasPot) {
				switch (dungeon.uzivatVolba("Use bed", "Use Pot", "Back")) {
				case 1:
					dungeon.saveData();
					break;
				case 2:
					Pot.POT.use(dungeon);
					break;
				case 3:
					break BACK;
				}
			} else if (dungeon.getForAll().bed > 0) {
				if (dungeon.uzivatVolba("Use bed", "Back") == 1) {
					dungeon.saveData();
				} else {
					break;
				}

			} else if (hasPot) {
				if (dungeon.uzivatVolba("Use pot", "Back") == 1) {
					Pot.POT.use(dungeon);
				} else {
					break;
				}

			} else {
				dungeon.println(Style.CENTER, "\nYou don't have anything to do at home.");
				dungeon.println(Style.CENTER, "You have to buy a pot or build a bed!");
				break;
			}
		}
	}
}