package olderman.dungeon.enemies;

import java.io.Serializable;

import olderman.dungeon.Dungeon;

public class Boss2 implements Serializable {
	private static final long serialVersionUID = 1L;

	public Boss2(Dungeon dungeon) {
		this.dungeon = dungeon;

	}

	public String boss2Name = "Weedy presented the high man";
	public int boss2MissChance = 50;
	public int boss2Health = 420;
	public int boss2Damage = 77;

	private final Dungeon dungeon;

	public void boss2Quote() {
		dungeon.println("Hi, want some weed?!");
		dungeon.println("HAHAhahaHA!!!");
	}

	public void boss2Fight() {
		boolean youMiss = dungeon.getRand().nextInt(100) <= dungeon.getCharacter().getMissChance();
		boolean boss2Miss = dungeon.getRand().nextInt(100) <= boss2MissChance;

		if (youMiss) {
			dungeon.println("\tYou MISS!");
		}
		if (boss2Miss) {
			dungeon.println("\tEnemy MISS!");
			dungeon.println("AAH!");
			dungeon.println("I will smash you!");
		}

		if (!boss2Miss) {
			dungeon.getForAll().health -= boss2Damage;
			dungeon.println("Ganja!");
		}
		dungeon.getForAll().resetDrinkHealthPotionCount();

	}

	public String getBoss2Name() {
		return boss2Name;
	}

	public void setBoss2Name(String boss2Name) {
		this.boss2Name = boss2Name;
	}

	public int getBoss2MissChance() {
		return boss2MissChance;
	}

	public void setBoss2MissChance(int boss2MissChance) {
		this.boss2MissChance = boss2MissChance;
	}

	public int getBoss2Health() {
		return boss2Health;
	}

	public void setBoss2Health(int boss2Health) {
		this.boss2Health = boss2Health;
	}
}
