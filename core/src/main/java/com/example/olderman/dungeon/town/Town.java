package com.example.olderman.dungeon.town;

import com.example.olderman.dungeon.Dungeon;
import com.example.olderman.dungeon.shop.Shop;

public class Town {

	public Town(Dungeon dungeon) {

		this.dungeon = dungeon;
		shop = new Shop(dungeon);
		workHouse = new WorkHouse(dungeon);
		house = new House(dungeon);
		forest = new Forest(dungeon);

	}

	private final Dungeon dungeon;
	private final Shop shop;
	private final WorkHouse workHouse;
	private final House house;
	private final Forest forest;

	public void town() throws InterruptedException {
		dungeon.println("Welcome in Stander!");

		if (dungeon.getForAll().house == 0) {
			switch (dungeon.uzivatVolba("Go in shop", "Buy house", "Go in workhouse", "Go in the forest", "Back")) {
			case 1:
				shop.shop();
				break;
			case 2:
				dungeon.getForAll().house++;
				break;

			case 3:
				workHouse.workHouse();
				break;
			case 4:
				forest.cutTrees();

			}

		} else {
			switch (dungeon.uzivatVolba("Go in shop", "Go in house", "Go in workhouse", "Back")) {
			case 1:
				shop.shop();
				break;
			case 2:
				house.inside();

			case 3:
				workHouse.workHouse();
				break;
			}
		}
	}
}