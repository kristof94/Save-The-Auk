package com.mygdx.sak;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Item extends Stage{
	public int pas = 1;



	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(pas==3)
			;			
		if(pas==0)
			pas=2;
		else if(pas==1)
		{
			if(( Math.random() >= 0.5 ))
				pas=2;
			else
				pas=0;
		}
		else if(pas==2)
			pas=0;

		return super.touchDown(screenX, screenY, pointer, button);
	}

}
