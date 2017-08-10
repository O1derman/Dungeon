package com.example.olderman.dungeon;

import android.app.Activity;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AndroidOS implements OS {

	private Activity activity;
	private TextView textView;
	private final SpannableStringBuilder sb = new SpannableStringBuilder();
	private LinearLayout buttons;

	public AndroidOS() {
	}

	public void set(Activity activity, TextView textView, LinearLayout buttons) {
		if (this.activity != null) {
			throw new IllegalStateException();
		}
		activity.getClass();// null check
		buttons.getClass();
		textView.setText(sb);// also a null check
		this.activity = activity;
		this.textView = textView;
		this.buttons = buttons;
		createButtons();
	}

	public void onActivityDestroy() {
		if (activity == null) {
			throw new IllegalStateException();
		}
		activity = null;
		textView = null;
		buttons = null;
	}

	private volatile int result = -1;
	private String[] buttonLabels;

	private void createButtons() {
		if (buttonLabels != null) {
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < buttonLabels.length; i++) {
						createButton(i + 1, buttonLabels[i]);
					}
				}
			});
		}
	}

	private void createButton(final int i, String text) {
		Button button = new Button(activity);
		button.setText(text);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				buttons.removeAllViews();
				buttonLabels = null;
				synchronized (AndroidOS.this) {
					result = i;
					AndroidOS.this.notifyAll();
				}
			}
		});
		buttons.addView(button, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
	}

	public void stop() {
		synchronized (this) {
			result = -2;
			notifyAll();
		}
	}

	@Override
	public int uzivatVolba(final String... options) {
		try {
			buttonLabels = options;
			createButtons();

			synchronized (this) {
				while (result == -1) {
					try {
						wait();
					} catch (InterruptedException e) {
					}
				}
			}
			if (result == -2) {
				throw new ExitException();
			}
			clear();
			return result;
		} finally {
			result = -1;
		}
	}

	@Override
	public void clear() {
		sb.clear();
		flush();
	}

	@Override
	public void print(String string) {
		sb.append(string);
	}

	@Override
	public void println() {
		print("\n");
	}

	@Override
	public void reset() {
		for (int i = 0; i < spans.length; i++) {
			reset(i);
		}
	}

	private void reset(int i) {
		if (spans[i] != null) {
			sb.setSpan(spans[i], spanStarts[i], sb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			spans[i] = null;
		}
		spanStarts[i] = sb.length();
	}

	private final CharacterStyle[] spans = new CharacterStyle[NUM_SPANS];
	private final int[] spanStarts = new int[NUM_SPANS];

	private static final int BG = 0;
	private static final int FG = 1;
	private static final int NUM_SPANS = 2;

	@Override
	public void color(Style.ColorStyle style) {
		int i = style.isBg() ? BG : FG;
		reset(i);
		if (style.getColor() == null) {
			spans[i] = null;
		} else {
			int color = ContextCompat.getColor(activity, mapColor(style));
			if (style.isBg()) {
				spans[i] = new BackgroundColorSpan(color);
			} else {
				spans[i] = new ForegroundColorSpan(color);
			}
		}

	}

	private int mapColor(Style.ColorStyle color) {
		if (color.isBright()) {
			switch (color.getColor()) {
			case BLACK:
				return R.color.brightBlack;
			case RED:
				return R.color.brightRed;
			case GREEN:
				return R.color.brightGreen;
			case YELLOW:
				return R.color.brightYellow;
			case BLUE:
				return R.color.brightBlue;
			case MAGENTA:
				return R.color.brightMagenta;
			case CYAN:
				return R.color.brightCyan;
			case WHITE:
				return R.color.brightWhite;
			default:
				throw new IllegalArgumentException();
			}
		} else {
			switch (color.getColor()) {
			case BLACK:
				return R.color.black;
			case RED:
				return R.color.red;
			case GREEN:
				return R.color.green;
			case YELLOW:
				return R.color.yellow;
			case BLUE:
				return R.color.blue;
			case MAGENTA:
				return R.color.magenta;
			case CYAN:
				return R.color.cyan;
			case WHITE:
				return R.color.white;
			default:
				throw new IllegalArgumentException();
			}
		}
	}

	@Override
	public void attribute(Style.AttributeStyle style) {

	}

	@Override
	public void flush() {
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				textView.setText(sb);
				textView.requestLayout();
			}
		});
	}

	private static final Pattern NEWLINE = Pattern.compile("\\v|$");

	@Override
	public void printAsciiArt(String asciiArt) {
		int textSize = (int) textView.getTextSize();
		int width = textView.getWidth();
		Matcher matcher = NEWLINE.matcher(asciiArt);
		float asciiArtWidth = 0;
		int previousEnd = 0;
		Paint paint = textView.getPaint();
		while (matcher.find()) {
			float lineWidth = paint.measureText(asciiArt, previousEnd, matcher.start());
			previousEnd = matcher.end();
			if (lineWidth > asciiArtWidth)
				asciiArtWidth = lineWidth;
		}
		if (asciiArtWidth > width)
			textSize = (int) (textSize * width / asciiArtWidth);

		int start = sb.length();
		print(asciiArt);
		sb.setSpan(new AbsoluteSizeSpan(textSize), start, sb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	}

	@Override
	public void beep() {

	}

}
