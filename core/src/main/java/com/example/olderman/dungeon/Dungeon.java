package com.example.olderman.dungeon;

import com.example.olderman.dungeon.characters.Dwarf;
import com.example.olderman.dungeon.characters.Elf;
import com.example.olderman.dungeon.characters.Orc;
import com.example.olderman.dungeon.enemies.Boss1;
import com.example.olderman.dungeon.enemies.Boss2;
import com.example.olderman.dungeon.enemies.Plebs;
import com.example.olderman.dungeon.characters.Goblin;
import com.example.olderman.dungeon.inventory.HealthPotion;
import com.example.olderman.dungeon.inventory.Inventory;
import com.example.olderman.dungeon.inventory.InventoryItem;
import com.example.olderman.dungeon.inventory.InventoryItem.Type;
import com.example.olderman.dungeon.map.Room;
import com.example.olderman.dungeon.map.Way;
import com.example.olderman.dungeon.town.Town;

import java.util.Random;

import static com.example.olderman.dungeon.Style.AttributeStyle;
import static com.example.olderman.dungeon.Style.ColorStyle;
import static com.example.olderman.dungeon.Style.DEFAULT_COLOR;
import static com.example.olderman.dungeon.Style.GREEN;
import static com.example.olderman.dungeon.Style.RED;
import static com.example.olderman.dungeon.Style.Reset;
import static com.example.olderman.dungeon.Style.YELLOW;

public class Dungeon {
	private final Random rand = new Random();
	private ForAll forAll;
	private Plebs plebs;
	private Room room;

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	private AbstractCharacter character;
	private Inventory inventory;

	public Random getRand() {
		return rand;
	}

	public void setPlebs(Plebs plebs) {
		this.plebs = plebs;
	}

	public void setCharacter(AbstractCharacter character) {
		this.character = character;
	}

	public Plebs getPlebs() {
		return plebs;
	}

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
			Boss1 boss1 = new Boss1(this);
			Boss2 boss2 = new Boss2(this);
			plebs = new Plebs();
			Way way = new Way(this);
			InventoryAndInfo inventoryAndInfo = new InventoryAndInfo(this);
			forAll = new ForAll();
			inventory = new Inventory(this);
			Room room = new Room(way, this);

			println("\n\n\t\tWelcome to the dungeon!\n");
			println("\tYou gain experience and have a small chance of getting some health potions for killing enemies.");
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

