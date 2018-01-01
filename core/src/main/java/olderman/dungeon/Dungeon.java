package olderman.dungeon;

import olderman.dungeon.Style.ColorStyle;
import olderman.dungeon.enemies.Boss1;
import olderman.dungeon.enemies.Plebs;
import olderman.dungeon.inventory.HealthPotion;
import olderman.dungeon.inventory.Inventory;
import olderman.dungeon.map.RandMapData;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.View;

public class Dungeon implements Serializable {
	private static final long serialVersionUID = 1L;

	// ascii art
	private static final String HEADLINE = Resources.getString("/Headline");
	// fields
	private final Random rand = new Random();
	public int score;
	public int bossX;
	public int bossY;
	boolean bossKilled = false;
	boolean back = false;
	private ForAll forAll;
	private Plebs currentPlebs;
	private GameCharacter character;
	private Inventory inventory;
	private Town town;
	private Boss1 boss1 = new Boss1(this);
	// private Boss2 boss2;
	private Way way;

	// getters
	public Random getRand() {
		return rand;
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

	// public Boss2 getBoss2() {
	// return boss2;
	// }

	public int getHealth() {
		return forAll.health;
	}

	public void setMissChance(int missChance) {
		forAll.missChance = Math.min(missChance, forAll.maximumHealth);
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

	public void startVideoAd(){
			os.startVideoAd();
	}

	void onRewardedVideoAdLoaded(){
os.onRewardedVideoAdLoaded();
	};

	void onRewardedVideoAdOpened(){
os.onRewardedVideoAdOpened();
	};

	void onRewardedVideoStarted(){
os.onRewardedVideoStarted();
	};

	void onRewardedVideoAdClosed(){
	os.onRewardedVideoAdClosed();
	};

	void onRewarded(){
			getForAll().energy = 100;
	};

	void onRewardedVideoAdLeftApplication(){
		os.onRewardedVideoAdLeftApplication();
	};

	void onRewardedVideoAdFailedToLoad(int i){
			os.onRewardedVideoAdFailedToLoad(i);
	};

	private void onPause() {
		os.onPause();
	}

	private void onResume() {
		os.onResume();
	}

	private void onDestroy() {
		os.onDestroy();
	}

	private void applyStyle(Style o) {
		if (o instanceof ColorStyle) {
			os.color((ColorStyle) o);
		} else if (o instanceof AttributeStyle) {
			os.attribute((AttributeStyle) o);
		} else if (o instanceof Reset) {
			os.reset();
		}
	}

	private void print0(Object... text) {
		for (Object o : text) {
			if (o instanceof Style) {
				applyStyle((Style) o);
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

	public void fillLine(Style style, String text) {
		applyStyle(style);
		os.fillLine(text);
		os.reset();
		os.flush();
	}

	public void fillLine(String text) {
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

	public void printResources() {
		if (forAll.snake > 1) {
			println(forAll.snake + " snakes");
		}
		if (forAll.snake == 1) {
			println(forAll.snake + " snake");

		}
		if (forAll.frog > 1) {
			println(forAll.frog + " frogs");

		}
		if (forAll.frog == 1) {
			println(forAll.frog + " frog");

		}
		if (forAll.worm > 1) {
			println(forAll.worm + " worms");

		}
		if (forAll.worm == 1) {
			println(forAll.worm + " worm");

		}
		if (forAll.squirrel > 1) {
			println(forAll.squirrel + " squirels");

		}
		if (forAll.squirrel == 1) {
			println(forAll.squirrel + " squirel");

		}
		if (forAll.wood > 1) {
			println(forAll.wood + " wood");

		}
		if (forAll.wood == 1) {
			println(forAll.wood + " wood");

		}
		if (forAll.bird > 1) {
			println(forAll.bird + " birds");

		}
		if (forAll.bird == 1) {
			println(forAll.bird + " bird");
		}
		if (forAll.feather > 1) {
			println(forAll.feather + " feathers");

		}
		if (forAll.feather == 1) {
			println(forAll.feather + " feather");
		}
		if (forAll.squirelTails > 1) {
			println(forAll.squirelTails + " squirel tails");

		}
		if (forAll.squirelTails == 1) {
			println(forAll.squirelTails + " squirel tail");
		}
		if (forAll.snakeBrain > 1) {
			println(forAll.snakeBrain + " snake brains");

		}
		if (forAll.snakeBrain == 1) {
			println(forAll.snakeBrain + " snake brain");
		}
		if (forAll.frogRightEye > 1) {
			println(forAll.frogRightEye + " frog right eyes");

		}
		if (forAll.frogRightEye == 1) {
			println(forAll.frogRightEye + " frog right eye");
		}
		if (forAll.frogLeg > 1) {
			println(forAll.frogRightEye + " frog legs");

		}
		if (forAll.frogLeg == 1) {
			println(forAll.frogRightEye + " frog leg");
		}
		if (forAll.frogLeftEye > 1) {
			println(forAll.frogLeftEye + " frog left eyes");

		}
		if (forAll.frogLeftEye == 1) {
			println(forAll.frogLeftEye + " frog left eye");
		}
		if (forAll.frogHead > 1) {
			println(forAll.frogHead + " frog heads");

		}
		if (forAll.frogHead == 1) {
			println(forAll.frogHead + " frog head");
		}
		if (forAll.frogHeart > 1) {
			println(forAll.frogHeart + " frog hearts");

		}
		if (forAll.frogHeart == 1) {
			println(forAll.frogHeart + " frog heart");
		}
		if (forAll.numPotionOfInvisibility == 1) {
			println(forAll.numPotionOfInvisibility + " potion of invisibility");
		}
		if (forAll.numPotionOfInvisibility > 1) {
			println(forAll.numPotionOfInvisibility + " potions of invisibility");
		}
		if (forAll.flashk == 1) {
			println(forAll.numPotionOfInvisibility + " flashk");
		}
		if (forAll.flashk > 1) {
			println(forAll.numPotionOfInvisibility + " flashks");
		}
	}

	// Serialize data
	public void saveData() {
		// Massive object to store all our objects
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(getForAll());
		data.add(way.randMap.data);
		data.add(getCharacter());

		try {
			FileOutputStream fileOut = new FileOutputStream("data.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(data);
			out.close();
			fileOut.close();
			println(Style.CENTER, "Saved");

		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void loadData() {
		// create arraylist to store deserialized objects
		ArrayList<Object> deserialized = new ArrayList<Object>();

		try {
			FileInputStream fileIn = new FileInputStream("data.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			deserialized = (ArrayList<Object>) in.readObject();
			for (Object o : deserialized) {
				if (o instanceof ForAll) {
					this.forAll = (ForAll) o;
				}
				if (o instanceof RandMapData) {
					way.randMap.data = (RandMapData) o;
				}
				if (o instanceof GameCharacter) {
					this.character = (GameCharacter) o;
				}

			}
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return;
		}

	}
	// main

	public Plebs getCurrentPlebs() {
		return currentPlebs;
	}

	public void run() throws InterruptedException {
		clear();
		menu();
	}

	long startTime = System.nanoTime();

	private void menu() throws InterruptedException {
		while (true) {
			forAll = new ForAll();
			town = new Town(this);
			way = new Way(this);
			inventory = new Inventory(this);

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

						way.randMap.createAsciiArtMap();
						forAll.health = character.getInitialHealth();
						forAll.maximumHealth = character.getInitialMaximumHealth();
						forAll.missChance = character.getMissChance();
						println(character.getBeginning());
						printAsciiArt(character.getAsciiArt());
						switch (uzivatVolba("continue")) {
						}
						town.town();
						getWay().randMap.asciiArtMap();
						game();
					}

					break;

				case 2:
					File f = new File("data.ser");
					if (f.exists()) {
						loadData();
						town.getHouse().inside();
						game();
					} else {
						println(Style.CENTER, "You don't have any saved progress yet!");
						switch (uzivatVolba("continue")) {
						}
						break;
					}
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
					println("You can collect ingredients to be able to brew potions in a pot.");
					println("You can save game in bed...if you die without save...you lose everything!");
					println("In every floor is a boss room, if you beat the boss, you go to the next floor.");
					println("Main goal of this game is to get the highest score!");
					println("Some actions cost you energy.");
					println("You get energy by drinking beer in a pub.");
					uzivatVolba("Back");
					break;

				case 4:
					return;
			}
		}

	}

	long timeElapsed;

	public void addEnergy() {
		timeElapsed = System.nanoTime() - startTime;
		if (timeElapsed >= 120 * 1e+9) {
			startTime += timeElapsed;
			if (forAll.energy > 95) {
				forAll.energy = 100;
			} else {
				forAll.energy += 5;
			}
		}
	}

	private void game() throws InterruptedException {
		int volba;
		boolean timeWithoutFight = true;
		FIGHT: while (true) {
			if (timeWithoutFight) {
				println(Style.CENTER, "You are on a floor " + forAll.floor + "!");
				timeWithoutFight = false;
			}
			Room room = way.randMap.data.mapRooms[way.randMap.data.w][way.randMap.data.l];
			if (room == null) {
				println(Style.CENTER, "You can't go there, there is a wall!");
				uzivatVolba("Continue");
				way.randMap.previousPosition = way.randMap.mapPosition;
				way.randMap.mapBarrier();
				way.back();
				continue FIGHT;
			} else {
				addEnergy();
				currentPlebs = room.getPlebs();
				forAll.resetDrinkHealthPotionCount();
				boolean plebFight = true;
				if (room.isFreeRoom()) {
					while (true) {
						if (way.randMap.data.l == bossY && way.randMap.data.w == bossX && room.isSearchedRoom()) {
							println(Style.CENTER, "Here are the stairs to next floor.");
							volba = uzivatVolba("Go to next floor", "Go on");
							if (volba == 1) {
								forAll.floor++;
								way.randMap.initializeRooms();
								timeWithoutFight = true;
								continue FIGHT;
							}
							way.go();
							way.randMap.asciiArtMap();
							back = true;
							continue FIGHT;
						} else if (room.isSearchedRoom()) {
							println(Style.CENTER, "What now?");
							volba = uzivatVolba("Go on", "Open inventory and info", "Go to town", "Exit");
							if (volba == 1) { // Go on
								way.go();
								way.randMap.asciiArtMap();
								back = true;
								continue FIGHT;
							} else if (volba == 2) { // Open inventory and info
								way.randMap.asciiArtMap();
								inventoryAndInfo(false);
								continue FIGHT;

							} else if (volba == 3) { // Go to town
								town.town();
								continue FIGHT;
							} else if (volba == 4) { // Exit
								println(Style.CENTER, "Really?...unsaved progres will be lost permanently!");
								if (uzivatVolba("Yes", "No") == 1) {
									return;
								}
							}

						} else {
							println(Style.CENTER, "What now?");
							volba = uzivatVolba("Go on", "Search room", "Open inventory and info", "Go to town",
									"Exit");
							if (volba == 1) { // Go on
								way.go();
								way.randMap.asciiArtMap();
								back = true;
								continue FIGHT;
							} else if (volba == 2) {

								if (forAll.energy < 20) {
									println(Style.CENTER, "You don't have enough energy!");
								} else {
									if (way.randMap.data.l == bossY && way.randMap.data.w == bossX) {
										println(Style.CENTER, "You found stairs to next floor!");
									}
									room.setSearchedRoom(true);
									room.normalRoom(this);
									forAll.energy -= 20;
								}
								if (way.randMap.data.l == bossY && way.randMap.data.w == bossX) {
									volba = uzivatVolba("Go to next floor", "Continue");
									if (volba == 1) {
										forAll.floor++;
										way.randMap.initializeRooms();
										timeWithoutFight = true;
										continue FIGHT;
									}
									if (volba == 2) {
										continue FIGHT;
									}
									uzivatVolba("Continue");
									continue FIGHT;
								}
							} else if (volba == 3) { // Open inventory and info
								way.randMap.asciiArtMap();
								inventoryAndInfo(false);
								continue FIGHT;

							} else if (volba == 4) { // Go to town
								town.town();
								continue FIGHT;
							} else if (volba == 5) { // Exit
								println(Style.CENTER, "Really?...unsaved progres will be lost permanently!");
								if (uzivatVolba("Yes", "No") == 1) {
									return;
								}
							}

						}

					}
				} else {
					if (way.randMap.data.l == bossY && way.randMap.data.w == bossX) {

						println(Style.CENTER, "Welcome in boss room for floor " + forAll.floor);
						beep();

					} else {
						println(Style.CENTER, "You see " + getCurrentPlebs().enemy.nameWithArticle() + "!");

					}

					while (true) {
						println(Style.CENTER, "What now?");
						volba = uzivatVolba("Fight", "Use potion of invisibility", "Go back", "Open inventory and info",
								"Go to town", "Exit");
						if (volba == 1) { // Attack
							if (way.randMap.data.l == bossY && way.randMap.data.w == bossX && forAll.floor == 1) {
								boss1.boss1Quote();

								plebFight = false;
							} else if (way.randMap.data.l == bossY && way.randMap.data.w == bossX
									&& forAll.floor == 2) {

							} else {

							}
							break;
						} else if (volba == 2) { // Use potion of invisibility
							if (forAll.numPotionOfInvisibility < 1) {
								println(Style.CENTER, "You don't have any!");
								switch (uzivatVolba("Continue")) {
								}
							} else {
								println(Style.CENTER, "Do you really want to use potion of invisibility?");
								println(Style.CENTER, "It will cost you one potion of invisibility!");
								int volbaRun = uzivatVolba("Yes", "No");
								switch (volbaRun) {
									case 1:
										clear();
										println(Style.CENTER, "You used potion of invisibility!");
										forAll.numPotionOfInvisibility--;
										back = false;
										forAll.resetDrinkHealthPotionCount();
										way.go();
										way.randMap.mapInvisibility();
										continue FIGHT;

								}

							}
						} else if (volba == 3) { // Go back
							if (back) {
								way.randMap.previousPosition = way.randMap.mapPosition;
								way.randMap.mapBack();
								way.back();
								back = false;
								continue FIGHT;
							} else {
								println(Style.CENTER, "You can't back!");
								uzivatVolba("Continue");
								continue FIGHT;
							}
						} else if (volba == 4) { // Open inventory and info
							way.randMap.asciiArtMap();
							inventoryAndInfo(false);
							continue FIGHT;
						} else if (volba == 5) { // Go to town
							town.town();
							continue FIGHT;
						} else if (volba == 6) { // Exit
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
					while (getCurrentPlebs().getHealth() > 0 && getHealth() > 0 && plebFight) {
						println("\t> Your HP: " + getHealth());
						println("\t> " + getCurrentPlebs().enemy.name + "'s HP: " + getCurrentPlebs().getHealth());
						println();
						println(Style.CENTER, "What would you like to do?");
						println();
						volba = uzivatVolba("Attack", "Use potion of invisibility", "Open inventory and info");
						if (volba == 1) { // Attack
							plebFight(getCurrentPlebs());
						} else if (volba == 2) { // Use potion of invisibility
							if (forAll.numPotionOfInvisibility < 1) {
								println(Style.CENTER, "You don't have any!");
								switch (uzivatVolba("Continue")) {
								}
							} else {
								println(Style.CENTER, "Do you really want to use potion of invisibility?");
								println(Style.CENTER, "It will cost you one potion of invisibility!");
								int volbaRun = uzivatVolba("Yes", "No");
								switch (volbaRun) {
									case 1:
										clear();
										println(Style.CENTER, "You used potion of invisibility!");
										forAll.numPotionOfInvisibility--;
										back = false;
										forAll.resetDrinkHealthPotionCount();
										way.go();
										way.randMap.mapInvisibility();
										continue FIGHT;

								}

							}
						} else if (volba == 3) {// Open inventory and info
							way.randMap.asciiArtMap();
							inventoryAndInfo(true);
						}

					}
					while (boss1.boss1Health > 0 && getHealth() > 0 && !plebFight) {
						println("\t> Your HP: " + getHealth());
						println("\t> " + boss1.boss1Name + "'s HP: " + boss1.boss1Health);
						println();
						volba = uzivatVolba("Attack", "Use potion of invisibility", "Open inventory and info");
						if (volba == 1) { // Attack
							boss1.boss1Fight();
						} else if (volba == 2) { // Run
							if (forAll.numPotionOfInvisibility < 1) {
								println(Style.CENTER, "You don't have any!");
								switch (uzivatVolba("Continue")) {
								}
							} else {
								println(Style.CENTER, "Do you really want to use potion of invisibility?");
								println(Style.CENTER, "It will cost you one potion of invisibility!");
								int volbaRun = uzivatVolba("Yes", "No");
								switch (volbaRun) {
									case 1:
										clear();
										println(Style.CENTER, "You used potion of invisibility!");
										forAll.numPotionOfInvisibility--;
										forAll.resetDrinkHealthPotionCount();
										way.go();
										way.randMap.mapInvisibility();
										continue FIGHT;
								}

							}
						} else if (volba == 3) {// Open inventory and info
							way.randMap.asciiArtMap();
							inventoryAndInfo(true);
						}
					}
				}

				if (getHealth() < 1) {
					println(Style.CENTER, "> You limp out of the dungeon, wounded from the battle.");
					uzivatVolba("Continue");
					return;
				} else if (getHealth() < 30 && getCurrentPlebs().getHealth() > 0) {
					println();
					fillLine(RED.BRIGHT, "!@");
					println();
					println(Style.CENTER, RED.BRIGHT,
							"> <ALERT>Your HP is very low " + "(" + getHealth() + " HP left)");
					println();
					fillLine(RED.BRIGHT, "!@");
					println();
				}
				int goldFound;
				way.randMap.data.mapRooms[way.randMap.data.w][way.randMap.data.l].setFreeRoom(true);
				println();
				println();
				println();
				if (way.randMap.data.l == bossY && way.randMap.data.w == bossX) {
					plebFight = true;
					bossKilled = true;
					forAll.bossesKilled++;
					goldFound = (rand.nextInt(100) + rand.nextInt(100)) + forAll.bossesKilled * 200;

				} else {
					forAll.experience += getCurrentPlebs().experienceGain;
					forAll.enemiesKilled++;
					goldFound = (rand.nextInt(100) + rand.nextInt(100)) + forAll.enemiesKilled * 20;
					println("# " + getCurrentPlebs().enemy.name + " was defeated!");
					println("# You have earned ", GREEN.BRIGHT, getCurrentPlebs().experienceGain + " exp",
							DEFAULT_COLOR, "!");
				}
				score = forAll.enemiesKilled * 5 + forAll.bossesKilled * 20;
				forAll.gold += goldFound;
				println("# You have ", RED.BRIGHT, getHealth() + "HP", DEFAULT_COLOR, " left ");
				println("# You found ", YELLOW.BRIGHT, goldFound + " gold", DEFAULT_COLOR, " (", YELLOW.BRIGHT,
						forAll.gold + " gold", DEFAULT_COLOR, " total)");
				if (rand.nextInt(100) < ForAll.SMALL_HEALTH_POTION_DROP_CHANCE
						|| rand.nextInt(100) <= character.getLuck()) {
					inventory.add(HealthPotion.SMALL);
					println("# The " + getCurrentPlebs().enemy.name + " dropped a small health potion! ("
							+ inventory.getCount(HealthPotion.SMALL) + " total)");

				}

				if (rand.nextInt(100) < ForAll.MEDIUM_HEALTH_POTION_DROP_CHANCE
						|| rand.nextInt(100) <= character.getLuck()) {
					inventory.add(HealthPotion.MEDIUM);
					println("# The " + getCurrentPlebs().enemy.name + " dropped a medium health potion! ("
							+ inventory.getCount(HealthPotion.MEDIUM) + " total)");

				}

				if (rand.nextInt(100) < ForAll.LARGE_HEALTH_POTION_DROP_CHANCE
						|| rand.nextInt(100) <= character.getLuck()) {
					inventory.add(HealthPotion.LARGE);
					println("# The " + getCurrentPlebs().enemy.name + " dropped a large health potion! ("
							+ inventory.getCount(HealthPotion.LARGE) + " total)");
				}
				if (forAll.experience >= forAll.levelUp ^ bossKilled) {
					uzivatVolba("Continue");
					clear();
					forAll.level++;
					forAll.experience = forAll.experience - forAll.levelUp;
					forAll.levelUp = 50 * forAll.level;
					fillLine(GREEN.BRIGHT, "*");
					println(Style.CENTER, GREEN.BRIGHT, "Level Up!");
					println(Style.CENTER, GREEN.BRIGHT, "Level " + forAll.level + "!");
					fillLine(GREEN.BRIGHT, "*");
					println();
					forAll.maximumHealth += ForAll.LEVEL_UP_HEALTH;
					increaseHealth(ForAll.LEVEL_UP_HEALTH);

					println("\tYour maximum health is " + forAll.maximumHealth + "HP.");
					println("\tYour damage is " + character.getDamage().minValue(this) + " - "
							+ character.getDamage().maxValue(this) + ".");
				}

				if (uzivatVolba("Search room", "Continue") == 1) {
					if (forAll.energy < 20) {
						println(Style.CENTER, "You don't have enough energy!");
					} else {
						if (way.randMap.data.l == bossY && way.randMap.data.w == bossX) {
							println(Style.CENTER, "You found stairs to next floor!");
						}
						room.setSearchedRoom(true);
						room.normalRoom(this);
						forAll.energy -= 20;
					}
					if (way.randMap.data.l == bossY && way.randMap.data.w == bossX) {
						volba = uzivatVolba("Go to next floor", "Continue");
						if (volba == 1) {
							forAll.floor++;
							way.randMap.initializeRooms();
							timeWithoutFight = true;
							continue FIGHT;
						}
						if (volba == 2) {
							continue FIGHT;
						}
						uzivatVolba("Continue");
						continue FIGHT;
					}
				}
			}

		}
	}

	public void inventoryAndInfo(boolean fighting) {
		do {
			addEnergy();
			println(RED.BRIGHT, this.getHealth() + "/" + this.getForAll().maximumHealth + " health");
			if (getForAll().experience > 0) {
				println(GREEN.BRIGHT, this.getForAll().experience + "/" + this.getForAll().levelUp + " experience");
			}
			if (getForAll().level > 0) {
				println(BLUE.BRIGHT, "level " + this.getForAll().level);
			}
			if (forAll.enemiesKilled > 0) {
				println("floor " + this.getForAll().floor);
			}
			if (getForAll().resistence > 0) {
				println(100 - this.getForAll().resistence + "% resistance");
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
				printResources();

			}
			println(forAll.lCapacity + "/" + this.getCharacter().loadCapacity + " load capacity");
			if (getForAll().numPotionOfStrength > 0) {
				println(this.getForAll().numPotionOfStrength + " potion(s) of strength");
			}
			if (getForAll().gold > 0) {
				println(YELLOW.BRIGHT, this.getForAll().gold + " gold");

			}
			println(forAll.energy + "/100 energy");
			println(forAll.missChance + "% miss chance");
			if (forAll.enemiesKilled == 1) {
				println(forAll.enemiesKilled + " killed enemy");
			} else {
				println(forAll.enemiesKilled + " killed enemies");
			}
			if (getForAll().bossesKilled == 1) {
				println(getForAll().bossesKilled + " killed boss");
			} else {
				println(getForAll().bossesKilled + " killed bosses");
			}
			println(score + " score");
			println();
			println("Map:");
			println();
			println(way.randMap.data.map);
			println("Map legend:");
			println("D - your position");
			println("x - wall");
			println("o - room with enemy");
			println("A - boss room");
			println("c - cleared room");
			println();
		} while (this.getInventory().showInventory(fighting) != null);
	}

	private void plebFight(Plebs plebs) {
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
			plebs.damageTaken(damageDealt);
		}

		if (!enemyMiss) {
			int damageTaken = ((rand.nextInt(plebs.enemyAttackDamage) + rand.nextInt(plebs.enemyAttackDamage))
					+ forAll.enemiesKilled * 5 + 10) * forAll.resistence / 100;

			println(Style.CENTER, "You receive " + damageTaken + " damage.");
			decreaseHealth(damageTaken);
		}

		// if (getHealth() < 1) {
		// println(Style.CENTER,
		// "You have taken too much damage, you are dying in pain covered in the
		// shit of your enemy while they are celebrating...zombies will a have
		// tasty dinner! ");
		// }
		forAll.resetDrinkHealthPotionCount();

	}

}