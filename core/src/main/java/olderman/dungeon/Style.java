package olderman.dungeon;

public interface Style {

	enum Color {
		BLACK, RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE
	}

	class ColorStyle implements Style {
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

	class NonBrightColorStyle extends ColorStyle {
		public final ColorStyle BRIGHT;

		private NonBrightColorStyle(Color color, boolean bg) {
			super(color, false, bg);
			BRIGHT = new ColorStyle(color, true, bg);
		}

		private NonBrightColorStyle(Color color) {
			this(color, false);
		}

	}

	NonBrightColorStyle BLACK = new NonBrightColorStyle(Color.BLACK);
	NonBrightColorStyle RED = new NonBrightColorStyle(Color.RED);
	NonBrightColorStyle GREEN = new NonBrightColorStyle(Color.GREEN);
	NonBrightColorStyle YELLOW = new NonBrightColorStyle(Color.YELLOW);
	NonBrightColorStyle BLUE = new NonBrightColorStyle(Color.BLUE);
	NonBrightColorStyle MAGENTA = new NonBrightColorStyle(Color.MAGENTA);
	NonBrightColorStyle CYAN = new NonBrightColorStyle(Color.CYAN);
	NonBrightColorStyle WHITE = new NonBrightColorStyle(Color.WHITE);
	ColorStyle DEFAULT_COLOR = new ColorStyle(null, false, false);

	NonBrightColorStyle BG_BLACK = new NonBrightColorStyle(Color.BLACK, true);
	NonBrightColorStyle BG_RED = new NonBrightColorStyle(Color.RED, true);
	NonBrightColorStyle BG_GREEN = new NonBrightColorStyle(Color.GREEN, true);
	NonBrightColorStyle BG_YELLOW = new NonBrightColorStyle(Color.YELLOW, true);
	NonBrightColorStyle BG_BLUE = new NonBrightColorStyle(Color.BLUE, true);
	NonBrightColorStyle BG_MAGENTA = new NonBrightColorStyle(Color.MAGENTA, true);
	NonBrightColorStyle BG_CYAN = new NonBrightColorStyle(Color.CYAN, true);
	NonBrightColorStyle BG_WHITE = new NonBrightColorStyle(Color.WHITE, true);
	ColorStyle BG_DEFAULT_COLOR = new ColorStyle(null, false, true);

	interface AttributeStyle extends Style {
		Attribute getAttribute();

		boolean isOn();
	}

	class AttributeOff implements AttributeStyle {
		private final Attribute attribute;

		private AttributeOff(Attribute attribute) {
			this.attribute = attribute;
		}

		@Override
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

	enum Attribute implements AttributeStyle {
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

	Attribute BOLD = Attribute.BOLD;
	Attribute ITALIC = Attribute.ITALIC;
	Attribute UNDERLINE = Attribute.UNDERLINE;

	class Reset implements Style {
		private Reset() {
		}

		@Override
		public String toString() {
			return "RESET";
		}
	}

	Reset RESET = new Reset();
}
