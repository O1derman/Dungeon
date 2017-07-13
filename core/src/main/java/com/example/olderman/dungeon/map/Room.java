package com.example.olderman.dungeon.map;

import java.awt.Toolkit;

import com.example.olderman.dungeon.Dungeon;

public class Room {
	public Room(Way way, Dungeon dungeon) {
		this.way = way;
		this.dungeon = dungeon;
	}

	public final Way way;
	private final Dungeon dungeon;

	public int room11 = 0;
	public int room12 = 0;
	public int room13 = 0;
	public int room14 = 0;
	public int room15 = 0;
	public int room21 = 0;
	public int room22 = 0;
	public int room23 = 0;
	public int room24 = 0;
	public int room25 = 0;
	public int room31 = 0;
	public int room32 = 0;
	public int room33 = 0;
	public int room34 = 0;
	public int room35 = 0;
	public int room41 = 0;
	public int room42 = 0;
	public int room43 = 0;
	public int room44 = 0;
	public int room45 = 0;
	public int room51 = 0;
	public int room52 = 0;
	public int room53 = 0;
	public int room54 = 0;
	public int room55 = 0;

	public void room() {
		dungeon.getForAll().health = dungeon.getCharacter().getInitialHealth();
		dungeon.getForAll().maximumHealth = dungeon.getCharacter().getInitialMaximumHealth();

		dungeon.println(
				"###############################################################################################################\n");
		dungeon.println("\n\t>You are on floor " + dungeon.getForAll().floor + "!");

		if (way.map.l == way.map.rightEdge && way.map.w == way.map.w1) {
			dungeon.println("\tWelcome in boss room for floor " + dungeon.getForAll().floor);
			Toolkit.getDefaultToolkit().beep();
		} else if (dungeon.getRand().nextInt(100) < dungeon.getPlebs().enemyChance) {
			dungeon.println("\t#You see " + dungeon.getPlebs().enemy.nameWithArticle() + "!");

		}

	}
}