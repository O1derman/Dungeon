package com.example.olderman.dungeon;

public class RandomValue {
	private final int constantFactor;
	private final int randomFactor;
	private final int floorFactor;
	private final int levelFactor;

	public RandomValue(int constantFactor, int randomFactor, int floorFactor, int levelFactor) {
		this.constantFactor = constantFactor;
		this.randomFactor = randomFactor;
		this.floorFactor = floorFactor;
		this.levelFactor = levelFactor;
	}

	public RandomValue(int constantFactor, int randomFactor) {
		this(constantFactor, randomFactor, 0, 0);
	}

	public RandomValue(int constantFactor, int randomFactor, int floorFactor) {
		this(constantFactor, randomFactor, floorFactor, 0);
	}

	public int getConstantFactor() {
		return constantFactor;
	}

	public int getRandomFactor() {
		return randomFactor;
	}

	public int getFloorFactor() {
		return floorFactor;
	}

	public int getLevelFactor() {
		return levelFactor;
	}

	public int nextValue(Dungeon dungeon) {
		return constantFactor
				+ (randomFactor == 0 ? 0
						: dungeon.getRand().nextInt(randomFactor / 2)
								+ dungeon.getRand().nextInt((randomFactor + 1) / 2))
				+ floorFactor * dungeon.getPlebs().enemiesKilled + levelFactor * dungeon.getForAll().level;
	}

	public int maxValue(Dungeon dungeon) {
		return constantFactor + randomFactor + floorFactor * dungeon.getPlebs().enemiesKilled
				+ levelFactor * dungeon.getForAll().level;
	}

	public int minValue(Dungeon dungeon) {
		return constantFactor + floorFactor * dungeon.getPlebs().enemiesKilled
				+ levelFactor * dungeon.getForAll().level;
	}

}
