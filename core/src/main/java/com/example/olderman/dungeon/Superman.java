package com.example.olderman.dungeon;

public class Superman extends AbstractCharacter {

	public Superman() {
		setHealth(999999999);
		this.maximumHealth = 999999999;
		this.experienceForVictory = 10000;
		this.missChance = 0;
		this.luck = 100;
		this.damage = new RandomValue(999999999 /* minimum damage */,
				0 /* damage range */, 0, 0 /* increase per level */);
	}

    @Override
	public String getBeginning() {

		return "\t> You are SUPER SUPERMAN, you have unlimited power until you meet criptonit.\n";

	}

}

// System.out
// .println("###############################################################################################################\n");
//
// int experienceForVictory = 10000;
// int health = 999999999;
// int attackDamage = 999999999;
//
// GAME: while (running) {
//
// System.out
// .println("###############################################################################################################\n");
//
// int enemyHealth = rand.nextInt(maxEnemyHealth);
// String enemy = enemies[rand.nextInt(enemies.length)];
// System.out.println("\t#You see a(n)" + enemy + "!");
//
// while (enemyHealth > 0) {
// System.out.println("\tYour HP: " + health);
// System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
// System.out.println("\n\tWhat would you like to do?");
// System.out.println("\t1. Attack");
// System.out.println("\t2. Drink health potion");
// System.out.println("\t3. Run");
// System.out.println("\t4. Exit\n\n");
// System.out
// .println("\tYou have:\n\t"
// + numSmallHealthPotions
// + " small health potions\n\t"
// + numMediumHealthPotions
// + " medium health potions\n\t"
// + numLargeHealthPotions
// + " large health potions\n\t"
// + numPotionOfStrength
// +
// " potions of
// srength\n\n###############################################################################################################");
//
// String input = in.nextLine();
// if (input.equals("1")) {
// int damageDealt = rand.nextInt(attackDamage);
// int damageTaken = rand.nextInt(enemyAttackDamage);
//
// enemyHealth -= damageDealt;
// health -= damageTaken;
//
// System.out.println("\t> You strike the " + enemy
// + " for " + damageDealt + " damage.");
// System.out.println("\t> You recieve " + damageTaken
// + " damage.");
//
// if (health < 1) {
// System.out
// .println("\t> You have taken too much damage, you are dying with pain in
// shits of your enemy while they are celebrating...zombies will have tasty
// dinner! ");
// break;
// }
//
// else if (health < 30 && health > 1) {
// System.out
// .println("You look like red tomato raced out by truck! You should go to a
// hospital imidietly!\n\n\n\n\n");
// }
//
// } else if (input.equals("2")) {
// System.out.println("\tSuperman can't drink potions!\n");
// } else if (input.equals("3")) {
// System.out.println("\tYou literally flew away!\n");
// // int runEnemyAttackDamage = 25;
// // int runDamageTaken =
// // rand.nextInt(runEnemyAttackDamage);
// // health -= runDamageTaken;
// // System.out.println("\tThe " + enemy + " hit you for "
// // + runDamageTaken + "!");
// // System.out.println("\tYou run away from the " + enemy
// // + "!");
// // System.out.println("\tYou now have " + health +
// // " HP.\n\n\n\n\n");
// continue GAME;
//
// } else if (input.equals("4")) {
// break;
// }
//
// else {
// System.out.println("\tInvalid command!\n\n\n\n\n");
//
// }
//
// }
//
// if (health < 1) {
// System.out
// .println("You limp out of the dungeon, weak from battle.\n\n\n\n\n");
// break;
