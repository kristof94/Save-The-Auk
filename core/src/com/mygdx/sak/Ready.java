package com.mygdx.sak;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Ready extends Stage implements Screen {

	float couleur ;
	private int u=0;
	
	public Ready(){
		couleur = com.mygdx.sak.SaveTheAuk.variable.color;
		
	}

	public void set_u(){
		u=0;
		com.mygdx.sak.SaveTheAuk.variable.menu.ready.setVisible(true);
		com.mygdx.sak.SaveTheAuk.variable.decor.setVisible(false);
		com.mygdx.sak.SaveTheAuk.variable.menu.start2.setVisible(true);
		com.mygdx.sak.SaveTheAuk.variable.awk.setX(com.mygdx.sak.SaveTheAuk.variable.width/2-com.mygdx.sak.SaveTheAuk.variable.awk.getWidth()/2);
		com.mygdx.sak.SaveTheAuk.variable.awk.live();
		com.mygdx.sak.SaveTheAuk.variable.stage.getCamera().position.y = com.mygdx.sak.SaveTheAuk.variable.height / 2;
		this.addActor(com.mygdx.sak.SaveTheAuk.variable.menu.awk);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0,couleur,1,1);
		com.mygdx.sak.SaveTheAuk.variable.stage.draw();
		com.mygdx.sak.SaveTheAuk.variable.menu.draw();
		actualiser();					

		if(u==0)
			com.mygdx.sak.SaveTheAuk.variable.bg.calculer_bord();
		else
			up();

	}

	public void up()
	{
		if(u<90)
		{
			u+=1;
			com.mygdx.sak.SaveTheAuk.variable.menu.move2(u);
		}

		else
		{
			Start();
		}

	}

	public void Start(){

		com.mygdx.sak.SaveTheAuk.variable.menu.ready.setVisible(false);
		com.mygdx.sak.SaveTheAuk.variable.menu.start2.setVisible(false);
		com.mygdx.sak.SaveTheAuk.variable.decor.setVisible(true);
		com.mygdx.sak.SaveTheAuk.variable.menu.awk.remove();
		com.mygdx.sak.SaveTheAuk.variable.bg.setVisible(false);
		com.mygdx.sak.SaveTheAuk.variable.prefs.putInteger("color", com.mygdx.sak.SaveTheAuk.variable.awk.getCurseur());
		com.mygdx.sak.SaveTheAuk.variable.prefs.flush();
		com.mygdx.sak.SaveTheAuk.variable.jeu.Start();
		com.mygdx.sak.SaveTheAuk.variable.jeu.gen_sc();
		Gdx.input.setInputProcessor(com.mygdx.sak.SaveTheAuk.variable.jeu.item);
		com.mygdx.sak.SaveTheAuk.variable.s.setScreen(com.mygdx.sak.SaveTheAuk.variable.jeu);		
	}


	public void actualiser()
	{			
		com.mygdx.sak.SaveTheAuk.variable.stage.getCamera().translate(0, com.mygdx.sak.SaveTheAuk.variable.vitesse, 0);
		com.mygdx.sak.SaveTheAuk.variable.awk.setY(com.mygdx.sak.SaveTheAuk.variable.awk.getY()+com.mygdx.sak.SaveTheAuk.variable.vitesse);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		
		com.mygdx.sak.SaveTheAuk.variable.handler.showAds(true);
		Gdx.input.setInputProcessor(this);
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

		com.mygdx.sak.SaveTheAuk.variable.handler.showAds(true);
		//Gdx.input.setInputProcessor(this);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		this.dispose();
	}

	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(u==0)
			u=1;
		return super.touchDown(screenX, screenY, pointer, button);
	}

}
