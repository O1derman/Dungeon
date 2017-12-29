package olderman.dungeon.map;

import java.io.Serializable;

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
		while (true) {
			wayChoice = dungeon.uzivatVolba("East", "North", "South", "West");
			previousW = randMap.data.w;
			previousL = randMap.data.l;
			switch (wayChoice) {
			case 1:
				randMap.goStraight();
				break;
			case 2:
				randMap.goLeft();
				break;
			case 3:
				randMap.goRight();
				break;
			case 4:
				randMap.goBackwards();
				break;

			}
			break;
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
