package com.example.olderman.dungeon;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */

public class Cheats {

	private final Dungeon dungeon;

	public Cheats(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	private final Pattern PATTERN = Pattern.compile("\\.(\\w*+)(?: (.*+))?");

	public boolean executeCommand(String command) {
		Matcher matcher = PATTERN.matcher(command);
		if (!matcher.matches()) {
			return false;
		}
		String commandName = matcher.group(1);
		String argumentsString = matcher.group(2);
		String arguments[] = null;
		if (argumentsString != null) {
			arguments = argumentsString.split(" ");
		}
		try {
			switch (commandName) {
			case "addgold": {
				int amount = Integer.parseInt(arguments[0]);
				dungeon.getForAll().gold += amount;
				return true;
			}
			case "sethp": {
				int health = Integer.parseInt(arguments[0]);
				dungeon.setHealth(health);
				return true;
			}
			case "setmaxhp": {
				int maxHealth = Integer.parseInt(arguments[0]);
				dungeon.getForAll().maximumHealth = maxHealth;
				return true;
			}
			case "heal": {
				dungeon.setHealth(dungeon.getForAll().maximumHealth);
				return true;
			}
			default:
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
}
