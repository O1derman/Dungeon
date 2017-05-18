package com.example.olderman.dungeon;

import java.io.IOException;
import java.io.InputStreamReader;

public class Resources {
	public static String getString(String name) {
		try (InputStreamReader reader = new InputStreamReader(Resources.class.getResourceAsStream(name))) {
			java.util.Scanner s = new java.util.Scanner(reader).useDelimiter("\\A");
			return s.hasNext() ? s.next() : "";
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
