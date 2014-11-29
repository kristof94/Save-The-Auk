package com.mygdx.sak;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class Splash implements Screen {

	private SaveTheAuk Game;
    private long startTime=0;  	
	private BitmapFont title;
	private float x,y;
	private SpriteBatch spriteBatch;
	
	public Splash(SaveTheAuk game) {

		Game = game;
		Game.getVariable().parameter.size = (int)(3*( Game.getVariable().width/20));
		title = com.mygdx.sak.SaveTheAuk.variable.generator.generateFont(com.mygdx.sak.SaveTheAuk.variable.parameter);
		title.setColor(Color.WHITE);
		x = Game.getVariable().width/2-7.5f*(Game.getVariable().width/20);
		y = (Game.getVariable().height/20)+(Game.getVariable().height/2);
		title.setColor(Color.WHITE);
		spriteBatch = new SpriteBatch();
	}
			
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0,0,0,1);
		
		spriteBatch.begin();
		spriteBatch.enableBlending();
		title.draw(spriteBatch, "Save The Auk", x, y);
		spriteBatch.end();
		//rendCount++;
        if (TimeUtils.millis()>(startTime+500)) 
        	Game.setHome();
        
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
        startTime = TimeUtils.millis();

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
