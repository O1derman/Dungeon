package com.example.olderman.dungeon;

public enum GameCharacter {

	DWARF {
		{
			initialHealth = 100;
			initialMaximumHealth = 100;
			missChance = 20;
			luck = 20;
			loadCapacity = 80;
			damage = new RandomValue(20 /* minimum damage */, 20 /* damage range */, 5 /* increase per level */);
			beginning = "\t> You are a small stinky dwarf with the same face as all the other dwarfs.\n";
			asciiArt = Resources.getString("/dwarf");
		}
	},
	ELF {
		{
			initialHealth = 100;
			initialMaximumHealth = 120;
			missChance = 5;
			luck = 10;
			loadCapacity = 30;
			damage = new RandomValue(10 /* minimum damage */, 10 /* damage range */, 5 /* increase per level */);
			beginning = "\t> You are a tall elf, very clever and handy, not very strong.\n";
			asciiArt = Resources.getString("/elf");
		}
	},
	GOBLIN {
		{
			initialHealth = 150;
			initialMaximumHealth = 150;
			missChance = 10;
			luck = 100;
			loadCapacity = 50;
			damage = new RandomValue(35 /* minimum damage */, 5 /* damage range */, 5 /* increase per level */);
			beginning = "\t> You are small hardworking goblin!.\n";
			asciiArt = Resources.getString("/goblin");
		}
	},
	ORC {
		{
			initialHealth = 200;
			initialMaximumHealth = 200;
			missChance = 30;
			luck = 0;
			loadCapacity = 500;
			damage = new RandomValue(40 /* minimum damage */, 20 /* damage range */, 5 /* increase per level */);
			beginning = "\t> You are huge orc staring into nothing...you don't have to outsmart them when you can smash them. \n";
			asciiArt = Resources.getString("/orc");
		}
	},
	SUPERMAN {
		{
			initialHealth = 999999999;
			initialMaximumHealth = 999999999;
			missChance = 0;
			luck = 100;
			loadCapacity = 999999999;
			damage = new RandomValue(999999999 /* minimum damage */, 0 /* damage range */, 0 /* increase per level */);
			beginning = "\t> You are SUPER SUPERMAN, you have unlimited power until you meet criptonit.\n";
			asciiArt = Resources.getString("/superman");
		}
	};

	protected int initialHealth;
	protected int initialMaximumHealth;
	protected int missChance;
	protected int luck;
	protected int loadCapacity;
	protected RandomValue damage;
	protected String beginning;
	protected String asciiArt;

	public int getInitialHealth() {
		return initialHealth;
	}

	public int getMissChance() {
		return missChance;
	}

	public int getLuck() {
		return luck;
	}

	public int getLoadCapacity() {
		return loadCapacity;
	}

	public int getInitialMaximumHealth() {
		return initialMaximumHealth;
	}

	public RandomValue getDamage() {
		return damage;
	}

	public String getBeginning() {
		return beginning;
	}

	public String getAsciiArt() {
		return asciiArt;
	}

}
