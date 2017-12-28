package olderman.dungeon.inventory;

import olderman.dungeon.Dungeon;
import olderman.dungeon.inventory.InventoryItem.Type;

public class PotionOfConcentration extends InventoryItem {

	public static final PotionOfConcentration CONCENTRATION = new PotionOfConcentration(5);

	private final int concentrationAmount;

	private PotionOfConcentration(int concentrationAmount) {
		super("potion of concentration", //
				"drink", true, true, Type.OTHER);
		this.concentrationAmount = concentrationAmount;
	}

	@Override
	public boolean use(Dungeon dungeon) {
		int missChance = dungeon.getForAll().missChance;
		if (missChance <= 5) {
			missChance -= concentrationAmount;
			dungeon.setMissChance(missChance);
		}
		return true;
	}

}
