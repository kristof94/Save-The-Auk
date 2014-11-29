package com.mygdx.game.androidsak;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import com.google.example.games.basegameutils.GameHelper.GameHelperListener;
import com.mygdx.sak.IActivityRequestHandler;
import com.mygdx.sak.Leaderboard;
import com.mygdx.sak.SaveTheAuk;

public class AndroidLauncher extends AndroidApplication implements IActivityRequestHandler,Leaderboard{

	AdView adView;
	private UiLifecycleHelper uiHelper;
	private final int SHOW_ADS = 1;
	private final int HIDE_ADS = 0;
	private GameHelper _gameHelper;
	boolean con=false;
	GameHelperListener gameHelperListener;
	
	protected Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what) {
			case SHOW_ADS:
			{	            
				adView.setVisibility(AdView.VISIBLE);


				break;
			}
			case HIDE_ADS:
			{
				adView.setVisibility(AdView.GONE);

				break;
			}
			}
		}
	};

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		uiHelper = new UiLifecycleHelper(this, null);
		RelativeLayout layout = new RelativeLayout(this);

		// Do the stuff that initialize() would do for you

		// Add the AdMob view


		RelativeLayout.LayoutParams adParams = 
				new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
						RelativeLayout.LayoutParams.WRAP_CONTENT);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		adView = new AdView(this);
		adView.setAdSize(AdSize.SMART_BANNER);// Put in your secret key here
		adView.setAdUnitId("ca-app-pub-6696968841829485/5242777655");
		adView.bringToFront();
		AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
		// Optionally populate the ad request builder.
		adRequestBuilder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);

		adView.loadAd(adRequestBuilder.build());

		//System.out.println("key : "+ );

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

		View gameView = initializeForView(new SaveTheAuk(this,this));
		layout.addView(gameView);
		layout.addView(adView, adParams);
		setContentView(layout);
		uiHelper.onCreate(savedInstanceState);
		if(isConnected())
		{
			_gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
			_gameHelper.enableDebugLog(false);

			gameHelperListener = new GameHelper.GameHelperListener(){

				@Override
				public void onSignInFailed() {

				}

				@Override
				public void onSignInSucceeded() {

				}

			};

			_gameHelper.setup(gameHelperListener);
		}

	}

	public boolean isConnected(){
		ConnectivityManager cm =
				(ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean isConnected = activeNetwork != null &&
				activeNetwork.isConnectedOrConnecting();
		if(isConnected && !con)
			con=true;
		return isConnected;
	}

	@Override
	public void showAds(boolean show) {
		handler.sendEmptyMessage(show ? SHOW_ADS : HIDE_ADS);

	}

	@Override
	public void submitScore(String user, int score) {

		if(this.isConnected())
		{
			if (FacebookDialog.canPresentShareDialog(getApplicationContext(), 
					FacebookDialog.ShareDialogFeature.SHARE_DIALOG)) {
				FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(this)
				.setLink("https://play.google.com/store/apps/details?id=com.mygdx.game.androidsak")
				.setDescription(user+" "+ String.valueOf(score)+" M. Who can do better than me ?")
				.setApplicationName("Save The Auk")
				.build();
				uiHelper.trackPendingDialogCall(shareDialog.present());
			} 
			else
			{
				handler.post(new Runnable() {

					@Override
					public void run() {
						String msg="You have to install FB app.";

						Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

					}

				});

			}
		}
		else
		{
			handler.post(new Runnable() {

				@Override
				public void run() {
					String msg="You must be connected to the internet.";

					Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

				}

			});
		}

	}

	@Override
	protected void onPause() {
		uiHelper.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		uiHelper.onResume();
		if(con)
			_gameHelper.onStart(this);
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		uiHelper.onDestroy();
		if(con)
			_gameHelper.onStop();
		super.onDestroy();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		uiHelper.onActivityResult(requestCode, resultCode, data, new FacebookDialog.Callback() {


			@Override
			public void onError(FacebookDialog.PendingCall pendingCall, Exception error, Bundle data) {
				Log.e("Activity", String.format("Error: %s", error.toString()));
			}

			@Override
			public void onComplete(FacebookDialog.PendingCall pendingCall, Bundle data) {

			}
		});		
		super.onActivityResult(requestCode, resultCode, data);
		_gameHelper.onActivityResult(requestCode, resultCode, data);

	}

	@Override
	public Context getContext() {
		// TODO Auto-generated method stub
		return super.getContext();
	}

	@Override
	public void signIn()
	{

		try
		{
			runOnUiThread(new Runnable()
			{
				//@Override
				public void run()
				{
					_gameHelper.beginUserInitiatedSignIn();
				}
			});
		}
		catch (Exception e)
		{
		}
	}

	@Override
	public void signOut()
	{

		try
		{
			runOnUiThread(new Runnable()
			{
				//@Override
				public void run()
				{
					_gameHelper.signOut();
				}
			});
		}
		catch (Exception e)
		{
		}
	}

	private final static int REQUEST_CODE_UNUSED = 9002;

	@Override
	public void rateGame()
	{
		String str ="https://play.google.com/store/apps/details?id=com.mygdx.game.androidsak&hl=fr";
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
	}

	@Override
	public void submitScore(long score)
	{

		if (isSignedIn()) {
			Games.Leaderboards.submitScore(_gameHelper.getApiClient(), getString(R.string.leaderboard_id), score);
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(_gameHelper.getApiClient(), getString(R.string.leaderboard_id)), REQUEST_CODE_UNUSED);
		}
		else if (!_gameHelper.isConnecting()) 
		{
			try {
				runOnUiThread(new Runnable() {
					public void run() {
						_gameHelper.beginUserInitiatedSignIn();
					}
				});
			} catch (final Exception ex) {
			}
			Games.Leaderboards.submitScore(_gameHelper.getApiClient(), getString(R.string.leaderboard_id), score);
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(_gameHelper.getApiClient(), getString(R.string.leaderboard_id)), REQUEST_CODE_UNUSED);
		}


	}

	@Override
	public void showScores()
	{

		if (isSignedIn() == true)
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(_gameHelper.getApiClient(), getString(R.string.leaderboard_id)), REQUEST_CODE_UNUSED);
		else
		{
			// Maybe sign in here then redirect to showing scores?
		}
	}

	@Override
	public boolean isSignedIn()
	{

		return _gameHelper.isSignedIn();
	}


}