package com.example.olderman.dungeon;

import java.io.IOException;
import java.util.Scanner;

public class DesktopOS implements OS {

	public static boolean enableClear = true;
	// System objects
	public static Scanner in = new Scanner(System.in);

	public void clear() {
		if (enableClear)
			println("\u001b[2J\u001b[H");
	}

	public int uzivatVolba(String... options) {
		for (int i = 0; i < options.length; i++) {
			String option = options[i];
			printf("\t%d. %s%n", i + 1, option);
		}
		println();
		println("###############################################################################################################");
		println();
		while (true) {

			String nabidka = DesktopOS.in.nextLine();
			try {
				int vysledek = Integer.parseInt(nabidka);
				if (vysledek > 0 && vysledek <= options.length) {
					clear();
					return vysledek;
				}
			} catch (NumberFormatException e) {
			}
			println("\tInvalid command!");
		}
	}

	public void print(String string) {
		System.out.print(string);
	}

	public void println(String string) {
		System.out.println(string);
	}

	public void println() {
		System.out.println();
	}

	public void printf(String string, Object... args) {
		System.out.printf(string, args);
	}

	public static void main(String[] args) throws IOException {
		if (args.length >= 1 && "--noClear".equals(args[0])) {
			enableClear = false;
		}
		new Dungeon(new DesktopOS()).run();
	}

}
