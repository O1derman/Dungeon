package com.example.olderman.dungeon;

/**
 * Created by Vrba on 5.5.2017.
 */

public interface NextActionInfo {
    Action nextAction();

    String name();

    boolean repeat();

    NextActionInfo ACTION_BACK = new NextActionInfo() {
        @Override
        public Action nextAction() {
            return null;
        }

        @Override
        public String name() {
            return "Back";
        }

        @Override
        public boolean repeat() {
            return false;
        }
    };
}
