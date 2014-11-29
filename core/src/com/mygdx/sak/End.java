package com.mygdx.sak;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class End implements Screen{

	Awk tabar[];
	Awk tabav[];
	Awk tabara[];
	Pin p[],min[];
	private float y;
	private int score_=0;

	public End(){
		int col=0;
		float taille;
		p = new Pin[35];
		min = new Pin[5];
		int zz = 2;
		
		for(int i = 0;i<min.length;i+=1)
		{
			min[i]= new Pin(col);
			min[i].setWidth(com.mygdx.sak.SaveTheAuk.variable.div_abscisse);
			min[i].setHeight(com.mygdx.sak.SaveTheAuk.variable.div_ordonnee);
			min[i].setX(com.mygdx.sak.SaveTheAuk.variable.div_abscisse*(zz));
			zz+=4;
			col++;
			if(col==6)
				col=0;
		}
		
		p[0]= new Pin(col);				
		p[0].setWidth( com.mygdx.sak.SaveTheAuk.variable.div_abscisse);
		p[0].setHeight( com.mygdx.sak.SaveTheAuk.variable.div_ordonnee);
		p[1]= new Pin(col);				
		p[1].setWidth( com.mygdx.sak.SaveTheAuk.variable.div_abscisse);
		p[1].setHeight( com.mygdx.sak.SaveTheAuk.variable.div_ordonnee);
		
		for(int i = 2;i<p.length;i+=1)
		{
			taille = (float) (1+(Math.random() * (2))) ;
			p[i]= new Pin(col);				
			p[i].setWidth(taille *com.mygdx.sak.SaveTheAuk.variable.div_abscisse);
			p[i].setHeight(taille *com.mygdx.sak.SaveTheAuk.variable.div_ordonnee);

			col++;

			if(col==6)
				col=0;
		}
		
		for(int i = 0;i<29;i+=2)
		{

			p[i].setX(com.mygdx.sak.SaveTheAuk.variable.div_abscisse*1);
			//p[i].setY(block_niveau-com.mygdx.game.SaveTheAuk.variable.div_ordonnee*2*i);			
			p[i+1].setX(com.mygdx.sak.SaveTheAuk.variable.div_abscisse*19-p[i+1].getWidth());
			//p[i+1].setY(block_niveau-com.mygdx.game.SaveTheAuk.variable.div_ordonnee*2*i);

		}


		p[30].setX(com.mygdx.sak.SaveTheAuk.variable.div_abscisse*1);
		//p[30].setY(p[0].getY() + com.mygdx.game.SaveTheAuk.variable.div_ordonnee * 3);
		p[31].setX(com.mygdx.sak.SaveTheAuk.variable.div_abscisse*5);
		//p[31].setY(p[30].getY());
		p[32].setX(com.mygdx.sak.SaveTheAuk.variable.div_abscisse*9);
		//p[32].setY(p[31].getY());
		p[33].setX(com.mygdx.sak.SaveTheAuk.variable.div_abscisse*13);
		//p[33].setY(p[32].getY());
		p[34].setX(com.mygdx.sak.SaveTheAuk.variable.div_abscisse*17);
		//p[34].setY(p[33].getY());
	
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,com.mygdx.sak.SaveTheAuk.variable.color,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);		
		com.mygdx.sak.SaveTheAuk.variable.stage.draw();
		move();


	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		com.mygdx.sak.SaveTheAuk.variable.menu.fb.setVisible(false);
		com.mygdx.sak.SaveTheAuk.variable.menu.leaderboard.setVisible(false);
		com.mygdx.sak.SaveTheAuk.variable.menu.tw.setVisible(false);

		y = com.mygdx.sak.SaveTheAuk.variable.stage.getCamera().position.y;
		for(int i=0;i<p.length;i++)
			com.mygdx.sak.SaveTheAuk.variable.stage.addActor(p[i]);

		for(int i=0;i<min.length;i++)
			com.mygdx.sak.SaveTheAuk.variable.stage.addActor(min[i]);
		
		float block_niveau = y+70*com.mygdx.sak.SaveTheAuk.variable.div_ordonnee; 		
		for(int i = 0;i<29;i+=2)
		{

			p[i].setY(block_niveau-com.mygdx.sak.SaveTheAuk.variable.div_ordonnee*2*i);			
			p[i+1].setY(block_niveau-com.mygdx.sak.SaveTheAuk.variable.div_ordonnee*2*i);

		}

		p[30].setY(p[0].getY() + com.mygdx.sak.SaveTheAuk.variable.div_ordonnee * 3);
		p[31].setY(p[30].getY());
		p[32].setY(p[31].getY());
		p[33].setY(p[32].getY());
		p[34].setY(p[33].getY());
			for(int i=0;i<min.length;i++)
				min[i].setY(p[30].getY()+com.mygdx.sak.SaveTheAuk.variable.div_ordonnee*3.5f);

			
	}

	public void move(){
		if (com.mygdx.sak.SaveTheAuk.variable.stage.getCamera().position.y-y>=com.mygdx.sak.SaveTheAuk.variable.div_ordonnee*5)
		{

			if(com.mygdx.sak.SaveTheAuk.variable.awk.getX()+(com.mygdx.sak.SaveTheAuk.variable.div_abscisse/2)<com.mygdx.sak.SaveTheAuk.variable.width/2)
				com.mygdx.sak.SaveTheAuk.variable.awk.auto(com.mygdx.sak.SaveTheAuk.variable.vitesse/2);
			if(com.mygdx.sak.SaveTheAuk.variable.awk.getX()+(com.mygdx.sak.SaveTheAuk.variable.div_abscisse/2)>com.mygdx.sak.SaveTheAuk.variable.width/2)
				com.mygdx.sak.SaveTheAuk.variable.awk.auto(-com.mygdx.sak.SaveTheAuk.variable.vitesse/2 );
		}
		if(com.mygdx.sak.SaveTheAuk.variable.stage.getCamera().position.y-y<=com.mygdx.sak.SaveTheAuk.variable.div_ordonnee*68)
			actualiser();
		if(com.mygdx.sak.SaveTheAuk.variable.stage.getCamera().position.y-y>=com.mygdx.sak.SaveTheAuk.variable.div_ordonnee*68)
		{
			com.mygdx.sak.SaveTheAuk.variable.menu.draw();
			if(score_<com.mygdx.sak.SaveTheAuk.variable.metre+1)
				com.mygdx.sak.SaveTheAuk.variable.menu.score.setString(String.valueOf(score_++)+" M");
			if(score_==com.mygdx.sak.SaveTheAuk.variable.metre)
				{
					com.mygdx.sak.SaveTheAuk.variable.menu.addActor(com.mygdx.sak.SaveTheAuk.variable.menu.congratu);
					Gdx.input.setInputProcessor(com.mygdx.sak.SaveTheAuk.variable.menu);
					com.mygdx.sak.SaveTheAuk.variable.menu.fb.setVisible(true);
					com.mygdx.sak.SaveTheAuk.variable.menu.leaderboard.setVisible(true);
					com.mygdx.sak.SaveTheAuk.variable.menu.tw.setVisible(true);
				}
			
			if(score_==com.mygdx.sak.SaveTheAuk.variable.metre+1)
				com.mygdx.sak.SaveTheAuk.variable.menu.score.setString(String.valueOf(com.mygdx.sak.SaveTheAuk.variable.metre+1)+" M");	
		}
	}

	@Override
	public void hide() {
		com.mygdx.sak.SaveTheAuk.variable.enregistrer_score(com.mygdx.sak.SaveTheAuk.variable.metre+1);
	}

	public void actualiser()
	{
		com.mygdx.sak.SaveTheAuk.variable.stage.getCamera().translate(0, com.mygdx.sak.SaveTheAuk.variable.vitesse, 0);
		com.mygdx.sak.SaveTheAuk.variable.awk.setY(com.mygdx.sak.SaveTheAuk.variable.awk.getY()+com.mygdx.sak.SaveTheAuk.variable.vitesse);
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
		if(tabar!=null)
		{
			for(int q=0;q<tabar.length;q++)
			{
				if(tabar[q]!=null)
					tabar[q].dispose();
			}
			for(int q=0;q<tabav.length;q++)
			{
				if(tabav[q]!=null)
					tabav[q].dispose();
			}
			for(int q=0;q<tabara.length;q++)
			{
				if(tabara[q]!=null)
					tabara[q].dispose();
			}
		}

	}

}
