package com.example.olderman.dungeon.shop;

import com.example.olderman.dungeon.Dungeon;

public class Hamburger extends ShopItem {

	private static final int initialHamburgerCost = 1000;
	private static final int hamburgerResist = 65;// %

	protected Hamburger(Dungeon dungeon) {
		super(dungeon, "Hamburger", initialHamburgerCost, "reduces enemy damage for 35%");
	}

	@Override
	public void buy() {
		dungeon.getForAll().resistence = dungeon.getForAll().resistence * hamburgerResist / 100;
		dungeon.println("\tEnemies now deal only " + dungeon.getForAll().resistence + "% damage!");

		setCost(getCost() * 3 / 2);
		dungeon.println("\tHamburger now costs " + getCost());
	}

}
