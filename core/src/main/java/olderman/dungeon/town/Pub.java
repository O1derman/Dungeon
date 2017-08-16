package olderman.dungeon.town;

import olderman.dungeon.Dungeon;

public class Pub {
	public Pub(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	private final Dungeon dungeon;

	public void inside() {
		dungeon.println("Welcome in \"Thirsty turtle\"!");
		dungeon.println();
		choices: while (true) {
			int volba = dungeon.uzivatVolba("Drink beer (" + dungeon.getForAll().beerCost + "G)", "Exit");
			switch (volba) {
			case 1:
				if (dungeon.getForAll().beerCost > dungeon.getForAll().gold) {
					dungeon.println("You don't have enough gold!");
					continue choices;
				} else {
					dungeon.println("You drunk beer.");
					if (dungeon.getForAll().energy >= 60)
						dungeon.getForAll().energy = 100;
					else {
						dungeon.getForAll().energy += 40;
					}
					continue choices;
				}

			}
			break;
		}
	}

}
