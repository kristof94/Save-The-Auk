package com.mygdx.sak;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bouton extends Actor{

	private TextureRegion image;
	private BitmapFont texte;
	private String text;
	private boolean presse=false;

	
	Bouton(TextureRegion t,float x,float y,float l,float h)
	{
		setWidth(l);
		setHeight(h);
		image = t;
		texte=null;
		setX(x);
		setY(y);		
	}
	
	Bouton(TextureRegion t,float x,float y,float l,float h,String z)
	{
		setWidth(l);
		setHeight(h);
		image = t;
		setX(x);
		setY(y);
		text=z;
		texte = com.mygdx.sak.SaveTheAuk.variable.generator.generateFont(com.mygdx.sak.SaveTheAuk.variable.parameter);	}
	
	Bouton(Texture  t,float x,float y,float l,float h,String z)
	{
		setWidth(l);
		setHeight(h);
		image = new TextureRegion(t);
		setX(x);
		setY(y);
		text=z;
		texte = com.mygdx.sak.SaveTheAuk.variable.generator.generateFont(com.mygdx.sak.SaveTheAuk.variable.parameter);	}
	
	public Bouton(Texture t, float x, float y, float l, float h) {
		// TODO Auto-generated constructor stub
		setWidth(l);
		setHeight(h);
		image = new TextureRegion(t);
		setX(x);
		setY(y);
		
		texte = null;
	}

	public void dispose()
	{

		if(texte!=null)
			texte.dispose();
	}

	
	@Override
	public void draw(Batch batch, float parentAlpha) {			
		super.draw(batch, parentAlpha);
			batch.draw(image, getX(), getY(),getWidth(),getHeight());	
		if(texte!=null)
			texte.draw(batch, text, getX()+1.5f*com.mygdx.sak.SaveTheAuk.variable.div_abscisse, getY()+2*(getHeight()/3));
	}

	public boolean ispressed(){
		return presse;
	}
	
	public void setpressed(boolean p){
		presse=p;
	}

	public void setImage(TextureRegion image) {
		this.image = image;
	}

	

}
