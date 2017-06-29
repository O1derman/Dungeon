package com.example.olderman.dungeon.characters;

import com.example.olderman.dungeon.AbstractCharacter;
import com.example.olderman.dungeon.RandomValue;

public class Dwarf extends AbstractCharacter {

	public Dwarf() {
		this.initialHealth = 100;
		this.initialMaximumHealth = 100;
		this.experienceForVictory = 230;
		this.missChance = 20;
		this.luck = 20;
		this.damage = new RandomValue(20 /* minimum damage */,
				20 /* damage range */, 0, 5 /* increase per level */);
	}

	@Override
	public String getBeginning() {
		return "\t> You are a small stinky dwarf with the same face as all the other dwarfs.\n";

	}

}
