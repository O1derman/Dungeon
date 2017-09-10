package olderman.dungeon;

import olderman.dungeon.Style.ColorStyle;
import olderman.dungeon.enemies.Boss1;
import olderman.dungeon.enemies.Boss2;
import olderman.dungeon.enemies.Plebs;
import olderman.dungeon.inventory.HealthPotion;
import olderman.dungeon.inventory.Inventory;
import olderman.dungeon.map.Room;
import olderman.dungeon.map.Way;
import olderman.dungeon.town.Town;

import static olderman.dungeon.Style.AttributeStyle;
import static olderman.dungeon.Style.BLUE;
import static olderman.dungeon.Style.DEFAULT_COLOR;
import static olderman.dungeon.Style.GREEN;
import static olderman.dungeon.Style.RED;
import static olderman.dungeon.Style.Reset;
import static olderman.dungeon.Style.YELLOW;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

public class Dungeon implements Serializable {
	private static final long serialVersionUID = 1L;

	// ascii art
	private static final String HEADLINE = Resources.getString("/Headline");
	// fields
	private final Random rand = new Random();
	public int score;
	private ForAll forAll;
	private Plebs plebs;
	private GameCharacter character;
	private Inventory inventory;
	private Town town;
	private Boss1 boss1 = new Boss1(this);
	private Boss2 boss2;
	private Way way;
	private Room room;

	// getters
	public Random getRand() {
		return rand;
	}

	public Plebs getPlebs() {
		return plebs;
	}

	public ForAll getForAll() {
		return forAll;
	}

