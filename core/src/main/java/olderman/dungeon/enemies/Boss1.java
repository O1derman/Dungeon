package olderman.dungeon.enemies;

import java.io.Serializable;

import olderman.dungeon.Dungeon;

public class Boss1 implements Serializable {
	private static final long serialVersionUID = 1L;

	public Boss1(Dungeon dungeon) {
		this.dungeon = dungeon;

	}

	public String boss1Name = "Syndyl presented the smasher";
	public int boss1MissChance = 5;
	public int boss1Health = 1200;

	private final Dungeon dungeon;

	public void boss1Quote() {
		dungeon.println("Any last will STRANGER?!");
		dungeon.println("Have you bought tomb already?");
		dungeon.println(
				"Oh wait, you won't even need that, because when I end with you, nothing will be left from you!");
		dungeon.println("HAHAAHA!!!");
	}

	public void boss1Fight() {
		boolean youMiss = dungeon.getRand().nextInt(100) <= dungeon.getCharacter().getMissChance();
		boolean boss1Miss = dungeon.getRand().nextInt(100) <= boss1MissChance;

		if (youMiss) {
			dungeon.println("\tYou MISS!");
		}
		if (boss1Miss) {
			dungeon.println("\tEnemy MISS!");
			dungeon.println("AAH!");
			dungeon.println("I will smash you!");
		}

		if (!boss1Miss) {
			dungeon.println("He just smashed you with his huge mace!");
			dungeon.getForAll().health -= dungeon.getForAll().health;
		}
		dungeon.getForAll().resetDrinkHealthPotionCount();

	}

	public String getBoss1Name() {
		return boss1Name;
	}

	public void setBoss1Name(String boss1Name) {
		this.boss1Name = boss1Name;
	}

	public int getBoss1MissChance() {
		return boss1MissChance;
	}

	public void setBoss1MissChance(int boss1MissChance) {
		this.boss1MissChance = boss1MissChance;
	}

	public int getBoss1Health() {
		return boss1Health;
	}

	public void setBoss1Health(int boss1Health) {
		this.boss1Health = boss1Health;
	}
}
