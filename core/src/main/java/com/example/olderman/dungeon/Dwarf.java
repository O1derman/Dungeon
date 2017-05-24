package com.example.olderman.dungeon;

public class Dwarf extends AbstractCharacter {

	public Dwarf() {
		setHealth(100);
		this.maximumHealth = 100;
		this.experienceForVictory = 230;
		this.missChance = 20;
		this.luck = 20;
		this.damage = new RandomValue(20 /* minimum damage */,
				20 /* damage range */, 0, 5 /* increase per level */);
	};

	@Override
	public String getBeginning() {
		return "\t> You are a small stinky dwarf with the same face as all the other dwarfs.\n";

	}

}
