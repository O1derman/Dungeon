package olderman.dungeon;

import olderman.dungeon.Style.AttributeStyle;
import olderman.dungeon.Style.ColorStyle;

public interface OS {

	void clear();

	int uzivatVolba(String... options);

	void print(String string);

	void println();

	void reset();

	void color(ColorStyle style);

	void attribute(AttributeStyle style);

	void flush();

	void printAsciiArt(String asciiArt);

	void beep();

	void printMiddle(String text);

	void fillLane(String text);

	int uzivatVolba2(String... options);

}
