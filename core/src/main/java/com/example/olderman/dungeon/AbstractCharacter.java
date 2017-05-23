package com.example.olderman.dungeon;

public abstract class AbstractCharacter {

	protected int health;
	protected int maximumHealth;
	protected int attackDamage;
	protected int experienceForVictory;
	protected int missChance;
	protected int luck;
	protected int flatDamage;

	public AbstractCharacter() {
	};

	public int getHealth() {
		return health;
	}

	public int getMissChance() {
		return missChance;
	}

	public int getLuck() {
		return luck;
	}

	public int getFlatDamage() {
		return flatDamage;
	}

	public int getMaximumHealth() {
		return maximumHealth;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public int getExperienceForVictory() {
		return experienceForVictory;
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
		attackDamage += damageIncrease;
	}

	public void increaseMaximumHealth(int maximumHealthIncrease) {
		maximumHealth += maximumHealthIncrease;
	}

	public abstract String getBeginning();

}
