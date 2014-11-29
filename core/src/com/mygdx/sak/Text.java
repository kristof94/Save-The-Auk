package com.mygdx.sak;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Text extends Actor{
	private BitmapFont texte;
	private String string;
	private boolean presse;
	private int size;
	
	public Text(float x,float y,float size,String s,Color c){
		
		com.mygdx.sak.SaveTheAuk.variable.parameter.size = (int) (size =(int)(com.mygdx.sak.SaveTheAuk.variable.div_abscisse*size)); 
		texte = com.mygdx.sak.SaveTheAuk.variable.generator.generateFont(com.mygdx.sak.SaveTheAuk.variable.parameter);		
		setX(x);
		setY(y);
		string = s;
		texte.setColor(c);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		texte.draw(batch, string, getX(),getY());
		super.draw(batch, parentAlpha);
	}

	public void setColor(Color c){
		texte.setColor(c);
	}

	
	
	public void dispose()
	{
		texte.dispose();
	}

	public void setString(String string) {
		this.string = string;
	}
	
	public boolean ispressed(){
		return presse;
	}
	
	public void setpressed(boolean p){
		presse=p;
	}
	public int get_size(){
		return size;
	}
	
}
