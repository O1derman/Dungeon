package com.example.olderman.dungeon.town;

import com.example.olderman.dungeon.Dungeon;
import com.example.olderman.dungeon.shop.Shop;

public class Town {

	public Town(Dungeon dungeon) {

		this.dungeon = dungeon;
		shop = new Shop(dungeon);
		workHouse = new WorkHouse(dungeon);

	}

	private final Dungeon dungeon;
	private final Shop shop;
	private final WorkHouse workHouse;

	public void town() {
		dungeon.println("Welcome in Stander!");

		switch (dungeon.uzivatVolba("Go in shop", "Go in workhouse", "Back")) {
		case 1:
			shop.shop();
			break;

		case 2:
			workHouse.workHouse();
			break;
		}

	}
}