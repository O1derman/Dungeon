package olderman.dungeon;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import olderman.dungeon.Dungeon;

/**
 *
 */

public class Cheats implements Serializable{
	private static final long serialVersionUID = 1L;
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
		String arguments[] = new String[0];
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
				dungeon.getForAll().maximumHealth = Integer.parseInt(arguments[0]);
				return true;
			}
			case "heal": {
				dungeon.setHealth(dungeon.getForAll().maximumHealth);
				return true;
			}
			case "setseed": {
				long seed = Long.parseLong(arguments[0]);
				dungeon.getRand().setSeed(seed);
			}
			default:
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
}
