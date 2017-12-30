package olderman.dungeon.town;

import olderman.dungeon.Dungeon;
import olderman.dungeon.Style;

public class Forest {
	public Forest(Dungeon dungeon) {
		this.dungeon = dungeon;

	}

	private final Dungeon dungeon;

	public void cutTrees() {
		NABIDKA: while (true) {
			dungeon.addEnergy();
			dungeon.clear();
			dungeon.println(dungeon.getForAll().energy + "/100 energy");
			dungeon.println(dungeon.getForAll().wood + " wood");
			if (dungeon.getForAll().bird == 1) {
				dungeon.println(dungeon.getForAll().bird + " bird");
			} else {
				dungeon.println(dungeon.getForAll().bird + " birds");
			}
			if (dungeon.getForAll().squirrel == 1) {
				dungeon.println(dungeon.getForAll().squirrel + " squirel");
			} else {
				dungeon.println(dungeon.getForAll().squirrel + " squirels");
			}
			dungeon.println();
			dungeon.println(Style.CENTER, "What now?");
			double waitTime;
			waitTime = 2 * 1e+9;
			if (dungeon.getForAll().bronzeAxe > 0) {
				waitTime = 1e+9;
			} else if (dungeon.getForAll().silverAxe > 0) {
				waitTime = 1e+9 / 5;
			} else if (dungeon.getForAll().ironAxe > 0) {
				waitTime = 1e+9 / 2;
			}

			switch (dungeon.uzivatVolba("Cut trees", "Hunt birds", "Open inventory and info", "Go in town")) {
			case 1:
				if (dungeon.getForAll().energy < 10) {
					dungeon.println(Style.CENTER, "You don't have enough energy.");
					switch (dungeon.uzivatVolba("Continue")) {
					}
					continue NABIDKA;
				} else {
					boolean cutting = true;
					long startTime;
					long timeElapsed;
					int wTime = 10;
					String strCutting = "You are cutting a tree!";
					startTime = System.nanoTime();
					dungeon.println(Style.CENTER, strCutting);
					dungeon.println(Style.CENTER, wTime + " until done");
					while (cutting) {
						timeElapsed = System.nanoTime() - startTime;
						if (timeElapsed >= waitTime) {
							wTime--;
							if (wTime == 0) {
								cutting = false;
								break;
							}
							dungeon.clear();
							dungeon.println(Style.CENTER, strCutting);
							dungeon.println(Style.CENTER, wTime + " until done");
							startTime = System.nanoTime();
						}
					}
					dungeon.clear();
					int foundWood = dungeon.getRand().nextInt(10);
					dungeon.getForAll().energy -= 10;
					dungeon.getForAll().wood += foundWood;
					dungeon.println(Style.CENTER, "You got " + foundWood + " wood.");
					switch (dungeon.uzivatVolba("Continue")) {
					}
					continue NABIDKA;
				}

			case 2:
				if (dungeon.getForAll().energy < 20) {
					dungeon.println(Style.CENTER, "You don't have enough energy.");
					switch (dungeon.uzivatVolba("Continue")) {
					}
					continue NABIDKA;
				} else {
					dungeon.getForAll().energy -= 20;
					if (dungeon.getRand().nextInt(100) < dungeon.getForAll().birdChance) {
						int foundBirds = dungeon.getRand().nextInt(5) + 1;
						if (dungeon.getForAll().birdHeft * foundBirds + dungeon.getForAll().lCapacity > dungeon
								.getCharacter().getLoadCapacity()) {
							dungeon.println(Style.CENTER, "You dont have enough load capacity!");
						} else {
							dungeon.getForAll().bird += foundBirds;
							dungeon.getForAll().lCapacity += dungeon.getForAll().birdHeft * foundBirds;
							if (foundBirds == 1) {
								dungeon.println(Style.CENTER, "You caught a bird.");
							} else {
								dungeon.println(Style.CENTER, "You caught " + foundBirds + " birds.");
							}
						}
						switch (dungeon.uzivatVolba("Continue")) {
						}
						continue NABIDKA;
					}
					if (dungeon.getRand().nextInt(100) < dungeon.getForAll().squirelChance) {
						if (dungeon.getForAll().squirrelHeft + dungeon.getForAll().lCapacity > dungeon.getCharacter()
								.getLoadCapacity()) {
							dungeon.println(Style.CENTER, "You dont have enough load capacity!");
						} else {
							dungeon.getForAll().squirrel++;
							dungeon.getForAll().lCapacity += dungeon.getForAll().squirrelHeft;
							dungeon.println(Style.CENTER, "You caught a squirel.");
						}
						switch (dungeon.uzivatVolba("Continue")) {
						}
						continue NABIDKA;
					} else {
						dungeon.println(Style.CENTER, "You didn't catch any bird!");
						switch (dungeon.uzivatVolba("Continue")) {
						}
						continue NABIDKA;
					}
				}
			case 3:
				dungeon.inventoryAndInfo(false);
				continue NABIDKA;

			}
			break;

		}
	}
}
