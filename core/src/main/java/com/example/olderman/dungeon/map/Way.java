package com.example.olderman.dungeon.map;

import com.example.olderman.dungeon.Dungeon;

public class Way {
	public Way(Dungeon dungeon) {
		this.dungeon = dungeon;

	}

	private final Dungeon dungeon;
	public Map map = new Map();

	public void way() {
		dungeon.println("\t Which way?");
		while (true) {
			if (map.l == map.leftEdge && map.w == map.leftEdge) { // left
																	// down
																	// corner

				int wayChoice = dungeon.uzivatVolba("Straight", "Right");
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
				int wayChoice = dungeon.uzivatVolba("Right", "Backwards");
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
				int wayChoice = dungeon.uzivatVolba("Straight", "Right");
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
				int wayChoice = dungeon.uzivatVolba("Left", "Backwards");
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
				int wayChoice = dungeon.uzivatVolba("Straight", "Left", "Right");
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
				int wayChoice = dungeon.uzivatVolba("Left", "Right", "Backwards");
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
				int wayChoice = dungeon.uzivatVolba("Straight", "Right", "Bacwards");
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
				int wayChoice = dungeon.uzivatVolba("Straight", "Left", "Bacwards");
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
				int wayChoice = dungeon.uzivatVolba("Straight", "Left", "Right", "Backwards");
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
}
