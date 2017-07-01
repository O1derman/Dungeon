package com.example.olderman.dungeon.characters;

import com.example.olderman.dungeon.AbstractCharacter;
import com.example.olderman.dungeon.RandomValue;

public class Elf extends AbstractCharacter {

	public Elf() {
		this.initialHealth = 100;
		this.initialMaximumHealth = 120;
		this.experienceForVictory = 300;
		this.missChance = 5;
		this.luck = 10;
		this.damage = new RandomValue(10 /* minimum damage */,
				10 /* damage range */, 0, 5 /* increase per level */);
	}

    @Override
	public String getBeginning() {

		return "\t> You are a tall elf, very clever and handy, not very strong.\n";

	}

}

// while (enemyHealth > 0) {
// System.out.println("\tYour HP: " + initialHealth);
// System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
// System.out.println("\n\tWhat would you like to do?");
// System.out.println("\t1. Attack");
// System.out.println("\t2. Drink initialHealth potion");
// System.out.println("\t3. Drink potion of strength");
// System.out.println("\t4. Run");
// System.out.println("\t5. Exit\n\n");
// System.out
// .println("\tYou have:\n\t"
// + numSmallHealthPotions
// + " small initialHealth potions\n\t"
// + numMediumHealthPotions
// + " medium initialHealth potions\n\t"
// + numLargeHealthPotions
// + " large initialHealth potions\n\t"
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
// initialHealth -= damageTaken;
//
// System.out.println("\t> You strike the " + enemy
// + " for " + damageDealt + " damage.");
// System.out.println("\t> You recieve " + damageTaken
// + " damage.");
//
// if (initialHealth < 1) {
// System.out
// .println("\t> You have taken too much damage, you are dying with pain in
// shits of your enemy while they are celebrating...zombies will have tasty
// dinner! ");
// break;
// }
//
// else if (initialHealth < 30 && initialHealth > 1) {
// System.out
// .println("You look like red tomato raced out by truck! You should go to a
// hospital imidietly!\n\n\n\n\n");
// }
//
// } else if (input.equals("2")) {
// System.out.println("\tWhich one?");
// System.out.println("\t1. Small initialHealth potion.");
// System.out.println("\t2. Medium initialHealth potion.");
// System.out
// .println("\t3. Large initialHealth potion.\n\n\n\n\n");
//
// String vlozene = in.nextLine();
// if (vlozene.equals("1")) {
// if (numSmallHealthPotions > 0) {
// initialHealth += smallHealthPotionHealAmount;
// numSmallHealthPotions--;
// System.out
// .println("\t> You drink a small initialHealth potion, healing yourself for "
// + smallHealthPotionHealAmount
// + "."
// + "\n\t> You now have "
// + initialHealth
// + "HP."
// + "\n\t> You have "
// + numSmallHealthPotions
// + " small initialHealth potions left. \n\n\n\n\n");
//
// } else {
// System.out
// .println("\t> You have no small initialHealth potions left! Defeat enemies for a
// chance to get one!\n\n\n\n\n");
// }
//
// } else if (vlozene.equals("2")) {
// if (numMediumHealthPotions > 0) {
// initialHealth += mediumHealthPotionHealAmount;
// numMediumHealthPotions--;
// System.out
// .println("\t> You drink a medium initialHealth potion, healing yourself for "
// + mediumHealthPotionHealAmount
// + "."
// + "\n\t> You now have "
// + initialHealth
// + "HP."
// + "\n\t> You have "
// + numMediumHealthPotions
// + " medium initialHealth potions left. \n\n\n\n\n");
//
// } else {
// System.out
// .println("\t> You have no medium initialHealth potions left! Defeat enemies for a
// chance to get one!\n\n\n\n\n");
// }
//
// } else if (vlozene.equals("3")) {
// if (numLargeHealthPotions > 0) {
// initialHealth += largeHealthPotionHealAmount;
// numLargeHealthPotions--;
// System.out
// .println("\t> You drink a large initialHealth potion, healing yourself for "
// + largeHealthPotionHealAmount
// + "."
// + "\n\t> You now have "
// + initialHealth
// + "HP."
// + "\n\t> You have "
// + numLargeHealthPotions
// + " large initialHealth potions left. \n\n\n\n\n");
//
// } else {
// System.out
// .println("\t> You have no large initialHealth potions left! Defeat enemies for a
// chance to get one!\n\n\n\\n\n");
// }
//
// } else {
// System.out.println("Invalid command!\n\n\n\n\n");
// }
//
// }
//
// else if (input.equals("3")) {
// if (numPotionOfStrength > 0) {
// numPotionOfStrength--;
// System.out
// .println("You drank a potion of strength");
// System.out
// .println("You feel strong, vitality and full of life!");
// attackDamage += potionOfStrengthPower;
//
// } else {
// System.out
// .println("You have no potion of strength left!");
// }
// } else if (input.equals("4")) {
// // int runEnemyAttackDamage = 25;
// // int runDamageTaken =
// // rand.nextInt(runEnemyAttackDamage);
// // initialHealth -= runDamageTaken;
// // System.out.println("\tThe " + enemy + " hit you for "
// // + runDamageTaken + "!");
// System.out.println("\tYou run away from the " + enemy
// + "!");
// // System.out.println("\tYou now have " + initialHealth +
// // " HP.\n\n\n\n\n");
// System.out.println("\t1. Go on");
// System.out.println("\t2. Create potion");
//
// String lektvar = in.nextLine();
// if (lektvar.equals("1")) {
// continue GAME;
// }
//
// else if (lektvar.equals("2")) {
// System.out.println("Which one?\n");
// System.out.println("You have " + experience
// + "exp.");
// System.out
// .println("\n\t1. Medium initialHealth potion (costs 20 exp)");
// System.out
// .println("\t2. Potion of strength (costs 100 exp)");
// System.out.println("\t3. Exit");
// }
// String potion = in.nextLine();
// if (potion.equals("1")) {
// if (experience >= 10) {
// numMediumHealthPotions++;
// experience -= 10;
// System.out
// .println("You created a medium initialHealth potion! ");
// } else {
// System.out
// .println("Not enough experience to create this type of potion! ");
// }
// }
//
// if (potion.equals("2")) {
// if (experience >= 100) {
// numPotionOfStrength++;
// experience -= 100;
// System.out
// .println("It stings a bit, but looks strong!");
// System.out
// .println("You created a potion of strength!");
// }
//
// else {
// System.out
// .println("Not enough experience to create this type of potion!");
//
// }
//
// if (potion.equals("3")) {
// break;
// }
//
// }
// } else if (input.equals("5")) {
// break;
// }
//
// else {
// System.out.println("\tInvalid command!\n\n\n\\n\n");
//
// }
//
// }
//
// if (initialHealth < 1) {
// System.out
// .println("You limp out of the dungeon, weak from battle.\n\n\n\n\n");
// break;
// }
//
// experience += experienceGain;
//
// if (experienceForVictory <= experience) {
// System.out.println("\tYou earned 300 experience already!");
// System.out.println("\tYOU WON!!!");
// break;
// }
// System.out
// .println("#############################################################################");
// System.out
// .println("#"
// + enemy
// + " was defeated! ");
// System.out.println("# You have " + initialHealth + "HP left ");
// System.out.println("#You have earned " + experienceGain
// + " exp!");
// System.out.println("#You have " + experience
// + " experience! You need" + levelUp
// + "experience for level up.");
// if (rand.nextInt(100) < smallHealthPotionDropChance) {
// numSmallHealthPotions++;
// System.out
// .println("# The "
// + enemy
// + " dropped a small initialHealth potion! ");
// System.out.println("# You have " + numSmallHealthPotions
// + " small initialHealth potion(s). ");
//
// }
//
// if (rand.nextInt(100) < mediumHealthPotionDropChance) {
// numMediumHealthPotions++;
// System.out
// .println("# The "
// + enemy
// + " dropped a medium initialHealth potion! ");
// System.out.println("# You now have "
// + numMediumHealthPotions
// + " medium initialHealth potion(s). ");
//
// }
//
// if (rand.nextInt(100) < largeHealthPotionDropChance) {
// numLargeHealthPotions++;
// System.out
// .println("# The "
// + enemy
// + " dropped a large initialHealth potion! ");
// System.out.println("# You now have "
// + numLargeHealthPotions
// + " large initialHealth potion(s). ");
//
// }
// System.out
// .println("#############################################################################");
// System.out.println("What would you like to do?");
// System.out.println("1. Continue fighting");
// System.out.println("2. Exit dungeon\n\n\n\n\n");
//
// String input = in.nextLine();
//
// while (!input.equals("1") && !input.equals("2")) {
// System.out.println("Invalid command!");
// input = in.nextLine();
//
// }
// if (input.equals("1")) {
// System.out.println("You continue on your adventure!");
//
// } else if (input.equals("2")) {
// System.out
// .println("Yout exit the dungeon, successful from your adventures!");
// break;
// }
//
// }
//
// System.out.println("Thanks for playing!");