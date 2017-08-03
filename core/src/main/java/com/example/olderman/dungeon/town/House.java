package com.example.olderman.dungeon.town;

import com.example.olderman.dungeon.Dungeon;
import com.example.olderman.dungeon.inventory.Pot;

public class House {
	public House(Dungeon dungeon) {

		this.dungeon = dungeon;

	}

	private final Dungeon dungeon;

	public void inside() {
		if (dungeon.getForAll().bed > 0) /* && dungeon.pot...> 1 */ {// dodelat...
			switch (dungeon.uzivatVolba("Use bed", "Use Pot", "Back")) {
			case 1:
				// save...
				break;
			case 2:
				// pot....
			}
		} else if (dungeon.getForAll().bed > 0) {
			switch (dungeon.uzivatVolba("Use bed", "Back")) {
			case 1:
				// save...
				break;
			}

		} // else if (Pot.class. > 0) {
		switch (dungeon.uzivatVolba("Use pot", "Back")) {
		case 1:
			// pot....
			break;
		}
		// }
	}
}