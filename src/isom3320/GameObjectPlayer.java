package isom3320;

import processing.core.PConstants;

public class GameObjectPlayer extends GameObject implements Shooter{

	private boolean isShooting;
	private GameObjectGun gun;
	private long cantShootFor;
	private long gunCantShootFor;
	private double randIncreaser;

	private boolean reloadFlag;
	private boolean emptyFlag;

	private GameObjectHUD hud;

	@Override
	public void init(){
		setPos(Main.WIDTH/2, Main.HEIGHT/2);

		String randomGun = GameObjectGun.name[(int)(Math.random()*GameObjectGun.name.length)];
		gun = new GameObjectGun(randomGun, this);

		hud = new GameObjectHUD(this);
		hud.setPos(pos.addY(100));

		isShooting = false;
		motionMult = 0.8D; 

		cantShootFor = cantBeHitUntil = 0;
		randIncreaser = 4;
		reloadFlag = false;

		maxHealth = health = 300;
	}

	@Override
	public void update(){
		super.update();

		//faces the mouse
		setAngle((float)(
				Vector.create(R.mouseX, R.mouseY)
				.sub(Vector.create(Main.WIDTH/2, Main.HEIGHT/2))
				.getAngle()
				));
		//lookAt(R.mouseX + pos.getX() - Main.WIDTH/2, R.mouseY + pos.getY() - Main.HEIGHT/2);

		gun.setAngle(angle);

		isShooting = R.mousePressed;
		if(!canShoot()){
			isShooting = false;
		}

		if(R.mousePressed){
			if(canShoot()){
				if(gun.shoot()){

					//calculates stuff to spawn bullets
					for(int i = 0; i < gun.getNumBullets(); i++){
						Vector spawnPoint = getPos().add(Vector.createFromAngle(getAngle(), 5));
						Vector velocity = Vector.createFromAngle(getAngle(), getGun().getSpeed());
						double randomness = isMoving()? 0 :randIncreaser * MathHelper.toRad;
						
						if(gun.getName().equals("mag7")) randomness += 7.5F * MathHelper.toRad;
						
						float damage = getGun().getDamage();
						GameObjectBullet b = new GameObjectBullet(this, spawnPoint, velocity, randomness, damage);
						
						if(gun.getName().equals("mag7")) b.setPos(b.getPos().add(Vector.random(3)));
						
						if(gun.getName().equals("awp")) b.setSize(10);
						state.spawn(b);
					}
					

					

					//awp users get recoiled
					if("awp".equals(getGun().getName()))
						motion.add(Vector.createFromAngle(angle, -4));
				} else { //shot, but gun is empty
					if(emptyFlag){
						emptyFlag = false;
						Main.playSound("clipempty_rifle");
					}

				}
			} // can't shoot b/c either drawing or reloading
		} else {
			randIncreaser = 2;
			emptyFlag = true;
			if(!gun.shootPreview())
				gun.reload();
		}

		Vector tempMotion = Vector.ZERO.clone();
		if(Main.isPressed('w')) tempMotion = tempMotion.addY(-0.4);
		if(Main.isPressed('s')) tempMotion = tempMotion.addY(+0.4);
		if(Main.isPressed('a')) tempMotion = tempMotion.addX(-0.4);
		if(Main.isPressed('d')) tempMotion = tempMotion.addX(+0.4);


		if(!Vector.ZERO.equals(tempMotion))
			tempMotion = tempMotion.normalize(0.4);
		if(R.mousePressed){
			tempMotion.scalar(0.4);
			randIncreaser = Math.min(randIncreaser + 2D, 8D);

		}


		motion = motion.add(tempMotion);

		//Handles Reloading
		if(Main.isPressed('r')){
			if(canShoot() && reloadFlag){
				reloadFlag = false;
				gun.reload();

			}
		} else reloadFlag = true;

		hud.update();
	}

	private boolean canShoot(){
		return cantShootFor <= System.currentTimeMillis() && gunCantShootFor <= System.currentTimeMillis();
	}

	@Override
	public void render(double framestep) {
		R.pushMatrix();
		{
			R.translate(getXF(), getYF());

			hud.render(framestep);

			R.rotate((float) getAngle());
			R.rectMode(PConstants.CENTER);

			gun.render(framestep);

			R.noStroke();
			R.fill(100, 0, 255);
			R.ellipse(0, 0, size, size);
		}
		R.popMatrix();
	}

	public boolean isShooting() {
		return isShooting;
	}

	public GameObjectGun getGun() {
		return gun;
	}

	public GameObjectHUD getHUD(){
		return hud;
	}

	public void setGun(GameObjectGun gun) {
		this.gun = gun;
		Main.playSound(gun.getName() + "_draw");
	}
	public void setGun(String gun) {
		setGun(new GameObjectGun(gun, this));
	}

	@Override
	public void cantShootFor(long time){
		cantShootFor = time + System.currentTimeMillis();
	}

	public void gunCantShootFor(long time){
		gunCantShootFor = time + System.currentTimeMillis();
	}

	@Override
	public Shooter dontCheck(){
		return this;
	}
	
	public boolean canBeHit(){
		return cantBeHitUntil <= System.currentTimeMillis();
	}

}
