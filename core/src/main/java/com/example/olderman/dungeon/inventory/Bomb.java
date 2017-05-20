package com.example.olderman.dungeon.inventory;

import com.example.olderman.dungeon.Dungeon;

public class Bomb extends InventoryItem {

	public static final Bomb BOMB = new Bomb();

	private Bomb() {
		super("Bomb", "throw", true, true, Type.WEAPON);
	}

	@Override
	public boolean use(Dungeon dungeon) {
		int actualBombDamage = dungeon.rand.nextInt(dungeon.getShop().bombRangeDamage)
				+ dungeon.getShop().bombBoomMinDamage;
		dungeon.getForAll().enemyHealth -= actualBombDamage;
		dungeon.println("You hit enemy for " + actualBombDamage + " damage!");
		return true;
	}

}
