package com.example.olderman.dungeon;

import java.io.Serializable;

import com.example.olderman.dungeon.inventory.Weapon;

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

	// Player variables
	public Weapon selectedWeapon = Weapon.HAND;
	public int maximumHealth;
	public int health;
	public int gold = 0;
	public int bossesKilled = 0;
	public int woodenAxe = 0;
	public int ironAxe = 0;
	public int silverAxe = 0;
	public int goldenAxe = 0;
	public int house = 0;
	public int chestChance = 5; // %
	public boolean hasDrunkHealthPotion = false;
	public int resistence = 100; // %
	public int level = 1;
	public int levelUp = 50 * level;
	public int experience = 0;
	public int numPotionOfStrength = 0;
	public int numPotionOfInvisibility = 0;
	public int numPotionOfTeleportation = 0;
	public int numPotionOfConcentration = 0;
	public int bed = 0;
	public int pot = 0;
	public int knife = 0;

	// Potion ingredients
	public int wood = 0;
	public int bird = 0;
	public int birdChance = 10; // %
	public int feather = 0;
	public int frogLeg = 0;
	public int frogHeart = 0;
	public int frogLeftEye = 0;
	public int frogRightEye = 0;
	public int worm = 0;
	public int wormChance = 10; // %
	public int sneakBrain = 0;
	public int squirelTails = 0;
	public int frogHead = 0;
	public int sneak = 0;
	public int sneakChance = 10; // %
	public int flashk = 0;
	public int frog = 0;
	public int frogChance = 10; // %
	public int squirel = 0;
	public int squirelChance = 10; // %

	// costs
	public int bedCostWood = 5;
	public int bedCostFeather = 100;
	public int woodForBed = bedCostWood - wood;
	public int featherForBed = bedCostFeather - feather;

}
