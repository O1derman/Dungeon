package com.example.olderman.dungeon;

import com.example.olderman.dungeon.characters.Dwarf;
import com.example.olderman.dungeon.characters.Elf;
import com.example.olderman.dungeon.characters.Orc;
import com.example.olderman.dungeon.characters.Goblin;
import com.example.olderman.dungeon.inventory.HealthPotion;
import com.example.olderman.dungeon.inventory.Inventory;
import com.example.olderman.dungeon.inventory.InventoryItem;
import com.example.olderman.dungeon.inventory.InventoryItem.Type;
import com.example.olderman.dungeon.map.Way;
import com.example.olderman.dungeon.town.Town;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

import static com.example.olderman.dungeon.Style.AttributeStyle;
import static com.example.olderman.dungeon.Style.ColorStyle;
import static com.example.olderman.dungeon.Style.DEFAULT_COLOR;
import static com.example.olderman.dungeon.Style.GREEN;
import static com.example.olderman.dungeon.Style.RED;
import static com.example.olderman.dungeon.Style.Reset;
import static com.example.olderman.dungeon.Style.YELLOW;

public class Dungeon {
	public final Random rand = new Random();
	private ForAll forAll;
	private AbstractCharacter character;
	private Inventory inventory;

	public ForAll getForAll() {
		return forAll;
	}

