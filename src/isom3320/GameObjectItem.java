package isom3320;

import processing.core.PImage;

public class GameObjectItem extends GameObject {
	
	public static String[] effects = new String[]{
			"maxAmmo","invulnerability","maxHealth", "nuke"
	};
	
	private String effect;
	private static GameObjectPlayer player;
	private double size;
	
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
		if(Main.rand.nextBoolean()){
			effect = effects[Main.rand.nextInt(effects.length)];
			img = R.loadImage(effect + ".png");
			img.resize((int)size, (int)size);
		} else {
			effect = GameObjectGun.name[Main.rand.nextInt(GameObjectGun.name.length)];
			img = null;
		}
		for(int i = 0; i < GameObjectGun.name.length; i++){
			if(GameObjectGun.name[i].equals(effect))
				color = GameObjectGun.colors[i];
		}
	}
	
	@Override
	public void init(){
		size = 40;
	}
	
	public static void setPlayer(GameObjectPlayer player){
		GameObjectItem.player = player;
	}
	
	@Override
	public void update(){
		if(playerIsNear()){
			for(int i = 0; i < GameObjectGun.name.length; i++){
				if(GameObjectGun.name[i].equals(effect) && 
						!player.getGun().getName().equals(effect)){
					player.setGun(effect);
					player.cantShootFor(750L);
				}
			}
			switch(effect){
			case "maxHealth": 
				player.heal(player.getMaxHealth()/2);
				break;
			case "maxAmmo": 
				player.getGun().setAmmo(player.getGun().getMaxAmmo());
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
			Main.playSound(effect);
			kill();
		}
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
	}

}
