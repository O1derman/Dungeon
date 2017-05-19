package com.example.olderman.dungeon;

public class InventoryAndInfo {
	public InventoryAndInfo(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	private final Dungeon dungeon;

	public InventoryItem inventoryAndInfo(boolean fighting) {
		dungeon.println(
				dungeon.getCharacter().getHealth() + "/" + dungeon.getCharacter().getMaximumHealth() + " health");
		if (dungeon.getForAll().experience > 0) {
			dungeon.println(dungeon.getForAll().experience + " experience");
		}
		if (dungeon.getForAll().level > 0) {
			dungeon.println("level " + dungeon.getForAll().level);
		}
		if (dungeon.getForAll().floor > 0) {
			dungeon.println("floor " + dungeon.getForAll().floor);
		}
		if (dungeon.getForAll().bombCount > 0) {
			dungeon.println(dungeon.getForAll().bombCount + " bomb(s)");
		}
		if (dungeon.getForAll().resistence > 0) {
			dungeon.println(100 - dungeon.getForAll().resistence + "% resistance");
		}
		if (dungeon.getForAll().numPotionOfStrength > 0) {
			dungeon.println(dungeon.getForAll().numPotionOfStrength + " potion(s) of strength");
		}
		if (dungeon.getForAll().gold > 0) {
			dungeon.println(dungeon.getForAll().gold + " gold");

		}
		dungeon.println(dungeon.getCharacter().getMissChance() + "% miss chance");

		dungeon.println();

		return dungeon.getInventory().showInventory(fighting);
	}
}
