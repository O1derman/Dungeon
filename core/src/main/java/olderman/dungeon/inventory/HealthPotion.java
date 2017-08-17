package olderman.dungeon.inventory;

import olderman.dungeon.Dungeon;

public class HealthPotion extends InventoryItem {

	public static final HealthPotion SMALL = new HealthPotion("small", 20);
	public static final HealthPotion MEDIUM = new HealthPotion("medium", 50);
	public static final HealthPotion LARGE = new HealthPotion("large", 100);

	private final String type;
	private final int healAmount;

	private HealthPotion(String type, int healAmount) {
		super(java.lang.Character.toUpperCase(type.charAt(0)) + type.substring(1) + " health potion", //
				"drink", true, true, Type.HEALTH_POTION);
		this.type = type;
		this.healAmount = healAmount;
	}

	@Override
	public boolean use(Dungeon dungeon) {
		int health = dungeon.getHealth();
		int maximumHealth = dungeon.getForAll().maximumHealth;
		health += healAmount;
		if (health <= maximumHealth) {
			dungeon.println(
					"\t> You drink a " + type + " health potion, healing yourself for " + healAmount + ".");
			dungeon.println("\t> You now have " + health + "HP.");
		} else {
			health = maximumHealth;
			dungeon.println("You drank health potion while you would overheal your maximum health, you have maximum health now!");
		}
		dungeon.setHealth(health);
		return true;
	}

}
