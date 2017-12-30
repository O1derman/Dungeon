package olderman.dungeon;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Resources {
	public static String getString(String name) {
		try (InputStreamReader reader = new InputStreamReader(getStream(name), "utf-8")) {
			final char[] buffer = new char[1024];
			final StringBuilder result = new StringBuilder();
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) >= 0)
				result.append(buffer, 0, read);
			return result.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static InputStream getStream(String name) {
		InputStream stream = Resources.class.getResourceAsStream(name);
		if (stream == null)
			throw new NoSuchResourceException(name);
		return stream;
	}

	public static class NoSuchResourceException extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public NoSuchResourceException(String resourceName) {
			super("Resource \"" + resourceName + "\" doesn't exist");
		}
	}
}
