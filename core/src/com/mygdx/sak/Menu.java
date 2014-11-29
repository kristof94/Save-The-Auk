package com.mygdx.sak;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;


public class Menu extends Stage{

	public Bouton tw,fb,start,carre,leaderboard;
	public Text foll,titre,score,best_score,congratu,ready,start2;
	float height,width;
	private Pixmap rectanglePixmap;
	public boolean other_touch=false;
	InputListener listener;
	public Actor awk;
	public float bss;

	public Menu(){
		height = com.mygdx.sak.SaveTheAuk.variable.height;
		width = com.mygdx.sak.SaveTheAuk.variable.width;		
		//pour ecrire
		awk = new Actor();
		awk.setX(com.mygdx.sak.SaveTheAuk.variable.awk.getX());
		awk.setWidth(com.mygdx.sak.SaveTheAuk.variable.awk.getWidth());
		awk.setY(com.mygdx.sak.SaveTheAuk.variable.ligne_pinguoin);
		awk.setHeight(com.mygdx.sak.SaveTheAuk.variable.awk.getHeight()*2);
		//pour dessiner	
		rectanglePixmap =new Pixmap(32,32,Format.RGB888);
		rectanglePixmap.setColor(Color.BLUE);
		rectanglePixmap.fillRectangle(0, 0,(int)(width/20)*10,(int)(height/20)*3);
		start = new Bouton(new Texture(rectanglePixmap,Format.RGB888,false),(com.mygdx.sak.SaveTheAuk.variable.width/20)*4.5f,(height/20)*12.5f,(width/20)*11,(height/20)*2.5f,"START");
		
		rectanglePixmap =new Pixmap(32,32,Format.RGB888);
		rectanglePixmap.setColor(Color.WHITE);
		rectanglePixmap.fillRectangle(0, 0,(int)(width/20)*3,(int)(height/20)*6);
		carre = new Bouton(new Texture(rectanglePixmap,Format.RGB888,false),(width/20)*3.2f,6f*(height/20),(width)-(2*(width/20)*3.2f),(start.getY()+start.getHeight()/2)-6f*(height/20));

		//pour bouton
		tw = new Bouton(com.mygdx.sak.SaveTheAuk.variable.icone.findRegion("tw2"),(com.mygdx.sak.SaveTheAuk.variable.width/20)*4.5f,(com.mygdx.sak.SaveTheAuk.variable.width/20)*4.5f-((width/20)*3.2f)+carre.getY(),(com.mygdx.sak.SaveTheAuk.variable.width/20)*3f,(com.mygdx.sak.SaveTheAuk.variable.width/20)*3f);
		fb = new Bouton(com.mygdx.sak.SaveTheAuk.variable.icone.findRegion("fb2"),tw.getX()+tw.getWidth()+com.mygdx.sak.SaveTheAuk.variable.div_abscisse*5.0f,tw.getY(),tw.getWidth(),tw.getWidth());		
		leaderboard = new Bouton(com.mygdx.sak.SaveTheAuk.variable.icone.findRegion("leader"),tw.getX()+tw.getWidth()+com.mygdx.sak.SaveTheAuk.variable.div_abscisse*1,(com.mygdx.sak.SaveTheAuk.variable.height/20)*5,(com.mygdx.sak.SaveTheAuk.variable.width/20)*3f,(com.mygdx.sak.SaveTheAuk.variable.width/20)*3f);

		
		foll = new Text((width/2)-com.mygdx.sak.SaveTheAuk.variable.div_abscisse*1.1f,fb.getY()+fb.getHeight()+com.mygdx.sak.SaveTheAuk.variable.div_ordonnee,1f,"Share",Color.BLUE);
		titre = new Text((width/2)-com.mygdx.sak.SaveTheAuk.variable.div_abscisse*6.25f,start.getY()+start.getHeight()+com.mygdx.sak.SaveTheAuk.variable.div_ordonnee*3,2.5f,"Save The Auk",Color.WHITE); 
		best_score = new Text(tw.getX(),start.getY()-com.mygdx.sak.SaveTheAuk.variable.div_ordonnee*1.0f,1.5f, "Save The Auk",Color.BLUE); 
		
		ready = new Text((com.mygdx.sak.SaveTheAuk.variable.width/20)*5,(height/20)*12.5f,2.5f,"Get Ready",Color.WHITE); 
		start2 = new Text((com.mygdx.sak.SaveTheAuk.variable.width/20)*7,(height/20)*10.5f,1.5f,"Tap to start",Color.WHITE); 

		score = new Text(( width/20)*8.5f,com.mygdx.sak.SaveTheAuk.variable.div_ordonnee*11.0f ,3f, "",Color.BLUE); 

		congratu = new Text(com.mygdx.sak.SaveTheAuk.variable.div_abscisse*2.2f, score.getY()+(com.mygdx.sak.SaveTheAuk.variable.height/20)*3f, 2, "CONGRATULATION !", Color.WHITE);
		bss = start.getY()-com.mygdx.sak.SaveTheAuk.variable.div_ordonnee*1.5f;
		leaderboard.addListener(new InputListener(){

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				leaderboard.setBounds(leaderboard.getX(), leaderboard.getY()-com.mygdx.sak.SaveTheAuk.variable.vitesse,leaderboard.getWidth(),leaderboard.getHeight());
				Timer.schedule(new Task(){

					int compteur = 0;
					@Override
					public void run() {
						while(compteur<2)					
							compteur++;
						leaderboard.setBounds(leaderboard.getX(), leaderboard.getY()+com.mygdx.sak.SaveTheAuk.variable.vitesse,leaderboard.getWidth(),leaderboard.getHeight());
						com.mygdx.sak.SaveTheAuk.variable.getLeader().submitScore(com.mygdx.sak.SaveTheAuk.variable.BEST_SCORE);
						this.cancel();
					}
				}, 0.2f);
				return super.touchDown(event, x, y, pointer, button);
			}
			
		});
		
