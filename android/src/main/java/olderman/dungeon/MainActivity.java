package olderman.dungeon;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class MainActivity extends Activity implements RewardedVideoAdListener {

	private static final String TAG_RETAINED_FRAGMENT = "RetainedFragment";

	private RetainedFragment fragment;

	private RewardedVideoAd mAd;
	private TextView mText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		mText = (TextView)findViewById(R.id.textView);
		MobileAds.initialize(this,"ca-app-pub-3904893950784361/7108760108");
		MobileAds.initialize(getApplicationContext(),"ca-app-pub-3904893950784361/5217093936");

		mAd = MobileAds.getRewardedVideoAdInstance(this);
		loadRewardedVideoAd();

		AdView adView = (AdView) findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder()
				.setRequestAgent("android_studio:ad_template").build();
		adView.loadAd(adRequest);

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

	private void loadRewardedVideoAd(){
		if(!mAd.isLoaded()){
			mAd.loadAd("ca-app-pub-3904893950784361/5217093936", new AdRequest.Builder().build());
		}
	}

	public void startVideoAd(View view){
		if(mAd.isLoaded()){
			 mAd.show();
		}
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
            loadRewardedVideoAd();
	}

	@Override
	public void onRewarded(RewardItem rewardItem) {

	}

	@Override
	public void onRewardedVideoAdLeftApplication() {

	}

	@Override
	public void onRewardedVideoAdFailedToLoad(int i) {

	}

    @Override
    protected void onPause() {
	    mAd.pause(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
	    mAd.resume(this);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
	    mAd.destroy(this);
        super.onDestroy();
    }
}
