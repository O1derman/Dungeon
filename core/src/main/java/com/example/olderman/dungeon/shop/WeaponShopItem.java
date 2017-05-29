package com.example.olderman.dungeon.shop;

import com.example.olderman.dungeon.Dungeon;
import com.example.olderman.dungeon.RandomValue;

public class WeaponShopItem extends ShopItem {

	private final int damageIncrease;

	protected WeaponShopItem(Dungeon dungeon, String name, int cost, int damageIncrease) {
		super(dungeon, name, cost, "gives you " + damageIncrease + " more damage!");
		this.damageIncrease = damageIncrease;
	}

	@Override
	public void buy() {
		dungeon.getCharacter().increaseAttackDamage(damageIncrease);
		RandomValue damage = dungeon.getCharacter().getDamage();
		dungeon.println(
				"\tYou now have " + damage.minValue(dungeon) + " - " + damage.maxValue(dungeon) + " attack damage!");
		setAvailable(false);
	}

}
