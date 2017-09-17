package olderman.dungeon;

import java.io.Serializable;

public enum GameCharacter implements Serializable {

	DWARF {
		{
			initialHealth = 100;
			initialMaximumHealth = 100;
			missChance = 20;
			luck = 20;
			loadCapacity = 80;
			damage = new RandomValue(20 /* minimum damage */,
					20 /* damage range */, 5 /* increase per level */);
			beginning = "You are a small stinky dwarf with the same face as all other dwarfs.\n";
			asciiArt = Resources.getString("/Dwarf");
		}
	},
	ELF {
		{
			initialHealth = 100;
			initialMaximumHealth = 120;
			missChance = 5;
			luck = 10;
			loadCapacity = 30;
			damage = new RandomValue(10 /* minimum damage */,
					10 /* damage range */, 5 /* increase per level */);
			beginning = "You are a tall elf, very clever and handy, not very strong.\n";
			asciiArt = Resources.getString("/Elf");
		}
	},
	GOBLIN {
		{
			initialHealth = 150;
			initialMaximumHealth = 150;
			missChance = 10;
			luck = 100;
			loadCapacity = 50;
			damage = new RandomValue(35 /* minimum damage */,
					5 /* damage range */, 5 /* increase per level */);
			beginning = "You are small hardworking goblin!.\n";
			asciiArt = Resources.getString("/Goblin");
		}
	},
	ORC {
		{
			initialHealth = 200;
			initialMaximumHealth = 200;
			missChance = 30;
			luck = 0;
			loadCapacity = 500;
			damage = new RandomValue(40 /* minimum damage */,
					20 /* damage range */, 5 /* increase per level */);
			beginning = "You are huge orc staring into nothing...you don't have to outsmart them when you can smash them. \n";
			asciiArt = Resources.getString("/Orc");
		}
	},
	SUPERMAN {
		{
			initialHealth = 999999999;
			initialMaximumHealth = 999999999;
			missChance = 0;
			luck = 100;
			loadCapacity = 999999999;
			damage = new RandomValue(999999999 /* minimum damage */,
					0 /* damage range */, 0 /* increase per level */);
			beginning = "You are SUPER SUPERMAN, you have unlimited power until you meet kryptonite.\n";
			asciiArt = Resources.getString("/Superman");
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
