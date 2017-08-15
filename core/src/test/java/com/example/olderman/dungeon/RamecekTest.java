package com.example.olderman.dungeon;

import static org.junit.Assert.*;

import org.junit.Test;

public class RamecekTest {

	@Test
	public void testVytvorCharArrayString() {
		// ┌', '┐', '┘', '└' }, { '─' }, { '│' }
		String expectedResult = "" //
				+ "┌─────┐\n" //
				+ "│ aa  │\n"//
				+ "│ aaa │\n"//
				+ "└─────┘";
		String result = Ramecek.vytvor(Ramecek.data[Ramecek.TYP_CARA], "aa\naaa");
		assertEquals(expectedResult, result);
	}

}
