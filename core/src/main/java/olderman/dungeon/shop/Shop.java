package olderman.dungeon.shop;

import static olderman.dungeon.Style.YELLOW;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import olderman.dungeon.Dungeon;
import olderman.dungeon.Style;
import olderman.dungeon.inventory.Bomb;
import olderman.dungeon.inventory.Pot;
import olderman.dungeon.inventory.Weapon;

public class Shop implements Serializable {
	private static final long serialVersionUID = 1L;

	public Shop(Dungeon dungeon) {
		this.dungeon = dungeon;

		items = new ShopItem[] {
				new InventoryShopItem(dungeon, new Weapon("Dagger", 15), 200, "gives you 15 more damage!"),
				new InventoryShopItem(dungeon, new Weapon("Mallet", 20), 400, "gives you 20 more damage!"),
				new InventoryShopItem(dungeon, new Weapon("Long sword", 40), 600, "gives you 40 more damage!"),
				new InventoryShopItem(dungeon, Bomb.BOMB, 400, "deals 100-300 damage! (only for 1 use)"),
				new InventoryShopItem(dungeon, Pot.POT, 450, "allows you to create potions"), new Hamburger(dungeon), };

	}

	private final Dungeon dungeon;
	private final ShopItem[] items;

	public void shop() {

		dungeon.println(Style.CENTER, "Welcome to a weapon shop \"Broken axe\"!");
		dungeon.println();
		while (true) {
			dungeon.println("You got:");
			dungeon.println(YELLOW.BRIGHT, dungeon.getForAll().gold + " gold");

			List<ShopItem> avilableItems = new ArrayList<>();
			List<String> descriptions = new ArrayList<>();

			for (ShopItem item : items) {
				if (item.isAvailable()) {
					avilableItems.add(item);
					descriptions.add(
							item.getName() + " -> " + item.getDescription() + "...costs " + item.getCost() + " Gold.");
				}
			}

			avilableItems.add(null);
			descriptions.add("Go away");

			ShopItem selectedItem = avilableItems
					.get(dungeon.uzivatVolba(descriptions.toArray(new String[descriptions.size()])) - 1);

			if (selectedItem == null) {
				break;
			}

			if (buyWithGold(selectedItem.getCost())) {
				dungeon.println(Style.CENTER, "You bought a " + selectedItem.getName() + "!");
				selectedItem.buy();
			} else {
				dungeon.println(Style.CENTER, "You don't have enough gold to buy a " + selectedItem.getName() + "!");
			}
		}
	}

	private boolean buyWithGold(int cost) {
		if (cost > dungeon.getForAll().gold)
			return false;
		dungeon.getForAll().gold -= cost;
		return true;
	}

}
