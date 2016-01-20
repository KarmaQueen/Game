package isom3320;

import processing.core.PConstants;

public class GameObjectBullet extends GameObject {
	
	private Vector velocity;
	private long timer, life;
	private float damage;
	
	public GameObjectBullet(Vector pos, Vector vel, double randomness, float damage) {
		super();
		setPos(pos);
		velocity = vel;
		life = 1000;
		timer = System.currentTimeMillis() + life;
		setAngle(vel.getAngle() + (Math.random() * randomness) - randomness *0.5D);
		velocity = Vector.createFromAngle(getAngle(), vel.getLength());
		size = 1;
		this.damage = damage;
	}

	@Override
	public void update(){
		super.update();
		pos = pos.add(velocity).add(Vector.random(0.5D));
		
		if(System.currentTimeMillis() >= timer)	kill();
	}

	@Override
	public void render(double framestep) {
		R.noStroke();
		R.fill(255, 55 + 200*(timer - System.currentTimeMillis())/life, 0);
		R.pushMatrix();
		{
			Vector partialPos = getPartialPos(framestep);
			R.translate(partialPos.getXF(), partialPos.getYF());
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

	public float getBulletDamage() {
		return damage;
	}

}
