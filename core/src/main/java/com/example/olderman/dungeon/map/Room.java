package com.example.olderman.dungeon.map;

import com.example.olderman.dungeon.Dungeon;

public class Room {

	public Room(Dungeon dungeon) {
		this.dungeon = dungeon;

	}

	private final Dungeon dungeon;

	public void normalRoom() {
		int chestGoldFound = dungeon.getRand().nextInt(600) + 550;
		boolean emptyRoom = true;
		if (dungeon.getRand().nextInt(100) < dungeon.getForAll().chestChance) {
			dungeon.println("\t>>>You found a Chest!<<<");
			dungeon.println("\tYou earned " + chestGoldFound + " Gold!");
			emptyRoom = false;
		}
		if (dungeon.getRand().nextInt(100) < dungeon.getForAll().sneakChance) {
			dungeon.getForAll().sneak++;
			dungeon.println("\tYou found a Sneak.");
			emptyRoom = false;
		}
		if (dungeon.getRand().nextInt(100) < dungeon.getForAll().frogChance) {
			dungeon.getForAll().frog++;
			dungeon.println("\tYou found a Frog.");
			emptyRoom = false;
		}
		if (dungeon.getRand().nextInt(100) < dungeon.getForAll().wormChance) {
			dungeon.getForAll().worm++;
			dungeon.println("\tYou found a Worm.");
			emptyRoom = false;
		}
		if (emptyRoom) {
			dungeon.println("This is just an empty room.");
		}
	}

}
