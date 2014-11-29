package com.mygdx.sak;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Awk extends Actor{

	private float width,height;
	private Animation animation;
	private float temps;
	private TextureRegion image;
	private int tt;
	private int curseur=0;
	int cc=0;
	private int tt2;
	public Awk() {

		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();		
		setWidth(width/10);
		setHeight(height/10);
		super.setX(width/2-getWidth()/2);
		tt = com.mygdx.sak.SaveTheAuk.variable.tt;
		tt2 = com.mygdx.sak.SaveTheAuk.variable.tt2;
	}

	public void color_change(int cursor)
	{
		cc = curseur = cursor;
		animation = com.mygdx.sak.SaveTheAuk.variable.anim.get(cursor);
	}

	public void change_color()
	{
		
		curseur++;
		if(curseur==6)
			curseur=0;
		animation = com.mygdx.sak.SaveTheAuk.variable.anim.get(curseur);
		temps = 0.0f;
		cc=curseur;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		temps+=Gdx.graphics.getDeltaTime();
		image = animation.getKeyFrame(temps,true);
		image.setRegion(image,0,0,tt,tt2);
		batch.draw(image, getX(), getY(),getWidth(),getHeight());
		super.draw(batch, parentAlpha);
	}

	public void dispose() {
		
	}
	
	public void live() {
		animation =com.mygdx.sak.SaveTheAuk.variable.anim.get(curseur);
	}

	public void dead() {
		animation =com.mygdx.sak.SaveTheAuk.variable.anim_dead.get(curseur);
	}

	public int getCc() {
		return cc;
	}

	public int getCurseur() {
		return curseur;
	}

	public void auto(float x)
	{
		setX(getX()+x);
	}

	
}