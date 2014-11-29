package com.mygdx.sak;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class Home implements Screen{

	private Awk awk;
	private Menu menu;
	private  SaveTheAuk game;
	private int u = 0;
	private int curseur = 0;		
	private float rr=0;
	float couleur ;
	
	public Home(SaveTheAuk G){

		com.mygdx.sak.SaveTheAuk.variable.menu = new Menu();
		menu = com.mygdx.sak.SaveTheAuk.variable.menu;
		game = G;
		curseur = game.getVariable().prefs.getInteger("color");
		if(curseur>5 || curseur <0)
			curseur=0;
		awk = com.mygdx.sak.SaveTheAuk.variable.awk;//new Awk(curseur);
		awk.color_change(curseur);		
		awk.setY(2*G.getVariable().ligne_pinguoin);
		//game.getVariable().parameter.size = (int)(2*(game.getVariable().width/20));

		com.mygdx.sak.SaveTheAuk.variable.bg = new Background(com.mygdx.sak.SaveTheAuk.variable.width,com.mygdx.sak.SaveTheAuk.variable.height);

		game.getVariable().stage.addActor(game.getVariable().bg);
		game.getVariable().stage.addActor(com.mygdx.sak.SaveTheAuk.variable.decor);
		game.getVariable().stage.addActor(awk);
		rr = game.getVariable().stage.getCamera().position.y;		
		Gdx.input.setInputProcessor(menu);
		game.getVariable().jeu  = new Jeu(this.game);
		game.getVariable().jeu.set_over();
		//menu.score.set_size(3);
		menu.best_score.setString("High Score : " + String.valueOf(game.getVariable().BEST_SCORE)+" M");
		menu.best_score.setY((game.getVariable().height/20)+(game.getVariable().height/2));
		menu.decalbs();
		couleur = com.mygdx.sak.SaveTheAuk.variable.color;
		com.mygdx.sak.SaveTheAuk.variable.setColor(couleur);
	}

	public void actualiser()
	{			
		game.getVariable().stage.getCamera().translate(0, game.getVariable().vitesse, 0);
		awk.setY(awk.getY()+game.getVariable().vitesse);
	}

	public void Start(){
				
		game.setScreen(com.mygdx.sak.SaveTheAuk.variable.ready);
		Gdx.input.setInputProcessor(game.getVariable().ready);
	}

	public void up()
	{
		if(u<20)
		{
			u+=1;
			menu.move(u);
			menu.best_score.setY(menu.best_score.getY()+u*com.mygdx.sak.SaveTheAuk.variable.vitesse);
		}

		else
		{
			menu.start.setpressed(false);
			menu.best_score.setVisible(false);
			menu.start2.setVisible(true);
			menu.ready.setVisible(true);
			Start();
		}

	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0,couleur,1,1);
		game.getVariable().stage.draw();
		menu.draw();		
		this.actualiser();			
		game.getVariable().bg.calculer_bord();
		if(menu.start.ispressed())
			up();
		
	}

	@Override
	public void resize(int width, int height) {

		game.getVariable().stage.getCamera().position.y=rr;
		awk.setY(game.getVariable().vitesse*8);
	}

	@Override
	public void show() {
		game.getVariable().bg.setdd();
		game.getVariable().stage.getCamera().position.y=rr;
		awk.setY(game.getVariable().vitesse*8);
		game.getVariable().handler.showAds(true);

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
		game.getVariable().bg.setdd();
		game.getVariable().stage.getCamera().position.y=rr;
		awk.setY(game.getVariable().vitesse*8);
		game.getVariable().handler.showAds(true);
		//Gdx.input.setInputProcessor(this);
	}

	@Override
	public void dispose() {
		//com.mygdx.game.SaveTheAuk.variable.decor.dispose();
		

	}

}
