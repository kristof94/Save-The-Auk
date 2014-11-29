package com.mygdx.sak;

import com.badlogic.gdx.Game;

public class SaveTheAuk extends Game {
	
	public static  Graph variable;
	private Home home=null;	
	private IActivityRequestHandler myRequestHandler;
	private Leaderboard leader;
	private Splash splash;
	//private ShapeRenderer shape;

	public SaveTheAuk(IActivityRequestHandler myRequestHandler,	Leaderboard leader){
		
		this.myRequestHandler=myRequestHandler;
		this.myRequestHandler.showAds(false);
		this.leader = leader;

	}
	
	@Override
	public void create() {		
		variable = new Graph(this,leader);		
		setScreen(splash = new Splash(this));
		variable.gen_obj();
		//shape = new ShapeRenderer();
	}

	public void setHome(){
		home = new Home(this);
		variable.handler = this.myRequestHandler;
		setScreen(home);


	}
	
	@Override
	public void dispose() {

		splash.dispose();
		this.getVariable().dispose();
		if(home != null)
			home.dispose();
		super.dispose();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void render() {
		super.render();
		//generer_grillage();
	}

	/*private void generer_grillage() {
		for(int nb=0;nb<20;nb++)
		{
			shape.setColor(0.51f,0.51f,0.51f, 0.94f);
			shape.begin(ShapeType.Line);
			shape.line(0, (getVariable().height/20)*nb,getVariable().width,(getVariable().height/20)*nb);
			shape.line(nb*(getVariable().width/20), 0,nb*(getVariable().width/20), getVariable().height);
			shape.end();
		}		
	}*/

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	public Graph getVariable() {
		return variable;
	}
}
