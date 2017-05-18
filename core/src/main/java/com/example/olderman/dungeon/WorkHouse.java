package com.example.olderman.dungeon;

public class WorkHouse {

	public WorkHouse(Dungeon dungeon) {
		this.dungeon = dungeon;

	}

	private final Dungeon dungeon;

	public void WorkHouse() {
		dungeon.println("What do you want to work on?");
		dungeon.println();
		while (true) {
			int posibilities = dungeon.uzivatVolba("Frog", "Sneak", "Squirel", "Build bad");
			if (posibilities == 1) {
				dungeon.println("");

			}

		}
	}
}
