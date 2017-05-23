package com.example.olderman.dungeon;

public class Orc extends AbstractCharacter {

	public Orc() {
		setHealth(200);
		this.maximumHealth = 200;
		this.flatDamage = 40;
		this.attackDamage = 10;
		this.experienceForVictory = 420;
		this.missChance = 30;
		this.luck = 0;
	};

	public String getBeginning() {

		return "\t> You are huge orc staring into nothing...you don't have to outsmart them when you can crush them. \n";

	}

}
