package com.example.olderman.dungeon;

public interface OS {

	public void clear();

	public int uzivatVolba(String... options);

	public void print(String string);

	public void println(String string);

	public void println();

	public void printf(String string, Object... args);

}
