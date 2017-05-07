package com.example.olderman.dungeon;

/**
 * Created by Vrba on 5.5.2017.
 */

public class RenamedAction implements NextActionInfo {
    private final NextActionInfo action;
    private final String name;

    public RenamedAction(NextActionInfo action, String name) {
        action.getClass();
        this.action = action;
        this.name = name;
    }

    @Override
    public Action nextAction() {
        return action.nextAction();
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean repeat() {
        return action.repeat();
    }
}
