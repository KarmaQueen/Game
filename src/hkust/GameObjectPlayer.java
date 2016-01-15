package hkust;

import processing.core.PConstants;

public class GameObjectPlayer extends GameObject {
	
	private boolean isShooting;
	
	@Override
	public void init(){
		setPos(Main.WIDTH/2, Main.HEIGHT/2);
		isShooting = false;
		motionMult = 0.9D; 
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
				.sub(getPos())
				.getAngle()
				));
		
		isShooting = R.mousePressed;
		
		if(isShooting)
			motion.scalar(0.7D);
	}

	@Override
	public void render(double framestep) {
		// TODO Auto-generated method stub
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
}
