package olderman.dungeon.inventory;

import olderman.dungeon.Dungeon;

public class PotionOfConcentration extends InventoryItem {

	private static final long serialVersionUID = 1L;

	public static final PotionOfConcentration CONCENTRATION = new PotionOfConcentration(5);

	private final int misschanceDecrease = 5;
	private final int concentrationAmount;

	private PotionOfConcentration(int concentrationAmount) {
		super("potion of concentration", //
				"drink", true, true, Type.OTHER);
		this.concentrationAmount = concentrationAmount;
	}

	@Override
	public boolean use(Dungeon dungeon) {
		int missChance = dungeon.getForAll().missChance;
		if (missChance >= 5) {
			missChance -= misschanceDecrease;
			dungeon.setMissChance(missChance);
		}
		return true;
	}

}
