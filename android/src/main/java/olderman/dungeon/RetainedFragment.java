package olderman.dungeon;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *
 */
public class RetainedFragment extends Fragment {

	private final AndroidOS os = new AndroidOS();
	private final Dungeon dungeon = new Dungeon(os);
	private boolean running = false;

	public void set(Activity activity, TextView textView, LinearLayout buttons) {
		os.set(activity, textView, buttons);
	}

	public void start() {
		if (!running) {
			running = true;
			new Thread("Dungeon thread") {
				@Override
				public void run() {
					try {
						Thread.sleep(500);
						dungeon.run();
					} catch (ExitException | InterruptedException ignored) {
					}
				}
			}.start();
		}
	}

	@Override
	public void onDetach() {
		os.onActivityDestroy();
		super.onDetach();
	}

	@Override
	public void onDestroy() {
		os.stop();
		super.onDestroy();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

}