	public AbstractCharacter getCharacter() {
		return character;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void increaseMaximumHealth(int maximumHealthIncrease) {
		forAll.maximumHealth += maximumHealthIncrease;
	}

	public int getHealth() {
		return forAll.health;
	}

	public void setHealth(int health) {
		forAll.health = Math.min(health, forAll.maximumHealth);
	}

	public void decreaseHealth(int healthDecrease) {
		setHealth(getHealth() - healthDecrease);
	}

	public void increaseHealth(int healthIncrease) {
		setHealth(getHealth() + healthIncrease);
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

	public void printAsciiArt(String asciiArt) {
		os.printAsciiArt(asciiArt);
		os.flush();
	}

	public void run() {
		clear();

		String orc = Resources.getString("/orc");
		String dwarf = Resources.getString("/dwarf");
		String elf = Resources.getString("/elf");
		// String superman = Resources.getString("/superman");
		String headLine = Resources.getString("/headline");
		String goblin = Resources.getString("/goblin");
		boolean running = true;

		MENU: while (running) {

			HLAVNI_NABIDKA: while (running) {
				printAsciiArt(headLine);
				println();
				int volba0 = uzivatVolba("Start", "Help", "Exit");
				switch (volba0) {
				case 1:
					break HLAVNI_NABIDKA;
				case 2:
					// Ramecek.ramecek(Ramecek.data[1][0], "Dwarf");
					println(">Orc");
					println("\thas 30% miss chance");
					println("\tcan carry 500kg");
					println(">Dwarf");
					println("\thas 20% miss chance");
					println("\tcan carry 80kg");
					println("\thas cheaper axes in store");
					println(">Goblin");
					println("\thas 10% miss chance");
					println("\tcan carry 50kg");
					println(">Elf");
					println("\thas 5% miss chance");
					println("\tcan carry 30kg");
					// println(">Superman");
					// println("\thas 0% miss chance");
					println();
					println(">Small health potion heals for 20 HP.");
					println(">Medium health potion heals for 50 HP.");
					println(">Large health potion heals for 100 HP.");
					println("\n\n\n");
					uzivatVolba("Back");

					continue MENU;

				case 3:
					return;

				}
			}
			Town town = new Town(this);
			Way way = new Way(this);
			InventoryAndInfo inventoryAndInfo = new InventoryAndInfo(this);
			forAll = new ForAll();
			inventory = new Inventory(this);

			println("\n\n\t\tWelcome to the dungeon!\n");
			println("\tYou can play this game however you want to.");
			println("\tYou gain experience and have a small chance of getting some health potions for killing enemies.");
			println("\tGame has 3 differend endings, so try them all for yourself :D!\n\n\n");
			println("\t\tWhich character would you like to play?\n");
			int volba = uzivatVolba("Dwarf", "Orc", "Elf",
					// "Superman",
					"Goblin", "Back");

			switch (volba) {
			case 1:
				character = new Dwarf();
				printAsciiArt(dwarf);
				println();
				break;
			case 2:
				character = new Orc();
				printAsciiArt(orc);
				println();
				break;
			case 3:
				character = new Elf();
				printAsciiArt(elf);
				println();
				break;
			// case 4:
			// character = new Superman();
			// printAsciiArt(superman);
			// println();
			// break;
			case 4:
				character = new Goblin();
				printAsciiArt(goblin);
				println();
				break;
			case 5:
				continue MENU;

			}

			forAll.health = character.getInitialHealth();
			forAll.maximumHealth = character.getInitialMaximumHealth();

			println("###############################################################################################################\n");
			println(character.getBeginning());
			FIGHT: while (running) {

				println("###############################################################################################################\n");
				println("\n\t>You are on floor " + forAll.floor + "!");

				println("\t#You see " + forAll.enemy.nameWithArticle() + "!");
				println("\n\tWhat would you like to do?");
				println();
				forAll.resetDrinkHealthPotionCount();
				while (running) {
					volba = uzivatVolba("Attack", "Go on", "Open inventory and info", "Go in town", "Exit");
					if (volba == 1) { // Attack
						attack();
						break;
					} else if (volba == 2) { // Go on
						way.way();
						forAll.resetEnemy();

					} else if (volba == 3) { // Open inventory and info
						InventoryItem usedItem = inventoryAndInfo.inventoryAndInfo(false);
						if (usedItem != null && usedItem.getType() == Type.WEAPON) {
							break;
						}

					} else if (volba == 4) { // Go in town
						town.town();
						continue FIGHT;
					} else if (volba == 5) { // Exit
						println("Yout exit the dungeon.");
						println("Thanks for playing!");
						continue MENU;
					}
				}
				while (forAll.enemyHealth > 0 && getHealth() > 0) {
					println("\tYour HP: " + getHealth());
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
							println("\t Do you reall want to run? It will cost you potion of invisibility!");
							int volbaRun = uzivatVolba("Yes", "No");
							switch (volbaRun) {
							case 1:
								println("\t>You run!");
								forAll.resetEnemy();
								forAll.numPotionOfInvisibility--;
								forAll.resetDrinkHealthPotionCount();
								continue FIGHT;
							case 2:
								break;

							}

						}
					} else if (volba == 3) {// Open inventory and info
						inventoryAndInfo.inventoryAndInfo(true);
					}

				}

				if (getHealth() < 1) {
					println("\t> You limp out of the dungeon, wounded from the battle.\n\n\n\n\n");
					continue MENU;
				}

				forAll.experience += forAll.experienceGain;
				forAll.floor++;
				forAll.enemyMissChance = forAll.enemyMissChance * 2 / 3;

				if (forAll.experience >= forAll.levelUp) {
					forAll.levelUp += 50;
					forAll.level++;
					println(GREEN.BRIGHT, "\t************************************************");
					println(GREEN.BRIGHT, "\t*Congratulations! Level Up! Level " + forAll.level + "!");
					println(GREEN.BRIGHT, "\t************************************************");
					increaseMaximumHealth(ForAll.LEVEL_UP_HEALTH);
					increaseHealth(ForAll.LEVEL_UP_HEALTH);

					println("\tYour maximum health is " + forAll.maximumHealth + "HP.");
					println("\tYour minimum damage is " + character.getDamage().minValue(this) + ".");
					println("\tYour maximum damage is " + character.getDamage().maxValue(this) + ".");
				}

				if (forAll.level >= 10) {
					println("\tYOU WON!!!");
					continue MENU;
				}

				int goldFound = (rand.nextInt(100) + rand.nextInt(100)) + forAll.floor * 20;
				forAll.gold += goldFound;
				println("\n#############################################################################\n");
				println("# " + forAll.enemy.name + " was defeated!                                                ");
				println("# You have ", RED.BRIGHT, getHealth() + "HP", DEFAULT_COLOR, " left ");
				println("# You have earned ", GREEN.BRIGHT, forAll.experienceGain + " exp", DEFAULT_COLOR, "!");
				println("# You have ", GREEN.BRIGHT, forAll.experience + " experience", DEFAULT_COLOR,
						"! You need " + forAll.levelUp + " experience for level up.");
				println("# You have level " + forAll.level + "!");
				println("# You found ", YELLOW.BRIGHT, goldFound + " gold", DEFAULT_COLOR, " (", YELLOW.BRIGHT,
						forAll.gold + " gold", DEFAULT_COLOR, " total)");
				if (rand.nextInt(100) < ForAll.SMALL_HEALTH_POTION_DROP_CHANCE
						|| rand.nextInt(100) <= character.getLuck()) {
					inventory.add(HealthPotion.SMALL);
					println("# The " + forAll.enemy.name + " dropped a small health potion! ("
							+ inventory.getCount(HealthPotion.SMALL) + " total)");

				}

				if (rand.nextInt(100) < ForAll.MEDIUM_HEALTH_POTION_DROP_CHANCE
						|| rand.nextInt(100) <= character.getLuck()) {
					inventory.add(HealthPotion.MEDIUM);
					println("# The " + forAll.enemy.name + " dropped a medium health potion! ("
							+ inventory.getCount(HealthPotion.MEDIUM) + " total)");

				}

				if (rand.nextInt(100) < ForAll.LARGE_HEALTH_POTION_DROP_CHANCE
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
				println("\n#############################################################################\n");
				println("What would you like to do?");

				volba = uzivatVolba("Go to next floor", "Exit dungeon");

				if (volba == 1) {
					println("You continue on your adventure!");
					forAll.resetEnemy();
				} else {
					println("You exit the dungeon, successful from your adventures!");
					println("Thanks for playing!");
					continue MENU;
				}

			}

		}
	}

	private void attack() {
		boolean youMiss = rand.nextInt(100) <= character.getMissChance();
		boolean enemyMiss = rand.nextInt(100) <= forAll.enemyMissChance;

		if (youMiss) {
			println("\tYou MISS!");
		}
		if (enemyMiss) {
			println("\tEnemy MISS!");
		}

		if (!youMiss) {
			int damageDealt = forAll.selectedWeapon.calculateDamage(this);
			println("\t> You strike the " + forAll.enemy.name + " for " + damageDealt + " damage.");
			forAll.enemyHealth -= damageDealt;
		}

		if (!enemyMiss) {
			int damageTaken = ((rand.nextInt(forAll.enemyAttackDamage) + rand.nextInt(forAll.enemyAttackDamage))
					+ forAll.floor * 5 + 10) * forAll.resistence / 100;

			println("\t> You receive " + damageTaken + " damage.");
			decreaseHealth(damageTaken);
		}

		if (getHealth() < 1) {
			println("\t> You have taken too much damage, you are dying in pain covered in the shit of your enemy while they are celebrating...zombies will a have tasty dinner! ");
		} else if (getHealth() < 30) {
			println(RED.BRIGHT, "\n\t!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@\n");
			println(RED.BRIGHT, "\t> <ALERT>Your HP is very low " + "(" + getHealth() + " HP left)");
			println(RED.BRIGHT, "\n\t!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@!@\n");
		}
		forAll.resetDrinkHealthPotionCount();

	}

}