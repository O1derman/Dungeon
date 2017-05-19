package com.example.olderman.dungeon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

import com.example.olderman.dungeon.InventoryItem.Type;

public class Inventory {
	private final Dungeon dungeon;

	public Inventory(Dungeon dungeon) {
		this.dungeon = dungeon;
		add(HealthPotion.SMALL, 3);
	}

	private Map<InventoryItem, Integer> items = new HashMap<>();

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
		return items.getOrDefault(item, 0);
	}

	/**
	 * 
	 * @param fighting
	 * @param canUsePotion
	 * @return used item
	 */
	public InventoryItem showInventory(boolean fighting) {
		Set<Entry<InventoryItem, Integer>> entries = items.entrySet();
		List<String> names = new ArrayList<>();
		List<InventoryItem> items = new ArrayList<>();

		for (Entry<InventoryItem, Integer> entry : entries) {
			InventoryItem item = entry.getKey();
			int count = entry.getValue();
			if (!fighting || item.canUseWhileFighting()) {
				names.add(item.getName() + (count > 1 ? " x" + count : "") + " - " + item.getUseText());
				items.add(item);
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
		}
		if (item.use(dungeon)) {
			remove(item);
		}
		return item;
	}
}
