package com.example.olderman.dungeon;

/**
 * Created by Vrba on 4.5.2017.
 */

public abstract class SimpleAction extends Action {

    private NextActionInfo[] actions;

    public SimpleAction(String name) {
        super(name);
    }

    protected abstract NextActionInfo[] doAction();

    @Override
    public String[] before() {
        actions = doAction();
        String[] names = new String[actions.length];
        for (int i = 0; i < actions.length; i++) {

            names[i] = actions[i].name();
        }
        return names;
    }

    @Override
    public NextActionInfo after(int volba) {
        return actions[volba - 1];
    }
}
