package com.example.olderman.dungeon;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

import com.example.olderman.dungeon.inventory.HealthPotion;
import com.example.olderman.dungeon.inventory.Inventory;
import com.example.olderman.dungeon.inventory.InventoryItem;
import com.example.olderman.dungeon.inventory.InventoryItem.Type;
import com.example.olderman.dungeon.shop.Shop;

import static com.example.olderman.dungeon.Style.*;

public class Dungeon {
	public final Random rand = new Random();

	private ForAll forAll;
	private Character character;
	private Shop shop;
	private Inventory inventory;

	public ForAll getForAll() {
		return forAll;
	}

	public Character getCharacter() {
		return character;
	}

	public Shop getShop() {
		return shop;
	}

	public Inventory getInventory() {
		return inventory;
	}

	private final OS os;

	public Dungeon(OS os) {
		this.os = os;
	}

	public void clear() {
		os.clear();
	}

	public int uzivatVolba(String... options) {
		return os.uzivatVolba(options);
	}

	private void print0(Object... text) {
		for (Object o : text) {
			if (o instanceof Style) {
				if (o instanceof ColorStyle) {
					os.color((ColorStyle) o);
				} else if (o instanceof AttributeStyle) {
					os.attribute((AttributeStyle) o);
				} else if (o instanceof Reset) {
					os.reset();
				}
			} else {
				os.print(o.toString());
			}
		}
		os.reset();
	}

	public void print(Object... text) {
		print0(text);
		os.flush();
	}

	public void println(Object... text) {
		print0(text);
		os.println();
		os.flush();
	}

	public void println() {
		os.println();
		os.flush();
	}

	public void printf(String string, Object... args) {
		os.print(String.format(string, args));
		os.flush();
	}

