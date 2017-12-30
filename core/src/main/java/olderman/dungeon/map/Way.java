package olderman.dungeon.map;

import java.io.Serializable;
import java.util.ArrayList;

import olderman.dungeon.Dungeon;
import olderman.dungeon.Style;

public class Way implements Serializable {
	private static final long serialVersionUID = 1L;

	public Way(Dungeon dungeon) {
		this.dungeon = dungeon;
		this.randMap = new RandMap(dungeon);

	}

	private final Dungeon dungeon;
	public RandMap randMap;
	public int wayChoice;
	int previousW;
	int previousL;

	public void go() {
		dungeon.println(Style.CENTER, "Which way?");
		previousW = randMap.data.w;
		previousL = randMap.data.l;

		Room east = randMap.getNextRoom(DirectionEnum.EAST);
		Room west = randMap.getNextRoom(DirectionEnum.WEST);
		Room south = randMap.getNextRoom(DirectionEnum.SOUTH);
		Room north = randMap.getNextRoom(DirectionEnum.NORTH);

		ArrayList<String> choices = new ArrayList<>();
		if (east != null) {
			choices.add(DirectionEnum.EAST.getDescription());
		}
		if (north != null) {
			choices.add(DirectionEnum.NORTH.getDescription());
		}
		if (south != null) {
			choices.add(DirectionEnum.SOUTH.getDescription());
		}
		if (west != null) {
			choices.add(DirectionEnum.WEST.getDescription());
		}

		wayChoice = dungeon.uzivatVolba(choices.toArray(new String[choices.size()]));

		String stringChoice = choices.get(wayChoice - 1);

		if (stringChoice.equals(DirectionEnum.EAST.getDescription())) {
			randMap.goStraight();
		} else if (stringChoice.equals(DirectionEnum.NORTH.getDescription())) {
			randMap.goLeft();
		} else if (stringChoice.equals(DirectionEnum.SOUTH.getDescription())) {
			randMap.goRight();
		} else if (stringChoice.equals(DirectionEnum.WEST.getDescription())) {
			randMap.goBackwards();
		}
	}

	public void back() {
		boolean back = true;
		while (back) {

			if (wayChoice == 1) {
				randMap.goBackwards();
				break;
			}
			if (wayChoice == 2) {
				randMap.goRight();
				break;
			}
			if (wayChoice == 3) {
				randMap.goLeft();
				break;
			}
			if (wayChoice == 4) {
				randMap.goStraight();
				break;
			}
			back = false;
		}

	}
}
