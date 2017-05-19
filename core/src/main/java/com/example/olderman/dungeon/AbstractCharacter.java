package com.example.olderman.dungeon;

public abstract class AbstractCharacter implements Character {

	private final Dungeon dungeon;

	private int health;
	private int maximumHealth;
	private int attackDamage;
	private int experienceForVictory;
	private int missChance;
	private int luck;
	private int flatDamage;

	public AbstractCharacter(Dungeon dungeon) {
		this.dungeon = dungeon;
	};

	@Override
	public void setFlatDamage(int flatDamage) {
		this.flatDamage = flatDamage;
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public int getMissChance() {
		return missChance;
	}

	@Override
	public int getLuck() {
		return luck;
	}

	@Override
	public void setLuck(int luck) {
		this.luck = luck;
	}

	@Override
	public int getFlatDamage() {
		return flatDamage;
	}

	@Override
	public void setMaximumHealth(int maximumHealth) {
		this.maximumHealth = maximumHealth;
	}

	@Override
	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	@Override
	public void setExperienceForVictory(int experienceForVictory) {
		this.experienceForVictory = experienceForVictory;
	}

	@Override
	public int getMaximumHealth() {
		return maximumHealth;
	}

	@Override
	public int getAttackDamage() {
		return attackDamage;
	}

	public int getExperienceForVictory() {
		return experienceForVictory;
	}

	@Override
	public void setHealth(int health) {
		this.health = health;
	}

	@Override
	public int setMissChance(int missChance) {
		return this.missChance = missChance;
	}

	@Override
	public void decreaseHealth(int healthDecrease) {
		health -= healthDecrease;
	}

	@Override
	public void increaseHealth(int healthIncrease) {
		health += healthIncrease;
		if (health > maximumHealth) {
			health = maximumHealth;
		}
	}

	@Override
	public void increaseAttackDamage(int damageIncrease) {
		attackDamage += damageIncrease;

	}

	@Override
	public void increaseMaximumHealth(int maximumHealthIncrease) {
		maximumHealth += maximumHealthIncrease;

	}

	@Override
	public void decreaseEnemyDamage(int myDamageReduction) {
		dungeon.getForAll().enemyAttackDamage -= myDamageReduction;
	}

	public void increaseGold(int goldIncrease) {
		dungeon.getForAll().gold += goldIncrease;
	}

}
