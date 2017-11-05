package olderman.dungeon.inventory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import olderman.dungeon.Dungeon;
import olderman.dungeon.inventory.InventoryItem.Type;

import java.util.NoSuchElementException;
import java.util.Set;

public class Inventory implements Serializable {
	private static final long serialVersionUID = 1L;

	private final Dungeon dungeon;

	public Inventory(Dungeon dungeon) {
		this.dungeon = dungeon;
		add(HealthPotion.SMALL, 3);
	}

	private final Map<InventoryItem, Integer> items = new HashMap<>();

	public void add(InventoryItem item) {
		add(item, 1);
	}

	public void add(InventoryItem item, int n) {
		Integer count = getCount(item);
		if (item.canHaveMoreThenOne()) {
			count += n;
		} else {
			count = 1;
		}
		items.put(item, count);
	}

	public void remove(InventoryItem item) {
		Integer count = items.get(item);
		if (count == null)
			throw new NoSuchElementException();
		count--;
		if (count > 0) {
			items.put(item, count);
		} else {
			items.remove(item);
		}
	}

	public int getCount(InventoryItem item) {
		Integer count = items.get(item);
		return count == null ? 0 : count;
	}

	public boolean has(InventoryItem item) {
		return getCount(item) > 0;
	}

	/**
	 * 
	 * @param fighting
	 * @return used item
	 */
	public InventoryItem showInventory(boolean fighting) {
		Set<Entry<InventoryItem, Integer>> entries = items.entrySet();
		List<String> names = new ArrayList<>();
		List<InventoryItem> items = new ArrayList<>();

		for (Entry<InventoryItem, Integer> entry : entries) {
			InventoryItem item = entry.getKey();
			int count = entry.getValue();
			String name = item.getName() + (count > 1 ? " x" + count : "");
			if (fighting ? item.canUseWhileFighting() : item.getType() != Type.WEAPON) {
				names.add(name + " - " + item.getUseText());
				items.add(item);
			} else {
				dungeon.println(name);
			}
		}

		names.add("Exit inventory");
		items.add(null);

		InventoryItem item = items.get(dungeon.uzivatVolba(names.toArray(new String[names.size()])) - 1);
		if (item == null) {
			return null;
		}
		Type type = item.getType();
		if (fighting && type == Type.HEALTH_POTION) {
			if (dungeon.getForAll().hasDrunkHealthPotion) {
				dungeon.println("You can't drink more health potions this turn!");
				return null;
			}
			dungeon.getForAll().hasDrunkHealthPotion = true;
			dungeon.getForAll().flashk++;
		}
		if (type == Type.WEAPON) {
			dungeon.getForAll().resetDrinkHealthPotionCount();
		}
		if (item.use(dungeon)) {
			remove(item);
		}
		return item;
	}
}
