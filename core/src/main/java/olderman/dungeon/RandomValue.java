package olderman.dungeon;

public class RandomValue {
	private final int constantFactor;
	private final int randomFactor;
	private final int levelFactor;

	public RandomValue(int constantFactor, int randomFactor, int levelFactor) {
		this.constantFactor = constantFactor;
		this.randomFactor = randomFactor;
		this.levelFactor = levelFactor;
	}

	public RandomValue(int constantFactor, int randomFactor) {
		this(constantFactor, randomFactor, 0);
	}

	public int getConstantFactor() {
		return constantFactor;
	}

	public int getRandomFactor() {
		return randomFactor;
	}

	public int getLevelFactor() {
		return levelFactor;
	}

	public int nextValue(Dungeon dungeon) {
		return constantFactor
				+ (randomFactor == 0 ? 0
						: dungeon.getRand().nextInt(randomFactor / 2)
								+ dungeon.getRand().nextInt((randomFactor + 1) / 2))
				+ levelFactor * dungeon.getForAll().level;
	}

	public int maxValue(Dungeon dungeon) {
		return constantFactor + randomFactor + levelFactor * dungeon.getForAll().level;
	}

	public int minValue(Dungeon dungeon) {
		return constantFactor + levelFactor * dungeon.getForAll().level;
	}

}
