package com.example.olderman.dungeon.enemies;

import java.io.Serializable;
import java.util.Random;

public class Plebs implements Serializable {
	private static final long serialVersionUID = 1L;

	public Plebs() {
		resetEnemy();
	}

	private final Random rand = new Random();

	// public int enemyChance = 90; // %

	// Enemy variables
	public int enemiesKilled = 0; // -1
	public Enemy enemy;
	public int enemyHealth;

	public int enemyAttackDamage = 5;
	public int experienceGain = 20;
	public int enemyMissChance = 20;

	public void resetEnemy() {
		enemyHealth = (rand.nextInt(10) + rand.nextInt(10)) + enemiesKilled * 5 + 50;
		this.enemy = Enemy.values()[rand.nextInt(Enemy.values().length)];

	}

}
