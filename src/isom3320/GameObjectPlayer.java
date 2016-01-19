package isom3320;

import processing.core.PConstants;


public class GameObjectPlayer extends GameObject {
	
	private boolean isShooting;
	private GameObjectGun gun;

	@Override
	public void init(){
		setPos(Main.WIDTH/2, Main.HEIGHT/2);
		isShooting = false;
		motionMult = 0.8D; 
		gun = new GameObjectGun("ak47");
	}
	
	@Override
	public void update(){
		super.update();
		Vector tempMotion = Vector.ZERO.clone();
		if(Main.isPressed('w')) tempMotion = tempMotion.addY(-0.5);
		if(Main.isPressed('s')) tempMotion = tempMotion.addY(+0.5);
		if(Main.isPressed('a')) tempMotion = tempMotion.addX(-0.5);
		if(Main.isPressed('d')) tempMotion = tempMotion.addX(+0.5);
		motion = motion.add(tempMotion);
		
		//faces the mouse
		setAngle((float)(
				Vector.create(R.mouseX, R.mouseY)
				//.sub(getPos())
				.sub(Vector.create(Main.WIDTH/2, Main.HEIGHT/2))
				.getAngle()
				));
		
		isShooting = R.mousePressed;
		
		if(isShooting && !gun.getName().equals("awp"))
			motion.scalar(0.8D);
		
	}

	@Override
	public void render(double framestep) {
		// TODO Auto-generated method stub
		gun.render(framestep);
		R.noStroke();
		R.fill(100, 0, 255);
		R.pushMatrix();
		{
			R.translate(getXF(), getYF());
			R.rotate((float) getAngle());
			R.rectMode(PConstants.CENTER);
			R.rect(0, 0, 10, 25);
		}
		R.popMatrix();
	}

	public boolean isShooting() {
		return isShooting;
	}
	
	public GameObjectGun getGun() {
		return gun;
	}

	public void setGun(GameObjectGun gun) {
		this.gun = gun;
	}
	
}
