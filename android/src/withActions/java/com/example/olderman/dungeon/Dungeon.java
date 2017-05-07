package com.example.olderman.dungeon;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Vrba on 4.5.2017.
 */

public class Dungeon {

    public void run() {
        Queue<Action> actionStack = Collections.asLifoQueue(new LinkedList<Action>());
        Action action = MAIN_MENU;
        while (action != null) {
            NextActionInfo info = action.after(uzivatVolba(action.before()));

            if (info.repeat()) {
                actionStack.add(action);
            }
            action = info.nextAction();
            if (action == null) {
                action = actionStack.poll();
            }
        }
    }

    private ForAll forAll;
    private Character character;
    private Shop shop;
    private InventoryAndInfo inventoryAndInfo;

    public ForAll getForAll() {
        return forAll;
    }

    public Character getCharacter() {
        return character;
    }

    private final OS os;

    public Dungeon(OS os) {
        this.os = os;
    }


    public final Action MAIN_MENU = new SimpleAction("Main menu") {
        @Override
        protected NextActionInfo[] doAction() {
            return new NextActionInfo[]{START.AND_THEN_REPEAT, HELP.AND_THEN_REPEAT, new RenamedAction(ACTION_BACK, "Exit")};
        }
    };
    public final Action HELP = new SimpleAction("Help") {
        @Override
        protected NextActionInfo[] doAction() {
            println(">Dwarf");
            println(">Orc");
            println(">Elf");
            println(">Superman");
            println(">Small health potion heals for 20 HP.");
            println(">Medium health potion heals for 50 HP.");
            println(">Large health potion heals for 100 HP.");
            println("");
            println("");
            println("");
            println("");

            return new NextActionInfo[]{ACTION_BACK};
        }
    };
    public final Action START = new Action("Start") {
        @Override
        public String[] before() {
            shop = new Shop(Dungeon.this);
            inventoryAndInfo = new InventoryAndInfo(Dungeon.this);
            forAll = new ForAll();

            println("\n\n\t\tWelcome to the dungeon!\n");
            println("\tYou can play this game however you want to.");
            println("\tYou gain experience and have a small chance of getting some health potions for killing enemies.");
            println("\tGame has 3 differend endings, so try them all for yourself :D!\n\n\n");
            println("\t\tWhich character would you like to play?\n");

            return new String[]{"Dwarf", "Orc", "Elf", "Superman"};
        }

        @Override
        public NextActionInfo after(int volba) {

            switch (volba) {
                case 1:
                    character = new Dwarf(Dungeon.this);
                    break;
                case 2:
                    character = new Orc(Dungeon.this);
                    break;
                case 3:
                    character = new Elf(Dungeon.this);
                    break;
                case 4:
                    character = new Superman(Dungeon.this);
                    break;

            }

            shop.setCharacter(character);

            println("###############################################################################################################\n");
            println(character.getBeginning());

            return FIGHT;
        }
    };

    public final Action FIGHT = new SimpleAction("Fight") {
        @Override
        protected NextActionInfo[] doAction() {
            return new NextActionInfo[]{};
        }
    };


    public void clear() {
        os.clear();
    }

    public int uzivatVolba(String... options) {
        return os.uzivatVolba(options);
    }

    public void print(String string) {
        os.print(string);
    }

    public void println(String string) {
        os.println(string);
    }

    public void println() {
        os.println();
    }

    public void printf(String string, Object... args) {
        os.printf(string, args);
    }

}
