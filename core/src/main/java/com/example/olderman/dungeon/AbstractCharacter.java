package com.example.olderman.dungeon;

public abstract class AbstractCharacter {

	protected int health;
	protected int maximumHealth;
	protected int experienceForVictory;
	protected int missChance;
	protected int luck;
	protected RandomValue damage;

	public int getHealth() {
		return health;
	}

	public int getMissChance() {
		return missChance;
	}

	public int getLuck() {
		return luck;
	}

	public int getMaximumHealth() {
		return maximumHealth;
	}

	public int getExperienceForVictory() {
		return experienceForVictory;
	}

	public RandomValue getDamage() {
		return damage;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void decreaseHealth(int healthDecrease) {
		health -= healthDecrease;
	}

	public void increaseHealth(int healthIncrease) {
		health += healthIncrease;
		if (health > maximumHealth) {
			health = maximumHealth;
		}
	}

	public void increaseAttackDamage(int damageIncrease) {
		damage = new RandomValue(damage.getConstantFactor() + damageIncrease, damage.getRandomFactor(),
				damage.getFloorFactor(), damage.getLevelFactor());
	}

	public void increaseMaximumHealth(int maximumHealthIncrease) {
		maximumHealth += maximumHealthIncrease;
	}

	public abstract String getBeginning();

}
