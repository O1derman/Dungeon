package com.example.olderman.dungeon;

public class Dwarf extends AbstractCharacter {

	public Dwarf() {
		setHealth(100);
		this.maximumHealth = 100;
		this.flatDamage = 20;
		this.attackDamage = 10;
		this.experienceForVictory = 230;
		this.missChance = 20;
		this.luck = 20;
	};

	@Override
	public String getBeginning() {
		return "\t> You are a small stinky dwarf with the same face as all the other dwarfs.\n";

	}

}
