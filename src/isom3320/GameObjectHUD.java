package isom3320;

public class GameObjectHUD extends GameObject {

	private String effect;
	private GameObjectPlayer player;

	private float health;

	private float redHealthHeight;
	private float redHealthWidth;
	
	private float ammo;

	public GameObjectHUD(GameObjectPlayer player){
		super();
		this.player = player;
		ammo = player.getGun().getCurrentAmmo();
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
		R.rectMode(0);
		R.fill(255,0,0);
		R.rect(0, Main.HEIGHT*0.4F, redHealthWidth, redHealthHeight);
		R.fill(0,255,0);
		R.rect(0, Main.HEIGHT*0.4F, health, redHealthHeight);
		
		R.rectMode(0);
		R.fill(255,255,0);
		R.rect(0, Main.HEIGHT*0.4F, ammo*10, redHealthHeight);
	}

	@Override
	public void update(){
		super.update();
		calculateHealth();
		calculateAmmo();
	}

	public void calculateHealth(){
		if(health >= 0)
			health-=0.5;
	}
	
	public void calculateAmmo(){
		ammo = player.getGun().getCurrentAmmo();
	}

}
