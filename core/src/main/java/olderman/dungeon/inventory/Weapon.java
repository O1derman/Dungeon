package olderman.dungeon.inventory;

import olderman.dungeon.Dungeon;
import olderman.dungeon.RandomValue;

public class Weapon extends InventoryItem {

	private static final long serialVersionUID = 1L;
	public static final Weapon HAND = new Weapon("Hand", 0);

	private final int damageIncrease;

	public Weapon(String name, int damageIncrease) {
		super(name, "select", false, false, Type.OTHER);
		this.damageIncrease = damageIncrease;
	}

	@Override
	public boolean use(Dungeon dungeon) {
		dungeon.getForAll().selectedWeapon = this;
		dungeon.println("\tYou now have " + minDamage(dungeon) + " - " + maxDamage(dungeon) + " attack damage!");
		return false;
	}

	public int calculateDamage(Dungeon dungeon) {
		RandomValue characterDamage = dungeon.getCharacter().getDamage();
		int baseDamage = characterDamage.nextValue(dungeon);
		if (baseDamage == characterDamage.maxValue(dungeon)) {
			dungeon.println("WOOOW, excellent hit!!!");
		}

		return baseDamage + damageIncrease;
	}

	public int getDamageIncrease() {
		return damageIncrease;
	}

	public int minDamage(Dungeon dungeon) {
		RandomValue characterDamage = dungeon.getCharacter().getDamage();
		return characterDamage.minValue(dungeon) + damageIncrease;
	}

	public int maxDamage(Dungeon dungeon) {
		RandomValue characterDamage = dungeon.getCharacter().getDamage();
		return characterDamage.maxValue(dungeon) + damageIncrease;
	}

}
