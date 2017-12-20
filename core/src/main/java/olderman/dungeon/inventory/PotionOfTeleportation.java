package olderman.dungeon.inventory;

import olderman.dungeon.Dungeon;
import olderman.dungeon.inventory.InventoryItem.Type;

public class PotionOfTeleportation extends InventoryItem {
	public static final PotionOfTeleportation TELEPORTATION = new PotionOfTeleportation();

	private PotionOfTeleportation() {
		super("potion of teleportation", //
				"drink", true, true, Type.OTHER);
	}

	@Override
	public boolean use(Dungeon dungeon) {
		// TODO
		return true;
	}

}
