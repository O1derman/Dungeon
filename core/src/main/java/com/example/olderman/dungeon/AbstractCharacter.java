package com.example.olderman.dungeon;

public abstract class AbstractCharacter {

	protected int initialHealth;
	protected int initialMaximumHealth;
	protected int experienceForVictory;
	protected int missChance;
	protected int luck;
	protected int loadCapacity;
	protected RandomValue damage;

	public int getInitialHealth() {
		return initialHealth;
	}

	public int getMissChance() {
		return missChance;
	}

	public int getLuck() {
		return luck;
	}

	public int getLoadCapacity() {
		return loadCapacity;
	}

	public int getInitialMaximumHealth() {
		return initialMaximumHealth;
	}

	public int getExperienceForVictory() {
		return experienceForVictory;
	}

	public RandomValue getDamage() {
		return damage;
	}

	public void increaseAttackDamage(int damageIncrease) {
		damage = new RandomValue(damage.getConstantFactor() + damageIncrease, damage.getRandomFactor(),
				damage.getFloorFactor(), damage.getLevelFactor());
	}

	public abstract String getBeginning();

}
