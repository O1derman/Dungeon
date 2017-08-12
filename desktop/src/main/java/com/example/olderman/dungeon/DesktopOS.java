package com.example.olderman.dungeon;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import com.example.olderman.dungeon.Style.AttributeStyle;
import com.example.olderman.dungeon.Style.Color;
import com.example.olderman.dungeon.Style.ColorStyle;

public class DesktopOS implements OS {

	private Cheats cheats = null;

	public static boolean enableClear = true;
	// System objects
	private static final Scanner in = new Scanner(System.in);

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double width = screenSize.getWidth();
	private String str = "";
	TimerTask task = new TimerTask() {

		@Override
		public void run() {
			if (str.equals("")) {
				System.exit(0);
			}
		}
	};

	@Override
	public void clear() {
		if (enableClear) {
			ansi().eraseScreen();
			flush();
		}
	}

	@Override
	public int uzivatVolba(String... options) {
		for (int i = 0; i < options.length; i++) {
			String option = options[i];
			ansi().format("\t%d. %s%n", i + 1, option);
		}
		println();
		println("###############################################################################################################");
		println();

		flush();
		while (true) {

			String nabidka = DesktopOS.in.nextLine();
			if (cheats != null) {
				while (cheats.executeCommand(nabidka)) {
					nabidka = DesktopOS.in.nextLine();
				}
			}
			try {
				int vysledek = Integer.parseInt(nabidka);
				if (vysledek > 0 && vysledek <= options.length) {
					clear();
					return vysledek;
				}
			} catch (NumberFormatException e) {
			}
			println("\tInvalid command!");
			flush();
		}
	}

	private void println(String string) {
		print(string);
		println();
	}

	@Override
	public void print(String string) {
		ansi().a(string);
	}

	@Override
	public void println() {
		ansi().newline();
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		if (args.length >= 1 && "--noClear".equals(args[0])) {
			enableClear = false;
		}
		DesktopOS os = new DesktopOS();
		Dungeon dungeon = new Dungeon(os);
		os.cheats = new Cheats(dungeon); // comment this line to disable cheats
		dungeon.run();
	}

	@Override
	public void flush() {
		AnsiConsole.out.print(mAnsi.toString());
		mAnsi = null;
	}

	@Override
	public void printAsciiArt(String asciiArt) {
		print(asciiArt);
	}

	private Ansi mAnsi = null;

	private Ansi ansi() {
		if (mAnsi == null)
			mAnsi = Ansi.ansi();
		return mAnsi;
	}

	@Override
	public void color(ColorStyle style) {
		if (style.isBg()) {
			if (style.isBright()) {
				ansi().bgBright(mapColor(style.getColor()));
			} else {
				ansi().bg(mapColor(style.getColor()));
			}
		} else {
			if (style.isBright()) {
				ansi().fgBright(mapColor(style.getColor()));
			} else {
				ansi().fg(mapColor(style.getColor()));
			}
		}
	}

	private Ansi.Color mapColor(Color color) {
		if (color == null)
			return Ansi.Color.DEFAULT;
		switch (color) {
		case BLACK:
			return Ansi.Color.BLACK;
		case RED:
			return Ansi.Color.RED;
		case GREEN:
			return Ansi.Color.GREEN;
		case YELLOW:
			return Ansi.Color.YELLOW;
		case BLUE:
			return Ansi.Color.BLUE;
		case MAGENTA:
			return Ansi.Color.MAGENTA;
		case CYAN:
			return Ansi.Color.CYAN;
		case WHITE:
			return Ansi.Color.WHITE;
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void attribute(AttributeStyle style) {
		if (style.isOn()) {
			switch (style.getAttribute()) {
			case BOLD:
				ansi().a(Ansi.Attribute.INTENSITY_BOLD);
				break;
			case ITALIC:
				ansi().a(Ansi.Attribute.ITALIC);
				break;
			case UNDERLINE:
				ansi().a(Ansi.Attribute.UNDERLINE);
				break;
			}
		} else {
			switch (style.getAttribute()) {
			case BOLD:
				ansi().a(Ansi.Attribute.INTENSITY_BOLD_OFF);
				break;
			case ITALIC:
				ansi().a(Ansi.Attribute.ITALIC_OFF);
				break;
			case UNDERLINE:
				ansi().a(Ansi.Attribute.UNDERLINE_OFF);
				break;
			}

		}
	}

	@Override
	public void reset() {
		ansi().reset();
	}

	@Override
	public void beep() {
		AnsiConsole.out.print("\u0007");
	}

	@Override
	public void printMidle(String text) {
		AffineTransform affinetransform = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
		Font font = new Font("Consolas", Font.PLAIN, 16);
		int textWidth = (int) (font.getStringBounds(text, frc).getWidth());
		int spaceWidth = (int) (font.getStringBounds(" ", frc).getWidth());
		for (int i = 0; i < width / 2 - textWidth / 2; i += spaceWidth) {
			print(" ");
		}
		print(text);
		println();
		flush();

	}

	@Override
	public void timeInput() {
		Timer timer = new Timer();
		timer.schedule(task, 1);
		uzivatVolba();
		timer.cancel();
	}

}
