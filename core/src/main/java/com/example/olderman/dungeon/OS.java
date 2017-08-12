package com.example.olderman.dungeon;

import com.example.olderman.dungeon.Style.AttributeStyle;
import com.example.olderman.dungeon.Style.ColorStyle;

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

	void printMidle(String text);

	void timeInput();

}
