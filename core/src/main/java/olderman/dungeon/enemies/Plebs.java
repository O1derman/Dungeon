package olderman.dungeon.enemies;

import java.io.Serializable;
import java.util.Random;

import olderman.dungeon.Dungeon;
import olderman.dungeon.map.RandMapData;
import olderman.dungeon.map.Room;

public class Plebs implements Serializable {
	public RandMapData data = new RandMapData();
	private static final long serialVersionUID = 1L;

	private final Dungeon dungeon;

	public Plebs(Dungeon dungeon) {
		this.dungeon = dungeon;
		this.enemy = Enemy.values()[dungeon.getRand().nextInt(Enemy.values().length)];
	}

	// public int enemyChance = 90; // %

	// Enemy variables
	public Integer getHealth() {
		if (enemyHealth == null) {
			enemyHealth = (dungeon.getRand().nextInt(10) + dungeon.getRand().nextInt(10))
					+ dungeon.getForAll().enemiesKilled * 5 + 50;
		}
		return enemyHealth;
	}

	public void damageTaken(int minusHealth) {
		enemyHealth -= minusHealth;
	}

	public Enemy enemy;
	private Integer enemyHealth;

	public int enemyAttackDamage = 5;
	public int experienceGain = 20;
	public int enemyMissChance = 20 * 2 / 3;
}
