package olderman.dungeon.map;

import java.io.Serializable;

import olderman.dungeon.Dungeon;
import olderman.dungeon.Style;

public class Way implements Serializable {
	private static final long serialVersionUID = 1L;

	public Way(Dungeon dungeon) {
		this.dungeon = dungeon;

	}

	private final Dungeon dungeon;
	public Map1 map1 = new Map1();
	public int wayChoice;

	public void go() {
        dungeon.println(Style.CENTER, "Which way?");
        while (true) {
			if (map1.l == map1.leftEdge && map1.w == map1.leftEdge) { // left
																		// down
																		// corner

				wayChoice = dungeon.uzivatVolba("Straight", "Right");
				switch (wayChoice) {
				case 1:
					map1.goStraight();
					break;
				case 2:
					map1.goRight();
					break;

				}
				break;
			} else if (map1.l == map1.rightEdge && map1.w == map1.leftEdge) { // left
																				// up
																				// corner
				wayChoice = dungeon.uzivatVolba("Right", "Backwards");
				switch (wayChoice) {
				case 1:
					map1.goRight();
					break;
				case 2:
					map1.goBackwards();
					break;
				}
				break;

			} else if (map1.l == map1.leftEdge && map1.w == map1.rightEdge) { // right
																				// down
																				// corner
				wayChoice = dungeon.uzivatVolba("Straight", "Right");
				switch (wayChoice) {
				case 1:
					map1.goStraight();
					break;
				case 2:
					map1.goRight();
					break;

				}
				break;
			} else if (map1.l == map1.rightEdge && map1.w == map1.rightEdge) { // right
																				// up
																				// corner
				wayChoice = dungeon.uzivatVolba("Left", "Backwards");
				switch (wayChoice) {
				case 1:
					map1.goLeft();
					break;
				case 2:
					map1.goBackwards();
					break;

				}
				break;
			} else if (map1.l == map1.leftEdge) { // down side
				wayChoice = dungeon.uzivatVolba("Straight", "Left", "Right");
				switch (wayChoice) {
				case 1:
					map1.goStraight();
					break;
				case 2:
					map1.goLeft();
					break;
				case 3:
					map1.goRight();
					break;

				}
				break;
			} else if (map1.l == map1.rightEdge) { // up side
				wayChoice = dungeon.uzivatVolba("Left", "Right", "Backwards");
				switch (wayChoice) {
				case 1:
					map1.goLeft();
					break;
				case 2:
					map1.goRight();
					break;
				case 3:
					map1.goBackwards();
					break;

				}
				break;
			} else if (map1.w == map1.leftEdge) { // left side
				wayChoice = dungeon.uzivatVolba("Straight", "Right", "Bacwards");
				switch (wayChoice) {
				case 1:
					map1.goStraight();
					break;
				case 2:
					map1.goRight();
					break;
				case 3:
					map1.goBackwards();
					break;

				}
				break;
			} else if (map1.w == map1.rightEdge) { // right side
				wayChoice = dungeon.uzivatVolba("Straight", "Left", "Bacwards");
				switch (wayChoice) {
				case 1:
					map1.goStraight();
					break;
				case 2:
					map1.goLeft();
					break;
				case 3:
					map1.goBackwards();
					break;

				}
				break;
			} else {
				wayChoice = dungeon.uzivatVolba("Straight", "Left", "Right", "Backwards");
				switch (wayChoice) {
				case 1:
					map1.goStraight();
					break;
				case 2:
					map1.goLeft();
					break;
				case 3:
					map1.goRight();
					break;
				case 4:
					map1.goBackwards();
					break;

				}
				break;
			}

		}
	}

	public void back() {
		while (true) {
			if (map1.l == map1.leftEdge && map1.w == map1.leftEdge && wayChoice == 1) {
				map1.goBackwards();
				break;
			} else if (map1.l == map1.leftEdge && map1.w == map1.leftEdge && wayChoice == 2) {
				map1.goLeft();
				break;
			} else if (map1.l == map1.rightEdge && map1.w == map1.leftEdge && wayChoice == 1) {
				map1.goLeft();
				break;
			} else if (map1.l == map1.rightEdge && map1.w == map1.leftEdge && wayChoice == 2) {
				map1.goStraight();
				break;
			} else if (map1.l == map1.leftEdge && map1.w == map1.rightEdge && wayChoice == 1) {
				map1.goBackwards();
				break;
			} else if (map1.l == map1.leftEdge && map1.w == map1.rightEdge && wayChoice == 2) {
				map1.goLeft();
				break;
			} else if (map1.l == map1.rightEdge && map1.w == map1.rightEdge && wayChoice == 1) {
				map1.goRight();
				break;
			} else if (map1.l == map1.rightEdge && map1.w == map1.rightEdge && wayChoice == 2) {
				map1.goStraight();
				break;
			} else if (map1.l == map1.leftEdge && wayChoice == 1) {
				map1.goBackwards();
				break;
			} else if (map1.l == map1.leftEdge && wayChoice == 2) {
				map1.goRight();
				break;
			} else if (map1.l == map1.leftEdge && wayChoice == 3) {
				map1.goLeft();
				break;
			} else if (map1.l == map1.rightEdge && wayChoice == 1) {
				map1.goRight();
				break;
			} else if (map1.l == map1.rightEdge && wayChoice == 2) {
				map1.goLeft();
				break;
			} else if (map1.l == map1.rightEdge && wayChoice == 3) {
				map1.goStraight();
				break;
			} else if (map1.w == map1.leftEdge && wayChoice == 1) {
				map1.goBackwards();
				break;
			} else if (map1.w == map1.leftEdge && wayChoice == 2) {
				map1.goLeft();
				break;
			} else if (map1.w == map1.leftEdge && wayChoice == 3) {
				map1.goStraight();
				break;
			} else if (map1.w == map1.rightEdge && wayChoice == 1) {
				map1.goBackwards();
				break;
			} else if (map1.w == map1.rightEdge && wayChoice == 2) {
				map1.goRight();
				break;
			} else if (map1.w == map1.rightEdge && wayChoice == 3) {
				map1.goStraight();
				break;
			} else if (wayChoice == 1) {
				map1.goBackwards();
				break;
			} else if (wayChoice == 2) {
				map1.goRight();
				break;
			} else if (wayChoice == 3) {
				map1.goLeft();
				break;
			} else if (wayChoice == 1) {
				map1.goStraight();
				break;
			}
			break;
		}

	}
}
