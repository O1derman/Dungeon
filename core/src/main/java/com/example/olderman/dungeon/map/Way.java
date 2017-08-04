package com.example.olderman.dungeon.map;

import com.example.olderman.dungeon.Dungeon;

public class Way {
	public Way(Dungeon dungeon) {
		this.dungeon = dungeon;

	}

	private final Dungeon dungeon;
	public Map1 map = new Map1();
	public int wayChoice;

	public void go() {
		dungeon.println("\t Which way?");
		while (true) {
			if (map.l == map.leftEdge && map.w == map.leftEdge) { // left
																	// down
																	// corner

				wayChoice = dungeon.uzivatVolba("Straight", "Right");
				switch (wayChoice) {
				case 1:
					map.goStraight();
					break;
				case 2:
					map.goRight();
					break;

				}
				break;
			} else if (map.l == map.rightEdge && map.w == map.leftEdge) { // left up corner
				wayChoice = dungeon.uzivatVolba("Right", "Backwards");
				switch (wayChoice) {
				case 1:
					map.goRight();
					break;
				case 2:
					map.goBackwards();
					break;
				}
				break;

			} else if (map.l == map.leftEdge && map.w == map.rightEdge) { // right down corner
				wayChoice = dungeon.uzivatVolba("Straight", "Right");
				switch (wayChoice) {
				case 1:
					map.goStraight();
					break;
				case 2:
					map.goRight();
					break;

				}
				break;
			} else if (map.l == map.rightEdge && map.w == map.rightEdge) { // right up corner
				wayChoice = dungeon.uzivatVolba("Left", "Backwards");
				switch (wayChoice) {
				case 1:
					map.goLeft();
					break;
				case 2:
					map.goBackwards();
					break;

				}
				break;
			} else if (map.l == map.leftEdge) { // down side
				wayChoice = dungeon.uzivatVolba("Straight", "Left", "Right");
				switch (wayChoice) {
				case 1:
					map.goStraight();
					break;
				case 2:
					map.goLeft();
					break;
				case 3:
					map.goRight();
					break;

				}
				break;
			} else if (map.l == map.rightEdge) { // up side
				wayChoice = dungeon.uzivatVolba("Left", "Right", "Backwards");
				switch (wayChoice) {
				case 1:
					map.goLeft();
					break;
				case 2:
					map.goRight();
					break;
				case 3:
					map.goBackwards();
					break;

				}
				break;
			} else if (map.w == map.leftEdge) { // left side
				wayChoice = dungeon.uzivatVolba("Straight", "Right", "Bacwards");
				switch (wayChoice) {
				case 1:
					map.goStraight();
					break;
				case 2:
					map.goRight();
					break;
				case 3:
					map.goBackwards();
					break;

				}
				break;
			} else if (map.w == map.rightEdge) { // right side
				wayChoice = dungeon.uzivatVolba("Straight", "Left", "Bacwards");
				switch (wayChoice) {
				case 1:
					map.goStraight();
					break;
				case 2:
					map.goLeft();
					break;
				case 3:
					map.goBackwards();
					break;

				}
				break;
			} else {
				wayChoice = dungeon.uzivatVolba("Straight", "Left", "Right", "Backwards");
				switch (wayChoice) {
				case 1:
					map.goStraight();
					break;
				case 2:
					map.goLeft();
					break;
				case 3:
					map.goRight();
					break;
				case 4:
					map.goBackwards();
					break;

				}
				break;
			}

		}
	}

	public void back() {
		while (true) {
			if (map.l == map.leftEdge && map.w == map.leftEdge && wayChoice == 1) {
				map.goBackwards();
				break;
			} else if (map.l == map.leftEdge && map.w == map.leftEdge && wayChoice == 2) {
				map.goLeft();
				break;
			} else if (map.l == map.rightEdge && map.w == map.leftEdge && wayChoice == 1) {
				map.goLeft();
				break;
			} else if (map.l == map.rightEdge && map.w == map.leftEdge && wayChoice == 2) {
				map.goStraight();
				break;
			} else if (map.l == map.leftEdge && map.w == map.rightEdge && wayChoice == 1) {
				map.goBackwards();
				break;
			} else if (map.l == map.leftEdge && map.w == map.rightEdge && wayChoice == 2) {
				map.goLeft();
				break;
			} else if (map.l == map.rightEdge && map.w == map.rightEdge && wayChoice == 1) {
				map.goRight();
				break;
			} else if (map.l == map.rightEdge && map.w == map.rightEdge && wayChoice == 2) {
				map.goStraight();
				break;
			} else if (map.l == map.leftEdge && wayChoice == 1) {
				map.goBackwards();
				break;
			} else if (map.l == map.leftEdge && wayChoice == 2) {
				map.goRight();
				break;
			} else if (map.l == map.leftEdge && wayChoice == 3) {
				map.goLeft();
				break;
			} else if (map.l == map.rightEdge && wayChoice == 1) {
				map.goRight();
				break;
			} else if (map.l == map.rightEdge && wayChoice == 2) {
				map.goLeft();
				break;
			} else if (map.l == map.rightEdge && wayChoice == 3) {
				map.goStraight();
				break;
			} else if (map.w == map.leftEdge && wayChoice == 1) {
				map.goBackwards();
				break;
			} else if (map.w == map.leftEdge && wayChoice == 2) {
				map.goLeft();
				break;
			} else if (map.w == map.leftEdge && wayChoice == 3) {
				map.goStraight();
				break;
			} else if (map.w == map.rightEdge && wayChoice == 1) {
				map.goBackwards();
				break;
			} else if (map.w == map.rightEdge && wayChoice == 2) {
				map.goRight();
				break;
			} else if (map.w == map.rightEdge && wayChoice == 3) {
				map.goStraight();
				break;
			} else if (wayChoice == 1) {
				map.goBackwards();
				break;
			} else if (wayChoice == 2) {
				map.goRight();
				break;
			} else if (wayChoice == 3) {
				map.goLeft();
				break;
			} else if (wayChoice == 1) {
				map.goStraight();
				break;
			}
			break;
		}

	}
}
