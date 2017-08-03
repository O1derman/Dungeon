package com.example.olderman.dungeon.map;

import com.example.olderman.dungeon.Dungeon;

public class Room {

	public Room(Dungeon dungeon) {
		this.dungeon = dungeon;

	}

	private final Dungeon dungeon;

	public void normalRoom() {
		int chestGoldFound = dungeon.getRand().nextInt(600) + 550;

		dungeon.println("\t#You see " + dungeon.getPlebs().enemy.nameWithArticle() + "!");

		if (dungeon.getRand().nextInt(100) < dungeon.getForAll().chestChance) {
			dungeon.println("\t>>>You found a Chest!<<<");
			dungeon.println("\tYou earned " + chestGoldFound + " Gold!");
		}
		if (dungeon.getRand().nextInt(100) < dungeon.getForAll().sneakChance) {
			dungeon.getForAll().sneak++;
		}
		if (dungeon.getRand().nextInt(100) < dungeon.getForAll().frogChance) {
			dungeon.getForAll().frog++;
		}
		if (dungeon.getRand().nextInt(100) < dungeon.getForAll().wormChance) {
			dungeon.getForAll().worm++;
		}
	}

}
