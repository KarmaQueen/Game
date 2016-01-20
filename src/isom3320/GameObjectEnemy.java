package isom3320;

import processing.core.PConstants;

public class GameObjectEnemy extends GameObject {
	
	public static GameObjectPlayer player;

	public GameObjectEnemy(Vector v, double angle) {
		super(v, angle);
	}
	
	@Override
	public void init(){
		
	}
	
	@Override
	public void update(){
		
	}
	
	@Override
	public void render(double framestep) {
		R.pushMatrix();
		{
			R.translate(getXF(), getYF());
			R.rotate((float) getAngle());
			R.rectMode(PConstants.CENTER);

			R.noStroke();
			R.fill(200, 0, 0);
			R.ellipse(0, 0, size, size);
		}
		R.popMatrix();
	}

}
