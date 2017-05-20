package com.example.olderman.dungeon.inventory;

import com.example.olderman.dungeon.Dungeon;

public abstract class InventoryItem {

	public enum Type {
		WEAPON, HEALTH_POTION, OTHER
	}

	private final String name;
	private final String useText;
	private final boolean canUseWhileFighting;
	private final boolean canHaveMoreThenOne;
	private final Type type;

	public InventoryItem(String name, String useText, boolean canUseWhileFighting, boolean canHaveMoreThenOne,
			Type type) {
		this.name = name;
		this.useText = useText;
		this.canUseWhileFighting = canUseWhileFighting;
		this.canHaveMoreThenOne = canHaveMoreThenOne;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getUseText() {
		return useText;
	}

	public boolean canUseWhileFighting() {
		return canUseWhileFighting;
	}

	public boolean canHaveMoreThenOne() {
		return canHaveMoreThenOne;
	}

	public Type getType() {
		return type;
	}

	/**
	 * 
	 * @param dungeon
	 * @return if the item was consumed
	 */
	public abstract boolean use(Dungeon dungeon);
}
