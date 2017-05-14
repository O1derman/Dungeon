package com.example.olderman.dungeon;

public interface Character {

	
	
	public int getHealth();

	public void setHealth(int health);

	public int getMaximumHealth();

	public int getMissChance();

	public int setMissChance(int missChance);

	public int getAttackDamage();

	public String getBeginning();

	public int getExperienceForVictory();

	public void setMaximumHealth(int maximumHealth);

	public void setAttackDamage(int attackDamage);

	public void setExperienceForVictory(int experienceForVictory);

	public void decreaseHealth(int healthDecrease);

	public void increaseHealth(int healthIncrease);

	public void drinkPotion(ForAll forAll);

	public void increaseAttackDamage(int damageIncrease);

	public void increaseMaximumHealth(int maximumHealthIncrease);

	public void increaseGold(int goldIncrease);

	public void decreaseEnemyDamage(int enemyDamageReduction);
	
	public int getFlatDamage();
	
	public void setFlatDamage(int flatDamage);
	
	public int getLuck();
	
	public void setLuck(int luck);
}
