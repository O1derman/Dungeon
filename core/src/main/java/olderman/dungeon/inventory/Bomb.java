package olderman.dungeon.inventory;

import olderman.dungeon.Dungeon;
import olderman.dungeon.Style;

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
		if (dungeon.getWay().map1.l == dungeon.getWay().map1.rightEdge
				&& dungeon.getWay().map1.w == dungeon.getWay().map1.w1) {
			dungeon.getBoss1().boss1Health -= actualBombDamage;
		} else {
			dungeon.getPlebs().enemyHealth -= actualBombDamage;
		}
		dungeon.println(Style.CENTER, "You hit enemy for " + actualBombDamage + " damage!");
		return true;
	}

}
