package com.mygdx.sak;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Background extends Actor{


	public float largeur;
	public float largeur2;
	private int tab[];
	private float val[];
	private TextureRegion snow[];
	private int vv2=0;
	private float div_ordonnee,div_abscisse,xx;
	private int vitesse;
	private float height,width;
	private float ord8;
	//private TextureRegion neige[][];

	public Background(float w,float h){
//
		//neige = TextureRegion.split(snow,snow.getWidth()/2, snow.getHeight()/2);
		snow = new TextureRegion[2];
		snow[0]= com.mygdx.sak.SaveTheAuk.variable.getIceberg0();
		snow[1]= com.mygdx.sak.SaveTheAuk.variable.getIceberg1();
		tab = new int[12];
		tab[0]=0;
		for(int i=1;i<tab.length;i++)
		{
			tab[i]=tab[i-1]+2;
			//System.out.println(tab[i]);
		}

		height = h;
		width = w;
		vitesse = (int) (height/80);				
		div_abscisse = width / 20;
		div_ordonnee=vitesse*4;//height/20;
		largeur = div_abscisse*3;//vitesse*2.5f;
		largeur2 = width-largeur;
		ord8 = 2*div_ordonnee;
		xx = 0;

		val = new float[12];

		int mul = 2;
		for(int i=0;i<val.length;i++)
		{
			val[i]=div_ordonnee*mul;
			//System.out.println(mul);
			//System.out.println(val[i]);
			mul+=2;
		}
	}	

	public void set_vv2(int vv)
	{
		vv2=vv;
	}

	public void setdd(){

		vv2=0;
		ii=0;
		tab[0]=0;
		for(int i=1;i<tab.length;i++)
		{
			tab[i]=tab[i-1]+2;
		}


		int mul = 2;
		for(int i=0;i<val.length;i++)
		{
			val[i]=div_ordonnee*mul;
			mul+=2;
		}

	}
	int ii=0;
	public void calculer_bord()
	{

			if(vv2==val[ii] && ii<val.length-1)
			{
				tab[ii]+=24;
				ii++;
			}					


			if(vv2==val[val.length-1])
			{
				tab[tab.length-1]+=24;
				vv2=0;
				ii=0;
			}

			vv2+=vitesse;
		

	}

	void set_ecart(float x){
		xx-=x;
		largeur2+=x;
	}

	boolean v=false;
	@Override
	public void draw(Batch batch, float parentAlpha) {

		for(int i=0;i<tab.length;i+=1)
		{
			batch.draw(snow[v? 1 : 0],xx, tab[i]*(div_ordonnee),largeur,ord8);
			batch.draw(snow[v? 1 : 0],largeur2,  tab[i]*(div_ordonnee),largeur,ord8);	
			v=!v;
		}			
		super.draw(batch, parentAlpha);
	}

	public void dispose(){


	}
}

