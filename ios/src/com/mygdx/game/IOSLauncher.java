package com.mygdx.game;

import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;
import com.mygdx.sak.IActivityRequestHandler;
import com.mygdx.sak.Leaderboard;
import com.mygdx.sak.SaveTheAuk;

public class IOSLauncher extends IOSApplication.Delegate implements IActivityRequestHandler,Leaderboard{
    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();
        return new IOSApplication(new SaveTheAuk(null, null), config);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }

	@Override
	public void showAds(boolean show) {
		
		
	}

	@Override
	public void submitScore(String user, int score) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void signIn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void signOut() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rateGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void submitScore(long score) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showScores() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSignedIn() {
		// TODO Auto-generated method stub
		return false;
	}
}