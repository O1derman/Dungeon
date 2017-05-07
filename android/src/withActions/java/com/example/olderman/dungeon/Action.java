package com.example.olderman.dungeon;

/**
 * Created by Vrba on 4.5.2017.
 */

public abstract class Action implements NextActionInfo {


    private final String name;

    protected Action(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public Action nextAction() {
        return this;
    }

    @Override
    public boolean repeat() {
        return false;
    }

    public abstract String[] before();

    public abstract com.example.olderman.dungeon.NextActionInfo after(int volba);

    public com.example.olderman.dungeon.NextActionInfo AND_THEN_REPEAT = new RepeatedAction(this);

    private static class RepeatedAction implements com.example.olderman.dungeon.NextActionInfo {
        private final Action nextAction;

        public RepeatedAction(Action nextAction) {
            this.nextAction = nextAction;
        }

        @Override
        public Action nextAction() {
            return nextAction;
        }

        @Override
        public String name() {
            return nextAction.name();
        }

        @Override
        public boolean repeat() {
            return true;
        }
    }
}
