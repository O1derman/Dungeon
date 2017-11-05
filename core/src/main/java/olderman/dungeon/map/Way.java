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

	public void go() {
		dungeon.println(Style.CENTER, "Which way?");
		while (true) {
			if (randMap.l == randMap.leftEdge && randMap.w == randMap.leftEdge) { // left
																		// down
																		// corner

				wayChoice = dungeon.uzivatVolba("East", "South");
				switch (wayChoice) {
				case 1:
					randMap.goStraight();
					break;
				case 2:
					randMap.goRight();
					break;

				}
				break;
			} else if (randMap.l == randMap.rightEdge && randMap.w == randMap.leftEdge) { // left
																				// up
																				// corner
				wayChoice = dungeon.uzivatVolba("Right", "West");
				switch (wayChoice) {
				case 1:
					randMap.goRight();
					break;
				case 2:
					randMap.goBackwards();
					break;
				}
				break;

			} else if (randMap.l == randMap.leftEdge && randMap.w == randMap.rightEdge) { // right
																				// down
																				// corner
				wayChoice = dungeon.uzivatVolba("East", "North");
				switch (wayChoice) {
				case 1:
					randMap.goStraight();
					break;
				case 2:
					randMap.goLeft();
					break;

				}
				break;
			} else if (randMap.l == randMap.rightEdge && randMap.w == randMap.rightEdge) { // right
																				// up
																				// corner
				wayChoice = dungeon.uzivatVolba("North", "West");
				switch (wayChoice) {
				case 1:
					randMap.goLeft();
					break;
				case 2:
					randMap.goBackwards();
					break;

				}
				break;
			} else if (randMap.l == randMap.leftEdge) { // down side
				wayChoice = dungeon.uzivatVolba("East", "North", "South");
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

				}
				break;
			} else if (randMap.l == randMap.rightEdge) { // up side
				wayChoice = dungeon.uzivatVolba("North", "South", "West");
				switch (wayChoice) {
				case 1:
					randMap.goLeft();
					break;
				case 2:
					randMap.goRight();
					break;
				case 3:
					randMap.goBackwards();
					break;

				}
				break;
			} else if (randMap.w == randMap.leftEdge) { // left side
				wayChoice = dungeon.uzivatVolba("East", "South", "West");
				switch (wayChoice) {
				case 1:
					randMap.goStraight();
					break;
				case 2:
					randMap.goRight();
					break;
				case 3:
					randMap.goBackwards();
					break;

				}
				break;
			} else if (randMap.w == randMap.rightEdge) { // right side
				wayChoice = dungeon.uzivatVolba("East", "North", "West");
				switch (wayChoice) {
				case 1:
					randMap.goStraight();
					break;
				case 2:
					randMap.goLeft();
					break;
				case 3:
					randMap.goBackwards();
					break;

				}
				break;
			} else {
				wayChoice = dungeon.uzivatVolba("East", "North", "South", "West");
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
	}

	public void back() {
		boolean back = true;
		while (back) {
			if (randMap.l == randMap.leftEdge && randMap.w == randMap.leftEdge && wayChoice == 1) {
				randMap.goBackwards();
				break;
			} else if (randMap.l == randMap.leftEdge && randMap.w == randMap.leftEdge && wayChoice == 2) {
				randMap.goLeft();
				break;
			} else if (randMap.l == randMap.rightEdge && randMap.w == randMap.leftEdge && wayChoice == 1) {
				randMap.goLeft();
				break;
			} else if (randMap.l == randMap.rightEdge && randMap.w == randMap.leftEdge && wayChoice == 2) {
				randMap.goStraight();
				break;
			} else if (randMap.l == randMap.leftEdge && randMap.w == randMap.rightEdge && wayChoice == 1) {
				randMap.goBackwards();
				break;
			} else if (randMap.l == randMap.leftEdge && randMap.w == randMap.rightEdge && wayChoice == 2) {
				randMap.goLeft();
				break;
			} else if (randMap.l == randMap.rightEdge && randMap.w == randMap.rightEdge && wayChoice == 1) {
				randMap.goRight();
				break;
			} else if (randMap.l == randMap.rightEdge && randMap.w == randMap.rightEdge && wayChoice == 2) {
				randMap.goStraight();
				break;
			} else if (randMap.l == randMap.leftEdge && wayChoice == 1) {
				randMap.goBackwards();
				break;
			} else if (randMap.l == randMap.leftEdge && wayChoice == 2) {
				randMap.goRight();
				break;
			} else if (randMap.l == randMap.leftEdge && wayChoice == 3) {
				randMap.goLeft();
				break;
			} else if (randMap.l == randMap.rightEdge && wayChoice == 1) {
				randMap.goBackwards();
				break;
			} else if (randMap.l == randMap.rightEdge && wayChoice == 2) {
				randMap.goLeft();
				break;
			} else if (randMap.l == randMap.rightEdge && wayChoice == 3) {
				randMap.goStraight();
				break;
			} else if (randMap.w == randMap.leftEdge && wayChoice == 1) {
				randMap.goBackwards();
				break;
			} else if (randMap.w == randMap.leftEdge && wayChoice == 2) {
				randMap.goLeft();
				break;
			} else if (randMap.w == randMap.leftEdge && wayChoice == 3) {
				randMap.goStraight();
				break;
			} else if (randMap.w == randMap.rightEdge && wayChoice == 1) {
				randMap.goBackwards();
				break;
			} else if (randMap.w == randMap.rightEdge && wayChoice == 2) {
				randMap.goRight();
				break;
			} else if (randMap.w == randMap.rightEdge && wayChoice == 3) {
				randMap.goStraight();
				break;
			} else if (wayChoice == 1) {
				randMap.goBackwards();
				break;
			} else if (wayChoice == 2) {
				randMap.goRight();
				break;
			} else if (wayChoice == 3) {
				randMap.goLeft();
				break;
			} else if (wayChoice == 1) {
				randMap.goStraight();
				break;
			}
			back = false;
		}

	}
}