			println("###############################################################################################################\n");
			println(character.getBeginning());
			FIGHT: while (running) {
				boolean attack = true;
				boolean freeRoom = true;
				forAll.resetDrinkHealthPotionCount();
				if (way.map.w == 1 && way.map.l == 1 && room.room11 > 0) {
					attack = false;

				}
				if (way.map.w == 1 && way.map.l == 2 && room.room12 > 0) {
					attack = false;

				}
				if (way.map.w == 1 && way.map.l == 3 && room.room13 > 0) {
					attack = false;

				}
				if (way.map.w == 1 && way.map.l == 4 && room.room14 > 0) {
					attack = false;

				}
				if (way.map.w == 1 && way.map.l == 5 && room.room15 > 0) {
					attack = false;

				}
				if (way.map.w == 2 && way.map.l == 1 && room.room21 > 0) {
					attack = false;

				}
				if (way.map.w == 2 && way.map.l == 2 && room.room22 > 0) {
					attack = false;

				}
				if (way.map.w == 2 && way.map.l == 3 && room.room23 > 0) {
					attack = false;

				}
				if (way.map.w == 2 && way.map.l == 4 && room.room24 > 0) {
					attack = false;

				}
				if (way.map.w == 2 && way.map.l == 5 && room.room25 > 0) {
					attack = false;

				}
				if (way.map.w == 3 && way.map.l == 1 && room.room31 > 0) {
					attack = false;

				}
				if (way.map.w == 3 && way.map.l == 2 && room.room32 > 0) {
					attack = false;

				}
				if (way.map.w == 3 && way.map.l == 3 && room.room33 > 0) {
					attack = false;

				}
				if (way.map.w == 3 && way.map.l == 4 && room.room34 > 0) {
					attack = false;

				}
				if (way.map.w == 3 && way.map.l == 5 && room.room35 > 0) {
					attack = false;

				}
				if (way.map.w == 4 && way.map.l == 1 && room.room41 < 0) {
					attack = false;

				}
				if (way.map.w == 4 && way.map.l == 2 && room.room42 > 0) {
					attack = false;

				}
				if (way.map.w == 4 && way.map.l == 3 && room.room43 > 0) {
					attack = false;

				}
				if (way.map.w == 4 && way.map.l == 4 && room.room44 > 0) {
					attack = false;

				}
				if (way.map.w == 4 && way.map.l == 5 && room.room45 > 0) {
					attack = false;

				}
				if (way.map.w == 5 && way.map.l == 1 && room.room51 > 0) {
					attack = false;

				}
				if (way.map.w == 5 && way.map.l == 2 && room.room52 > 0) {
					attack = false;

				}
				if (way.map.w == 5 && way.map.l == 3 && room.room53 > 0) {
					attack = false;

				}
				if (way.map.w == 5 && way.map.l == 4 && room.room54 > 0) {
					attack = false;

				}
				if (way.map.w == 5 && way.map.l == 5 && room.room55 > 0) {
					attack = false;

				} else
					freeRoom = false;
				room.room();

				while (freeRoom) {
					volba = uzivatVolba("Go on", "Open inventory and info", "Go in town", "Exit");
					if (volba == 1) { // Go on
						way.way();
						plebs.resetEnemy();
						continue FIGHT;
					} else if (volba == 2) { // Open inventory and info
						InventoryItem usedItem = inventoryAndInfo.inventoryAndInfo(false);
						if (usedItem != null && usedItem.getType() == Type.WEAPON) {
							break;
						}

					} else if (volba == 3) { // Go in town
						town.town();
						continue FIGHT;
					} else if (volba == 4) { // Exit
						println("Yout exit the dungeon.");
						println("Thanks for playing!");
						continue MENU;
					}

				}
				while (attack) {
					volba = uzivatVolba("Attack", "Go back", "Open inventory and info", "Go in town", "Exit");
					if (volba == 1) { // Attack
						if (way.map.l == way.map.rightEdge && way.map.w == way.map.w1 && forAll.floor == 1) {
							boss1.boss1Fight();
						} else if (way.map.l == way.map.rightEdge && way.map.w == way.map.w1 && forAll.floor == 2) {
							boss2.boss2Fight();
						} else {
							plebFight();
						}
						break;
					} else if (volba == 2) { // Go on
						way.way();
						plebs.resetEnemy();
						continue FIGHT;
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
				while (plebs.enemyHealth > 0 && getHealth() > 0) {
					println("\tYour HP: " + getHealth());
					println("\t" + plebs.enemy.name + "'s HP: " + plebs.enemyHealth);
					println("\n\tWhat would you like to do?");
					println();
					volba = uzivatVolba("Attack", "Run", "Open inventory and info");
					if (volba == 1) { // Attack
						plebFight();
					} else if (volba == 2) { // Run
						if (forAll.numPotionOfInvisibility == 0) {
							println("\t> No time to run!\n");
						} else {
							println("\t Do you reall want to run? It will cost you potion of invisibility!");
							int volbaRun = uzivatVolba("Yes", "No");
							switch (volbaRun) {
							case 1:
								println("\t>You run!");
								plebs.resetEnemy();
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
				if (way.map.w == 1 && way.map.l == 1) {
					room.room11++;
				}
				if (way.map.w == 1 && way.map.l == 2) {
					room.room12++;
				}
				if (way.map.w == 1 && way.map.l == 3) {
					room.room13++;
				}
				if (way.map.w == 1 && way.map.l == 4) {
					room.room14++;
				}
				if (way.map.w == 1 && way.map.l == 5) {
					room.room15++;
				}
				if (way.map.w == 2 && way.map.l == 1) {
					room.room21++;
				}
				if (way.map.w == 2 && way.map.l == 2) {
					room.room22++;
				}
				if (way.map.w == 2 && way.map.l == 3) {
					room.room23++;
				}
				if (way.map.w == 2 && way.map.l == 4) {
					room.room24++;
				}
				if (way.map.w == 2 && way.map.l == 5) {
					room.room25++;
				}
				if (way.map.w == 3 && way.map.l == 1) {
					room.room31++;
				}
				if (way.map.w == 3 && way.map.l == 2) {
					room.room32++;
				}
				if (way.map.w == 3 && way.map.l == 3) {
					room.room33++;
				}
				if (way.map.w == 3 && way.map.l == 4) {
					room.room34++;
				}
				if (way.map.w == 3 && way.map.l == 5) {
					room.room35++;
				}
				if (way.map.w == 4 && way.map.l == 1) {
					room.room41++;
				}
				if (way.map.w == 4 && way.map.l == 2) {
					room.room42++;
				}
				if (way.map.w == 4 && way.map.l == 3) {
					room.room43++;
				}
				if (way.map.w == 4 && way.map.l == 4) {
					room.room44++;
				}
				if (way.map.w == 4 && way.map.l == 5) {
					room.room45++;
				}
				if (way.map.w == 5 && way.map.l == 1) {
					room.room51++;
				}
				if (way.map.w == 5 && way.map.l == 2) {
					room.room52++;
				}
				if (way.map.w == 5 && way.map.l == 3) {
					room.room53++;
				}
				if (way.map.w == 5 && way.map.l == 4) {
					room.room54++;
				}
				if (way.map.w == 5 && way.map.l == 5) {
					room.room55++;
				}
				forAll.experience += plebs.experienceGain;
				plebs.enemiesKilled++;
				plebs.enemyMissChance = plebs.enemyMissChance * 2 / 3;

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

				int goldFound = (rand.nextInt(100) + rand.nextInt(100)) + plebs.enemiesKilled * 20;
				forAll.gold += goldFound;
				println("\n#############################################################################\n");
				println("# " + plebs.enemy.name + " was defeated!                                                ");
				println("# You have ", RED.BRIGHT, getHealth() + "HP", DEFAULT_COLOR, " left ");
				println("# You have earned ", GREEN.BRIGHT, plebs.experienceGain + " exp", DEFAULT_COLOR, "!");
				println("# You have ", GREEN.BRIGHT, forAll.experience + " experience", DEFAULT_COLOR,
						"! You need " + forAll.levelUp + " experience for level up.");
				println("# You have level " + forAll.level + "!");
				println("# You found ", YELLOW.BRIGHT, goldFound + " gold", DEFAULT_COLOR, " (", YELLOW.BRIGHT,
						forAll.gold + " gold", DEFAULT_COLOR, " total)");
				if (rand.nextInt(100) < ForAll.SMALL_HEALTH_POTION_DROP_CHANCE
						|| rand.nextInt(100) <= character.getLuck()) {
					inventory.add(HealthPotion.SMALL);
					println("# The " + plebs.enemy.name + " dropped a small health potion! ("
							+ inventory.getCount(HealthPotion.SMALL) + " total)");

				}

				if (rand.nextInt(100) < ForAll.MEDIUM_HEALTH_POTION_DROP_CHANCE
						|| rand.nextInt(100) <= character.getLuck()) {
					inventory.add(HealthPotion.MEDIUM);
					println("# The " + plebs.enemy.name + " dropped a medium health potion! ("
							+ inventory.getCount(HealthPotion.MEDIUM) + " total)");

				}

				if (rand.nextInt(100) < ForAll.LARGE_HEALTH_POTION_DROP_CHANCE
						|| rand.nextInt(100) <= character.getLuck()) {
					inventory.add(HealthPotion.LARGE);
					println("# The " + plebs.enemy.name + " dropped a large health potion! ("
							+ inventory.getCount(HealthPotion.LARGE) + " total)");

				}

				forAll.experience += plebs.experienceGain;

				println("\n#############################################################################\n");

				volba = uzivatVolba("Continue");

				if (volba == 1) {
					plebs.resetEnemy();

				}

			}

		}
	}

	private void plebFight() {
		boolean youMiss = rand.nextInt(100) <= character.getMissChance();
		boolean enemyMiss = rand.nextInt(100) <= plebs.enemyMissChance;

		if (youMiss) {
			println("\tYou MISS!");
		}
		if (enemyMiss) {
			println("\tEnemy MISS!");
		}

		if (!youMiss) {
			int damageDealt = forAll.selectedWeapon.calculateDamage(this);
			println("\t> You strike the " + plebs.enemy.name + " for " + damageDealt + " damage.");
			plebs.enemyHealth -= damageDealt;
		}

		if (!enemyMiss) {
			int damageTaken = ((rand.nextInt(plebs.enemyAttackDamage) + rand.nextInt(plebs.enemyAttackDamage))
					+ plebs.enemiesKilled * 5 + 10) * forAll.resistence / 100;

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