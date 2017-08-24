package olderman.dungeon;

import android.app.FragmentManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	private static final String TAG_RETAINED_FRAGMENT = "RetainedFragment";

	private RetainedFragment fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView textView = (TextView) findViewById(R.id.textView);
		LinearLayout buttons = (LinearLayout) findViewById(R.id.buttons);

		FragmentManager fm = getFragmentManager();
		fragment = (RetainedFragment) fm.findFragmentByTag(TAG_RETAINED_FRAGMENT);
		if (fragment == null) {
			fragment = new RetainedFragment();
			fm.beginTransaction().add(fragment, TAG_RETAINED_FRAGMENT).commit();
		}
		fragment.set(this, textView, buttons);
		fragment.start();
	}

	@Override
	public void onBackPressed() {
		if (fragment.isFinished()) {
			super.onBackPressed();
		} else {
			new AlertDialog.Builder(this).setMessage(R.string.confirm_exit)
					.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							MainActivity.super.onBackPressed();
						}
					}).setNegativeButton(android.R.string.no, null).show();
		}
	}
}
