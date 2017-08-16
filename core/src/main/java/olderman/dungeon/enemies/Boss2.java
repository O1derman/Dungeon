package olderman.dungeon.enemies;

import static olderman.dungeon.Style.RED;

import olderman.dungeon.Dungeon;

public class Boss2 {
	public Boss2(Dungeon dungeon) {
		this.dungeon = dungeon;

	}

	private final Dungeon dungeon;

	public void boss2Fight() {
		boolean youMiss = dungeon.getRand().nextInt(100) <= dungeon.getCharacter().getMissChance();
		boolean enemyMiss = dungeon.getRand().nextInt(100) <= dungeon.getPlebs().enemyMissChance;

		if (youMiss) {
			dungeon.println("\tYou MISS!");
		}
		if (enemyMiss) {
			dungeon.println("\tEnemy MISS!");
		}

		if (!youMiss) {
			int damageDealt = dungeon.getForAll().selectedWeapon.calculateDamage(dungeon);
			dungeon.println("\t> You strike the " + dungeon.getPlebs().enemy.name + " for " + damageDealt + " damage.");
			dungeon.getPlebs().enemyHealth -= damageDealt;
		}

		if (!enemyMiss) {
			int damageTaken = ((dungeon.getRand().nextInt(dungeon.getPlebs().enemyAttackDamage)
					+ dungeon.getRand().nextInt(dungeon.getPlebs().enemyAttackDamage))
					+ dungeon.getPlebs().enemiesKilled * 5 + 10) * dungeon.getForAll().resistence / 100;

			dungeon.println("\t> You receive " + damageTaken + " damage.");
			dungeon.decreaseHealth(damageTaken);
		}

		if (dungeon.getHealth() < 1) {
			dungeon.println(
					"\t> You have taken too much damage, you are dying in pain covered in the shit of your enemy while they are celebrating...zombies will a have tasty dinner! ");
		} else if (dungeon.getHealth() < 30) {
			dungeon.println(RED.BRIGHT, "\n\t!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@\n");
			dungeon.println(RED.BRIGHT, "\t> <ALERT>Your HP is very low " + "(" + dungeon.getHealth() + " HP left)");
			dungeon.println(RED.BRIGHT, "\n\t!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@\n");
		}
		dungeon.getForAll().resetDrinkHealthPotionCount();

	}
}