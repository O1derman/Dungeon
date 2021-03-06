package olderman.dungeon;

import java.io.Serializable;

import olderman.dungeon.inventory.Weapon;

public class ForAll implements Serializable {
	private static final long serialVersionUID = 1L;

	// Game variables
	public int floor = 1;

	public void resetDrinkHealthPotionCount() {
		hasDrunkHealthPotion = false;
	}

	// Player constants
	public int energy = 100;
	public static final int LEVEL_UP_HEALTH = 20;
	public static final int POTION_OF_STRENGTH_POWER = 500;
	public static final int SMALL_HEALTH_POTION_DROP_CHANCE = 80; // %
	public static final int MEDIUM_HEALTH_POTION_DROP_CHANCE = 50; // %
	public static final int LARGE_HEALTH_POTION_DROP_CHANCE = 20; // %

	// booleans
	private boolean firstTimeTown = true;

	// Player variables
	public Weapon selectedWeapon = Weapon.HAND;
	public int maximumHealth;
	public int enemiesKilled = 0; // -1
	public int missChance;
	public int health;
	public int gold = 0;
	public int bossesKilled = 0;
	public int beerCost = 50;
	public int lCapacity = 0;
	public int woodenAxe = 1;
	public int ironAxe = 0;
	public int ironAxeCost = 300;
	public int silverAxe = 0;
	public int silverAxeCost = 500;
	public int bronzeAxe = 0;
	public int bronzeAxeCost = 100;
	public int houseCost = 650;
	public int house = 0;
	public int chestChance = 5; // %
	public boolean hasDrunkHealthPotion = false;
	public int resistence = 100; // %
	public int level = 1;
	public int levelUp = 50;
	public int experience = 0;
	public int numPotionOfStrength = 0;
	public int numPotionOfInvisibility = 3;
	public int numPotionOfTeleportation = 0;
	public int numPotionOfConcentration = 0;
	public int bed = 0;
	public int knife = 0;
	public int knifeCost = 200;

	// Potion ingredients
	public int wood = 0;
	public int woodHeft = 15;
	public int bird = 0;
	public int birdHeft = 5;
	public int birdChance = 50; // %
	public int feather = 0;
	public int frogLeg = 0;
	public int frogHeart = 0;
	public int frogLeftEye = 0;
	public int frogRightEye = 0;
	public int worm = 0;
	public int wormHeft = 5;
	public int wormChance = 10; // %
	public int snakeBrain = 0;
	public int squirelTails = 0;
	public int frogHead = 0;
	public int snake = 0;
	public int snakeHeft = 5;
	public int snakeChance = 10; // %
	public int flashk = 0;
	public int flashkCost = 100;
	public int frog = 0;
	public int frogHeft = 10;
	public int frogChance = 10; // %
	public int squirrel = 0;
	public int squirrelHeft = 5;
	public int squirelChance = 10; // %

	// costs
	public int bedCostWood = 5;
	public int bedCostFeather = 100;
	public int woodForBed = bedCostWood - wood;
	public int featherForBed = bedCostFeather - feather;

	// remove ingrediences
	public void addWorm() {
		worm++;
		lCapacity += wormHeft;
	}

	public void removeWorm() {
		worm--;
		lCapacity -= wormHeft;

	}

	public void removeBird() {
		bird--;
		lCapacity -= birdHeft;
	}

	public void removeFrog() {
		frog--;
		lCapacity -= frogHeft;
	}

	public void removeSquirrel() {
		squirrel--;
		lCapacity -= squirrelHeft;
	}

	public void removeSnake() {
		snake--;
		lCapacity -= snakeHeft;
	}

	public void removeWood() {
		wood--;
		lCapacity -= woodHeft;
	}

	public boolean isFirstTimeTown() {
		return firstTimeTown;
	}

	public void setFirstTimeTown(boolean firstTimeTown) {
		this.firstTimeTown = firstTimeTown;
	}

}
