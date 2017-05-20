package com.example.olderman.dungeon.inventory;

import com.example.olderman.dungeon.Dungeon;

public class Pot extends InventoryItem {

	public static final Pot POT = new Pot();

	private Pot() {
		super("Pot", "use", false, false, Type.OTHER);
	}

	public int potCost = 450;

	public boolean use(Dungeon dungeon) {
		while (true) {
			int potCrafting = dungeon.uzivatVolba("Read dormitory", "Create small health potion",
					"Create medium health potion", "Create large health potion", "Create teleport potion",
					"Create potion of concentration", "Create potion of invisibility", "Back");

			if (potCrafting == 1) {
				dungeon.println("You need flashk to create any potion!");
				dungeon.println("To create small health potion you need 1 frog heart and 2 warms.");
				dungeon.println("To create medium health potion you need 2 frog hearts and 2 warms.");
				dungeon.println("To create large health potion you need 3 frog hearts and 4 warms.");
				dungeon.println("To create teleport potion you need 4 frog legs and .");
				dungeon.println(
						"To create potion of concentration you need 1 sneak brain, 2 squirel tails and 1 frog head.");
				dungeon.println("To create potion of invisibility you need eye of snake and both eyes of frog.");
			} else if (potCrafting == 2) {
				// if(){
				//
				// };
			} else if (potCrafting == 3) {

			} else if (potCrafting == 4) {

			} else if (potCrafting == 5) {

			} else if (potCrafting == 6) {

			} else if (potCrafting == 7) {

			} else {// Back
				return false;
			}
		}
	}
}