	public GameCharacter getCharacter() {
		return character;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Way getWay() {
		return way;
	}

	public Town getTown() {
		return town;
	}

	public Boss1 getBoss1() {
		return boss1;
	}

	public Boss2 getBoss2() {
		return boss2;
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

	public Dungeon(OS os) {
		this.os = os;
	}

	// os
	private final OS os;

	public void beep() {
		os.beep();
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

	public void printEmptyLines(int count) {
		for (int i = 0; i < count; i++)
			os.println();
		os.flush();
	}

	public void fillLine(ColorStyle bRIGHT, String text) {
		os.fillLine(text);
		os.flush();
	}

	public void println() {
		os.println();
		os.flush();
	}

	public void printAsciiArt(String asciiArt) {
		os.printAsciiArt(asciiArt);
		os.flush();
	}

	// Serialize data
	public void saveData() {

		try {
			FileOutputStream fileOut = new FileOutputStream("data.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in data.ser");

		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	// main

	public void run() throws InterruptedException {
		clear();
		menu();
	}

	long startTime = System.nanoTime();

	private void menu() throws InterruptedException {
		while (true) {
			clear();
			printAsciiArt(HEADLINE);
			int volba = uzivatVolba("Start", "Load saved game", "Help", "Exit");
			switch (volba) {
			case 1:
				println(Style.CENTER, "Welcome to the dungeon!");
				println();
				println(Style.CENTER, "Which character would you like to play?\n");

				volba = uzivatVolba("Dwarf", "Orc", "Elf", "Goblin", /* "Superman", */ "Back");

				if (volba != 5) {
					character = new GameCharacter[] { GameCharacter.DWARF, GameCharacter.ORC, GameCharacter.ELF,
							GameCharacter.GOBLIN }[volba - 1];

					forAll = new ForAll();
					room = new Room(this);
					plebs = new Plebs(rand);
					town = new Town(this);
					way = new Way(this);
					inventory = new Inventory(this);

					forAll.health = character.getInitialHealth();
					forAll.maximumHealth = character.getInitialMaximumHealth();
					println(character.getBeginning());
					town.town();
					game();
				}

				break;

			case 2:
				throw new RuntimeException("loading not implemented");

			case 3:
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
				println();
				println("You gain experience and have a small chance of getting some health potions for killing enemies.");
				println("You can collect ingrediences to be able to craft lektvars in a pot.");
				println("You can save game in bed...if you die without save...you lose everything!");
				println("In every floor is a boss room, if you beat the boss, you go in next floor.");
				println("Main goal of this game is to collect the highest score!");
				println("Some actions cost you energy.");
				println("You get energy by drinking a beer in pub.");
				uzivatVolba("Back");
				break;

			case 4:
				return;
			}
		}

	}

	private void game() throws InterruptedException {
		int volba;
		boolean reset = true;

		printAsciiArt(character.getAsciiArt());
		printEmptyLines(2);
		println(Style.CENTER, "You are on floor " + forAll.floor + "!");
		FIGHT: while (true) {
			way.map1.asciiArtMap();
			long timeElapsed = System.nanoTime() - startTime;
			if (timeElapsed >= 120 * 1e+9) {
				startTime += timeElapsed;
				if (forAll.energy > 95) {
					forAll.energy = 100;
				} else {
					forAll.energy += 5;
				}
			}
			boolean freeRoom = way.map1.rooms[way.map1.w][way.map1.l];
			forAll.resetDrinkHealthPotionCount();
			boolean plebFight = true;
			if (freeRoom) {
				while (true) {
					println(Style.CENTER, "What now?");
					volba = uzivatVolba("Go on", "Open inventory and info", "Go in town", "Exit");
					if (volba == 1) { // Go on
						way.go();
						if (reset) {
							plebs.resetEnemy();
						}
						continue FIGHT;
					} else if (volba == 2) { // Open inventory and info
						inventoryAndInfo(false);
						continue FIGHT;

					} else if (volba == 3) { // Go in town
						town.town();
						continue FIGHT;
					} else if (volba == 4) { // Exit
						println("\tReally?...unsaved progres will be lost permanently!");
						if (uzivatVolba("Yes", "No") == 1) {
							return;
						}
					}

				}
			} else {
				if (way.map1.l == way.map1.rightEdge && way.map1.w == way.map1.w1) {
					println("\tWelcome in boss room for floor " + forAll.floor);
					beep();
				} else {
					println(Style.CENTER, "You see " + getPlebs().enemy.nameWithArticle() + "!");

				}

				while (true) {
					println(Style.CENTER, "What now?");
					volba = uzivatVolba("Fight", "Go back", "Open inventory and info", "Go in town", "Exit");
					if (volba == 1) { // Attack
						if (way.map1.l == way.map1.rightEdge && way.map1.w == way.map1.w1 && forAll.floor == 1) {
							boss1.boss1Quote();

							plebFight = false;
						} else if (way.map1.l == way.map1.rightEdge && way.map1.w == way.map1.w1 && forAll.floor == 2) {

						} else {

						}
						break;
					} else if (volba == 2) { // Go back
						way.map1.mapBack();
						way.back();
						reset = false;
						continue FIGHT;
					} else if (volba == 3) { // Open inventory and info
						inventoryAndInfo(false);
						continue FIGHT;
					} else if (volba == 4) { // Go in town
						town.town();
						continue FIGHT;
					} else if (volba == 5) { // Exit
						println(Style.CENTER, "Really?");
						println(Style.CENTER, "Unsaved progres will be lost permanently!");
						if (uzivatVolba("Yes", "No") == 1) {
							println(Style.CENTER, "You exit the dungeon.");
							println(Style.CENTER, "Thanks for playing!");
							uzivatVolba("Continue");
							return;
						}
					}

				}
				while (plebs.enemyHealth > 0 && getHealth() > 0 && plebFight) {
					println("\t> Your HP: " + getHealth());
					println("\t> " + plebs.enemy.name + "'s HP: " + plebs.enemyHealth);
					println();
					println(Style.CENTER, "What would you like to do?");
					println();
					volba = uzivatVolba("Attack", "Run", "Open inventory and info");
					if (volba == 1) { // Attack
						plebFight();
					} else if (volba == 2) { // Run
						if (forAll.numPotionOfInvisibility == 0) {
							println(Style.CENTER, "No time to run!");
							switch (uzivatVolba("Continue")) {
							}
						} else {
							println(Style.CENTER, "Do you really want to run?");
							println(Style.CENTER, "It will cost you potion of invisibility!");
							int volbaRun = uzivatVolba("Yes", "No");
							switch (volbaRun) {
							case 1:
								println(Style.CENTER, "You run!");
								plebs.resetEnemy();
								forAll.numPotionOfInvisibility--;
								forAll.resetDrinkHealthPotionCount();
								switch (uzivatVolba("Continue")) {
								}
								continue FIGHT;
							case 2:
								break;

							}

						}
					} else if (volba == 3) {// Open inventory and info
						inventoryAndInfo(true);
					}

				}
				while (boss1.boss1Health > 0 && getHealth() > 0 && !plebFight) {
					println("\t> Your HP: " + getHealth());
					println("\t> " + boss1.boss1Name + "'s HP: " + boss1.boss1Health);
					println();
					volba = uzivatVolba("Attack", "Run", "Open inventory and info");
					if (volba == 1) { // Attack
						boss1.boss1Fight();
					} else if (volba == 2) { // Run
						if (forAll.numPotionOfInvisibility == 0) {
							println(Style.CENTER, "No time to run!");
							uzivatVolba("Continue");
						} else {
							println(Style.CENTER, "Do you really want to run?");
							println(Style.CENTER, "It will cost you potion of invisibility!");
							if (uzivatVolba("Yes", "No") == 1) {
								println(Style.CENTER, "You run!");
								plebs.resetEnemy();
								forAll.numPotionOfInvisibility--;
								forAll.resetDrinkHealthPotionCount();
								uzivatVolba("Continue");
								continue FIGHT;
							}

						}
					} else if (volba == 3) {// Open inventory and info
						inventoryAndInfo(true);
					}
				}
			}

			if (getHealth() < 1) {
				println(Style.CENTER, "> You limp out of the dungeon, wounded from the battle.");
				uzivatVolba("Continue");
				return;
			} else if (getHealth() < 30 ^ plebs.enemyHealth > 0) {
				println();
				fillLine(RED.BRIGHT, "!@");
				println();
				println(Style.CENTER, RED.BRIGHT, "> <ALERT>Your HP is very low " + "(" + getHealth() + " HP left)");
				println();
				fillLine(RED.BRIGHT, "!@");
				println();
			}
			int goldFound;
			way.map1.rooms[way.map1.w][way.map1.l] = true;
			boolean bossKilled = false;
			if (way.map1.l == way.map1.rightEdge && way.map1.w == way.map1.w1) {
				plebFight = true;
				bossKilled = true;
				forAll.bossesKilled++;
				goldFound = (rand.nextInt(100) + rand.nextInt(100)) + forAll.bossesKilled * 200;
				forAll.floor++;
				way.map1.setStartingPosition();

			} else {
				forAll.experience += plebs.experienceGain;
				plebs.enemiesKilled++;
				plebs.enemyMissChance = plebs.enemyMissChance * 2 / 3;
				goldFound = (rand.nextInt(100) + rand.nextInt(100)) + plebs.enemiesKilled * 20;
				println("# " + plebs.enemy.name + " was defeated!");
				println("# You have earned ", GREEN.BRIGHT, plebs.experienceGain + " exp", DEFAULT_COLOR, "!");
			}
			score = plebs.enemiesKilled * 5 + forAll.bossesKilled * 20;
			forAll.gold += goldFound;
			reset = true;
			println();
			println();
			println();
			int levelUp = forAll.level++;
			println("# You have ", RED.BRIGHT, getHealth() + "HP", DEFAULT_COLOR, " left ");
			if (forAll.experience >= forAll.levelUp ^ bossKilled) {
				println("# You have ", BLUE.BRIGHT, "level " + forAll.level + " --> " + levelUp, DEFAULT_COLOR, "!");
			} else {
				println("# You have ", BLUE.BRIGHT, "level " + forAll.level, DEFAULT_COLOR, "!");
			}
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
			if (forAll.experience >= forAll.levelUp ^ bossKilled) {
				uzivatVolba("Continue");
				clear();
				forAll.levelUp += forAll.level * 50;
				forAll.level = levelUp;
				println();
				fillLine(GREEN.BRIGHT, "*");
				println(Style.CENTER, GREEN.BRIGHT, "Congratulations!");
				println(Style.CENTER, GREEN.BRIGHT, "Level Up!");
				println(Style.CENTER, GREEN.BRIGHT, "Level " + forAll.level + "!");
				fillLine(GREEN.BRIGHT, "*");
				println();
				forAll.maximumHealth += ForAll.LEVEL_UP_HEALTH;
				increaseHealth(ForAll.LEVEL_UP_HEALTH);

				println("\tYour maximum health is " + forAll.maximumHealth + "HP.");
				println("\tYour minimum damage is " + character.getDamage().minValue(this) + ".");
				println("\tYour maximum damage is " + character.getDamage().maxValue(this) + ".");
			}

			if (uzivatVolba("Search room", "Continue") == 1) {
				if (forAll.energy < 20) {
					println(Style.CENTER, "You don't have enough energy!");
				} else {
					room.normalRoom();
					forAll.energy -= 20;
				}
				uzivatVolba("Continue");
			}
			plebs.resetEnemy();

		}
	}

	public void inventoryAndInfo(boolean fighting) {
		do {
			println(RED.BRIGHT, this.getHealth() + "/" + this.getForAll().maximumHealth + " health");
			if (getForAll().experience > 0) {
				println(GREEN.BRIGHT, this.getForAll().experience + "/" + this.getForAll().levelUp + " experience");
			}
			if (getForAll().level > 0) {
				println(BLUE.BRIGHT, "level " + this.getForAll().level);
			}
			if (getPlebs().enemiesKilled > 0) {
				println("floor " + this.getForAll().floor);
			}
			if (getForAll().resistence > 0) {
				println(100 - this.getForAll().resistence + "% resistance");
			}
			if (forAll.woodenAxe > 0) {
				println("Wooden axe");
			}
			if (forAll.bronzeAxe > 0) {
				println("Bronze axe");
			}
			if (forAll.silverAxe > 0) {
				println("Silver axe");
			}
			if (forAll.ironAxe > 0) {
				println("Iron axe");
			}
			if (forAll.sneak > 1) {
				println(forAll.sneak + " sneaks");

			}
			if (forAll.sneak == 1) {
				println(forAll.sneak + " sneak");

			}
			if (forAll.sneak > 1) {
				println(forAll.frog + " frogs");

			}
			if (forAll.sneak == 1) {
				println(forAll.frog + " frog");

			}
			if (forAll.sneak > 1) {
				println(forAll.worm + " worms");

			}
			if (forAll.sneak == 1) {
				println(forAll.worm + " worm");

			}
			if (forAll.sneak > 1) {
				println(forAll.squirel + " squirels");

			}
			if (forAll.sneak == 1) {
				println(forAll.squirel + " squirel");

			}
			if (forAll.sneak > 1) {
				println(forAll.wood + " wood");

			}
			if (forAll.sneak == 1) {
				println(forAll.wood + " wood");

			}
			if (forAll.sneak > 1) {
				println(forAll.bird + " birds");

			}
			if (forAll.sneak == 1) {
				println(forAll.bird + " bird");

			}
			println(forAll.lCapacity + "/" + this.getCharacter().loadCapacity + " load capacity");
			if (getForAll().numPotionOfStrength > 0) {
				println(this.getForAll().numPotionOfStrength + " potion(s) of strength");
			}
			if (getForAll().gold > 0) {
				println(YELLOW.BRIGHT, this.getForAll().gold + " gold");

			}
			println(forAll.energy + "/100 energy");
			println(this.getCharacter().getMissChance() + "% miss chance");
			if (getPlebs().enemiesKilled == 1) {
				println(getPlebs().enemiesKilled + " killed enemy");
			} else {
				println(getPlebs().enemiesKilled + " killed enemies");
			}
			if (getForAll().bossesKilled == 1) {
				println(getForAll().bossesKilled + " killed boss");
			} else {
				println(getForAll().bossesKilled + " killed bosses");
			}
			println(score + " score");
			println();
			println("Map:");
			printEmptyLines(3);
			println(way.map1.map1);
			printEmptyLines(3);
			println("Map legend:");
			println("D - your position");
			println("x - wall");
			println("o - room with enemy");
			println("c - cleared room");
			println();
		} while (this.getInventory().showInventory(fighting) != null);
	}

	private void plebFight() {
		boolean youMiss = rand.nextInt(100) <= character.getMissChance();
		boolean enemyMiss = rand.nextInt(100) <= plebs.enemyMissChance;

		if (youMiss) {
			println(Style.CENTER, "You MISS!");
		}
		if (enemyMiss) {
			println(Style.CENTER, "Enemy MISS!");
		}

		if (!youMiss) {
			int damageDealt = forAll.selectedWeapon.calculateDamage(this);
			println(Style.CENTER, "You strike the " + plebs.enemy.name + " for " + damageDealt + " damage.");
			plebs.enemyHealth -= damageDealt;
		}

		if (!enemyMiss) {
			int damageTaken = ((rand.nextInt(plebs.enemyAttackDamage) + rand.nextInt(plebs.enemyAttackDamage))
					+ plebs.enemiesKilled * 5 + 10) * forAll.resistence / 100;

			println(Style.CENTER, "You receive " + damageTaken + " damage.");
			decreaseHealth(damageTaken);
		}

		if (getHealth() < 1) {
			println(Style.CENTER,
					"You have taken too much damage, you are dying in pain covered in the shit of your enemy while they are celebrating...zombies will a have tasty dinner! ");
		}
		forAll.resetDrinkHealthPotionCount();

	}

}