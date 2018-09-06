package olderman.dungeon;

import olderman.dungeon.Style.ColorStyle;
import olderman.dungeon.enemies.Boss1;
import olderman.dungeon.enemies.Boss2;
import olderman.dungeon.enemies.Plebs;
import olderman.dungeon.inventory.HealthPotion;
import olderman.dungeon.inventory.Inventory;
import olderman.dungeon.map.MapConstants;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Dungeon implements Serializable {
    private static final long serialVersionUID = 1L;

    // ascii art
    private static final String HEADLINE = Resources.getString("/Headline");
    // fields
    private final Random rand = new Random();
    public int score;
    public int bossX;
    public int bossY;
    private boolean changeMap;
    private boolean previousState;
    boolean bossKilled = false;
    boolean back = false;
    private ForAll forAll;
    private MapConstants mapConstants;
    private Plebs currentPlebs;
    private GameCharacter character;
    private Inventory inventory;
    private Town town;
    private Boss1 boss1 = new Boss1(this);
    private Boss2 boss2 = new Boss2(this);
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
    private transient final OS os;

    public void beep() {
        os.beep();
    }

    public void trySaveData(ArrayList<Object> data) throws FileNotFoundException {
        os.trySaveData(data);
    }

    public FileInputStream tryLoadData() throws FileNotFoundException {
        return os.tryLoadData();
    }

    public void clear() {
        os.clear();
    }

    public int uzivatVolba(String... options) {
        return os.uzivatVolba(options);
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

    public void visibleSwitch() {
        os.visibleSwitch();
    }

    public void switchSwitch() {
        os.switchSwitch();
    }

    public void looper() {
        os.looper();
    }

    public boolean checkSwitch() {
        return os.checkSwitch();
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
            println(forAll.frogLeg + " frog legs");

        }
        if (forAll.frogLeg == 1) {
            println(forAll.frogLeg + " frog leg");
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
    public void saveData() throws FileNotFoundException {
        // Massive object to store all our objects
        ArrayList<Object> data = new ArrayList<Object>();
        data.add(getForAll());
        data.add(way.randMap.data);
        data.add(getCharacter());
        data.add(way.randMap);

        trySaveData(data);
        println(Style.CENTER, "Saved!");
    }

    @SuppressWarnings("unchecked")
    public void loadData() {
        // create arraylist to store deserialized objects
        ArrayList<Object> deserialized = new ArrayList<Object>();

        try {
            FileInputStream fileIn = this.tryLoadData();
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

    public void run() throws InterruptedException, FileNotFoundException {
        clear();
        menu();
    }

    long startTime = System.nanoTime();

    private void menu() throws InterruptedException, FileNotFoundException {
        while (true) {
            looper();
            forAll = new ForAll();
            town = new Town(this);
            way = new Way(this);
            inventory = new Inventory(this);

            clear();
            printAsciiArt(HEADLINE);
            int volba = uzivatVolba("Start", "Load saved game", "Help");
            switch (volba) {
                case 1:
                    println(Style.CENTER, "Welcome to the dungeon!");
                    println();
                    println(Style.CENTER, "Which character would you like to play?\n");

                    volba = uzivatVolba("Dwarf", "Orc", "Elf", "Goblin", /* "Superman", */ "Back");

                    if (volba != 5) {
                        character = new GameCharacter[]{GameCharacter.DWARF, GameCharacter.ORC, GameCharacter.ELF,
                                GameCharacter.GOBLIN}[volba - 1];

                        forAll.health = character.getInitialHealth();
                        forAll.maximumHealth = character.getInitialMaximumHealth();
                        forAll.missChance = character.getMissChance();
                        println(Style.CENTER, character.getBeginning());
                        printAsciiArt(character.getAsciiArt());
                        switch (uzivatVolba("continue")) {
                        }
                        println(Style.CENTER, "Which map do you find more clear?");
                        println("Example 1):");
                        println(Style.CENTER, "      \u26DE \u26DE \u26DE");
                        println(Style.CENTER, "   \u26DE \u263B \u2686 \u26DE");
                        println(Style.CENTER, "\u26DE \u2688 \u2686 \u2620 \u26DE");
                        println(Style.CENTER, "\u26DE \u26DE \u26DE \u26DE \u26DE");
                        println();
                        println("Example 2):");
                        println(Style.CENTER, "\u3000 \u3000 \uff38 \uff38 \uff38");
                        println(Style.CENTER, "\u3000 \uff38 \uff0a \uff10 \uff38");
                        println(Style.CENTER, "\uff38 \uff1f \uff10 \uff01 \uff38");
                        println(Style.CENTER, "\uff38 \uff38 \uff38 \uff38 \uff38");
                        switch (uzivatVolba("1", "2")) {
                            case 1:
                                changeMap = false;
                                break;
                            case 2:
                                changeMap = true;
                                switchSwitch();
                        }
                        mapConstants = new MapConstants(this);
                        setPreviousState(isChangeMap());
                        town.town();
                        game();
                        break;
                    }
                    break;

                case 2:
                    if (os.saveFileExists()) {
                        loadData();
                        town.getHouse().inside();
                        town.town();
                        game();
                        break;
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

    private void game() throws InterruptedException, FileNotFoundException {
        int volba;
        Room room = way.randMap.data.mapRooms[way.randMap.data.yourPositionx][way.randMap.data.yourPositiony];
        room.setYourPosition(true);
        way.randMap.drawAsciiArtMap();
        boolean timeWithoutFight = true;
        FIGHT:
        while (true) {
            room = way.randMap.data.mapRooms[way.randMap.data.yourPositionx][way.randMap.data.yourPositiony];
            if (timeWithoutFight) {
                println(Style.CENTER, "You are on a floor " + forAll.floor + "!");
                timeWithoutFight = false;
            }
            addEnergy();
            currentPlebs = room.getPlebs();
            forAll.resetDrinkHealthPotionCount();
            boolean plebFight = true;
            if (room.isFreeRoom()) {
                while (true) {
                    room = way.randMap.data.mapRooms[way.randMap.data.yourPositiony][way.randMap.data.yourPositionx];
                    if (way.randMap.data.yourPositiony == bossY && way.randMap.data.yourPositionx == bossX && room.isSearchedRoom()) {
                        println(Style.CENTER, "Here are the stairs to next floor.");
                        volba = uzivatVolba("Go to next floor", "Go on");
                        if (volba == 1) {
                            forAll.floor++;
                            bossKilled = false;
                            way.randMap.initializeRooms();
                            way.randMap.drawAsciiArtMap();
                            timeWithoutFight = true;
                            continue FIGHT;
                        }
                        way.go();
                        back = true;
                        continue FIGHT;
                    } else if (room.isSearchedRoom()) {
                        println(Style.CENTER, "What now?");
                        volba = uzivatVolba("Go on", "Open inventory and info", "Go to town", "Exit to main menu");
                        if (volba == 1) { // Go on
                            way.go();
                            back = true;
                            continue FIGHT;
                        } else if (volba == 2) { // Open inventory and info
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
                                "Exit to main menu");
                        if (volba == 1) { // Go on
                            way.go();
                            back = true;
                            continue FIGHT;
                        } else if (volba == 2) {

                            if (forAll.energy < 20) {
                                println(Style.CENTER, "You don't have enough energy!");
                            } else {
                                if (way.randMap.data.yourPositiony == bossY && way.randMap.data.yourPositionx == bossX) {
                                    println(Style.CENTER, "You found stairs to next floor!");
                                }
                                room.normalRoom(this, room);
                                forAll.energy -= 20;
                            }
                            if (way.randMap.data.yourPositiony == bossY && way.randMap.data.yourPositionx == bossX) {
                                volba = uzivatVolba("Go to next floor", "Continue");
                                if (volba == 1) {
                                    forAll.floor++;
                                    bossKilled = false;
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
                if (way.randMap.data.yourPositiony == bossY && way.randMap.data.yourPositionx == bossX) {
                    room.setBossRoom(true);

                    println(Style.CENTER, "Welcome in boss room for floor " + forAll.floor);
                    beep();

                } else {
                    println(Style.CENTER, "You see " + getCurrentPlebs().enemy.nameWithArticle() + "!");

                }

                while (true) {
                    println(Style.CENTER, "What now?");
                    volba = uzivatVolba("Fight", "Use potion of invisibility", "Go back", "Open inventory and info",
                            "Go to town", "Exit to main menu");
                    if (volba == 1) { // Attack
                        if (way.randMap.data.yourPositiony == bossY && way.randMap.data.yourPositionx == bossX && forAll.floor == 1) {
                            boss1.boss1Quote();

                            plebFight = false;
                        } else if (way.randMap.data.yourPositiony == bossY && way.randMap.data.yourPositionx == bossX
                                && forAll.floor == 2) {
                            boss2.boss2Quote();

                            plebFight = false;

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
                                    continue FIGHT;

                            }

                        }
                    } else if (volba == 3) { // Go back
                        if (back) {
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
                                    continue FIGHT;

                            }

                        }
                    } else if (volba == 3) {// Open inventory and info
                        inventoryAndInfo(true);
                    }

                }
                while (boss1.boss1Health > 0 && getHealth() > 0 && !plebFight && getForAll().floor == 1) {
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
                                    continue FIGHT;
                            }

                        }
                    } else if (volba == 3) {// Open inventory and info
                        inventoryAndInfo(true);
                    }
                }
                while (boss2.boss2Health > 0 && getHealth() > 0 && !plebFight && getForAll().floor == 2) {
                    println("\t> Your HP: " + getHealth());
                    println("\t> " + boss2.boss2Name + "'s HP: " + boss2.boss2Health);
                    println();
                    volba = uzivatVolba("Attack", "Use potion of invisibility", "Open inventory and info");
                    if (volba == 1) { // Attack
                        boss2.boss2Fight();
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
            String enemy;
            room.setFreeRoom(true);
            println();
            println();
            println();
            if (way.randMap.data.yourPositiony == bossY && way.randMap.data.yourPositionx == bossX) {
                bossKilled = true;
                forAll.bossesKilled++;
                println("# CONGRATULATIONS, you have killed boss of this room!");
                enemy = "boss";
                goldFound = (rand.nextInt(100) + rand.nextInt(100)) + forAll.bossesKilled * 200;

            } else {
                enemy = getCurrentPlebs().enemy.name;
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
                println("# The " + enemy + " dropped a small health potion! ("
                        + inventory.getCount(HealthPotion.SMALL) + " total)");

            }

            if (rand.nextInt(100) < ForAll.MEDIUM_HEALTH_POTION_DROP_CHANCE
                    || rand.nextInt(100) <= character.getLuck()) {
                inventory.add(HealthPotion.MEDIUM);
                println("# The " + enemy + " dropped a medium health potion! ("
                        + inventory.getCount(HealthPotion.MEDIUM) + " total)");

            }

            if (rand.nextInt(100) < ForAll.LARGE_HEALTH_POTION_DROP_CHANCE
                    || rand.nextInt(100) <= character.getLuck()) {
                inventory.add(HealthPotion.LARGE);
                println("# The " + enemy + " dropped a large health potion! ("
                        + inventory.getCount(HealthPotion.LARGE) + " total)");
            }
            if (forAll.experience >= forAll.levelUp ^ bossKilled) {
                uzivatVolba("Continue");
                clear();
                forAll.level++;
                forAll.experience = forAll.experience - forAll.levelUp;
                forAll.levelUp = 50 * forAll.level;
                fillLine(GREEN.BRIGHT, "*");
                println();
                println(Style.CENTER, GREEN.BRIGHT, "Level Up!");
                println(Style.CENTER, GREEN.BRIGHT, "Level " + forAll.level + "!");
                println();
                fillLine(GREEN.BRIGHT, "*");
                println();
                forAll.maximumHealth += ForAll.LEVEL_UP_HEALTH;
                increaseHealth(ForAll.LEVEL_UP_HEALTH);
                println("\tYour maximum health is " + forAll.maximumHealth + "HP.");
                println("\tYour base damage is " + character.getDamage().minValue(this) + "-"
                        + character.getDamage().maxValue(this));
                println("\tYour weapon damage is " + forAll.selectedWeapon.getDamageIncrease());
                println("\tYour final damage is " + forAll.selectedWeapon.minDamage(this) + "-" + forAll.selectedWeapon.maxDamage(this));
                uzivatVolba("Continue");
                clear();
                println(Style.CENTER, "Which stat do you want do upgrade?");
                println();
                println("Load capacity - lets you carry more things");
                fillLine("-");
                println();
                println("Max health - increases your max health, not current health");
                fillLine("-");
                println();
                println("Perception - increases your chance to find chest in room and reduces miss chance on high ranks");
                println();
                int lvlUpChoices;
                ArrayList<String> choices = new ArrayList<>();
                choices.add("Load capacity");
                choices.add("Max health");
                if (getForAll().missChance >= 5) {
                    choices.add("Perception");
                }

                lvlUpChoices = uzivatVolba(choices.toArray(new String[choices.size()]));
                String stringChoice = choices.get(lvlUpChoices - 1);

                if (stringChoice.equals("Load capacity")) {
                    println(Style.CENTER, "load capacity before: " + getForAll().lCapacity);
                    getForAll().lCapacity += 50;
                    println(Style.CENTER, "load capacity now: " + getForAll().lCapacity);
                }
                if (stringChoice.equals("Max health")) {
                    println(Style.CENTER, "maximum health before: " + getForAll().maximumHealth);
                    getForAll().maximumHealth += 50;
                    println(Style.CENTER, "maximum health now: " + getForAll().maximumHealth);
                }
                if (stringChoice.equals("Perception")) {
                    if (getForAll().chestChance < 61) {
                        getForAll().chestChance += 10;
                        println(Style.CENTER, "Your chance to find chest is " + getForAll().chestChance + "%");
                    } else {
                        getForAll().missChance -= 10;
                        println(Style.CENTER, "Your miss chance is " + getForAll().missChance + "%");
                    }
                }
                uzivatVolba("Continue");

            }

            if (uzivatVolba("Search room", "Continue") == 1) {
                if (forAll.energy < 20) {
                    println(Style.CENTER, "You don't have enough energy!");
                } else {
                    if (way.randMap.data.yourPositiony == bossY && way.randMap.data.yourPositionx == bossX) {
                        println(Style.CENTER, "You found stairs to next floor!");
                    }
                    room.normalRoom(this, room);
                    forAll.energy -= 20;
                }
                if (way.randMap.data.yourPositiony == bossY && way.randMap.data.yourPositionx == bossX) {
                    volba = uzivatVolba("Go to next floor", "Continue");
                    if (volba == 1) {
                        forAll.floor++;
                        bossKilled = false;
                        way.randMap.initializeRooms();
                        way.randMap.drawAsciiArtMap();
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

    public void inventoryAndInfo(boolean fighting) {
        visibleSwitch();
        do {
            setPreviousState(isChangeMap());
            clear();
            addEnergy();
            println(RED.BRIGHT, this.getHealth() + "/" + this.getForAll().maximumHealth + " health");
            println("Your base damage is " + character.getDamage().minValue(this) + "-"
                    + character.getDamage().maxValue(this));
            if (forAll.selectedWeapon.getDamageIncrease() > 0) {
                println("Your weapon damage is " + forAll.selectedWeapon.getDamageIncrease());
                println("Your final damage is " + forAll.selectedWeapon.minDamage(this) + "-" + forAll.selectedWeapon.maxDamage(this));
            }
            if (getForAll().experience > 0) {
                println(GREEN.BRIGHT, this.getForAll().experience + "/" + this.getForAll().levelUp + " experience");
            }
            if (getForAll().level > 0) {
                println(BLUE.BRIGHT, "level " + this.getForAll().level);
            }
            if (forAll.enemiesKilled > 0) {
                println("floor " + this.getForAll().floor);
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

            }
            fillLine("-");
            println();
            printResources();
            fillLine("-");
            println();
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
            if (way.randMap.data.map.length() == 0) {

            } else {
                println();
                println("Map:");
                println();
                println(way.randMap.data.map);
                println("Map legend:");
                println(mapConstants.playerChar + " - your position");
                println(mapConstants.wallChar + " - wall");
                println(mapConstants.bossChar + " - boss room");
                println(mapConstants.fullRoomChar + " - unsearched room");
                println(mapConstants.clearRoomChar + " - searched room");
            }
            println();
        } while (this.getInventory().showInventory(fighting) != null);
        visibleSwitch();
        if (isPreviousState() != isChangeMap()) {
            mapConstants = new MapConstants(this);
            way.randMap.drawAsciiArtMap();
            inventoryAndInfo(fighting);
        }
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

    public boolean isChangeMap() {
        return changeMap;
    }

    public MapConstants getMapConstants() {
        return mapConstants;
    }

    public void setChangeMap(boolean changeMap) {
        this.changeMap = changeMap;
    }

    public boolean isPreviousState() {
        return previousState;
    }

    public void setPreviousState(boolean previousState) {
        this.previousState = previousState;
    }
}