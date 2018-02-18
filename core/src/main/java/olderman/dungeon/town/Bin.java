package olderman.dungeon.town;

import java.util.ArrayList;

import olderman.dungeon.Dungeon;

public class Bin {

	private final Dungeon dungeon;

	public Bin(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	private int binChoices;
	boolean throwingItem = true;

	public void throwItems() {
		throwingItem = true;
		while (throwingItem) {
			dungeon.clear();
			dungeon.println("Your load capacity: " + dungeon.getForAll().lCapacity + "/"
					+ dungeon.getCharacter().getLoadCapacity());
			dungeon.printResources();
			ArrayList<String> choices = new ArrayList<>();
			if (dungeon.getForAll().snake > 0) {
				choices.add("snake");
			}
			if (dungeon.getForAll().frog > 0) {
				choices.add("frog");
			}
			if (dungeon.getForAll().worm > 0) {
				choices.add("worm");
			}
			if (dungeon.getForAll().squirrel > 0) {
				choices.add("squirrel");
			}
			if (dungeon.getForAll().wood > 0) {
				choices.add("wood");
			}
			if (dungeon.getForAll().bird > 0) {
				choices.add("bird");
			}
			if (dungeon.getForAll().feather > 0) {
				choices.add("feather");
			}
			if (dungeon.getForAll().squirelTails > 0) {
				choices.add("squirel tails");
			}
			if (dungeon.getForAll().snakeBrain > 0) {
				choices.add("snake brain");
			}
			if (dungeon.getForAll().frogRightEye > 0) {
				choices.add("frog right eye");
			}
			if (dungeon.getForAll().frogLeg > 0) {
				choices.add("frog leg");
			}
			if (dungeon.getForAll().frogLeftEye > 0) {
				choices.add("frog left eye");
			}
			if (dungeon.getForAll().frogHead > 0) {
				choices.add("frog head");
			}
			if (dungeon.getForAll().frogHeart > 0) {
				choices.add("frog heart");
			}
			choices.add("don't throw any item");

			binChoices = dungeon.uzivatVolba(choices.toArray(new String[choices.size()]));
			String stringChoice = choices.get(binChoices - 1);

			if (stringChoice.equals("snake")) {
				dungeon.getForAll().removeSnake();
			} else if (stringChoice.equals("frog")) {
				dungeon.getForAll().removeFrog();
			} else if (stringChoice.equals("worm")) {
				dungeon.getForAll().removeWorm();
			} else if (stringChoice.equals("squirrel")) {
				dungeon.getForAll().removeSquirrel();
			} else if (stringChoice.equals("wood")) {
				dungeon.getForAll().removeWood();
			} else if (stringChoice.equals("bird")) {
				dungeon.getForAll().removeBird();
			} else if (stringChoice.equals("feather")) {
				dungeon.getForAll().feather--;
			} else if (stringChoice.equals("squirel tails")) {
				dungeon.getForAll().squirelTails--;
			} else if (stringChoice.equals("snake brain")) {
				dungeon.getForAll().snakeBrain--;
			} else if (stringChoice.equals("frog right eye")) {
				dungeon.getForAll().frogRightEye--;
			} else if (stringChoice.equals("frog leg")) {
				dungeon.getForAll().frogLeg--;
			} else if (stringChoice.equals("frog left eye")) {
				dungeon.getForAll().frogLeftEye--;
			} else if (stringChoice.equals("frog head")) {
				dungeon.getForAll().frogHead--;
			} else if (stringChoice.equals("frog heart")) {
				dungeon.getForAll().frogHeart--;
			} else if (stringChoice.equals("don't throw any item")) {
				throwingItem = false;
			}
		}
	}
}