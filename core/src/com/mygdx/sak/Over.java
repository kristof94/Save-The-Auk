package com.mygdx.sak;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class Over implements Screen {

	private int u=0;
	private Menu menu;
	private SaveTheAuk game;
	private float couleur;
	private Jeu jeu;
	private Graph graph;
	private int score_;

	
	public Over(SaveTheAuk game)
	{
		this.game=game;
		menu = com.mygdx.sak.SaveTheAuk.variable.menu;
		jeu = com.mygdx.sak.SaveTheAuk.variable.jeu;
		couleur = jeu.couleur;
		graph = com.mygdx.sak.SaveTheAuk.variable;
	}
	
	public void reset(){
		com.mygdx.sak.SaveTheAuk.variable.handler.showAds(true);
		menu.best_score.setVisible(true);
		u=0;		
		couleur = jeu.couleur;
	}

	@Override
	public void render(float delta) {
		//
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0,couleur,1,1);
		com.mygdx.sak.SaveTheAuk.variable.stage.draw();
		menu.draw();		
		if(score_<graph.metre)
			{
				menu.decal(score_);
				menu.score.setString(String.valueOf(score_++)+" M");				
			}
		if(score_==graph.metre)
			menu.score.setString(String.valueOf(graph.metre)+" M");									
		
		if(menu.start.ispressed())
		{
			up();
		}
		
	}

	private void up() {
		// TODO Auto-generated method stub
		if(u<15*2)
		{
			u+=1;
			menu.move(u);
			graph.stage.getCamera().translate(0, -u*graph.vitesse, 0);
		}
		else
		{
			score_=0;
			u=0;
			menu.start.setpressed(false);
			menu.best_score.setVisible(false);
			com.mygdx.sak.SaveTheAuk.variable.bg.setdd();
			com.mygdx.sak.SaveTheAuk.variable.decor.setVisible(false);
			com.mygdx.sak.SaveTheAuk.variable.awk.setVisible(true);
			com.mygdx.sak.SaveTheAuk.variable.menu.reset2();
			com.mygdx.sak.SaveTheAuk.variable.bg.setVisible(true);
			com.mygdx.sak.SaveTheAuk.variable.awk.setY(2*graph.ligne_pinguoin);
			graph.ready.set_u();		
			game.setScreen(graph.ready);			
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

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
