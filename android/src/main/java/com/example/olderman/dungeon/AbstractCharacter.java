package com.example.olderman.dungeon;

public abstract class AbstractCharacter implements Character {

	private final Dungeon dungeon;

	private int health;
	private int maximumHealth;
	private int attackDamage;
	private int experienceForVictory;
	private int missChance;
	private int flatDamage;

	public AbstractCharacter(Dungeon dungeon) {
		this.dungeon = dungeon;
	};

	@Override
	public void setFlatDamage(int flatDamage) {
		this.flatDamage = flatDamage;
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public int getMissChance() {
		return missChance;
	}

	@Override
	public int getFlatDamage() {
		return flatDamage;
	}

	@Override
	public void setMaximumHealth(int maximumHealth) {
		this.maximumHealth = maximumHealth;
	}

	@Override
	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	@Override
	public void setExperienceForVictory(int experienceForVictory) {
		this.experienceForVictory = experienceForVictory;
	}

	@Override
	public int getMaximumHealth() {
		return maximumHealth;
	}

	@Override
	public int getAttackDamage() {
		return attackDamage;
	}

	public int getExperienceForVictory() {
		return experienceForVictory;
	}

	@Override
	public void setHealth(int health) {
		this.health = health;
	}

	@Override
	public int setMissChance(int missChance) {
		return this.missChance = missChance;
	}

	@Override
	public void decreaseHealth(int healthDecrease) {
		health -= healthDecrease;
	}

	@Override
	public void increaseHealth(int healthIncrease) {
		health += healthIncrease;
		if (health > maximumHealth) {
			health = maximumHealth;
		}
	}

	@Override
	public void drinkPotion(ForAll forAll) {
		dungeon.println("\tWhich one?");
		int volba = dungeon.uzivatVolba("Small health potion", "Medium health potion", "Large health potion", "Exit");

		if (volba == 1) {
			if (forAll.numSmallHealthPotions > 0) {
				if (health <= maximumHealth - 30) {
					health += forAll.smallHealthPotionHealAmount;
					forAll.numSmallHealthPotions--;
					dungeon.println("\t> You drink a small health potion, healing yourself for " + forAll.smallHealthPotionHealAmount + "." + "\n\t> You now have " + health + "HP."
							+ "\n\t> You have " + forAll.numSmallHealthPotions + " small health potions left. \n\n\n\n\n");
				} else {
					forAll.numSmallHealthPotions--;
					health = maximumHealth;
					dungeon.println("You drank health potion while you would overheal your maximum health, you have maximum health now!");
				}

			} else {
				dungeon.println("\t> You have no small health potions left! Defeat enemies for a chance to get one!\n\n\n\n\n");
			}

		} else if (volba == 2) {
			if (forAll.numMediumHealthPotions > 0) {
				if (health <= maximumHealth - 50) {
					health += forAll.mediumHealthPotionHealAmount;
					forAll.numMediumHealthPotions--;
					dungeon.println("\t> You drink a medium health potion, healing yourself for " + forAll.mediumHealthPotionHealAmount + "." + "\n\t> You now have " + health + "HP."
							+ "\n\t> You have " + forAll.numMediumHealthPotions + " medium health potions left. \n\n\n\n\n");
				} else {
					forAll.numMediumHealthPotions--;
					health = maximumHealth;
					dungeon.println("You drank health potion while you would overheal your maximum health, you have maximum health now!");
				}
			} else {
				dungeon.println("\t> You have no medium health potions left! Defeat enemies for a chance to get one!\n\n\n\n\n");
			}

		} else if (volba == 3) {
			if (forAll.numLargeHealthPotions > 0) {
				if (health <= maximumHealth - 80) {
					health += forAll.largeHealthPotionHealAmount;
					forAll.numLargeHealthPotions--;
					dungeon.println("\t> You drink a large health potion, healing yourself for " + forAll.largeHealthPotionHealAmount + "." + "\n\t> You now have " + health + "HP."
							+ "\n\t> You have " + forAll.numLargeHealthPotions + " large health potions left. \n\n\n\n\n");
				} else {
					forAll.numLargeHealthPotions--;
					health = maximumHealth;
					dungeon.println("You drank health potion while you would overheal your maximum health, you have maximum health now!");
				}

			} else {
				dungeon.println("\t> You have no large health potions left! Defeat enemies for a chance to get one!\n\n\n\n\n");
			}

		} else if (volba == 4) {

		} else {
			dungeon.println("Invalid command");
		}
		setHealth(health);
	}

	@Override
	public void increaseAttackDamage(int damageIncrease) {
		attackDamage += damageIncrease;

	}

	@Override
	public void increaseMaximumHealth(int maximumHealthIncrease) {
		maximumHealth += maximumHealthIncrease;

	}

	@Override
	public void decreaseEnemyDamage(int myDamageReduction) {
		dungeon.getForAll().enemyAttackDamage -= myDamageReduction;
	}

	public void increaseGold(int goldIncrease) {
		dungeon.getForAll().gold += goldIncrease;
	}

}
