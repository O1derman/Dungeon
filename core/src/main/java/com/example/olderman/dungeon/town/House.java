package com.example.olderman.dungeon.town;

import com.example.olderman.dungeon.Dungeon;
import com.example.olderman.dungeon.inventory.Pot;

public class House {
	public House(Dungeon dungeon) {

		this.dungeon = dungeon;

	}

	private final Dungeon dungeon;

	public void inside() {
		BACK: while (true) {
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
				dungeon.println("\nYou dont have nothing to do at home. You have to buy pot or build bed!\n");
				break;
			}
		}
	}
}