package com.example.olderman.dungeon.map;

import com.example.olderman.dungeon.Dungeon;

public class MapLegend {
	public MapLegend(Dungeon dungeon) {

		this.dungeon = dungeon;
	}

	private final Dungeon dungeon;

	public void legend() {
		dungeon.println("D - your position");
		dungeon.println("x - wall");
		dungeon.println("o - room with enemy");
		dungeon.println("c - cleared room");
		int volba = dungeon.uzivatVolba("Exit");
		switch (volba) {

		}
	}
}
