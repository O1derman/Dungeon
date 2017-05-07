package com.example.olderman.dungeon;

public class Orc extends AbstractCharacter {

	public Orc(Dungeon dungeon) {
		super(dungeon);
		setHealth(250);
		setMaximumHealth(250);
		setAttackDamage(10);
		setExperienceForVictory(420);
		setMissChance(30);
		setFlatDamage(40);
	};

	public String getBeginning() {

		return "\t> You are huge orc staring into nothing...you don't have to outsmart them when you can crush them. \n";

	}

}
