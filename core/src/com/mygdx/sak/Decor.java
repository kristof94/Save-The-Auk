package com.mygdx.sak;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Decor extends Actor{

	private byte map[][],map9[][];

	private int z =1;
	public float depart = 0;
	private int nb_obstacle ;
	private int niveau = 1;
	private int sizetableau = 500;
	private float uu=0;
	private float uu2=0;
	private int last_line=0;
	private int first_line = 0;
	@SuppressWarnings("unused")
	private int co=0;
	public float vitesse_x;
	int tt=0,tt2=0;
	End end;
	boolean test=false;

	public Decor(){

		vitesse_x = com.mygdx.sak.SaveTheAuk.variable.width/80;
		end = new End();
		map = new byte[sizetableau][20];
		this.niveau_bonus9();
	}

	public void gen_map_t(){
		gen_map();
	}

	public void gen_map(){

		for(int i=0;i<sizetableau;i++)
		{
			for(int j=0;j<map[0].length;j++)
			{
				map[i][j]=0;					
			}
		}

		if((this.niveau%2) !=0 && this.niveau != 20 )
		{									
			nb_obstacle = 82;						
			new Runnable() {
				public void run() {
					niveau_facile2();
				}
			}.run();
		}

		if(this.niveau==2 )
		{					
			this.niveau_bonus();
		}

		if(this.niveau==4 )
		{
			this.niveau_bonus2();

		}

		if(this.niveau==6 )
		{
			this.niveau_bonus3();

		}


		if(this.niveau==8 )
		{
			this.niveau_bonus4();

		}

		if(this.niveau==10 )
		{
			this.niveau_middle();

		}

		if(this.niveau==12 )
		{
			this.niveau_bonus6();

		}

		if(this.niveau==14 )
		{
			this.niveau_bonus7();

		}

		if(this.niveau==16 )
		{
			this.niveau_bonus8();

		}


		if(this.niveau==18 )
		{
			map = map9;

		}

		if(this.niveau==20 )
		{	
			nb_obstacle = 40;						

			this.niveau_end();
		}

		if(this.niveau==21){
			end();
		}

		boolean g;

		for(int i =0;i<this.sizetableau;i++)
		{
			for(int j =0;j<map[0].length;j++)
			{
				g = ( Math.random() >= 0.5 );
				//System.out.print(map[i][j]);
				if(map[i][j]==1 && !g)					
				{
					map[i][j]=2;
				}
				if(map[i][j]==1 && g)					
				{
					map[i][j]=1;
				}
			}
			//System.out.println("ddasas");
		}

	}

	private void end(){
		com.mygdx.sak.SaveTheAuk.variable.jeu.scor.setVisible(false);
		com.mygdx.sak.SaveTheAuk.variable.menu.score.setColor(Color.WHITE);
		com.mygdx.sak.SaveTheAuk.variable.menu.foll.setColor(Color.WHITE);
		com.mygdx.sak.SaveTheAuk.variable.menu.carre.remove();
		com.mygdx.sak.SaveTheAuk.variable.menu.best_score.remove();
		com.mygdx.sak.SaveTheAuk.variable.menu.reset();
		com.mygdx.sak.SaveTheAuk.variable.mess="I saved the Auk ";
		com.mygdx.sak.SaveTheAuk.variable.menu.carre.remove();
		com.mygdx.sak.SaveTheAuk.variable.menu.titre.remove();
		com.mygdx.sak.SaveTheAuk.variable.menu.start.remove();
		com.mygdx.sak.SaveTheAuk.variable.menu.score.setString("");


		com.mygdx.sak.SaveTheAuk.variable.BEST_SCORE=com.mygdx.sak.SaveTheAuk.variable.metre+1;
		com.mygdx.sak.SaveTheAuk.variable.menu.score.setX(com.mygdx.sak.SaveTheAuk.variable.width/2-com.mygdx.sak.SaveTheAuk.variable.div_abscisse*4);
		com.mygdx.sak.SaveTheAuk.variable.s.setScreen(end);
	}

	public void calculer_block()
	{
		if(uu==(com.mygdx.sak.SaveTheAuk.variable.div_ordonnee))
		{
			z++;
			uu=0;
		}

		if(uu2==(2*com.mygdx.sak.SaveTheAuk.variable.div_ordonnee))
		{
			first_line++;
			uu2=0;
		}

		uu+=com.mygdx.sak.SaveTheAuk.variable.vitesse;
		uu2+=com.mygdx.sak.SaveTheAuk.variable.vitesse;
		last_line=z+25;
		if(last_line>=this.sizetableau)
			last_line=this.sizetableau;

	}

	public byte[][] getMap() {
		return map;
	}

	public void setZ(int z) {
		co=0;
		uu=0;
		uu2 = 0;
		this.z = z;
		this.first_line = 0;

	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau() {
		this.niveau+=1;
	}

	public void calcul_collision(int x,int y,int l,int h)
	{
		for(int j=0; j<h;j++)
		{
			for(int i=0; i<l;i++)
			{				
				map[y+j][x+i]= 3;
			}
		}
	}

	public void calcul_collision(int x,int y,int l,int h,byte qq)
	{
		for(int j=0; j<h;j++)
		{
			for(int i=0; i<l;i++)
			{				
				map[y+j][x+i]= qq;
			}
		}
	}

	public void init_niveau(int m)
	{
		niveau = m;
	}

	private void niveau_facile2(){
		int x,y;
		Random r = new Random();		
		for(int g=0;g<60;g++)
		{
			y =  r.nextInt((sizetableau-6-25))+20;

			while( verification( x=(r.nextBoolean()?0:17) , y) )
			{
				y =  r.nextInt((sizetableau-6-20))+20;
			}
			this.calcul_collision(x, y, (19-x>2)?3:19-x, 2);
			map[y][x]=1;
		}

		
		for(int i =0;i<this.nb_obstacle;i++)
		{
			while( verification(x=  (int) ( Math.random() * (19)),y=20+r.nextInt((sizetableau-26))) )
			{								
			}
			this.calcul_collision(x, y, (19-x>2)?3:19-x, 2);
			map[y][x]=1;
		}
		
		/*for(int i=20;i<sizetableau-20;i+=ratio)
		{
			zizi = i+ratio;

			for(int j=0;j<nb_obstacle;j++)
			{
				x =  (int) ( Math.random() * (19));

				if(i==420)
					y = i + r.nextInt((sizetableau-6)-i);

				else
					y = i + r.nextInt(zizi-i);

				while( verification(x,y) )
				{
					y = i + r.nextInt((sizetableau-6)-i);
					x =  (int) ( Math.random() * (19));
				}
				this.calcul_collision(x, y, (19-x>2)?3:19-x, 2);
				map[y][x]=1;				
			}
		}*/		
	}

	private boolean verification(int x,int y){

		test=false;
		//int fin = (19-x>2)?3:(19-x);
		for(int j=-1;j<4;j++)
		{
			for(int i=(x>=4)?-4:0;i<Math.floor((1+(19-x)));i++)
			{
				if(map[y+j][x+i]!=0)
				{
					test = true;
					break;
				}
			}
		}
		return test;
	}

	private void niveau_end(){
		int x,y;
		Random r = new Random();		
		for(int g=0;g<60;g++)
		{
			y =  r.nextInt((sizetableau-150))+20;

			while( verification( x=(r.nextBoolean()?0:17) , y) )
			{
				y =  r.nextInt((sizetableau-100))+20;
			}
			this.calcul_collision(x, y, (19-x>2)?3:19-x, 2);
			map[y][x]=1;
		}

		
		for(int i =0;i<this.nb_obstacle;i++)
		{
			while( verification(x=  (int) ( Math.random() * (19)),y=20+r.nextInt((sizetableau-150))) )
			{								
			}
			this.calcul_collision(x, y, (19-x>2)?3:19-x, 2);
			map[y][x]=1;
		}

	}

	private void block_line(boolean v,int j)
	{
		if( v)
		{
			for(int i=0;i<map[0].length/2;i+=3)
			{
				this.calcul_collision(i, j,3, 2);
				map[j][i]=1;
			}
		}
		else
		{
			for(int i=map[0].length/2;i<map[0].length;i+=3)
			{
				if(i>=map[0].length-3)
					this.calcul_collision(i, j, (map[0].length)-i, 2);
				else
					this.calcul_collision(i, j,3, 2);
				map[j][i]=1;					
			}
		}
	}

	private void niveau_bonus()
	{
		boolean	v = ( Math.random() >= 0.5 );		
		for(int j=20;j<sizetableau-5;j+=9)
		{			
			if(j<sizetableau/4)
			{
				this.block_line(v, j);
			}
			if(j>8+sizetableau/4 && j<sizetableau/2)
			{
				this.block_line(!v, j);
			}

			if(j>8+sizetableau/2 && j < 3*(sizetableau/4) )
			{
				this.block_line(v, j);
			}
			if(j>3 * (sizetableau/4) )
			{
				this.block_line(!v, j);
			}
		}		
	}

	private void niveau_bonus2()
	{
		for(int j=19;j<sizetableau-10;j+=9)
		{			
			if(j%2==0)
			{
				for(int i=0;i<(map[0].length/2)-1;i+=3)
				{
					this.calcul_collision(i, j,3, 2);
					map[j][i]=1;					
				}
			}
			if(j%2==1)
			{
				for(int i=(map[0].length/2)-1;i<map[0].length;i+=3)
				{
					if(i>=map[0].length-3)
						this.calcul_collision(i, j, (map[0].length)-i, 2);
					else
						this.calcul_collision(i, j,3, 2);
					map[j][i]=1;					
				}
			}
		}

	}

	private void niveau_bonus3()
	{
		int up=0; 
		for(int j=20;j<sizetableau-10;j+=9)
		{
			for(int i=0;i<6;i+=3)
			{								
				this.calcul_collision(i, j+up,3, 2);
				map[j+up][i]=1;
				this.calcul_collision(i+14, j+up,3, 2);
				map[j+up][i+14]=1;
				up+=3;
			}
			up=0;
		}


	}

	private void niveau_bonus6(){

		int x=2;
		int d=1;


		for(int j=20;j<sizetableau-15;j+=5)
		{
			this.calcul_collision(x,j,3, 2);
			map[j][x]=1;

			this.calcul_collision(x+14,j,3, 2);			
			map[j][x+14]=1;

			if(x==3)
				d=-1;
			if(x==2)
				d=1;

			x+=d;

		}

		/*for(int j=20;j<sizetableau-15;j+=2)
		{
			if(c>=14)
				sens=!sens;

			if(c==14)
				sens=!sens;

			this.calcul_collision(c,j,3, 2);			
			map[j][c]=1;



			if(sens)
				c+=2;
			if(!sens)
				c-=2;
		}*/
	}

	private void niveau_middle(){
		for(int j=20;j<sizetableau-5;j+=4)
		{
			this.calcul_collision(0,j,3, 2);			
			map[j][0]=1;

			this.calcul_collision( map[0].length-3,j,3, 2);
			map[j][map[0].length-3]=1;

		}

	}

	private void niveau_bonus4()
	{
		for(int j=20;j<sizetableau/2;j+=9)
		{
			for(int i=0;i<map[0].length-2;i+=3)
			{								
				this.calcul_collision(i, j,3, 2);
				map[j][i]=1;

			}									
		}

		for(int j=20+(sizetableau/2);j<sizetableau-10;j+=9)
		{
			for(int i=map[0].length-3;i>0;i-=3)
			{								
				this.calcul_collision(i, j,3, 2);
				map[j][i]=1;

			}
		}
	}

	private void niveau_bonus7(){
		for(int j=20;j<sizetableau/2;j+=4)
		{

			this.calcul_collision(0, j,3, 2);
			map[j][0]=1;
			this.calcul_collision(15, j,3, 2);
			map[j][15]=1;

		}

		for(int j=30+(sizetableau/2);j<sizetableau-10;j+=4)
		{
			this.calcul_collision(0, j,3, 2);
			map[j][0]=1;
			this.calcul_collision(15, j,3, 2);
			map[j][15]=1;									
		}
	}

	private void niveau_bonus8(){

		this.calcul_collision(18, 20,2, 2);
		map[20][18]=1;
		for(int j=20;j<(sizetableau/2);j+=4)
		{

			this.calcul_collision(0, j,3, 2);
			map[j][0]=1;
			this.calcul_collision(15, j,3, 2);
			map[j][15]=1;

		}

		for(int j=30+(sizetableau/2);j<sizetableau-10;j+=4)
		{
			this.calcul_collision(0, j,3, 2);
			map[j][0]=1;
			this.calcul_collision(15, j,3, 2);
			map[j][15]=1;									
		}
	}

	private void niveau_bonus9(){

		map9 = new byte[sizetableau][20];
		int ku = 3;
		for(int ligne=0;ligne<this.sizetableau;ligne+=100)
		{
			for(int j=ligne+20;j<=ligne+40;j+=2)
			{
				this.calcul_collision(3,j,3, 2);			
				map9[j][3]=1;						
			}
			this.calcul_collision(0,ligne+42,3, 2);			
			map9[ligne+42][0]=1;

			for(int j=ligne+20;j<=ligne+40;j+=2)
			{
				this.calcul_collision(14,j,3, 2);			
				map9[j][14]=1;						
			}
			this.calcul_collision(17,ligne+42,3, 2);			
			map9[ligne+42][17]=1;
			//
			int i = 0;
			int w = 1;

			for(int j =ligne+55;j<=ligne+58;j+=2)
			{
				this.calcul_collision(i,j,3, 2);			
				map9[j][i]=1;
				this.calcul_collision(16-i,j,w, 2);
				if(w<=3)
					w++;
				map9[j][16-i]=1;
				i+=3;
			}

			this.calcul_collision(9,ligne+70,3, 2);			
			map9[ligne+70][9]=1;
			i=3;
			for(int j = ligne+72;j<=ligne+74;j+=2)
			{
				this.calcul_collision(9+i,j,3, 2);			
				map9[j][9+i]=1;
				this.calcul_collision(9-i,j,3, 2);			
				map9[j][9-i]=1;
				i+=3;

			}
			if(ligne >=400)
			{
				for(int qq = ligne+80;qq<=ligne+90;qq+=3)
				{
					for(int q = ku;q<=17;q+=4)
					{
						this.calcul_collision(q,qq,3, 2);			
						map9[qq][q]=1;
					}
				}		

			}
			else
			{	
				for(int qq = ligne+80;qq<=ligne+98;qq+=3)
				{
					for(int q = ku;q<=17;q+=4)
					{
						this.calcul_collision(q,qq,3, 2);			
						map9[qq][q]=1;
					}
				}		
			}
			if(ku==3)
				ku=2;
			else if(ku==2)
				ku=3;
		}
	}

	public int getSizetableau() {
		return sizetableau;
	}

	public void setMap(int x,int y) {
		map[y][x]=5;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {

		for(int i=first_line;i<last_line;i++)
		{			
			for(int j=0;j<map[0].length;j++)
			{				
				if(map[i][j]==1)			
					batch.draw(com.mygdx.sak.SaveTheAuk.variable.getIceberg0(), (com.mygdx.sak.SaveTheAuk.variable.div_abscisse*j) ,depart + (com.mygdx.sak.SaveTheAuk.variable.div_ordonnee*i) ,com.mygdx.sak.SaveTheAuk.variable.div_abscisse*3,com.mygdx.sak.SaveTheAuk.variable.div_ordonnee*2);
				if(map[i][j]==2)			
					batch.draw(com.mygdx.sak.SaveTheAuk.variable.getIceberg1(), (com.mygdx.sak.SaveTheAuk.variable.div_abscisse*j) ,depart + (com.mygdx.sak.SaveTheAuk.variable.div_ordonnee*i) ,com.mygdx.sak.SaveTheAuk.variable.div_abscisse*3,com.mygdx.sak.SaveTheAuk.variable.div_ordonnee*2);									
			}
		}
		super.draw(batch, parentAlpha);
	}

}

