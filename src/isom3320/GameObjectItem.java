package isom3320;

import processing.core.PConstants;
import processing.core.PImage;

public class GameObjectItem extends GameObject {

	public static String[] effects = new String[]{
			"maxAmmo","maxHealth","invulnerability","nuke"
	};
	public static float[] probability = new float[]{
			0.4F,             0.3F,       0.2F,    0.1F
	};

	private String effect;
	private static GameObjectPlayer player;
	private double size;
	private boolean isGun;
	private long despawnTimer;

	private PImage img;

	public GameObjectItem(String effect, Vector pos){
		super(pos);
		this.effect = effect;
		for(int i = 0; i < GameObjectGun.name.length; i++){
			if(GameObjectGun.name[i].equals(effect))
				color = GameObjectGun.colors[i];
		}
	}
	public GameObjectItem(Vector pos){
		super(pos);
		if(MathHelper.rand.nextBoolean() || MathHelper.rand.nextBoolean()){
			effect = effects[this.getRandomEffectIndex()];
			img = R.loadImage(effect + ".png");
			img.resize((int)size, (int)size);
			isGun = false;
		} else {
			effect = GameObjectGun.name[MathHelper.rand.nextInt(GameObjectGun.name.length)];
			img = null;
			isGun = true;
		}
		for(int i = 0; i < GameObjectGun.name.length; i++){
			if(GameObjectGun.name[i].equals(effect))
				color = GameObjectGun.colors[i];
		}
	}

	@Override
	public void init(){
		size = 40;
		despawnTimer = System.currentTimeMillis();
	}

	public static void setPlayer(GameObjectPlayer player){
		GameObjectItem.player = player;
	}

	@Override
	public void update(){
		if(playerIsNear()){
			for(int i = 0; i < GameObjectGun.name.length; i++){
				if(GameObjectGun.name[i].equals(effect)){
					if(player.getGun().getName().equals(effect)){
						
						player.getGun().setAmmo(player.getGun().getMaxAmmo());
						Main.playSound("maxAmmo");
					} else {
						player.setGun(effect);
						player.cantShootFor(750L);
					}
				}
			}
			
			
			switch(effect){
			case "maxHealth": 
				player.heal(player.getMaxHealth()/2);
				break;
			case "maxAmmo": 
				player.getGun().setAmmo(player.getGun().getMaxAmmo());
				player.cantShootFor(-10000);
				break;
			case "invulnerability":
				player.cantBeHitFor(5000L);
				break;
			case "nuke":
				for(GameObject go : state.gameobjects)
					go.kill();
				player.damage(player.getHealth()*0.75F);
				break;
			default: break;
			}
			if(!isGun)
				Main.playSound(effect);
			StateGame.killScore += 5;
			kill();
		}
		if(despawnTimer <= System.currentTimeMillis()){
			damage(0.5F);
			despawnTimer = System.currentTimeMillis() + 100;
		}
		if(health <= 0) kill();
	}

	private boolean playerIsNear() {
		double distSq = Vector.getDistanceSq(pos, player.pos);
		return distSq <= size*size;
	}

	@Override
	public void render(double framestep) {
		setColorAsFill();
		if(img != null)
			R.image(img, getXF() - (float)size*0.5F, getYF() - (float)size*0.5F);
		else
			R.ellipse(getXF(), getYF(), (float)size, (float)size);

		R.rectMode(PConstants.CORNER);
		R.fill(40, 40, 255);
		R.rect(getXF()-20, getYF()-30, 40, 4);
		R.fill(100, 100,255);
		R.rect(getXF()-20, getYF()-30, 40*getHealthRatio(), 4);
	}

	private int getRandomEffectIndex(){
		double d = Math.random();
		double sum = 0;

		for(int i = 0; i < effects.length; i++){
			sum += probability[i];

			if(d <= sum) return i;
		}
		return 0;
	}

}
