package olderman.dungeon.inventory;

import olderman.dungeon.Dungeon;

public class PotionOfTeleportation extends InventoryItem {

	private static final long serialVersionUID = 1L;

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