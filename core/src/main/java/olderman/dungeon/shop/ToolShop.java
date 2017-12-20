package olderman.dungeon.shop;

import static olderman.dungeon.Style.YELLOW;

import olderman.dungeon.Dungeon;
import olderman.dungeon.Style;

public class ToolShop {

	public ToolShop(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	private final Dungeon dungeon;
	boolean firstTime = true;
	boolean ownKnife = false;
	boolean ownBronzeAxe = false;
	boolean ownIronAxe = false;
	boolean ownSilverAxe = false;

	public void shop() {
		String knife = "Knife (" + dungeon.getForAll().knifeCost + "Gold)...allows you to separate animals!";
		String flashk = "Flashk (" + dungeon.getForAll().flashkCost + "Gold)...needed to be able to create potions!";
		String bronzeAxe = "Bronze axe (" + dungeon.getForAll().bronzeAxeCost
				+ "Gold)...allows you to cut trees faster!";
		String ironAxe = "Iron axe (" + dungeon.getForAll().ironAxeCost + "Gold)...allows you to cut trees faster!";
		String silverAxe = "Silver axe (" + dungeon.getForAll().silverAxeCost
				+ "Gold)...allows you to cut trees faster!";
		if (dungeon.getForAll().bronzeAxe > 0) {
			ownBronzeAxe = true;
		}
		if (dungeon.getForAll().ironAxe > 0) {
			ownIronAxe = true;
		}
		if (dungeon.getForAll().silverAxe > 0) {
			ownSilverAxe = true;
		}
		if (ownKnife) {
			knife = "Knife - owned";
		}
		if (ownBronzeAxe) {
			knife = "Bronze axe - owned";
		}
		if (ownIronAxe) {
			knife = "Iron axe - owned";
		}
		if (ownSilverAxe) {
			knife = "Silver axe - owned";
		}
		dungeon.println(Style.CENTER, "Welcome in \"Digged monster\"!");
		dungeon.println();
		if (firstTime) {
			dungeon.println("When you buy any axe, you lose the previous one!");
			dungeon.println();
			firstTime = false;
		}
		dungeon.println("You got:");
		dungeon.println(YELLOW.BRIGHT, dungeon.getForAll().gold + " gold");
		choices: while (true) {
			int volba = dungeon.uzivatVolba(knife, flashk, bronzeAxe, ironAxe, silverAxe, "Go away");
			switch (volba) {
			case 1:
				if (ownKnife) {
					dungeon.println("You already own knife.");
					continue choices;
				}
				if (dungeon.getForAll().gold < dungeon.getForAll().knifeCost) {
					dungeon.println("You don't have enough gold!");
					continue choices;
				} else {
					dungeon.getForAll().knife++;
					dungeon.getForAll().gold -= dungeon.getForAll().knifeCost;
					dungeon.println("You bought a knife.");
					ownKnife = true;
					continue choices;
				}
			case 2:
				if (dungeon.getForAll().gold < dungeon.getForAll().flashkCost) {
					dungeon.println("You don't have enough gold!");
					continue choices;
				} else {
					dungeon.getForAll().flashk++;
					dungeon.println("You bought a flashk.");
					dungeon.getForAll().gold -= dungeon.getForAll().flashkCost;
					continue choices;
				}
			case 3:
				if (ownBronzeAxe) {
					dungeon.print("You already own Bronze Axe.");
					continue choices;
				}
				if (dungeon.getForAll().gold < dungeon.getForAll().bronzeAxeCost) {
					dungeon.println("You don't have enough gold!");
					continue choices;
				} else {
					dungeon.getForAll().bronzeAxe++;
					dungeon.getForAll().gold -= dungeon.getForAll().bronzeAxeCost;
					dungeon.getForAll().ironAxe = 0;
					dungeon.getForAll().silverAxe = 0;
					dungeon.getForAll().woodenAxe = 0;
					dungeon.println("You bought a bronze axe.");
					continue choices;
				}
			case 4:
				if (ownIronAxe) {
					dungeon.print("You already own Iron Axe.");
					continue choices;
				}
				if (dungeon.getForAll().gold < dungeon.getForAll().ironAxeCost) {
					dungeon.println("You don't have enough gold!");
					continue choices;
				} else {
					dungeon.getForAll().ironAxe++;
					dungeon.getForAll().gold -= dungeon.getForAll().ironAxeCost;
					dungeon.getForAll().silverAxe = 0;
					dungeon.getForAll().bronzeAxe = 0;
					dungeon.getForAll().woodenAxe = 0;
					dungeon.println("You bought a iron axe.");
					continue choices;
				}
			case 5:
				if (ownSilverAxe) {
					dungeon.print("You already own Silver Axe.");
					continue choices;
				}
				if (dungeon.getForAll().gold < dungeon.getForAll().silverAxeCost) {
					dungeon.println("You don't have enough gold!");
					continue choices;
				} else {
					dungeon.getForAll().silverAxe++;
					dungeon.println("You bought a silver axe.");
					dungeon.getForAll().gold -= dungeon.getForAll().silverAxeCost;
					dungeon.getForAll().bronzeAxe = 0;
					dungeon.getForAll().ironAxe = 0;
					dungeon.getForAll().woodenAxe = 0;
					continue choices;
				}
			}
			break;
		}
	}

}