		fb.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				fb.setBounds(fb.getX(), fb.getY()-com.mygdx.sak.SaveTheAuk.variable.vitesse,fb.getWidth(),fb.getHeight());
				Timer.schedule(new Task(){

					int compteur = 0;
					@Override
					public void run() {
						while(compteur<2)					
							compteur++;
						fb.setBounds(fb.getX(), fb.getY()+com.mygdx.sak.SaveTheAuk.variable.vitesse,fb.getWidth(),fb.getHeight());
						com.mygdx.sak.SaveTheAuk.variable.getLeader().submitScore(com.mygdx.sak.SaveTheAuk.variable.mess+"My high score is ",com.mygdx.sak.SaveTheAuk.variable.BEST_SCORE);
						this.cancel();
					}
				}, 0.2f);
				return super.touchDown(event, x, y, pointer, button);
			}
			
		});

		start.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				start.setBounds(start.getX(), start.getY()-com.mygdx.sak.SaveTheAuk.variable.vitesse,start.getWidth(),start.getHeight());
				Timer.schedule(new Task(){

					int compteur = 0;
					@Override
					public void run() {
						while(compteur<2)											
							compteur++;					
						start.setBounds(start.getX(), start.getY()+com.mygdx.sak.SaveTheAuk.variable.vitesse,start.getWidth(),start.getHeight());
						com.mygdx.sak.SaveTheAuk.variable.decor.gen_map_t();
						start.setpressed(true);
						this.cancel();
					}
				}, 0.2f);	
				return super.touchDown(event, x, y, pointer, button);
			}
			
		});
				
		tw.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				tw.setBounds(tw.getX(), tw.getY()-com.mygdx.sak.SaveTheAuk.variable.vitesse,tw.getWidth(),tw.getHeight());
				Timer.schedule(new Task(){

					int compteur = 0;
					@Override
					public void run() {
						while(compteur<2)					
							compteur++;
						tw.setBounds(tw.getX(), tw.getY()+com.mygdx.sak.SaveTheAuk.variable.vitesse,tw.getWidth(),tw.getHeight());
						Gdx.net.openURI("http://twitter.com/share?text="+com.mygdx.sak.SaveTheAuk.variable.mess+"My high score is "+com.mygdx.sak.SaveTheAuk.variable.BEST_SCORE+" M."+" Who can do better than me ? "+"@SaveTheAuK &url=https://play.google.com/store/apps/details?id=com.mygdx.game.androidsak");
						this.cancel();
					}
				}, 0.2f);
				return super.touchDown(event, x, y, pointer, button);
			}
			
		});
		 
		awk.addListener( listener = new InputListener(){

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				com.mygdx.sak.SaveTheAuk.variable.awk.change_color();
				return super.touchDown(event, x, y, pointer, button);
			}
			
		});
		
		this.addActor(awk);
		this.addActor(carre);				
		this.addActor(fb);
		this.addActor(tw);	
		this.addActor(start);
		//this.addActor(foll);
		this.addActor(start2);
		this.addActor(titre);
		this.addActor(score);
		this.addActor(best_score);
		this.addActor(ready);
		this.addActor(leaderboard);

		ready.setVisible(false);
		start2.setVisible(false);
	}

	public void decalbs()
	{
		if(com.mygdx.sak.SaveTheAuk.variable.BEST_SCORE<10)
			best_score.setX(tw.getX()+com.mygdx.sak.SaveTheAuk.variable.div_abscisse*1f);
		if(com.mygdx.sak.SaveTheAuk.variable.BEST_SCORE>9)
			best_score.setX(tw.getX()+com.mygdx.sak.SaveTheAuk.variable.div_abscisse*0.7f);
		if(com.mygdx.sak.SaveTheAuk.variable.BEST_SCORE>99)
			best_score.setX(tw.getX()+com.mygdx.sak.SaveTheAuk.variable.div_abscisse*0.0f);
		if(com.mygdx.sak.SaveTheAuk.variable.BEST_SCORE>999)
			best_score.setX(tw.getX());
			
	}
	
	public void decal(int metre){
		if(metre <= 1 )
			score.setX( (width/20)*8f);
		if(metre==10)
			score.setX( (width/20)*7.5f);
		if(metre==100)
			score.setX( (width/20)*7f);
		if(metre>998)
			score.setX( (width/20)*6f);
		
	}
	
	public void move(int u){
		carre.setY(carre.getY()+u*com.mygdx.sak.SaveTheAuk.variable.vitesse);
		start.setY(start.getY()+u*com.mygdx.sak.SaveTheAuk.variable.vitesse);
		fb.setY(fb.getY()+u*com.mygdx.sak.SaveTheAuk.variable.vitesse);
		tw.setY(tw.getY()+u*com.mygdx.sak.SaveTheAuk.variable.vitesse);
		foll.setY(fb.getY()+fb.getHeight()+com.mygdx.sak.SaveTheAuk.variable.div_ordonnee+u*com.mygdx.sak.SaveTheAuk.variable.vitesse);
		titre.setY(titre.getY()+u*com.mygdx.sak.SaveTheAuk.variable.vitesse);
		score.setY(score.getY()+u*com.mygdx.sak.SaveTheAuk.variable.vitesse);
		best_score.setY(best_score.getY()+u*com.mygdx.sak.SaveTheAuk.variable.vitesse);
		leaderboard.setY(leaderboard.getY()+u*com.mygdx.sak.SaveTheAuk.variable.vitesse);

	}

	public void move2(int u)
	{
		ready.setY(ready.getY()+u*com.mygdx.sak.SaveTheAuk.variable.vitesse);
		start2.setY(start2.getY()+u*com.mygdx.sak.SaveTheAuk.variable.vitesse);
	}
	
	public void reset2()
	{
		ready.setY((height/20)*12.5f);
		start2.setY((height/20)*10.5f);
	}
	
	public void reset(){
			
		carre.setY(6f*(height/20));
		tw.setY((com.mygdx.sak.SaveTheAuk.variable.width/20)*4.5f-((width/20)*3.2f)+carre.getY());
		fb.setY(tw.getY());	
		start.setY((height/20)*12.5f);
		foll.setY(fb.getY()+fb.getHeight()+com.mygdx.sak.SaveTheAuk.variable.div_ordonnee);
		titre.setY(start.getY()+start.getHeight()+com.mygdx.sak.SaveTheAuk.variable.div_ordonnee*3);		
		best_score.setY(		bss = start.getY()-com.mygdx.sak.SaveTheAuk.variable.div_ordonnee*0.5f);
		score.setY(bss-com.mygdx.sak.SaveTheAuk.variable.div_ordonnee*1.5f);
		leaderboard.setY(tw.getY());
	}

	public void dispose()
	{
		tw.dispose();
		fb.dispose();
		start.dispose();
		titre.dispose();
		foll.dispose();
		rectanglePixmap.dispose();
		score.dispose();
		best_score.dispose();
		congratu.dispose();
		ready.dispose();
		start2.dispose();
		leaderboard.dispose();
	}

	@Override
	public void draw() {		
		super.draw();
	}

}
