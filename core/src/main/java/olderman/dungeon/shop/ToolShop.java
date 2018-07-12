package olderman.dungeon.shop;

import static olderman.dungeon.Style.YELLOW;

import java.io.Serializable;

import olderman.dungeon.Dungeon;
import olderman.dungeon.Style;

public class ToolShop implements Serializable {
	private static final long serialVersionUID = 1L;

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
		choices: while (true) {
			dungeon.clear();
			dungeon.println(Style.CENTER, "Welcome in \"Digged monster\"!");
			dungeon.println();
			if (firstTime) {
				dungeon.println(Style.CENTER, "When you buy any axe, you lose the previous one!");
				dungeon.println();
				firstTime = false;
			}
			dungeon.println("You got:");
			dungeon.println(YELLOW.BRIGHT, dungeon.getForAll().gold + " gold");
			String knife = "Knife (" + dungeon.getForAll().knifeCost + "Gold)...allows you to separate animals!";
			String flask = "Flask (" + dungeon.getForAll().flashkCost + "Gold)...needed to be able to create potions!";
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
				bronzeAxe = "Bronze axe - owned";
			}
			if (ownIronAxe) {
				ironAxe = "Iron axe - owned";
			}
			if (ownSilverAxe) {
				silverAxe = "Silver axe - owned";
			}
			int volba = dungeon.uzivatVolba(knife, flask, bronzeAxe, ironAxe, silverAxe, "Go away");
			switch (volba) {
			case 1:
				if (ownKnife) {
					dungeon.println(Style.CENTER, "You already own knife.");
					dungeon.uzivatVolba("Continue");
					continue choices;
				}
				if (dungeon.getForAll().gold < dungeon.getForAll().knifeCost) {
					dungeon.println(Style.CENTER, "You don't have enough gold!");
					dungeon.uzivatVolba("Continue");
					continue choices;
				} else {
					dungeon.getForAll().knife++;
					dungeon.getForAll().gold -= dungeon.getForAll().knifeCost;
					dungeon.println(Style.CENTER, "You bought a knife.");
					ownKnife = true;
					dungeon.uzivatVolba("Continue");
					continue choices;
				}
			case 2:
				if (dungeon.getForAll().gold < dungeon.getForAll().flashkCost) {
					dungeon.println(Style.CENTER, "You don't have enough gold!");
					dungeon.uzivatVolba("Continue");
					continue choices;
				} else {
					dungeon.getForAll().flashk++;
					dungeon.println(Style.CENTER, "You bought a flask.");
					dungeon.getForAll().gold -= dungeon.getForAll().flashkCost;
					dungeon.uzivatVolba("Continue");
					continue choices;
				}
			case 3:
				if (ownBronzeAxe) {
					dungeon.print(Style.CENTER, "You already own Bronze Axe.");
					dungeon.uzivatVolba("Continue");
					continue choices;
				}
				if (dungeon.getForAll().gold < dungeon.getForAll().bronzeAxeCost) {
					dungeon.println(Style.CENTER, "You don't have enough gold!");
					dungeon.uzivatVolba("Continue");
					continue choices;
				} else {
					dungeon.getForAll().bronzeAxe++;
					dungeon.getForAll().gold -= dungeon.getForAll().bronzeAxeCost;
					ownBronzeAxe = true;
					dungeon.getForAll().ironAxe = 0;
					dungeon.getForAll().silverAxe = 0;
					dungeon.getForAll().woodenAxe = 0;
					dungeon.println(Style.CENTER, "You bought a bronze axe.");
					dungeon.uzivatVolba("Continue");
					continue choices;
				}
			case 4:
				if (ownIronAxe) {
					dungeon.print(Style.CENTER, "You already own Iron Axe.");
					dungeon.uzivatVolba("Continue");
					continue choices;
				}
				if (dungeon.getForAll().gold < dungeon.getForAll().ironAxeCost) {
					dungeon.println(Style.CENTER, "You don't have enough gold!");
					dungeon.uzivatVolba("Continue");
					continue choices;
				} else {
					dungeon.getForAll().ironAxe++;
					dungeon.getForAll().gold -= dungeon.getForAll().ironAxeCost;
					ownIronAxe = true;
					dungeon.getForAll().silverAxe = 0;
					dungeon.getForAll().bronzeAxe = 0;
					dungeon.getForAll().woodenAxe = 0;
					dungeon.println(Style.CENTER, "You bought a iron axe.");
					dungeon.uzivatVolba("Continue");
					continue choices;
				}
			case 5:
				if (ownSilverAxe) {
					dungeon.print(Style.CENTER, "You already own Silver Axe.");
					dungeon.uzivatVolba("Continue");
					continue choices;
				}
				if (dungeon.getForAll().gold < dungeon.getForAll().silverAxeCost) {
					dungeon.println(Style.CENTER, "You don't have enough gold!");
					dungeon.uzivatVolba("Continue");
					continue choices;
				} else {
					dungeon.getForAll().silverAxe++;
					dungeon.println(Style.CENTER, "You bought a silver axe.");
					dungeon.getForAll().gold -= dungeon.getForAll().silverAxeCost;
					ownSilverAxe = true;
					dungeon.getForAll().bronzeAxe = 0;
					dungeon.getForAll().ironAxe = 0;
					dungeon.getForAll().woodenAxe = 0;
					dungeon.uzivatVolba("Continue");
					continue choices;
				}
			}
			break;
		}
	}

}
