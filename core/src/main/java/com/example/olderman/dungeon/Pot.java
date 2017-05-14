package com.example.olderman.dungeon;

public class Pot {
	public Pot(Dungeon dungeon) {
		this.dungeon = dungeon;

	}

	private final Dungeon dungeon;
	int potCost = 450;

	public void pot() {
		while (true) {
			int potCrafting = dungeon.uzivatVolba("Read dormitory", "Create small health potion", "Create medium health potion", "Create large health potion", "Create teleport potion",
					"Create potion of concentration", "Create potion of invisibility");

			if (potCrafting == 1) {
				dungeon.println("You need flashk to create any potion!");
				dungeon.println("To create small health potion you need 1 frog heart and 2 warms.");
				dungeon.println("To create medium health potion you need 2 frog hearts and 2 warms.");
				dungeon.println("To create large health potion you need 3 frog hearts and 4 warms.");
				dungeon.println("To create teleport potion you need 4 frog legs and .");
				dungeon.println("To create potion of concentration you need 1 sneak brain, 2 squirel tails and 1 frog head.");
				dungeon.println("To create potion of invisibility you need eye of snake and both eyes of frog.");
			}
			if (potCrafting == 2) {
				// if(){
				//
				// };
			}
			if (potCrafting == 3) {

			}
			if (potCrafting == 4) {

			}
			if (potCrafting == 5) {

			}
			if (potCrafting == 6) {

			}
			if (potCrafting == 7) {

			}
		}
	}
}
