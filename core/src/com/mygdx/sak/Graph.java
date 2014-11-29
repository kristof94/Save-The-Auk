package com.mygdx.sak;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.FPSLogger;
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
	public List<Animation> anim,anim_dead;
	public int tt;
	public int tt2;
	private Leaderboard leader;
	public int curseur=0;
	public TextureAtlas iceberg,awklive,awkdead,icone,pause;
	private TextureRegion iceberg0,iceberg1,PAUSE,RESUME;
	public Jeu jeu;
	public IActivityRequestHandler handler;
	public boolean life=true;
	public Over over;
	public int metre;
	public FPSLogger lll;
	public float color=0.4f;
	public SaveTheAuk s;
	public Sound hurt;
	public String mess;
	public Ready ready;
	public Background bg;
	public FreeTypeFontGenerator generator;
	 
	
	public Graph(SaveTheAuk  d,Leaderboard l){
		
		height = Gdx.graphics.getHeight();
		width = Gdx.graphics.getWidth();
		
		
		vitesse = (int)height/80;
		ligne_pinguoin = vitesse*4;
		div_ordonnee=vitesse*4;//height/20;
		div_abscisse=width/20;
		sizetableau = 500;
		leader = l;
		lll = new FPSLogger();
		generator = new FreeTypeFontGenerator(Gdx.files.internal("ice.ttf"));
		parameter = new FreeTypeFontParameter();
		iceberg = new TextureAtlas(Gdx.files.internal("ice/ice.atlas"));
		awklive = new TextureAtlas(Gdx.files.internal("anim/live/live.atlas"));
		awkdead = new TextureAtlas(Gdx.files.internal("anim/dead/mort.atlas"));
		icone = new TextureAtlas(Gdx.files.internal("icone/icone.atlas"));
		pause = new TextureAtlas(Gdx.files.internal("icone/pause/resume.atlas"));

		hurt = Gdx.audio.newSound(Gdx.files.internal("sound/hurt.wav"));

		iceberg0 = iceberg.findRegion("iceberg0");		
		iceberg1 = iceberg.findRegion("iceberg1");

		PAUSE = pause.findRegion("pause");
		RESUME = pause.findRegion("pause2");
		mess = "I have not been able to save the Auk. ";
		anim = new ArrayList<Animation>();
		anim_dead = new ArrayList<Animation>();
		TextureRegion live[] = new TextureRegion[6];
		TextureRegion dead[] = new TextureRegion[6];

		for(int curseur=0;curseur<6;curseur++)
		{		
			live[curseur]=awklive.findRegion("anim"+String.valueOf(curseur));
			dead[curseur]=awkdead.findRegion("dead"+String.valueOf(curseur));
			anim_dead.add(this.gen_animation(awkdead.findRegion("dead"+String.valueOf(curseur)))                                                                 ) ;
			anim.add(this.gen_animation(awklive.findRegion("anim"+String.valueOf(curseur)))                                                                 ) ;
		}
		tt = awklive.findRegion("anim0").getRegionWidth()/3;
		tt2 = awklive.findRegion("anim0").getRegionHeight(); 
		s = d;

	}
	
	public Animation gen_animation(TextureRegion t)
	{		
		TextureRegion[] anima = new TextureRegion[3];
		TextureRegion[][] tmp2 = t.split(t.getRegionWidth()/3, t.getRegionHeight());
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
		ready = new Ready();

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
		bg.dispose();
		hurt.dispose();
		awklive.dispose();
		awkdead.dispose();
		generator.dispose();
		icone.dispose();
		spriteBatch.dispose();
		stage.dispose();
		iceberg.dispose();
		pause.dispose();
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

	public void setColor(float color) {
		this.color = color;
	}
	public TextureRegion get_pause(){
		return PAUSE;
	}
	public TextureRegion get_resume(){
		return RESUME;
	}
}