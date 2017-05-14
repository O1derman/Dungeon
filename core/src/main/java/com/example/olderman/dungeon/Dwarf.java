package com.example.olderman.dungeon;

public class Dwarf extends AbstractCharacter {

	public Dwarf(Dungeon dungeon) {
		super(dungeon);
		setHealth(100);
		setMaximumHealth(100);
		setAttackDamage(10);
		setExperienceForVictory(230);
		setMissChance(20);
		setFlatDamage(20);
		setLuck(20);


	};

	@Override
	public String getBeginning() {
		return "\t> You are a small stinky dwarf with the same face as all the other dwarfs.\n";

	}

}
