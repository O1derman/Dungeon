package com.example.olderman.dungeon;

/**
 * Created by Vrba on 5.5.2017.
 */

public class RepeatSelfAction implements NextActionInfo {
    private final String name;

    public RepeatSelfAction(String name) {
        this.name = name;
    }

    public RepeatSelfAction() {
        this("");
    }

    @Override
    public Action nextAction() {
        return null;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean repeat() {
        return true;
    }
}
