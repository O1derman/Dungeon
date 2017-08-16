package olderman.dungeon.town;

import olderman.dungeon.Dungeon;

public class Forest {
	public Forest(Dungeon dungeon) {
		this.dungeon = dungeon;

	}

	private final Dungeon dungeon;

	public void cutTrees() throws InterruptedException {
		NABIDKA: while (true) {
			dungeon.println("What now?");
			double waitTime;
			waitTime = 2 * 1e+9;
			if (dungeon.getForAll().bronzeAxe > 0) {
				waitTime = 1e+9;
			} else if (dungeon.getForAll().silverAxe > 0) {
				waitTime = 1e+9 / 5;
			} else if (dungeon.getForAll().ironAxe > 0) {
				waitTime = 1e+9 / 2;
			}

			switch (dungeon.uzivatVolba("Cut trees", "Hunt birds", "Back")) {
			case 1:
				if (dungeon.getForAll().energy < 10) {
					dungeon.println("You don't have enough energy.");
					continue NABIDKA;
				} else {
					boolean cutting = true;
					long startTime;
					long sTime;
					long limit;
					long timeElapsed;
					int wTime = 10;
					String strCutting = "You are cutting a tree!";
					startTime = System.nanoTime();
					dungeon.println(strCutting);
					dungeon.println(wTime + "s" + " until done");
					while (cutting) {
						timeElapsed = System.nanoTime() - startTime;
						if (timeElapsed >= waitTime) {
							wTime--;
							if (wTime == 0) {
								cutting = false;
								break;
							}
							dungeon.clear();
							dungeon.println(strCutting);
							dungeon.println(wTime + "s" + " until done");
							startTime = System.nanoTime();
						}
					}
					dungeon.clear();
					int foundWood = dungeon.getRand().nextInt(10);
					dungeon.getForAll().energy -= 10;
					dungeon.getForAll().wood += foundWood;
					dungeon.println("You got " + foundWood + " wood.");
					continue NABIDKA;
				}

			case 2:
				if (dungeon.getForAll().energy < 20) {
					dungeon.println("You don't have enough energy.");
					continue NABIDKA;
				} else {
					dungeon.getForAll().energy -= 20;
					if (dungeon.getRand().nextInt(100) < dungeon.getForAll().squirelChance) {
						if (dungeon.getForAll().squirelHeft + dungeon.getForAll().lCapacity > dungeon.getCharacter()
								.getLoadCapacity()) {
							dungeon.println("You dont have enough load capacity!");
						} else {
							dungeon.getForAll().squirel++;
							dungeon.getForAll().lCapacity += dungeon.getForAll().squirelHeft;
							dungeon.println("You catched " + 1 + " squirel.");
						}
						continue NABIDKA;
					}
					if (dungeon.getRand().nextInt(100) < dungeon.getForAll().birdChance) {
						int foundBirds = dungeon.getRand().nextInt(5) + 1;
						if (dungeon.getForAll().birdHeft * foundBirds + dungeon.getForAll().lCapacity > dungeon
								.getCharacter().getLoadCapacity()) {
							dungeon.println("You dont have enough load capacity!");
						} else {
							dungeon.getForAll().bird += foundBirds;
							dungeon.getForAll().lCapacity += dungeon.getForAll().birdHeft * foundBirds;
							dungeon.println("You catched " + foundBirds + " birds.");
						}
						continue NABIDKA;
					} else {
						dungeon.println("You didn't catch any bird!");
						continue NABIDKA;
					}

				}

			}
			break;

		}
	}
}