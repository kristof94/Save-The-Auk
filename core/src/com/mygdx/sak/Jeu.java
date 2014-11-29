package com.mygdx.sak;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Jeu implements Screen {

	private Awk awk;
	public float y_pinguoin,x_pinguoin;
	boolean v=true;
	private int y_score;
	private int x_score;
	private int xt,xt2,yt,yt2;
	private float moitie;
	int metre_multiplicateur=0;
	private int u=0;
	private SaveTheAuk game;
	private Bouton pause;
	float xx=0;
	float yy=0;
	//public SC sc;
	Text scor;
	int b=0;
	Graph graph;
	Menu menu;
	public float couleur;
	boolean col = false;
	public Item item;
	
	
	public Jeu(SaveTheAuk game){

		Gdx.graphics.setVSync(false);
		this.game = game;
		graph = this.game.getVariable();
		y_score = (int) (graph.height - (graph.width/20));
		x_score = (int) (graph.width/20);
		moitie = graph.height / 2;
		yt=0;
		awk = com.mygdx.sak.SaveTheAuk.variable.awk;
		//sc = new SC();
		//sc.x=this.x_score;
		//sc.y=this.y_score;
		x_pinguoin = graph.width/2-awk.getWidth()/2;
		couleur = com.mygdx.sak.SaveTheAuk.variable.color;
		menu = graph.menu;
		menu.reset();
		
		pause = new Bouton(com.mygdx.sak.SaveTheAuk.variable.get_pause(),com.mygdx.sak.SaveTheAuk.variable.div_abscisse*17,com.mygdx.sak.SaveTheAuk.variable.div_ordonnee*20, com.mygdx.sak.SaveTheAuk.variable.div_abscisse*2, com.mygdx.sak.SaveTheAuk.variable.div_abscisse*2);
		scor = new Text(this.x_score,this.y_score, 2, "0", Color.WHITE);
		pause.addListener(new InputListener(){

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				pause.setpressed(!pause.ispressed());
				if(pause.ispressed())
					{
						pause.setImage(com.mygdx.sak.SaveTheAuk.variable.get_resume());
					}
				

				else
					{
						pause.setImage(com.mygdx.sak.SaveTheAuk.variable.get_pause());
					}
				
				return super.touchDown(event, x, y, pointer, button);
			}
			
		});
		item = new Item();
		item.addActor(pause);
		item.addActor(scor);
		scor.toFront();
		
	}

	public void gen_sc(){
		//com.mygdx.sak.SaveTheAuk.variable.stage.addActor(sc);
	}

	public void Start(){
		Gdx.graphics.setContinuousRendering(false);
		gen_niveau();
		com.mygdx.sak.SaveTheAuk.variable.decor.setVisible(false);
		graph.handler.showAds(false);
		com.mygdx.sak.SaveTheAuk.variable.decor.setVisible(true);
		com.mygdx.sak.SaveTheAuk.variable.awk.setVisible(true);
		awk.live();	
//		sc.y=(int) (graph.height - (graph.width/20));
	//	sc.setVisible(true);
		scor.setVisible(true);
		awk.setY(2*graph.ligne_pinguoin);
		y_pinguoin = graph.ligne_pinguoin;
		yt = 0;
		yt2 = 0;
		xt2 = 0;
		xt = 0;
		item.pas=1;
		x_pinguoin = graph.width/2-awk.getWidth()/2;
		awk.setX(x_pinguoin);
		graph.decor.depart = 0;
		metre_multiplicateur = 0;
		graph.decor.init_niveau(1);
		graph.stage.getCamera().position.y = moitie;
		com.mygdx.sak.SaveTheAuk.variable.life=true;
		couleur = com.mygdx.sak.SaveTheAuk.variable.color;
		col = true;
		Gdx.graphics.setContinuousRendering(true);
	}

	public void gen_niveau()
	{		
		graph.decor.setZ(0);
		y_pinguoin = graph.ligne_pinguoin;		
	}

	public void set_over()
	{
		if(graph.over==null)
			graph.over = new Over(this.game);
	}

	@Override
	public void render(float delta) 
	{
		
		Gdx.gl.glClearColor(0,couleur,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		item.draw();
		if(pause.ispressed())
		{
			graph.stage.draw();
		}
		else
		{
			
		
		this.fin_niveau();			
		this.collision();
		awk.setX(x_pinguoin);
		graph.stage.draw();
		//sc.y+=graph.vitesse;
		//sc.setMetre(graph.metre);
		scor.setString(String.valueOf(graph.metre));
		this.deplacement_pinguoin();
		com.mygdx.sak.SaveTheAuk.variable.decor.calculer_block();
		actualiser();
		}
		//com.mygdx.game.SaveTheAuk.variable.lll.log();
	}

	public void actualiser()
	{	
		y_pinguoin+=graph.vitesse;
		graph.stage.getCamera().translate(0, graph.vitesse, 0);
		awk.setY(awk.getY()+graph.vitesse);
	}
		
	private boolean fin_niveau()
	{
		if(yt< com.mygdx.sak.SaveTheAuk.variable.decor.getMap().length-3)
		{			
			xx  = (x_pinguoin / graph.div_abscisse);
			yy = (y_pinguoin / graph.div_ordonnee);
			xt = (int) Math.floor(xx) ;
			xt2 = (int) Math.ceil(xx+1);
			yt = (int) Math.floor(yy+2);
			yt2 = (int)Math.ceil(yy+2);
			graph.metre = (int) ((yt+(metre_multiplicateur* com.mygdx.sak.SaveTheAuk.variable.decor.getMap().length))/10) ;
		}

		if(yt == com.mygdx.sak.SaveTheAuk.variable.decor.getMap().length-4)
		{
			col = true;
			metre_multiplicateur++;
			graph.decor.depart = graph.stage.getCamera().position.y-moitie;
			graph.decor.setNiveau();
			com.mygdx.sak.SaveTheAuk.variable.decor.gen_map_t();
			this.gen_niveau();

		}
		return col;

	}

	public void up()
	{
		if(u<23*1)
		{
			u+=1;
			menu.move(u);
		}
		else
			Start();
	}

	public boolean collision()
	{
		if(com.mygdx.sak.SaveTheAuk.variable.decor.getMap()[yt2][xt]>0 || com.mygdx.sak.SaveTheAuk.variable.decor.getMap()[yt2][xt2]>0 || com.mygdx.sak.SaveTheAuk.variable.decor.getMap()[yt][xt]>0 || com.mygdx.sak.SaveTheAuk.variable.decor.getMap()[yt][xt2]>0)
		{
			com.mygdx.sak.SaveTheAuk.variable.life=false;		
			scor.setVisible(false);
			menu.reset();
			graph.over.reset();		
			u=0;
			com.mygdx.sak.SaveTheAuk.variable.decor.init_niveau(1);
			awk.dead();
			menu.score.setColor(Color.BLUE);
			menu.best_score.setString("High Score : " + String.valueOf(com.mygdx.sak.SaveTheAuk.variable.BEST_SCORE)+" M");
			//graph.hurt.play();
			Gdx.input.setInputProcessor(com.mygdx.sak.SaveTheAuk.variable.menu);
			game.setScreen(graph.over);
			graph.enregistrer_score(graph.metre);
			 
			return true;			
		}
		else
			return false;

	}

	public void detection_bordure()
	{
		if(x_pinguoin<=0)
		{
			x_pinguoin=0;
		}

		if(x_pinguoin>=(graph.width-0)-awk.getWidth())
		{
			x_pinguoin=(graph.width-0)-awk.getWidth();
		}
	}

	public void deplacement_pinguoin()
	{

		if(item.pas == 2)
			x_pinguoin-=com.mygdx.sak.SaveTheAuk.variable.decor.vitesse_x;
		if(item.pas == 0)
			x_pinguoin+=com.mygdx.sak.SaveTheAuk.variable.decor.vitesse_x;
		else
			x_pinguoin+=0;

		detection_bordure();

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
		graph.enregistrer_score(graph.metre);
		//this.dispose();
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
		//score.dispose();
		//sc.dispose();
		scor.dispose();
		//bg.dispose();

	}


}
