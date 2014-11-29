package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Graph {

	public float width;
	public float height;
	public int vitesse;
	public float ligne_pinguoin;
	public float div_ordonnee;
	public float div_abscisse;
	public int sizetableau;
	public Menu menu;
	public FreeTypeFontGenerator generator;
	public FreeTypeFontParameter parameter;
	public Stage stage;
	public Preferences prefs;
	public int BEST_SCORE;
	public final static int a1=5423;
	public final static int b2=-5;
	public final static int c3=3612;
	public SpriteBatch spriteBatch;
	public Decor decor;
	public Awk awk;	
	private List<Texture> awkdead,awklive;
	public List<Animation> anim;
	public int tt;
	public int tt2;
	private Leaderboard leader;
	public int curseur=0;
	public TextureAtlas iceberg;
	private TextureRegion iceberg0,iceberg1;
	public Jeu jeu;
	public IActivityRequestHandler handler;
	public boolean life=true;
	public Over over;
	public int metre;
	
	public Graph(Leaderboard l){
		
		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();
		vitesse = (int)height/80;
		ligne_pinguoin = vitesse*4;
		div_ordonnee=vitesse*4;//height/20;
		div_abscisse=width/20;
		sizetableau = 500;
		leader = l;
		generator = new FreeTypeFontGenerator(Gdx.files.internal("ice.ttf"));
		parameter = new FreeTypeFontParameter();
		iceberg = new TextureAtlas(Gdx.files.internal("ice/ice.atlas"));
		iceberg0 = iceberg.findRegion("iceberg0");
		iceberg1 = iceberg.findRegion("iceberg1");
		awklive = new ArrayList<Texture>();		
		awkdead = new ArrayList<Texture>();
		anim = new ArrayList<Animation>();
		
		for(int curseur=0;curseur<6;curseur++)
		{
			awklive.add( new Texture(Gdx.files.internal("anim"+String.valueOf(curseur)+".png")));
			awkdead.add( new Texture(Gdx.files.internal("dead"+String.valueOf(curseur)+".png")));
			anim.add(this.gen_animation(awklive.get(curseur)));
		}
		tt = awklive.get(0).getWidth()/3;
		tt2 = awklive.get(0).getHeight(); 
	}
	
	public Texture get_colorl(int c){
		return awklive.get(c);
		
	}

	public Texture get_colord(int c){
		return awkdead.get(c);
		
	}
	
	public Animation gen_animation(Texture t)
	{		
		TextureRegion[] anima = new TextureRegion[3];
		TextureRegion[][] tmp2 = TextureRegion.split(t,t.getWidth()/3,t.getHeight());
		//anim.get(index) = new TextureRegion[3];
		int index = 0;
		for(int j = 0; j <3; j++) 
		{
			anima[index++] = tmp2[0][j];
		}
		return new Animation(0.26f/3, anima);
	}
	
	public void gen_obj(){
		
		stage = new Stage(new StretchViewport(width,height));
		prefs = Gdx.app.getPreferences("Itsbadtocheat");
		BEST_SCORE = (( prefs.getInteger("int") / b2) - c3) / a1;
		if (BEST_SCORE>1000)
			BEST_SCORE = 0;
		spriteBatch= new SpriteBatch();
		awk = new Awk();
		decor = new Decor();
		
	}
	
	public void enregistrer_score(int metre){
		if(metre >BEST_SCORE)
		{
			BEST_SCORE = metre;
			prefs.putInteger("int", ((metre*a1)+c3)*(b2));
			prefs.flush();
		}
	}
	
	public void dispose(){
		
		for(int i=0;i<awklive.size();i++)
		{
			if(awklive.get(i)!=null)
			{
				awklive.get(i).dispose();
				awkdead.get(i).dispose();
			}
		}
		generator.dispose();
		spriteBatch.dispose();
		stage.dispose();
		iceberg.dispose();
	}

	public Leaderboard getLeader() {
		return leader;
	}

	public TextureRegion getIceberg0() {
		return iceberg0;
	}

	public TextureRegion getIceberg1() {
		return iceberg1;
	}
	
}