package olderman.dungeon.inventory;

import olderman.dungeon.Dungeon;
import olderman.dungeon.Style;

public class Pot extends InventoryItem {

    private static final long serialVersionUID = 1L;

    public static final Pot POT = new Pot();

    private Pot() {
        super("Pot", "use", false, false, Type.OTHER);
    }

    @Override
    public boolean use(Dungeon dungeon) {
        boolean crafting = true;
        POTIONS:
        while (crafting) {
            dungeon.clear();
            dungeon.printResources();
            dungeon.println(Style.CENTER, "What do you want to do?");
            switch (dungeon.uzivatVolba("Read dormitory", "Create small health potion",
                    "Create medium health potion", "Create large health potion", "Create teleport potion",
                    "Create potion of concentration", "Create potion of invisibility", "Stop using pot")) {


                case 1: {// Read dormitory
                    dungeon.println("You need flask to create a potion!");
                    dungeon.println("To create small health potion you need 1 frog heart and 2 worms.");
                    dungeon.println("To create medium health potion you need 2 frog hearts and 2 worms.");
                    dungeon.println("To create large health potion you need 3 frog hearts and 4 worms.");
                    dungeon.println("To create teleport potion you need 4 frog legs and 1 left frog eye.");
                    dungeon.println(
                            "To create potion of concentration you need 1 sneak brain, 2 squirel tails and 1 frog head.");
                    dungeon.println("To create potion of invisibility you need eye of snake and both eyes of frog.");
                    switch (dungeon.uzivatVolba("Back")) {

                    }
                    continue POTIONS;
                }
                case 2: {// Create small health potion
                    if (dungeon.getForAll().flashk == 0) {
                        dungeon.println(Style.CENTER, "You don't have flask to fill with potion.");
                    } else if (dungeon.getForAll().frogHeart >= 1 && dungeon.getForAll().worm >= 2) {
                        dungeon.println(Style.CENTER, "You created a small health potion.");
                        dungeon.getForAll().frogHeart--;
                        dungeon.getForAll().worm -= 2;
                        dungeon.getInventory().add(HealthPotion.SMALL);
                    } else {
                        dungeon.println(Style.CENTER, "You don't have enough ingredience to create small health potion.");
                    }
                    break;
                }
                case 3: {// Create medium health potion
                    if (dungeon.getForAll().flashk == 0) {
                        dungeon.println(Style.CENTER, "You don't have flask to fill with potion.");
                    } else if (dungeon.getForAll().frogHeart >= 2 && dungeon.getForAll().worm >= 2) {
                        dungeon.println(Style.CENTER, "You created a medium health potion.");
                        dungeon.getForAll().frogHeart -= 2;
                        dungeon.getForAll().worm -= 2;
                        dungeon.getInventory().add(HealthPotion.MEDIUM);
                    } else {
                        dungeon.println(Style.CENTER, "You dont have enough ingredients to create a medium health potion.");
                    }
                    break;
                }
                case 4: {// Create large health potion
                    if (dungeon.getForAll().flashk == 0) {
                        dungeon.println(Style.CENTER, "You don't have flask to fill with potion.");
                    } else if (dungeon.getForAll().frogHeart >= 3 && dungeon.getForAll().worm >= 4) {
                        dungeon.println(Style.CENTER, "You created a large health potion.");
                        dungeon.getForAll().frogHeart -= 3;
                        dungeon.getForAll().worm -= 4;
                        dungeon.getInventory().add(HealthPotion.LARGE);
                    } else {
                        dungeon.println(Style.CENTER, "You dont have enough ingredients to create a large health potion.");
                    }
                    break;
                }
                case 5: {// Create teleport potion
                    if (dungeon.getForAll().flashk == 0) {
                        dungeon.println(Style.CENTER, "You don't have flask to fill with potion.");
                    } else if (dungeon.getForAll().frogLeftEye >= 1 && dungeon.getForAll().frogLeg >= 4) {
                        dungeon.println(Style.CENTER, "You created a teleportation potion.");
                        dungeon.getForAll().frogLeftEye--;
                        dungeon.getForAll().frogLeg -= 4;
                        dungeon.getForAll().numPotionOfTeleportation++;
                    } else {
                        dungeon.println(Style.CENTER, "You dont have enough ingredients to create a teleport potion.");
                    }
                    break;
                }
                case 6: {// Create potion of concentration
                    if (dungeon.getForAll().flashk == 0) {
                        dungeon.println(Style.CENTER, "You don't have flask to fill with potion.");
                    } else if (dungeon.getForAll().snakeBrain >= 1 && dungeon.getForAll().squirelTails >= 2
                            && dungeon.getForAll().frogHead >= 1) {
                        dungeon.println(Style.CENTER, "You created a potion of concentration.");
                        dungeon.getForAll().snakeBrain--;
                        dungeon.getForAll().squirelTails -= 2;
                        dungeon.getForAll().frogHead--;
                        dungeon.getForAll().numPotionOfConcentration++;
                    } else {
                        dungeon.println(Style.CENTER,
                                "You dont have enough ingredients to create a potion of concentration.");
                    }
                    break;
                }
                case 7: {// Create potion of invisibility
                    if (dungeon.getForAll().flashk == 0) {
                        dungeon.println(Style.CENTER, "You don't have flask to fill with potion.");
                    } else if (dungeon.getForAll().frogLeftEye >= 2 && dungeon.getForAll().frogLeg >= 4) {
                        dungeon.println(Style.CENTER, "You created a potion of invisibility.");
                        dungeon.getForAll().snakeBrain--;
                        dungeon.getForAll().squirelTails -= 2;
                        dungeon.getForAll().frogHead--;
                        dungeon.getForAll().numPotionOfInvisibility++;
                    } else {
                        dungeon.println(Style.CENTER,
                                "You dont have enough ingredients to create a potion of invisibility.");
                    }
                    break;
                }
                case 8: {
                    crafting = false;
                }
            }
            if (crafting) {
                switch (dungeon.uzivatVolba("Continue")) {
                }
            }
        }
        return false;
    }

}
