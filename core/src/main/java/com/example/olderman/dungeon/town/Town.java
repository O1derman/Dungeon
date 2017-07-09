package com.example.olderman.dungeon.town;

import com.example.olderman.dungeon.Dungeon;
import com.example.olderman.dungeon.shop.Shop;

public class Town {

	public Town(Dungeon dungeon) {

		this.dungeon = dungeon;

	}

	private final Dungeon dungeon;

	public void town() {
		Shop shop = new Shop(dungeon);
		WorkHouse workHouse = new WorkHouse(dungeon);

		dungeon.println("Welcome in Stander!");

	}
}