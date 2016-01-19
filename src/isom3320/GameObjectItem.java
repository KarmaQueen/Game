package isom3320;

public class GameObjectItem extends GameObject {

	private String effect;
	private static GameObjectPlayer player;
	private double size;

	public GameObjectItem(String effect, Vector pos){
		super(pos);
		this.effect = effect;
		for(int i = 0; i < GameObjectGun.name.length; i++){
			if(GameObjectGun.name[i].equals(effect))
				color = GameObjectGun.colors[i];
		}
	}
	
	@Override
	public void init(){
		size = 30;
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
			
			default: break;
			}
		}
	}

	private boolean playerIsNear() {
		double distSq = Vector.getDistanceSq(pos, player.pos);
		return distSq <= size*size;
	}

	@Override
	public void render(double framestep) {
		setColorAsFill();
		R.ellipse(getXF(), getYF(), (float)size, (float)size);
	}

}
