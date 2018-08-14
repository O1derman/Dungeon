package olderman.dungeon.inventory;

import olderman.dungeon.Dungeon;
import olderman.dungeon.Style;

public class Bomb extends InventoryItem {

    private static final long serialVersionUID = 1L;
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
        if (dungeon.getWay().randMap.data.yourPositiony == dungeon.bossY && dungeon.getWay().randMap.data.yourPositionx == dungeon.bossX) {
            dungeon.getBoss1().boss1Health -= actualBombDamage;
        } else {
            dungeon.getCurrentPlebs().damageTaken(actualBombDamage);
        }
        dungeon.println(Style.CENTER, "You hit enemy for " + actualBombDamage + " damage!");
        return true;
    }

}
