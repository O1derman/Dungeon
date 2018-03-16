package olderman.dungeon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import javax.swing.text.View;

import olderman.dungeon.Style.AttributeStyle;
import olderman.dungeon.Style.Color;
import olderman.dungeon.Style.ColorStyle;
import olderman.dungeon.map.RandMapData;

public class DesktopOS implements OS, Serializable {
	private static final long serialVersionUID = 1L;

	private Cheats cheats = null;

	public static boolean enableClear = true;

	private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

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
		fillLine("#");
		println();

		flush();
		while (true) {
			String nabidka;
			try {
				nabidka = in.readLine();
				if (cheats != null) {
					while (cheats.executeCommand(nabidka)) {
						nabidka = in.readLine();
					}
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			try {
				int vysledek = Integer.parseInt(nabidka);
				if (vysledek > 0 && vysledek <= options.length) {
					clear();
					return vysledek;
				}
			} catch (NumberFormatException ignored) {
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

	@SuppressWarnings("incomplete-switch")
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
	public void fillLine(String text) {

		int count = 100 / text.length();

		for (int i = 0; i < count; i++) {
			print(text);
		}
	}

	@Override
	public void startVideoAd() {
		println("This is just for android users!");
	}

	@Override
	public void onRewardedVideoAdLoaded() {

	}

	@Override
	public void onRewardedVideoAdOpened() {

	}

	@Override
	public void onRewardedVideoStarted() {

	}

	@Override
	public void onRewardedVideoAdClosed() {

	}

	@Override
	public void onRewarded() {

	}

	@Override
	public void onRewardedVideoAdLeftApplication() {

	}

	@Override
	public void onRewardedVideoAdFailedToLoad(int i) {

	}

	@Override
	public void onPause() {

	}

	@Override
	public void onResume() {

	}

	@Override
	public void onDestroy() {

	}

	@Override
	public void trySaveData(ArrayList<Object> data) {

		try {
			FileOutputStream fileOut = new FileOutputStream("data.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(data);
			out.close();
			fileOut.close();

		} catch (IOException i) {
			i.printStackTrace();
		}

	}

	@Override
	public void tryLoadData() {
	}
}
