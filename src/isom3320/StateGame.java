package isom3320;

import java.util.ArrayList;

public class StateGame extends State{
	
	private GameObjectPlayer player;
	//private GameMap map;
	private ArrayList<GameObject> gameobjects;
	private ArrayList<GameObjectBullet> bullets;
	public static int score;

	public void init(){
		player = new GameObjectPlayer();
		gameobjects = new ArrayList<GameObject>();
		bullets = new ArrayList<GameObjectBullet>();
		score = 0;

		//map = new GameMap(null); //TODO: later change null to something else
		player.setGun(new GameObjectGun("ak47", player));
		GameObjectItem.setPlayer(player);
		GameObject.state = this;
		GameObjectEnemy.player = player;
		
		this.spawn(new GameObjectItem("ak47", Vector.create(1000, 500)));
		this.spawn(new GameObjectItem("awp", Vector.create(1400, 700)));
		this.spawn(new GameObjectItem("m4a1s", Vector.create(700, 700)));
		this.spawn(new GameObjectEnemy(Vector.create(300, 300), 180*MathHelper.invPI));
	}

	@Override
	public void deinit() {
		GameObject.state = null;
	}

	@Override
	public void update() {
		player.update();
		
		for(int i = bullets.size() - 1; i >= 0; i--){
			bullets.get(i).update();
			for(GameObject go : gameobjects){
				if(bullets.get(i).collidesWith(go)){
					go.damage((float) bullets.get(i).getBulletDamage());
					if(!player.getGun().getName().equals("awp"))
						bullets.get(i).kill();
				}
			}
			if(bullets.get(i).isDead())
				bullets.remove(i);
		}
		
		for(int i = gameobjects.size() - 1; i >= 0; i--){
			GameObject go = gameobjects.get(i);
			go.update();
			if(go.isDead()){
				gameobjects.remove(i);
			}
		}
		
		if(rand.nextInt(50) == 0){
			spawn(new GameObjectEnemy(Vector.create(rand.nextInt(Main.WIDTH),rand.nextInt(Main.HEIGHT)), 0));
		}
	}

	@Override
	public void render(double framestep) {
		GameObject.R.translate(
				Main.WIDTH/2-player.getXF(), 
				Main.HEIGHT/2-player.getYF());
		for(GameObjectBullet gob : bullets)
			gob.render(framestep);
		for(GameObject go : gameobjects)
			go.render(framestep);
		
		player.render(framestep);
	}
	
	public void spawn(GameObject go){
		if(go instanceof GameObjectBullet){
			bullets.add((GameObjectBullet) go);
		} 
		else gameobjects.add(go);
	}
}
