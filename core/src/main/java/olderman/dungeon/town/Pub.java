package olderman.dungeon.town;

import olderman.dungeon.Dungeon;
import olderman.dungeon.Resources;
import olderman.dungeon.Style;

public class Pub {
	public Pub(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	private final Dungeon dungeon;

	public void inside() {
		final String BEER = Resources.getString("/Beer");
        dungeon.println(Style.CENTER, "Welcome in \"Thirsty turtle\"!");
        dungeon.println();
		choices: while (true) {

			dungeon.printAsciiArt(BEER);

			dungeon.clear();
			int volba = dungeon.uzivatVolba("Drink beer (" + dungeon.getForAll().beerCost + "G)", "Exit");
			switch (volba) {
			case 1:
				if (dungeon.getForAll().beerCost > dungeon.getForAll().gold) {
                    dungeon.println(Style.CENTER, "You don't have enough gold!");
                    continue choices;
				} else {
                    dungeon.println(Style.CENTER, "You drank beer.");
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
