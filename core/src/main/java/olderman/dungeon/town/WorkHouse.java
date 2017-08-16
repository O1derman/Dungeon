package olderman.dungeon.town;

import olderman.dungeon.Dungeon;

public class WorkHouse {

	public WorkHouse(Dungeon dungeon) {
		this.dungeon = dungeon;

	}

	private final Dungeon dungeon;
	String cut = "You dont have knife to cut it.";
	String separate = "You separated ";

	public void workHouse() {
		dungeon.println("What do you want to work on?");
		dungeon.println();
		while (true) {
			int posibilities = dungeon.uzivatVolba("Cut frog", "Cut sneak", "Cut squirel", "Bird", "Build bad", "Exit");
			if (posibilities == 1) {
				if (dungeon.getForAll().knife == 1 && dungeon.getForAll().frog >= 1) {
					dungeon.println("You separated frog.");
					dungeon.println("You got 2 frog legs, 2 frog eyes, 1 frog head and 1 frog heart.");
					dungeon.getForAll().frogLeg++;
					dungeon.getForAll().frogHeart++;
					dungeon.getForAll().frogHead++;
					dungeon.getForAll().frogLeftEye++;
					dungeon.getForAll().frogRightEye++;
					dungeon.getForAll().frog--;
				} else {
					dungeon.println(cut);
				}

			} else if (posibilities == 2) {
				if (dungeon.getForAll().knife == 1 && dungeon.getForAll().sneak >= 1) {
					dungeon.println(separate + "sneak.");
					dungeon.getForAll().sneakBrain++;
					dungeon.getForAll().sneak--;
				} else {
					dungeon.println(cut);
				}

			} else if (posibilities == 3) {
				if (dungeon.getForAll().knife == 1) {
					dungeon.println(separate + "squirel.");
					dungeon.getForAll().squirelTails++;
					dungeon.getForAll().squirel--;
				} else {
					dungeon.println(cut);
				}
			} else if (posibilities == 4) {
				if (dungeon.getForAll().knife == 1) {
					dungeon.println("bird.");
					dungeon.getForAll().feather++;
					dungeon.getForAll().bird--;

				} else {
					dungeon.println(cut);
				}

			} else if (posibilities == 5) {
				if (dungeon.getForAll().wood >= 5 && dungeon.getForAll().feather >= 100) {
					dungeon.println("You built a bad.");
					dungeon.println("You can save you game from now on.");
					dungeon.getForAll().feather -= dungeon.getForAll().featherForBed;
					dungeon.getForAll().wood -= dungeon.getForAll().woodForBed;
					dungeon.getForAll().bed++;
				} else {
					dungeon.println("You dont have enough ingrediences!");
					if (dungeon.getForAll().wood < 5 && dungeon.getForAll().feather >= 100) {
						dungeon.println("You need " + dungeon.getForAll().woodForBed + " more wood.");
					} else if (dungeon.getForAll().wood >= 5 && dungeon.getForAll().feather < 100) {
						dungeon.println("You need " + dungeon.getForAll().featherForBed + " more feathers.");
					} else {
						dungeon.println("You need " + dungeon.getForAll().woodForBed + " more wood and "
								+ dungeon.getForAll().featherForBed + " more feathers.\n\n");
					}
				}

			} else if (posibilities == 6) {
				break;
			}

		}
	}
}
