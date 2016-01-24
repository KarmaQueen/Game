package isom3320;

import processing.core.PConstants;

public class GameObjectEnemy extends GameObject {
	
	public static GameObjectPlayer player;
	
	private long time;
	private double speedA, speedB;
	
	private long cantHitPlayerUntil;
	
	public GameObjectEnemy(Vector v, double angle) {
		super(v, angle);
		cantHitPlayerUntil = 0;
	}
	
	@Override
	public void init(){
		time = System.currentTimeMillis();
		speedA = Math.random() * 0.4;
		speedB = Math.random() * 1.75;
	}
	
	@Override
	public void update(){
		super.update();
		motion = motion.add(Math.random() - 0.5,Math.random() - 0.5).scalar(speedA);
		lookAt(player);
		motion = motion.add(Vector.createFromAngle(angle, speedB));
		
		if(collidesWith(player) && cantHitPlayerUntil <= System.currentTimeMillis()){
			player.damage(20);
			cantHitPlayerUntil = System.currentTimeMillis() + 500;
		}
	}
	
	@Override
	public void kill(){
		super.kill();
		StateGame.score += 1;
	}
	
	@Override
	public void render(double framestep) {
		R.pushMatrix();
		{
			Vector v = this.getPartialPos(framestep);
			R.translate(v.getXF(), v.getYF());
			
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
