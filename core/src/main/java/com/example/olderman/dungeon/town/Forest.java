package com.example.olderman.dungeon.town;

import com.example.olderman.dungeon.Dungeon;

public class Forest {
	public Forest(Dungeon dungeon) {
		this.dungeon = dungeon;

	}

	private final Dungeon dungeon;

	public void cutTrees() throws InterruptedException {
		while (true) {
			int waitTime = 2000;
			switch (dungeon.uzivatVolba("Cut trees", "Hunt birds", "Back")) {
			case 1:
				if (dungeon.getForAll().energy < 10) {
					dungeon.println("You don't have enough energy.");
					break;
				} else {
					dungeon.println("You are cutting a tree!");
					Thread.sleep(waitTime);
					dungeon.println("10");
					Thread.sleep(waitTime);
					dungeon.println("9");
					Thread.sleep(waitTime);
					dungeon.println("8");
					Thread.sleep(waitTime);
					dungeon.println("7");
					Thread.sleep(waitTime);
					dungeon.println("6");
					Thread.sleep(waitTime);
					dungeon.println("5");
					Thread.sleep(waitTime);
					dungeon.println("4");
					Thread.sleep(waitTime);
					dungeon.println("3");
					Thread.sleep(waitTime);
					dungeon.println("2");
					Thread.sleep(waitTime);
					dungeon.println("1");
					Thread.sleep(waitTime);
					int foundWood = dungeon.getRand().nextInt(10);
					dungeon.getForAll().energy -= 10;
					dungeon.getForAll().wood += foundWood;
					dungeon.println("You got " + foundWood + " wood.");
					break;
				}
			case 2:
				if (dungeon.getForAll().energy < 20) {
					dungeon.println("You don't have enough energy.");
					break;
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
						break;
					}
				}

			}

		}
	}
}
