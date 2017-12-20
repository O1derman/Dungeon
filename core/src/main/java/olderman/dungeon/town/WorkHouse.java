package olderman.dungeon.town;

import olderman.dungeon.Dungeon;
import olderman.dungeon.Style;

public class WorkHouse {

	public WorkHouse(Dungeon dungeon) {
		this.dungeon = dungeon;

	}

	private final Dungeon dungeon;
	String cut = "You don't have the animal or you don't have a knife to cut it.";
	String separate = "You separated ";

	public void workHouse() {
		while (true) {
			dungeon.clear();
			dungeon.println(Style.CENTER, "What do you want to work on?");
			dungeon.println();
			dungeon.println("You got:");
			dungeon.printResources();
			int posibilities = dungeon.uzivatVolba("Cut frog", "Cut sneak", "Cut squirel", "Cut bird", "Build bed",
					"Exit");
			if (posibilities == 1) {
				if (dungeon.getForAll().knife == 1 && dungeon.getForAll().frog >= 1) {
					dungeon.println(Style.CENTER, separate + "a frog.");
					dungeon.getForAll().frogLeg++;
					dungeon.getForAll().frogHeart++;
					dungeon.getForAll().frogHead++;
					dungeon.getForAll().frogLeftEye++;
					dungeon.getForAll().frogRightEye++;
					dungeon.getForAll().frog--;
				} else {
					dungeon.println(Style.CENTER, cut);
				}

			}
			if (posibilities == 2) {
				if (dungeon.getForAll().knife == 1 && dungeon.getForAll().snake >= 1) {
					dungeon.println(Style.CENTER, separate + "a snake.");
					dungeon.getForAll().snakeBrain++;
					dungeon.getForAll().snake--;
				} else {
					dungeon.println(Style.CENTER, cut);
				}

			}
			if (posibilities == 3) {
				if (dungeon.getForAll().knife == 1 && dungeon.getForAll().squirrel >= 1) {
					dungeon.println(Style.CENTER, separate + "a squirel.");
					dungeon.getForAll().squirelTails++;
					dungeon.getForAll().squirrel--;
				} else {
					dungeon.println(Style.CENTER, cut);
				}
			}
			if (posibilities == 4) {
				if (dungeon.getForAll().knife == 1 && dungeon.getForAll().bird >= 1) {
					dungeon.println(Style.CENTER, separate + "a bird.");
					dungeon.getForAll().feather += 10;
					dungeon.getForAll().bird--;

				} else {
					dungeon.println(Style.CENTER, cut);
				}

			}
			if (posibilities == 5) {
				if (dungeon.getForAll().wood >= 5 && dungeon.getForAll().feather >= 100) {
					dungeon.println(Style.CENTER, "You built a bed.");
					dungeon.getForAll().feather -= dungeon.getForAll().featherForBed;
					dungeon.getForAll().wood -= dungeon.getForAll().woodForBed;
					dungeon.getForAll().bed++;
				} else {
					dungeon.println(Style.CENTER, "You don't have enough ingredients!");
					if (dungeon.getForAll().wood < 5 && dungeon.getForAll().feather >= 100) {
						dungeon.println(Style.CENTER, "You need " + dungeon.getForAll().woodForBed + " more wood.");
					} else if (dungeon.getForAll().wood >= 5 && dungeon.getForAll().feather < 100) {
						dungeon.println(Style.CENTER,
								"You need " + dungeon.getForAll().featherForBed + " more feathers.");
					} else {
						dungeon.println(Style.CENTER, "You need " + dungeon.getForAll().woodForBed + " more wood and "
								+ dungeon.getForAll().featherForBed + " more feathers.");
					}
				}

			}
			if (posibilities == 6) {
				break;
			}
			switch (dungeon.uzivatVolba("Continue")) {
			}
		}
	}
}
