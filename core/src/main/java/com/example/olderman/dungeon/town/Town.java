package com.example.olderman.dungeon.town;

import java.util.Scanner;

import com.example.olderman.dungeon.Dungeon;
import com.example.olderman.dungeon.shop.Shop;
import com.example.olderman.dungeon.shop.ToolShop;

public class Town {

	public Town(Dungeon dungeon) {

		this.dungeon = dungeon;
		shop = new Shop(dungeon);
		workHouse = new WorkHouse(dungeon);
		house = new House(dungeon);
		forest = new Forest(dungeon);
		toolShop = new ToolShop(dungeon);
		pub = new Pub(dungeon);

	}

	private final Dungeon dungeon;
	private final Pub pub;
	private final ToolShop toolShop;
	private final Shop shop;
	private final WorkHouse workHouse;
	private final House house;
	private final Forest forest;
	boolean firstTime = true;
	Scanner scanner = new Scanner(System.in);
	String playersAnswer;
	String rightAnswer = "a right angle";

	public void town() throws InterruptedException {
		dungeon.clear();

		if (firstTime) {
			dungeon.println("***********************");
			dungeon.println("* WELCOME in Stander! *");
			dungeon.println("***********************");
			firstTime = false;
		}
		// dungeon.println("\n\tan old poorly looking wanderer: HEY STRANGER!");
		// dungeon.println("\n\tan old poorly looking wanderer: Yea, YOU!");
		// dungeon.println("\n\tan old poorly looking wanderer: Come
		// closer.\n");
		// switch (dungeon.uzivatVolba("Come closer to him", "Don't mind him at
		// all",
		// "Yell at him",
		// "Try to kill him")) {
		// case 1:
		// dungeon.println("\n\tI will give you a conundrum.");
		// dungeon.println("\n\tWhat can be right but never wrong?...(write it
		// with article!)");
		// playersAnswer = scanner.nextLine();
		// if (rightAnswer.equals(playersAnswer)) {
		// dungeon.println("\tCongratulation, you answered correctly!");
		// dungeon.println("\n\tI give you 4 potions of invisibility and a
		// knife!");
		// dungeon.getForAll().numPotionOfInvisibility += 4;
		// dungeon.getForAll().knife++;
		// } else {
		// dungeon.println("\tYour answer was not right!");
		// }
		// break;
		// case 2:
		// break;
		// case 3:
		// dungeon.println("\tHe drank some potion and disappeared.");
		// break;
		// case 4:
		// dungeon.println("\tHe said some words you didn't understand and
		// teleported
		// away!");
		// break;
		// }
		// }

		BACK1: while (dungeon.getForAll().house == 0)

		{
			dungeon.println("You can buy house for " + dungeon.getForAll().houseCost + "g");
			switch (dungeon.uzivatVolba("Go in weapon shop", "Go in tool shop", "Buy house", "Go in workhouse",
					"Go in the forest", "Go in pub", "Back")) {
			case 1:
				shop.shop();
				continue BACK1;
			case 2:
				toolShop.shop();
			case 3:
				if (dungeon.getForAll().gold < dungeon.getForAll().houseCost) {
					dungeon.println("You don't have enough gold!");

				} else {
					dungeon.getForAll().house++;
					dungeon.getForAll().gold -= dungeon.getForAll().houseCost;
				}
				continue BACK1;

			case 4:
				workHouse.workHouse();
				continue BACK1;
			case 5:
				forest.cutTrees();
				continue BACK1;
			case 6:
				pub.inside();
			}
			break;

		}
		BACK2: while (dungeon.getForAll().house > 0) {
			switch (dungeon.uzivatVolba("Go in shop", "Go in tool shop", "Go in house", "Go in workhouse", "Go in pub",
					"Back")) {
			case 1:
				continue BACK2;
			case 2:
				toolShop.shop();
			case 3:
				house.inside();
				continue BACK2;
			case 4:
				workHouse.workHouse();
				continue BACK2;
			case 5:
				pub.inside();
			}
			break;

		}

	}
}