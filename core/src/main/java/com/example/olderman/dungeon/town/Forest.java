package com.example.olderman.dungeon.town;

import com.example.olderman.dungeon.Dungeon;

public class Forest {
	public Forest(Dungeon dungeon) {
		this.dungeon = dungeon;

	}

	private final Dungeon dungeon;

	public void cutTrees() throws InterruptedException {
		switch (dungeon.uzivatVolba("Cut trees", "Hunt birds", "Back")) {
		case 1:
			dungeon.println("You are cutting a tree!");
			Thread.sleep(120000);
			dungeon.getForAll().wood++;
		case 2:
			if (dungeon.getRand().nextInt(100) < dungeon.getForAll().birdChance) {
				dungeon.getForAll().bird++;
			}

		}

	}

}
