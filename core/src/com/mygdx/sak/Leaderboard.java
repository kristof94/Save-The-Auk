package com.mygdx.sak;

public interface Leaderboard {

	public void submitScore(String user, int score);
	public void signIn();
	public void signOut();
	public void rateGame();
	public void submitScore(long score);
	public void showScores();
	public boolean isSignedIn();

}
