package olderman.dungeon.town;

import java.io.Serializable;

import olderman.dungeon.Dungeon;
import olderman.dungeon.Resources;
import olderman.dungeon.Style;
import olderman.dungeon.inventory.Pot;

public class House implements Serializable {
	private static final long serialVersionUID = 1L;

	public House(Dungeon dungeon) {

		this.dungeon = dungeon;

	}

	private final Dungeon dungeon;

	public void inside() throws InterruptedException {
		final String HOUSE = Resources.getString("/House");
		BACK: while (true) {
			dungeon.printAsciiArt(HOUSE);
			boolean hasPot = dungeon.getInventory().has(Pot.POT);
			if (dungeon.getForAll().bed > 0 && hasPot) {
				switch (dungeon.uzivatVolba("Use bed", "Use Pot", "Go out of house")) {
				case 1:
					dungeon.saveData();
					dungeon.println("Game is saved");
					switch (dungeon.uzivatVolba("Continue")) {
					}
					continue BACK;
				case 2:
					Pot.POT.use(dungeon);
					continue BACK;
				case 3:
					break;
				}
			} else if (dungeon.getForAll().bed > 0) {
				if (dungeon.uzivatVolba("Use bed", "Go out of house") == 1) {
					dungeon.saveData();
					dungeon.println("Game is saved");
					switch (dungeon.uzivatVolba("Continue")) {
					}
					continue BACK;
				} else {
					break;
				}

			} else if (hasPot) {
				if (dungeon.uzivatVolba("Use pot", "Go out of house") == 1) {
					Pot.POT.use(dungeon);
					continue BACK;
				} else {
					break;
				}

			} else {
				dungeon.println(Style.CENTER, "\nYou don't have anything to do at home.");
				dungeon.println(Style.CENTER, "You have to buy a pot or build a bed!");
				switch (dungeon.uzivatVolba("Continue")) {
				}
			}
			break;
		}
	}
}