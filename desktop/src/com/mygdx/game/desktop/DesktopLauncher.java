package com.mygdx.game.desktop;

import org.lwjgl.input.Keyboard;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.sak.IActivityRequestHandler;
import com.mygdx.sak.Leaderboard;
import com.mygdx.sak.SaveTheAuk;

public class DesktopLauncher implements IActivityRequestHandler, Leaderboard{
	IActivityRequestHandler handler;
	private static DesktopLauncher application;
	
	public static void main (String[] arg) {
		 if (application == null) {
	            application = new DesktopLauncher();
	        }
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "SaveTheAwk";
        cfg.useGL30 = false;
        cfg.vSyncEnabled = false;
        cfg.width = 500;
        cfg.height = 624;
		new LwjglApplication(new SaveTheAuk(application,application), cfg);
		Keyboard.enableRepeatEvents(true);
	}

	@Override
	public void showAds(boolean show) {
		// TODO Auto-generated method stub
		
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

