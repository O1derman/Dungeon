package olderman.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.text.View;

import olderman.dungeon.Style.AttributeStyle;
import olderman.dungeon.Style.ColorStyle;

public interface OS {

	void clear();

	int uzivatVolba(String... options);

	void print(String string);

	void trySaveData(ArrayList<Object> data) throws FileNotFoundException;

	void println();

	void reset();

	void color(ColorStyle style);

	void attribute(AttributeStyle style);

	void flush();

	void printAsciiArt(String asciiArt);

	void beep();

	void fillLine(String text);

	void startVideoAd();

	void onRewardedVideoAdLoaded();

	void onRewardedVideoAdOpened();

	void onRewardedVideoStarted();

	void onRewardedVideoAdClosed();

	void onRewarded();

	void onRewardedVideoAdLeftApplication();

	void onRewardedVideoAdFailedToLoad(int i);

	void onPause();

	void onResume();

	void onDestroy();

}
