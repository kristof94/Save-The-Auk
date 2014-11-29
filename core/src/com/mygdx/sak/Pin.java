package com.mygdx.sak;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Pin extends Actor{

	private float temps=0;
	private TextureRegion image;
	private Animation animation;
	private int tt,tt2;
	boolean follow=false;
	
	Pin(int color){
		tt = com.mygdx.sak.SaveTheAuk.variable.tt;
		tt2 = com.mygdx.sak.SaveTheAuk.variable.tt2;

		animation = com.mygdx.sak.SaveTheAuk.variable.anim.get(color);
	}
	
	Pin(int color,float x,float y,float w,float h){
		
		this.setX(x);
		this.setY(y);
		this.setWidth(w);
		this.setHeight(h);
		
		tt = com.mygdx.sak.SaveTheAuk.variable.tt;
		tt2 = com.mygdx.sak.SaveTheAuk.variable.tt2;
		animation = com.mygdx.sak.SaveTheAuk.variable.anim.get(color);
	}
		
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		
		temps+=Gdx.graphics.getDeltaTime();
		image = animation.getKeyFrame(temps,true);
		image.setRegion(image,0,0,tt,tt2);
		batch.draw(image,getX(), getY(), getWidth(),getHeight());
		super.draw(batch, parentAlpha);
	}

}