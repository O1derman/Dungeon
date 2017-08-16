package olderman.dungeon.inventory;

import olderman.dungeon.Dungeon;

public class Bomb extends InventoryItem {

	public static final int MIN_DAMAGE = 100;
	public static final int MAX_DAMAGE = 300;
	public static final int DAMAGE_RANGE = MAX_DAMAGE - MIN_DAMAGE;

	public static final Bomb BOMB = new Bomb();

	private Bomb() {
		super("Bomb", "throw", true, true, Type.WEAPON);
	}

	@Override
	public boolean use(Dungeon dungeon) {
		int actualBombDamage = dungeon.getRand().nextInt(DAMAGE_RANGE) + MIN_DAMAGE;
		dungeon.getPlebs().enemyHealth -= actualBombDamage;
		dungeon.getBoss1().boss1Health -= actualBombDamage;
		dungeon.getBoss2();
		dungeon.println("You hit enemy for " + actualBombDamage + " damage!");
		return true;
	}

}
