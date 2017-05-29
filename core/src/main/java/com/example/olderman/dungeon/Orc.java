package com.example.olderman.dungeon;

public class Orc extends AbstractCharacter {

	public Orc() {
		this.initialHealth = 200;
		this.initialMaximumHealth = 200;
		this.experienceForVictory = 420;
		this.missChance = 30;
		this.luck = 0;
		this.damage = new RandomValue(40 /* minimum damage */,
				20 /* damage range */, 0, 5 /* increase per level */);
	}

    @Override
	public String getBeginning() {

		return "\t> You are huge orc staring into nothing...you don't have to outsmart them when you can crush them. \n";

	}

}
