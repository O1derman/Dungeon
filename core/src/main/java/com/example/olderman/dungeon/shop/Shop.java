package com.example.olderman.dungeon.shop;

import java.util.ArrayList;
import java.util.List;

import com.example.olderman.dungeon.Character;
import com.example.olderman.dungeon.Dungeon;
import com.example.olderman.dungeon.inventory.Bomb;
import com.example.olderman.dungeon.inventory.Pot;

public class Shop {

	public Shop(Dungeon dungeon) {
		this.dungeon = dungeon;

		items = new ShopItem[] { new WeaponShopItem(dungeon, "Malet", 500, 15),
				new WeaponShopItem(dungeon, "Onehand axe", 600, 20),
				new WeaponShopItem(dungeon, "Twohand axe", 800, 40),
				new InventoryShopItem(dungeon, Bomb.BOMB, 400, "deals 100-300 damage! (only for 1 use)"),
				new InventoryShopItem(dungeon, Pot.POT, 450, "allows you to create potions"), new Hamburger(dungeon) };
	}

	private final Dungeon dungeon;

	// int knifeCost = 200;

	private final ShopItem[] items;

	public void shop() {

		boolean running = true;

		dungeon.println("\tWelcome to a weapon shop \"Broken axe\"!");
		dungeon.println("\tWe have some great offers for you!");
		dungeon.println("\tI know you will choose visely!");

		while (running) {
			int volba = dungeon.uzivatVolba("Look around!", "Leave!");

			if (volba == 1) {
				dungeon.println("You have " + dungeon.getForAll().gold + " gold.");
				dungeon.println("\n\tWhat will you get?");

				List<ShopItem> avilableItems = new ArrayList<>();
				List<String> descriptions = new ArrayList<>();

				for (ShopItem item : items) {
					if (item.isAvailable()) {
						avilableItems.add(item);
						descriptions.add(item.getName() + " -> " + item.getDescription() + "...costs " + item.getCost()
								+ " Gold.");
					}
				}

				avilableItems.add(null);
				descriptions.add("Exit");

				ShopItem selectedItem = avilableItems
						.get(dungeon.uzivatVolba(descriptions.toArray(new String[descriptions.size()])) - 1);

				if (selectedItem == null) {
					break;
				}

				if (buyWithGold(selectedItem.getCost())) {
					dungeon.println("\tYou bought a " + selectedItem.getName() + "!");
					selectedItem.buy();
				} else {
					dungeon.println("\tYou don't have enough gold to buy a " + selectedItem.getName() + "!");

				}

			} else if (volba == 2) {
				break;
			} else {
				dungeon.println("Invalid command");
			}

		}
	}

	public boolean buyWithGold(int cost) {
		if (cost > dungeon.getForAll().gold)
			return false;
		dungeon.getForAll().gold -= cost;
		return true;
	}

}
