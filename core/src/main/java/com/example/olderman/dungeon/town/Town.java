package com.example.olderman.dungeon.town;

import java.util.Scanner;

import com.example.olderman.dungeon.Dungeon;
import com.example.olderman.dungeon.shop.Shop;

public class Town {

	public Town(Dungeon dungeon) {

		this.dungeon = dungeon;
		shop = new Shop(dungeon);
		workHouse = new WorkHouse(dungeon);
		house = new House(dungeon);
		forest = new Forest(dungeon);

	}

	private final Dungeon dungeon;
	private final Shop shop;
	private final WorkHouse workHouse;
	private final House house;
	private final Forest forest;
	boolean firstTime = false;
	Scanner scanner = new Scanner(System.in);
	String playersAnswer;
	String rightAnswer = "a right angle";

	public void town() throws InterruptedException {
		dungeon.println("Welcome in Stander!");
		if (firstTime) {
			firstTime = false;
			dungeon.println("\n\tan old poorly looking wanderer: HEY STRANGER!");
			dungeon.println("\tan old poorly looking wanderer: Yea, YOU!");
			dungeon.println("\tan old poorly looking wanderer: Come closer.\n");
			switch (dungeon.uzivatVolba("Come closer to him", "Don't mind him at all", "Yell at him",
					"Try to kill him")) {
			case 1:
				dungeon.println("I will give you a conundrum.");
				dungeon.println("What can be right but never wrong?...(write it witharticle!)");
				playersAnswer = scanner.nextLine();
				if (rightAnswer.equals(playersAnswer)) {
					dungeon.println("Congratulation, you answered correctly!");
					dungeon.println("I give you 4 potions of invisibility and a knife!");
					dungeon.getForAll().numPotionOfInvisibility += 4;
					dungeon.getForAll().knife++;
				} else {
					dungeon.println("Your answer was not right!");
				}
				break;
			case 2:
				break;
			case 3:
				dungeon.println("He drank some potion and disappeared.");
				break;
			case 4:
				dungeon.println("He said some words you didn't understand and teleported away!");
				break;
			}
		}

		BACK1: while (dungeon.getForAll().house == 0)

		{
			switch (dungeon.uzivatVolba("Go in shop", "Buy house", "Go in workhouse", "Go in the forest", "Back")) {
			case 1:
				shop.shop();
				continue BACK1;
			case 2:
				dungeon.getForAll().house++;
				continue BACK1;

			case 3:
				workHouse.workHouse();
				continue BACK1;
			case 4:
				forest.cutTrees();
				continue BACK1;

			}
			break;

		}
		BACK2: while (dungeon.getForAll().house > 0) {
			switch (dungeon.uzivatVolba("Go in shop", "Go in house", "Go in workhouse", "Back")) {
			case 1:
				continue BACK2;
			case 2:
				house.inside();
				continue BACK2;
			case 3:
				workHouse.workHouse();
				continue BACK2;
			}
			break;

		}

	}
}