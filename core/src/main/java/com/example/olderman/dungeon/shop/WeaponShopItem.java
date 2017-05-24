package com.example.olderman.dungeon.shop;

import com.example.olderman.dungeon.Dungeon;

public class WeaponShopItem extends ShopItem {

	private final int damageIncrease;

	protected WeaponShopItem(Dungeon dungeon, String name, int cost, int damageIncrease) {
		super(dungeon, name, cost, "gives you " + damageIncrease + " more damage!");
		this.damageIncrease = damageIncrease;
	}

	@Override
	public void buy() {
		dungeon.getCharacter().increaseAttackDamage(damageIncrease);
		dungeon.println(
				"\tYou now have " + dungeon.getCharacter().getDamage().maxValue(dungeon) + " maximum attack damage!");
		setAvailable(false);
	}

}
