package olderman.dungeon.shop;

import olderman.dungeon.Dungeon;
import olderman.dungeon.inventory.InventoryItem;

public class InventoryShopItem extends ShopItem {

	private final InventoryItem item;

	protected InventoryShopItem(Dungeon dungeon, InventoryItem item, int cost, String description) {
		super(dungeon, item.getName(), cost, description);
		this.item = item;
	}

	@Override
	public void buy() {
		dungeon.getInventory().add(item);
		dungeon.println("\tYou now have " + dungeon.getInventory().getCount(item) + " " + item.getName()
				+ "(s) in your inventory!");
	}

	@Override
	public boolean isAvailable() {
		return item.canHaveMoreThenOne() || dungeon.getInventory().getCount(item) == 0;
	}
}
