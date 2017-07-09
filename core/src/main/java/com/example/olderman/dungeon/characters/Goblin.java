package com.example.olderman.dungeon.characters;

import com.example.olderman.dungeon.AbstractCharacter;
import com.example.olderman.dungeon.RandomValue;

public class Goblin extends AbstractCharacter {

	public Goblin() {
		this.initialHealth = 150;
		this.initialMaximumHealth = 150;
		this.missChance = 10;
		this.luck = 100;
		this.loadCapacity = 50;
		this.damage = new RandomValue(35 /* minimum damage */, 5 /* damage range */, 0, 5 /* increase per level */);
	}

	@Override
	public String getBeginning() {

		return "\t> You are small hardworking goblin!.\n";

	}

}