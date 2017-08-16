package olderman.dungeon;

import static org.junit.Assert.*;

import org.junit.Test;

import olderman.dungeon.Ramecek;

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
