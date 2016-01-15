package hkust;

import processing.core.PConstants;

public class GameObjectBullet extends GameObject {
	
	private Vector velocity;
	private long timer, life;
	
	public GameObjectBullet(Vector pos, Vector vel) {
		setPos(pos);
		velocity = vel;
		life = 1000;
		timer = System.currentTimeMillis() + life;
		setAngle(vel.getAngle());
	}

	@Override
	public void update(){
		super.update();
		pos = pos.add(velocity).add(Vector.random(0.5D));
		
		if(System.currentTimeMillis() >= timer)	setDead();
	}

	@Override
	public void render(double framestep) {
		R.noStroke();
		R.fill(255, 255*(timer - System.currentTimeMillis())/life, 0);
		R.pushMatrix();
		{
			R.translate(getXF(), getYF());
			R.rotate((float)angle);
			R.rectMode(PConstants.CENTER);
			R.rect(0, 0, 10, 2);
		}
		R.popMatrix();
	}
	
	public Vector getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}

}
