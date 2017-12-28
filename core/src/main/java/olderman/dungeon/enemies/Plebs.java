package olderman.dungeon.enemies;

import java.io.Serializable;
import java.util.Random;

import olderman.dungeon.map.RandMapData;
import olderman.dungeon.map.Room;

public class Plebs implements Serializable {
	public RandMapData data = new RandMapData();
	private static final long serialVersionUID = 1L;

	private final Random rand;

	public Plebs(Random rand) {
		this.rand = rand;
		resetEnemy(); // FIXME overridable method call in constructor
	}

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

	public void inicializeMonsters() {
		for (int i = 0; i < data.downEdge; i++) {

			for (int j = 0; j < data.rightEdge; j++) {

			}
		}
	}

}
