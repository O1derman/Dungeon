package olderman.dungeon.town;

import java.io.FileNotFoundException;
import java.io.Serializable;

import olderman.dungeon.Dungeon;
import olderman.dungeon.Resources;
import olderman.dungeon.Style;
import olderman.dungeon.inventory.Pot;

public class House implements Serializable {
	private static final long serialVersionUID = 1L;

	private Bin bin;

	public House(Dungeon dungeon) {

		this.dungeon = dungeon;
		this.bin = new Bin(dungeon);

	}

	private final Dungeon dungeon;

	public void inside() throws InterruptedException, FileNotFoundException {
		final String HOUSE = Resources.getString("/House");
		BACK: while (true) {
			dungeon.printAsciiArt(HOUSE);
			boolean hasPot = dungeon.getInventory().has(Pot.POT);
			if (dungeon.getForAll().bed > 0 && hasPot) {
				switch (dungeon.uzivatVolba("Use bed", "Use Pot", "Throw item in a bin", "Go out of house")) {
				case 1:
					dungeon.saveData();
					switch (dungeon.uzivatVolba("Continue")) {
					}
					continue BACK;
				case 2:
					Pot.POT.use(dungeon);
					continue BACK;
				case 3:
					bin.throwItems();
					continue BACK;
				case 4:
					break;
				}
			} else if (dungeon.getForAll().bed > 0) {
				switch (dungeon.uzivatVolba("Use bed", "Throw item in a bin", "Go out of house")) {
				case 1: {
					dungeon.saveData();
				}
					switch (dungeon.uzivatVolba("Continue")) {
					}
					continue BACK;
				case 2: {
					bin.throwItems();
					continue BACK;
				}
				case 3: {

					break;
				}

				}
			} else if (hasPot) {
				switch (dungeon.uzivatVolba("Use pot", "Throw item in a bin", "Go out of house")) {
				case 1: {
					Pot.POT.use(dungeon);
					continue BACK;
				}
				case 2: {
					bin.throwItems();
					continue BACK;
				}
				case 3: {
					break;
				}

				}
			} else {
				dungeon.println(Style.CENTER, "\nYou don't have anything to do at home.");
				dungeon.println(Style.CENTER, "You have to buy a pot or build a bed!");
				switch (dungeon.uzivatVolba("Throw item in a bin", "Go out of house")) {
				case 1: {
					bin.throwItems();
					continue BACK;
				}
				}
			}
			break;
		}
	}
}