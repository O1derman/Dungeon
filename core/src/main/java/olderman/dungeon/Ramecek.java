package olderman.dungeon;

import java.util.Arrays;

/**
 * Created by w526_000 on 04.05.2017.
 */
public class Ramecek {
	// data rámu - rám[0] : rohy od levého horního po směru hod. ruč ;rám[1][0]
	// vodorovné ohraničení; rám[2][0] : horizontální ohraničení
	public static final char[][] data = { //
			{ '+', '+', '+', '+', '-', '|' }, //
			{ '@', '@', '@', '@', '#', '#' }, //
			{ '#', '#', '#', '#', '=', '|' }, //
			{ '=', '=', '=', '=', '=', ' ' }, //
			{ '!', '!', '!', '!', '-', '!' }, //
			{ '■', '■', '■', '■', ' ', '■' }, //
			{ '█', '█', '█', '█', ' ', '█' }, //
			{ '║', '║', '║', '║', ' ', '║' }, //
			{ '┌', '┐', '┘', '└', '─', '│' },//
	};

	public static final int TYP_PLUS = 0;
	public static final int TYP_HASHTAG = 1;
	public static final int TYP_ROVNASE = 2;
	public static final int TYP_ROVNASE_BEZESTRAN = 3;
	public static final int TYP_VYKRICNIK = 4;
	public static final int TYP_CTVEREC = 5;
	public static final int TYP_VEL_CTVEREC = 6;
	public static final int TYP_DVOJTA_CARA = 7;
	public static final int TYP_CARA = 8;

	private static final int TOP_LEFT = 0;
	private static final int TOP_RIGHT = 1;
	private static final int BOTTOM_RIGHT = 2;
	private static final int BOTTOM_LEFT = 3;
	private static final int HORIZONTAL = 4;
	private static final int VERTICAL = 5;
	private static final int DATA_LENGTH = 6;

	public static String vytvor(char[] ram, String text) {
		if (ram.length != DATA_LENGTH) {
			throw new IllegalArgumentException();
		}
		String[] radky = text.split("\\v"); // rozdeli text na pole po radcich
		int nejdelsiRadek = 0;
		for (String radek : radky) { // najdu delku nejdelsiho radku
			if (radek.length() > nejdelsiRadek)
				nejdelsiRadek = radek.length();
		}

		StringBuilder sb = new StringBuilder((nejdelsiRadek + 5) * radky.length);
		// prvni radek
		sb.append(ram[TOP_LEFT]);
		for (int i = 0; i < nejdelsiRadek + 2; i++) {
			sb.append(ram[HORIZONTAL]);
		}
		sb.append(ram[TOP_RIGHT]);
		sb.append('\n');

		// ostatni radky
		char[] chars = new char[nejdelsiRadek + 4];
		chars[0] = ram[VERTICAL];
		chars[chars.length - 1] = ram[VERTICAL];
		for (String radek : radky) {
			Arrays.fill(chars, 1, chars.length - 1, ' ');
			radek.getChars(0, radek.length(), chars, 2);
			sb.append(chars);
			sb.append('\n');
		}

		// posledni radek
		sb.append(ram[BOTTOM_LEFT]);
		for (int i = 0; i < nejdelsiRadek + 2; i++) {
			sb.append(ram[HORIZONTAL]);
		}
		sb.append(ram[BOTTOM_RIGHT]);

		return sb.toString();
	}

	public static String vytvor(int typ, String text) {
		return vytvor(data[typ], text);
	}

	public static String vytvor(String ram, String text) {
		return vytvor(ram.toCharArray(), text);
	}

}
