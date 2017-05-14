package com.example.olderman.dungeon;

import java.io.InputStream;

public class FileUtils {
	static String convertStreamToString(String fileName) {
		InputStream io = FileUtils.class.getClassLoader().getResourceAsStream(fileName);

		java.util.Scanner s = new java.util.Scanner(io).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}

}
