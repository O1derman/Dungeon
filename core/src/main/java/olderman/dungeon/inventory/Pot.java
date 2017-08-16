package olderman.dungeon.inventory;

import olderman.dungeon.Dungeon;

public class Pot extends InventoryItem {

	public static final Pot POT = new Pot();

	private Pot() {
		super("Pot", "use", false, false, Type.OTHER);
	}

	@Override
	public boolean use(Dungeon dungeon) {
		POTIONS: while (true) {
			int potCrafting = dungeon.uzivatVolba("Read dormitory", "Create small health potion",
					"Create medium health potion", "Create large health potion", "Create teleport potion",
					"Create potion of concentration", "Create potion of invisibility");

			if (potCrafting == 1) {// Read dormitory
				dungeon.println("You need flask to create any potion!");
				dungeon.println("To create small health potion you need 1 frog heart and 2 warms.");
				dungeon.println("To create medium health potion you need 2 frog hearts and 2 warms.");
				dungeon.println("To create large health potion you need 3 frog hearts and 4 warms.");
				dungeon.println("To create teleport potion you need 4 frog legs and 1 left frog eye.");
				dungeon.println(
						"To create potion of concentration you need 1 sneak brain, 2 squirel tails and 1 frog head.");
				dungeon.println("To create potion of invisibility you need eye of snake and both eyes of frog.");
			} else if (potCrafting == 2 ^ potCrafting == 3 ^ potCrafting == 4 ^ potCrafting == 5 ^ potCrafting == 6
					^ potCrafting == 7 && dungeon.getForAll().flashk == 0) {
				dungeon.println("You dont have flashk to full up with lektvar.");
				continue POTIONS;
			} else if (potCrafting == 2) {// Create small health potion
				if (dungeon.getForAll().frogHeart >= 1 && dungeon.getForAll().worm >= 2) {
					dungeon.println("You created a small health potion.");
					dungeon.getForAll().frogHeart--;
					dungeon.getForAll().worm -= 2;
					dungeon.getInventory().add(HealthPotion.SMALL);
				} else {
					dungeon.println("You dont have enough ingredience to create small health potion.");
				}
			} else if (potCrafting == 3) {// Create medium health potion
				if (dungeon.getForAll().frogHeart >= 2 && dungeon.getForAll().worm >= 2) {
					dungeon.println("You created a medium health potion.");
					dungeon.getForAll().frogHeart -= 2;
					dungeon.getForAll().worm -= 2;
					dungeon.getInventory().add(HealthPotion.MEDIUM);
				} else {
					dungeon.println("You dont have enough ingredience to create medium health potion.");
				}
			} else if (potCrafting == 4) {// Create large health potion
				if (dungeon.getForAll().frogHeart >= 3 && dungeon.getForAll().worm >= 4) {
					dungeon.println("You created a large health potion.");
					dungeon.getForAll().frogHeart -= 3;
					dungeon.getForAll().worm -= 4;
					dungeon.getInventory().add(HealthPotion.LARGE);
				} else {
					dungeon.println("You dont have enough ingredience to create large health potion.");
				}
			} else if (potCrafting == 5) {// Create teleport potion
				if (dungeon.getForAll().frogLeftEye >= 1 && dungeon.getForAll().frogLeg >= 4) {
					dungeon.println("You created a teleportation potion.");
					dungeon.getForAll().frogLeftEye--;
					dungeon.getForAll().frogLeg -= 4;
					dungeon.getForAll().numPotionOfTeleportation++;
				} else {
					dungeon.println("You dont have enough ingredience to create teleport potion.");
				}
			} else if (potCrafting == 6) {// Create potion of concentration
				if (dungeon.getForAll().sneakBrain >= 1 && dungeon.getForAll().squirelTails >= 2
						&& dungeon.getForAll().frogHead >= 1) {
					dungeon.println("You created a potion of concentration.");
					dungeon.getForAll().sneakBrain--;
					dungeon.getForAll().squirelTails -= 2;
					dungeon.getForAll().frogHead--;
					dungeon.getForAll().numPotionOfConcentration++;
				} else {
					dungeon.println("You dont have enough ingredience to potion of concentration.");
				}
			} else if (potCrafting == 7) {// Create potion of invisibility
				if (dungeon.getForAll().frogLeftEye >= 2 && dungeon.getForAll().frogLeg >= 4) {
					dungeon.println("You created a potion of concentration.");
					dungeon.getForAll().sneakBrain--;
					dungeon.getForAll().squirelTails -= 2;
					dungeon.getForAll().frogHead--;
					dungeon.getForAll().numPotionOfInvisibility++;
				} else {
					dungeon.println("You dont have enough ingredience to create teleport potion.");
				}
			} else {
				break;
			}
		}
		return false;
	}

}
