package com.example.olderman.dungeon;

import com.example.olderman.dungeon.Style.AttributeStyle;
import com.example.olderman.dungeon.Style.ColorStyle;

public interface OS {

	public void clear();

	public int uzivatVolba(String... options);

	public void print(String string);

	public void println();

	public void reset();

	public void color(ColorStyle style);

	public void attribute(AttributeStyle style);

	public void flush();

	public void printAsciiArt(String asciiArt);

}
