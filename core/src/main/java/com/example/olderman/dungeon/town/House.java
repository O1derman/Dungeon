package com.example.olderman.dungeon.town;

import com.example.olderman.dungeon.Dungeon;
import com.example.olderman.dungeon.inventory.Pot;

public class House {
	public House(Dungeon dungeon) {

		this.dungeon = dungeon;

	}

	private Pot pot = new Pot();
	private final Dungeon dungeon;

	public void inside() {
		new Pot();
		BACK: while (true) {
			if (dungeon.getForAll().bed > 0 && dungeon.getForAll().pot > 0) {
				switch (dungeon.uzivatVolba("Use bed", "Use Pot", "Back")) {
				case 1:
					dungeon.saveData();
					continue BACK;
				case 2:
					pot.canUseWhileFighting();
					continue BACK;
				}
			} else if (dungeon.getForAll().bed > 0) {
				switch (dungeon.uzivatVolba("Use bed", "Back")) {
				case 1:
					dungeon.saveData();
					continue BACK;
				}

			} else if (dungeon.getForAll().pot > 0) {
				switch (dungeon.uzivatVolba("Use pot", "Back")) {
				case 1:
					pot.canUseWhileFighting();
					continue BACK;
				}

			} else {
				dungeon.println("\nYou dont have nothing to do at home. You have to buy pot or build bed!\n");
			}
			break;
		}
	}
}