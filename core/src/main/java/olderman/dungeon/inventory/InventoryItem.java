package olderman.dungeon.inventory;

import java.io.Serializable;

import olderman.dungeon.Dungeon;

public abstract class InventoryItem implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum Type {
        WEAPON, HEALTH_POTION, OTHER
    }

    private final String name;
    private final String useText;
    private final boolean canUseWhileFighting;
    private final boolean canHaveMoreThanOne;
    private final Type type;

    public InventoryItem(String name, String useText, boolean canUseWhileFighting, boolean canHaveMoreThenOne,
                         Type type) {
        this.name = name;
        this.useText = useText;
        this.canUseWhileFighting = canUseWhileFighting;
        this.canHaveMoreThanOne = canHaveMoreThenOne;
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
        return canHaveMoreThanOne;
    }

    public Type getType() {
        return type;
    }

    /**
     * @param dungeon
     * @return weather the item was consumed
     */
    public abstract boolean use(Dungeon dungeon);
}
