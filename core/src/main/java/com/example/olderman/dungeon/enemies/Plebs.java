package com.example.olderman.dungeon.enemies;

import java.io.Serializable;
import java.util.Random;

public class Plebs implements Serializable {

	public Plebs() {
		resetEnemy();
	}

	private final Random rand = new Random();

	// Enemy constants
	public static final int MAX_ENEMY_HEALTH = 10;

	// Enemy variables
	public int enemiesKilled = 1; // -1
	public Enemy enemy;
	public int enemyHealth;
	public int enemyChance = 90; // %

	public int enemyAttackDamage = 5;
	public int experienceGain = 20;
	public int enemyMissChance = 20;

	public void resetEnemy() {
		enemyHealth = (rand.nextInt(MAX_ENEMY_HEALTH) + rand.nextInt(MAX_ENEMY_HEALTH)) + enemiesKilled * 5 + 50;
		this.enemy = Enemy.values()[rand.nextInt(Enemy.values().length)];

	}

}
