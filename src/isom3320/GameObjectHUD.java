package isom3320;

import processing.core.PConstants;

public class GameObjectHUD extends GameObject {
	private String effect;

	private float redHealthHeight;
	private float redHealthWidth;

	private float health;


	public GameObjectHUD(){

	}

	@Override
	public void init(){
		setPos(Main.WIDTH/2, Main.HEIGHT/1.2);
		health = 300;
		redHealthHeight = 25;
		redHealthWidth = 300;
	}

	@Override
	public void render(double framestep) {
		R.fill(255,0,0);
		R.rect(0, Main.HEIGHT*0.4F, redHealthWidth, redHealthHeight);
		R.fill(0,255,0);
		R.rect(0, Main.HEIGHT*0.4F, health, redHealthHeight);
	}

	@Override
	public void update(){
		super.update();
		calculateHealth();
	}

	public void calculateHealth(){
		health-=0.5;
	}

}