	public void run() throws IOException {
		clear();

		boolean running = true;
		String orc = Resources.getString("/orc");
		String dwarf = Resources.getString("/dwarf");
		String elf = Resources.getString("/elf");
		String superman = Resources.getString("/superman");
		String headLine = Resources.getString("/headline");

		MENU: while (true) {

			HLAVNI_NABIDKA: while (true) {
				println(headLine);
				int volba0 = uzivatVolba("Start", "Help", "Exit");
				switch (volba0) {
				case 1:
					break HLAVNI_NABIDKA;
				case 2:
					// Ramecek.ramecek(Ramecek.data[1][0], "Dwarf");
					println(">Dwarf");
					println("\thas 20% miss chance");
					println(">Orc");
					println("\thas 30% miss chance");
					println(">Elf");
					println("\thas 5% miss chance");
					println(">Superman");
					println("\thas 0% miss chance");
					println();
					println(">Small health potion heals for 20 HP.");
					println(">Medium health potion heals for 50 HP.");
					println(">Large health potion heals for 100 HP.");
					println("\n\n\n");
					uzivatVolba("Return");

					continue MENU;

				case 3:
					return;

				}
			}
			shop = new Shop(this);
			WorkHouse workHouse = new WorkHouse(this);
			InventoryAndInfo inventoryAndInfo = new InventoryAndInfo(this);
			forAll = new ForAll();
			inventory = new Inventory(this);

			println("\n\n\t\tWelcome to the dungeon!\n");
			println("\tYou can play this game however you want to.");
			println("\tYou gain experience and have a small chance of getting some health potions for killing enemies.");
			println("\tGame has 3 differend endings, so try them all for yourself :D!\n\n\n");
			println("\t\tWhich character would you like to play?\n");
			int volba = uzivatVolba("Dwarf", "Orc", "Elf", "Superman");
			// println("\t1. Dwarf");
			// println("\t2. Orc");
			// println("\t3. Elf");
			// println("\t4. Superman\n");
			// println("###############################################################################################################");
			// println();

			// String nabidka = in.nextLine();

			switch (volba) {
			case 1:
				character = new Dwarf(this);
				println(dwarf);
				break;
			case 2:
				character = new Orc(this);
				println(orc);
				break;
			case 3:
				character = new Elf(this);
				println(elf);
				break;
			case 4:
				character = new Superman(this);
				println(superman);
				break;

			}

			println("###############################################################################################################\n");
			println(character.getBeginning());
			RUN: while (running) {

				println("###############################################################################################################\n");
				println("\n\t>You are on floor " + forAll.floor + "!");

				println("\t#You see " + forAll.enemy.nameWithArticle() + "!");
				println("\n\tWhat would you like to do?");
				println();
				forAll.resetDrinkHealthPotionCount();
				while (true) {
					volba = uzivatVolba("Attack", "Go on", "Open inventory and info", "Go in shop", "Go in workhouse",
							"Exit");
					if (volba == 1) { // Attack
						attack();
						break;
					} else if (volba == 2) { // Run
						println("\t>You run!");
						forAll.resetEnemy();
						continue RUN;

						// int runEnemyAttackDamage = 25;
						// int runDamageTaken =
						// rand.nextInt(runEnemyAttackDamage);
						// health -= runDamageTaken;
						// println("\tThe " + enemy + " hit you for "
						// + runDamageTaken + "!");
						// println("\tYou run away from the " + enemy
						// + "!");
						// println("\tYou now have " + health +
						// " HP.\n\n\n\n\n");
						// continue GAME;

					} else if (volba == 3) {// Open inventory and info
						InventoryItem usedItem = inventoryAndInfo.inventoryAndInfo(false);
						if (usedItem != null && usedItem.getType() == Type.WEAPON) {
							break;
						}
					} else if (volba == 4) { // Go in shop
						shop.shop();
					} else if (volba == 5) {
						workHouse.workHouse();
					} else if (volba == 6) { // Exit
						println("Yout exit the dungeon.");
						println("Thanks for playing!");
						continue MENU;
					}
				}
				while (forAll.enemyHealth > 0 && character.getHealth() > 0) {
					println("\tYour HP: " + character.getHealth());
					println("\t" + forAll.enemy.name + "'s HP: " + forAll.enemyHealth);
					println("\n\tWhat would you like to do?");
					println();
					volba = uzivatVolba("Attack", "Run", "Open inventory and info");
					if (volba == 1) { // Attack
						attack();
					} else if (volba == 2) { // Run
						if (forAll.numPotionOfInvisibility == 0) {
							println("\t> No time to run!\n");
						} else {
							println("\t>You run!");
							forAll.resetEnemy();
							forAll.numPotionOfInvisibility--;
							forAll.resetDrinkHealthPotionCount();
							continue RUN;

						}
						// int runEnemyAttackDamage = 25;
						// int runDamageTaken =
						// rand.nextInt(runEnemyAttackDamage);
						// health -= runDamageTaken;
						// println("\tThe " + enemy + " hit you for "
						// + runDamageTaken + "!");
						// println("\tYou run away from the " + enemy
						// + "!");
						// println("\tYou now have " + health +
						// " HP.\n\n\n\n\n");
						// continue GAME;

					} else if (volba == 3) {// Open inventory and info
						inventoryAndInfo.inventoryAndInfo(true);
					}

				}

				if (character.getHealth() < 1) {
					println("\t> You limp out of the dungeon, wounded from the battle.\n\n\n\n\n");
					continue MENU;
				}

				forAll.experience += forAll.experienceGain;
				forAll.floor++;
				forAll.enemyMissChance = forAll.enemyMissChance * 2 / 3;

				if (forAll.experience >= forAll.levelUp) {
					forAll.levelUp += 50;
					forAll.level++;
					println("\t************************************************");
					println("\t*Congratulations! Level Up! Level " + forAll.level + "!");
					println("\t************************************************");
					println("\tYour " + character.getMaximumHealth() + " + " + forAll.levelUpHealth
							+ " maximum health.");
					println("\tYour " + character.getAttackDamage() + " + " + forAll.levelUpDamage + " damage.");
					character.increaseMaximumHealth(10 * forAll.floor);
					character.increaseHealth(forAll.levelUpHealth);
					character.setHealth(character.getHealth() + forAll.levelUpHealth);

				}

				if (forAll.level >= 10) {
					println("\tYOU WON!!!");
					continue MENU;
				}

				int goldFound = (forAll.getGoldFound() + forAll.getGoldFound()) + forAll.floor * 20;
				character.increaseGold(goldFound);
				println("\n#############################################################################\n");
				println("# " + forAll.enemy.name + " was defeated!                                                ");
				println("# You have " + character.getHealth() + "HP left ");
				println("# You have earned " + forAll.experienceGain + " exp!");
				println("# You have " + forAll.experience + " experience! You need " + forAll.levelUp
						+ " experience for level up.");
				println("# You have level " + forAll.level + "!");
				println("# You found " + goldFound + " gold (" + forAll.gold + " gold total)");
				if (rand.nextInt(100) < forAll.smallHealthPotionDropChance
						|| rand.nextInt(100) <= character.getLuck()) {
					inventory.add(HealthPotion.SMALL);
					println("# The " + forAll.enemy.name + " dropped a small health potion! ("
							+ inventory.getCount(HealthPotion.SMALL) + " total)");

				}

				if (rand.nextInt(100) < forAll.mediumHealthPotionDropChance
						|| rand.nextInt(100) <= character.getLuck()) {
					inventory.add(HealthPotion.MEDIUM);
					println("# The " + forAll.enemy.name + " dropped a medium health potion! ("
							+ inventory.getCount(HealthPotion.MEDIUM) + " total)");

				}

				if (rand.nextInt(100) < forAll.largeHealthPotionDropChance
						|| rand.nextInt(100) <= character.getLuck()) {
					inventory.add(HealthPotion.LARGE);
					println("# The " + forAll.enemy.name + " dropped a large health potion! ("
							+ inventory.getCount(HealthPotion.LARGE) + " total)");

				}

				forAll.experience += forAll.experienceGain;

				if (character.getExperienceForVictory() <= forAll.experience) {
					println("\tYour keyboard still isn't broken from smashing those two buttons?");
					println("\tYOU WON!!!");
					println("\tBut your keyboard died in the last fight.");
					println("RIP keyboard " + DateFormat.getDateInstance().format(new Date()));
					continue MENU;
				}
				// println("#############################################################################");
				// println("#" + forAll.enemy +
				// " was defeated! ");
				// println("# You have " + character.getHealth() + "HP left ");
				// println("#You have earned " + forAll.experienceGain +
				// " exp!");
				// println("#You now have " + forAll.experience +
				// " experience!");
				// if (rand.nextInt(100) < forAll.smallHealthPotionDropChance) {
				// forAll.numSmallHealthPotions++;
				// println("# The " + forAll.enemy +
				// " dropped a small health potion! ");
				// println("# You now have " + forAll.numSmallHealthPotions +
				// " small health potion(s). ");
				//
				// }
				//
				// if (rand.nextInt(100) < forAll.mediumHealthPotionDropChance)
				// {
				// forAll.numMediumHealthPotions++;
				// println("# The " + forAll.enemy +
				// " dropped a medium health potion! ");
				// println("# You now have " + forAll.numMediumHealthPotions +
				// " medium health potion(s). ");
				//
				// }
				//
				// if (rand.nextInt(100) < forAll.largeHealthPotionDropChance) {
				// forAll.numLargeHealthPotions++;
				// println("# The " + forAll.enemy +
				// " dropped a large health potion! ");
				// println("# You now have " + forAll.numLargeHealthPotions +
				// " large health potion(s). ");
				//
				// }
				println("\n#############################################################################\n");
				println("What would you like to do?");

				volba = uzivatVolba("Go to next floor", "Exit dungeon");

				if (volba == 1) {
					println("You continue on your adventure!");
					forAll.resetEnemy();
				} else {
					println("Yout exit the dungeon, successful from your adventures!");
					println("Thanks for playing!");
					continue MENU;
				}

			}

		}
	}

