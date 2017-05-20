package com.example.olderman.dungeon;

import com.example.olderman.dungeon.inventory.Bomb;

public class Shop {

	public Shop(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	private Character character = null;
	private final Dungeon dungeon;

	int maletCost = 500;
	int maletDamage = 15;
	int oneHandAxeCost = 600;
	int oneHandAxeDamage = 20;
	int twoHandAxeCost = 800;
	int twoHandAxeDamage = 40;
	int bombCost = 400;
	public int bombBoomMinDamage = 100;
	public int bombBoomMaxDamage = 300;
	public int bombRangeDamage = bombBoomMaxDamage - bombBoomMinDamage;
	int hamburgerCost = 1000;
	int hamburgerResist = 65;// %
	int knifeCost = 200;

	public void shop() {

		boolean running = true;

		dungeon.println("\tWelcome to a weapon shop \"Broken axe\"!");
		dungeon.println("\tWe have some great offers for you!");
		dungeon.println("\tI know you will choose visely!");

		while (running) {
			int volba = dungeon.uzivatVolba("Look around!", "Leave!");

			if (volba == 1) {
				dungeon.println("You have " + dungeon.getForAll().gold + " gold.");
				dungeon.println("\n\tWhat will you get?");

				int nabidkaObchodu = dungeon.uzivatVolba("malet -> gives you 15 more damge!...costs 500 Gold.", //
						"onehand axe -> gives you 20 more damage!...costs 600 Gold.", //
						"twohand axe -> gives you 40 more damage!...costs 800 Gold.", //
						"bomb -> deals 100-300 damage! (only for 1 use!...costs 400 Gold.", //
						"hamburger -> reduces enemy damage for 35%...costs " + hamburgerCost + " Gold", //
						"Exit");

				if (nabidkaObchodu == 1) {
					if (buyWithGold(maletCost)) {
						dungeon.println("\tYou bought a malet!");
						character.increaseAttackDamage(maletDamage);
						dungeon.println("\tYou now have " + character.getAttackDamage() + " attack damage!");

					} else {
						dungeon.println("\tYou don't have enough gold to buy a malet!");

					}
				} else if (nabidkaObchodu == 2) {
					if (buyWithGold(oneHandAxeCost)) {
						dungeon.println("\tYou bought an one hand axe!");
						character.increaseAttackDamage(oneHandAxeDamage);
						dungeon.println("\tYou now have " + character.getAttackDamage() + " attack damage!");

					} else {
						dungeon.println("\tYou don't have enough gold to buy a one handed axe!");

					}
				} else if (nabidkaObchodu == 3) {
					if (buyWithGold(twoHandAxeCost)) {
						dungeon.println("\tYou bought an two hand axe!");
						character.increaseAttackDamage(twoHandAxeDamage);
						dungeon.println("\tYou now have " + character.getAttackDamage() + " attack damage!");

					} else {
						dungeon.println("\tYou don't have enough gold to buy a two handed axe!");

					}
				} else if (nabidkaObchodu == 4) {
					if (buyWithGold(bombCost)) {
						dungeon.println("\tYou bought a bomb!");
						dungeon.getInventory().add(Bomb.BOMB);
						dungeon.println("\tYou now have " + dungeon.getInventory().getCount(Bomb.BOMB)
								+ " bomb(s) in your inventory!");

					} else {
						dungeon.println("\tYou don't have enough gold to buy a bomb!");

					}
				} else if (nabidkaObchodu == 5) {
					if (buyWithGold(hamburgerCost)) {
						dungeon.println("\tYou bought a hamburger!");
						dungeon.getForAll().resistence = dungeon.getForAll().resistence * hamburgerResist / 100;
						dungeon.println("\tEnemies now deal only " + dungeon.getForAll().resistence + "% damage!");

						hamburgerCost = hamburgerCost * 3 / 2;
						dungeon.println("\tHamburger now costs " + hamburgerCost);

					} else {
						dungeon.println("\tYou don't have enough gold to buy a Hamburger!");

					}

				} else if (nabidkaObchodu == 6) {
					break;
				} else {
					dungeon.println("Invalid command");
				}
			} else if (volba == 2) {
				break;
			} else {
				dungeon.println("Invalid command");
			}

		}
	}

	public boolean buyWithGold(int cost) {
		if (cost > dungeon.getForAll().gold)
			return false;
		dungeon.getForAll().gold -= cost;
		return true;
	}

}
