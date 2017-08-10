package com.example.olderman.dungeon.shop;

import com.example.olderman.dungeon.Dungeon;

public class ToolShop {

	public ToolShop(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	private final Dungeon dungeon;
	boolean firstTime = true;

	public void shop() {
		dungeon.println("Welcome in \"Digged monster\"!");
		dungeon.println();
		while (firstTime) {
			dungeon.println("When you buy any axe, you lose the previous one!");
			dungeon.println();
			firstTime = false;
		}
		choices: while (true) {
			int volba = dungeon.uzivatVolba(
					"Knife (" + dungeon.getForAll().knifeCost + "Gold)...allows you to separate animals!",
					"Bronze axe (" + dungeon.getForAll().bronzeAxeCost + "Gold)...allows you to cut trees faster!",
					"Iron axe (" + dungeon.getForAll().ironAxeCost + "Gold)...allows you to cut trees faster!",
					"Silver axe (" + dungeon.getForAll().silverAxeCost + "Gold)...allows you to cut trees faster!",
					"Go away");
			switch (volba) {
			case 1:
				if (dungeon.getForAll().gold < dungeon.getForAll().knifeCost) {
					dungeon.println("You don't have enough gold!");
					continue choices;
				} else {
					dungeon.getForAll().knife++;
					dungeon.println("You bought a knife.");
					continue choices;
				}
			case 2:
				if (dungeon.getForAll().gold < dungeon.getForAll().bronzeAxeCost) {
					dungeon.println("You don't have enough gold!");
					continue choices;
				} else {
					dungeon.getForAll().bronzeAxe++;
					dungeon.getForAll().ironAxe = 0;
					dungeon.getForAll().silverAxe = 0;
					dungeon.println("You bought a bronze axe.");
					continue choices;
				}
			case 3:
				if (dungeon.getForAll().gold < dungeon.getForAll().ironAxeCost) {
					dungeon.println("You don't have enough gold!");
					continue choices;
				} else {
					dungeon.getForAll().ironAxe++;
					dungeon.getForAll().silverAxe = 0;
					dungeon.getForAll().bronzeAxe = 0;
					dungeon.println("You bought a iron axe.");
					continue choices;
				}
			case 4:
				if (dungeon.getForAll().gold < dungeon.getForAll().silverAxeCost) {
					dungeon.println("You don't have enough gold!");
					continue choices;
				} else {
					dungeon.getForAll().silverAxe++;
					dungeon.println("You bought a silver axe.");
					dungeon.getForAll().bronzeAxe = 0;
					dungeon.getForAll().ironAxe = 0;
					continue choices;
				}
			}
			break;
		}
	}

}
