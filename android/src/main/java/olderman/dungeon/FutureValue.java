package olderman.dungeon;

/**
 * 
 */

public class FutureValue<T> {
	private T value;
	private boolean isSet;

	/**
	 * Waits until the value is set and that returns it.
	 *
	 * @return the value
	 * @throws InterruptedException
	 */
	public synchronized T get() throws InterruptedException {
		while (!isSet)
			wait();
		return value;
	}

	/**
	 * Returns the value or null if the value is not set.
	 * 
	 * @return the value or null if the value is not set
	 */
	public synchronized T getIfSet() {
		if (!isSet)
			return null;
		return value;
	}

	/**
	 * Sets the value and notifies all threads blocked on {@link FutureValue#get()}.
	 *
	 * @param value
	 * @throws IllegalStateException
	 *             when the value is already set
	 */
	public synchronized void set(T value) {
		if (isSet)
			throw new IllegalStateException("already set");
		this.value = value;
		isSet = true;
		notifyAll();
	}

	/**
	 * Sets the value and possibly overwrites the previous one.
	 *
	 * @param value
	 * @return whether the value was set before
	 */
	public synchronized boolean setAndOverwrite(T value) {
		this.value = value;
		if (!isSet) {
			isSet = true;
			notifyAll();
			return false;
		}
		return true;
	}

	/**
	 * Unsets the value so that this object can be reused. Throws an exception when
	 * the value is not set.
	 * 
	 * @throws IllegalStateException
	 *             when the value is not set
	 */
	public synchronized void unset() {
		if (!isSet)
			throw new IllegalStateException("not set");
		unsetIfSet();
	}

	/**
	 * Unsets the value so that this object can be reused. Does nothing when the
	 * value is not set.
	 */
	public synchronized void unsetIfSet() {
		value = null;
		isSet = false;
	}

	/**
	 * Returns true if the value is set, and false otherwise.
	 * 
	 * @return whether the value is set
	 */
	public synchronized boolean isSet() {
		return isSet;
	}
}