	private void attack() {
		int damageDealt = (rand.nextInt(character.getAttackDamage()) + rand.nextInt(character.getAttackDamage())
				+ forAll.level * 5 + character.getFlatDamage());
		int damageTaken = ((rand.nextInt(forAll.enemyAttackDamage) + rand.nextInt(forAll.enemyAttackDamage))
				+ forAll.floor * 5 + 10) * forAll.resistence / 100;

		boolean youMiss = rand.nextInt(100) <= character.getMissChance();
		boolean enemyMiss = rand.nextInt(100) <= forAll.enemyMissChance;

		if (youMiss) {
			println("\tYou MISS!");
		}
		if (enemyMiss) {
			println("\tEnemy MISS!");
		}

		if (!youMiss) {
			forAll.enemyHealth -= damageDealt;

			if (damageDealt == character.getAttackDamage()) {
				println("WOOOW, excelent hit!!!");
			}
			println("\t> You strike the " + forAll.enemy.name + " for " + damageDealt + " damage.");
		}

		if (!enemyMiss) {
			println("\t> You recieve " + damageTaken + " damage.");
			character.decreaseHealth(damageTaken);
		}

		if (character.getHealth() < 1) {
			println("\t> You have taken too much damage, you are dying in pain covered in the shit of your enemy while they are celebrating...zombies will a have tasty dinner! ");
		} else if (character.getHealth() < 30) {
			println("\n\t!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@\n");
			println("\t> <ALERT>Your HP is very low " + "(" + character.getHealth() + " HP left)");
			println("\n\t!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@\n");
		}
		forAll.resetDrinkHealthPotionCount();

	}

}