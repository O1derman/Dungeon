package com.example.olderman.dungeon;

public interface Style {

	public static enum Color {
		BLACK, RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE;
	}

	public static class ColorStyle implements Style {
		private final Color color;
		private final boolean bright;
		private final boolean bg;

		private ColorStyle(Color color, boolean bright, boolean bg) {
			this.color = color;
			this.bright = bright;
			this.bg = bg;
		}

		public Color getColor() {
			return color;
		}

		public boolean isBright() {
			return bright;
		}

		public boolean isBg() {
			return bg;
		}

		@Override
		public String toString() {
			if (color == null)
				return "default " + (bg ? "background " : "") + "color";
			return (bright ? "bright " : "") + color + (bg ? " background" : "");
		}
	}

	public static class NonBrightColorStyle extends ColorStyle {
		public final ColorStyle BRIGHT;

		private NonBrightColorStyle(Color color, boolean bg) {
			super(color, false, bg);
			BRIGHT = new ColorStyle(color, true, bg);
		}

		private NonBrightColorStyle(Color color) {
			this(color, false);
		}

	}

	public static final NonBrightColorStyle BLACK = new NonBrightColorStyle(Color.BLACK);
	public static final NonBrightColorStyle RED = new NonBrightColorStyle(Color.RED);
	public static final NonBrightColorStyle GREEN = new NonBrightColorStyle(Color.GREEN);
	public static final NonBrightColorStyle YELLOW = new NonBrightColorStyle(Color.YELLOW);
	public static final NonBrightColorStyle BLUE = new NonBrightColorStyle(Color.BLUE);
	public static final NonBrightColorStyle MAGENTA = new NonBrightColorStyle(Color.MAGENTA);
	public static final NonBrightColorStyle CYAN = new NonBrightColorStyle(Color.CYAN);
	public static final NonBrightColorStyle WHITE = new NonBrightColorStyle(Color.WHITE);
	public static final ColorStyle DEFAULT_COLOR = new ColorStyle(null, false, false);

	public static final NonBrightColorStyle BG_BLACK = new NonBrightColorStyle(Color.BLACK, true);
	public static final NonBrightColorStyle BG_RED = new NonBrightColorStyle(Color.RED, true);
	public static final NonBrightColorStyle BG_GREEN = new NonBrightColorStyle(Color.GREEN, true);
	public static final NonBrightColorStyle BG_YELLOW = new NonBrightColorStyle(Color.YELLOW, true);
	public static final NonBrightColorStyle BG_BLUE = new NonBrightColorStyle(Color.BLUE, true);
	public static final NonBrightColorStyle BG_MAGENTA = new NonBrightColorStyle(Color.MAGENTA, true);
	public static final NonBrightColorStyle BG_CYAN = new NonBrightColorStyle(Color.CYAN, true);
	public static final NonBrightColorStyle BG_WHITE = new NonBrightColorStyle(Color.WHITE, true);
	public static final ColorStyle BG_DEFAULT_COLOR = new ColorStyle(null, false, true);

	public static interface AttributeStyle extends Style {
		public Attribute getAttribute();

		public boolean isOn();
	}

	public static class AttributeOff implements AttributeStyle {
		private final Attribute attribute;

		private AttributeOff(Attribute attribute) {
			this.attribute = attribute;
		}

		public Attribute getAttribute() {
			return attribute;
		}

		@Override
		public boolean isOn() {
			return false;
		}

		@Override
		public String toString() {
			return attribute + " off";
		}

	}

	public static enum Attribute implements AttributeStyle {
		BOLD, ITALIC, UNDERLINE;

		public final AttributeOff OFF = new AttributeOff(this);

		@Override
		public Attribute getAttribute() {
			return this;
		}

		@Override
		public boolean isOn() {
			return true;
		}
	}

	public static final Attribute BOLD = Attribute.BOLD;
	public static final Attribute ITALIC = Attribute.ITALIC;
	public static final Attribute UNDERLINE = Attribute.UNDERLINE;

	public static class Reset implements Style {
		private Reset() {
		}

		@Override
		public String toString() {
			return "RESET";
		}
	}

	public static final Reset RESET = new Reset();
}
