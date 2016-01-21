package isom3320;

import processing.core.PConstants;

public class GameObjectEnemyShooter extends GameObjectEnemy implements Shooter{
	
	protected long cantShootFor, reloadTimer;
	protected GameObjectGun gun;
	private long gunCantShootFor;
	
	public GameObjectEnemyShooter(Vector v, double angle) {
		super(v, angle);
		gun = new GameObjectGun("ak47", this);
	}
	
	@Override
	public void init(){
		cantShootFor(1000);
	}
	
	@Override
	public void update(){
		super.update();
		this.lookAt(player);
		
		if(gun.shootPreview()){
			gun.shoot();
			cantShootFor(3000);
			
			Vector spawnPoint = getPos().add(Vector.createFromAngle(getAngle(), 5));
			Vector velocity = Vector.createFromAngle(angle, gun.getSpeed());
			float damage = gun.getDamage() * 0.5F;
			state.spawn(new GameObjectBullet(this, spawnPoint, velocity, 0, damage));
			
		} else {
			if(reloadTimer <= System.currentTimeMillis()){
				gun.reload();
				reloadTimer = System.currentTimeMillis() + gun.getReloadTime() + 1000;
			}
		}
		
		gun.update();
	}
	
	@Override
	public void render(double framestep){
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

			gun.render(framestep);
			
			R.noStroke();
			R.fill(200, 200, 0);
			R.ellipse(0, 0, size, size);
			
			
		}
		R.popMatrix();
	}
	
	@Override
	public void cantShootFor(long time) {
		cantShootFor = time + System.currentTimeMillis();
	}
	
	public void gunCantShootFor(long time){
		gunCantShootFor = time + System.currentTimeMillis();
	}
	
	@Override
	public Shooter dontCheck(){
		return this;
	}

}
