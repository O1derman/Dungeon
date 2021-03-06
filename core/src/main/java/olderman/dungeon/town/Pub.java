package olderman.dungeon.town;

import static olderman.dungeon.Style.YELLOW;

import java.io.Serializable;

import olderman.dungeon.Dungeon;
import olderman.dungeon.Resources;
import olderman.dungeon.Style;

public class Pub implements Serializable {
	private static final long serialVersionUID = 1L;

	public Pub(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	private final Dungeon dungeon;

	public void inside() {
		final String BEER = Resources.getString("/Beer");
		dungeon.println(Style.CENTER, "Welcome in \"Thirsty turtle\"!");
		dungeon.println();
		choices: while (true) {
			dungeon.addEnergy();
			dungeon.clear();
			dungeon.println(YELLOW.BRIGHT, dungeon.getForAll().gold + " gold");
			dungeon.println(dungeon.getForAll().energy + "/100 energy");
			dungeon.println();
			dungeon.printAsciiArt(BEER);
			int volba = dungeon.uzivatVolba("Drink beer (" + dungeon.getForAll().beerCost + "G)...adds you 20 energy",
					"Play video to get full energy", "Go away");
			switch (volba) {
			case 1:
				if (dungeon.getForAll().beerCost > dungeon.getForAll().gold) {
					dungeon.println(Style.CENTER, "You don't have enough gold!");
					switch (dungeon.uzivatVolba("Continue")) {
					}
					continue choices;
				} else if (dungeon.getForAll().energy == 100) {
					dungeon.print(Style.CENTER, "You have max energy!");
					switch (dungeon.uzivatVolba("Continue")) {
					}
					continue choices;
				} else {
					dungeon.getForAll().gold -= dungeon.getForAll().beerCost;
					dungeon.println(Style.CENTER, "You drank beer.");
					if (dungeon.getForAll().energy >= 80)
						dungeon.getForAll().energy = 100;
					else {
						dungeon.getForAll().energy += 20;
					}
					switch (dungeon.uzivatVolba("Continue")) {
					}
					continue choices;
				}
			case 2:
				switch (dungeon.uzivatVolba("Continue")) {
				}
				continue choices;
			}
			break;
		}
	}

}
