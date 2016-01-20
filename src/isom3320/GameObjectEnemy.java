package isom3320;

import processing.core.PConstants;

public class GameObjectEnemy extends GameObject {
	
	public static GameObjectPlayer player;
	
	private long time;
	
	public GameObjectEnemy(Vector v, double angle) {
		super(v, angle);
	}
	
	@Override
	public void init(){
		time = System.currentTimeMillis();
	}
	
	@Override
	public void update(){
		super.update();
		if(time <= System.currentTimeMillis()){
			//damage(10);
			time = System.currentTimeMillis() + 1000;
		}
		motion = motion.add(Math.random() * 10-5,Math.random() * 10-5).scalar(0.75);
	}
	
	@Override
	public void render(double framestep) {
		R.pushMatrix();
		{
			R.translate(getXF(), getYF());
			
			R.rectMode(PConstants.CORNER);
			R.fill(255, 0, 0);
			R.rect(-20, -30, 40, 4);
			R.fill(0, 255, 0);
			R.rect(-20, -30, 40*getHealthRatio(), 4);
			
			R.rotate((float) getAngle());
			R.rectMode(PConstants.CENTER);

			R.noStroke();
			R.fill(200, 0, 0);
			R.ellipse(0, 0, size, size);
			
			
		}
		R.popMatrix();
	}

}
