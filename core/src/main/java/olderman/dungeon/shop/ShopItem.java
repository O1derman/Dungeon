package olderman.dungeon.shop;

import olderman.dungeon.Dungeon;

public abstract class ShopItem {

	protected final Dungeon dungeon;
	private final String name;
	private int cost;
	private String description;
	private boolean available = true;

	protected ShopItem(Dungeon dungeon, String name, int cost, String description) {
		this.dungeon = dungeon;
		this.name = name;
		this.cost = cost;
		this.description = description;
	}

	public int getCost() {
		return cost;
	}

	protected void setCost(int cost) {
		this.cost = cost;
	}

	public String getDescription() {
		return description;
	}

	protected void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public boolean isAvailable() {
		return available;
	}

	protected void setAvailable(boolean available) {
		this.available = available;
	}

	public abstract void buy();
}
