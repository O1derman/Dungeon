package com.example.olderman.dungeon;

import java.util.Random;

public class ForAll {

	private Random rand = new Random();

	// Game variables
	public final String[] enemies = { "Skeleton", "Zombie", "Warrior", "Assasin" };
	public int maxEnemyHealth = 10;
	public int enemyAttackDamage = 5;
	public int experienceGain = 20;
	public int enemyHealth;
	public Enemy enemy;
	public int floor = 1;
	public int enemyMissChance = 20;

	public void resetEnemy() {
		enemyHealth = (rand.nextInt(maxEnemyHealth) + rand.nextInt(maxEnemyHealth)) + floor * 5 + 50;
		this.enemy = Enemy.values()[rand.nextInt(Enemy.values().length)];
	}

	public void resetDrinkHealthPotionCount() {
		hasDrunkHealthPotion = false;
	}

	// Player variables

	public int gold = 0;
	public int knife = 0;
	public int wood = 0;
	public int bird = 0;
	public int feather = 0;
	public int pot = 0;
	public boolean hasDrunkHealthPotion = false;
	public int frogLeg = 0;
	public int frogHeart = 0;
	public int frogLeftEye = 0;
	public int frogRightEye = 0;
	public int warm = 0;
	public int sneakBrain = 0;
	public int squirelTails = 0;
	public int frogHead = 0;
	public int snake = 0;
	public int maxGoldFound = 100;
	public int levelUpHealth = 20;
	public int levelUpDamage = 15;
	public int resistence = 100;// %
	public int bombCount = 0;
	public int level = 1;
	public int levelUp = 50;
	public int experience = 0;
	public int numPotionOfStrength = 0;
	public int potionOfStrengthPower = 500;
	public int numPotionOfInvisibility = 0;
	public int smallHealthPotionDropChance = 80; // percentage
	public int mediumHealthPotionDropChance = 50; // percentage
	public int largeHealthPotionDropChance = 20; // percentage

	public ForAll() {
		resetEnemy();
	}

	public int getGoldFound() {
		return rand.nextInt(maxGoldFound);
	}

}
