package isom3320;

import java.util.ArrayList;

public class StateGame extends State{
	
	private GameObjectPlayer player;
	//private GameMap map;
	public ArrayList<GameObject> gameobjects;
	private ArrayList<GameObjectBullet> bullets;
	public static int killScore, timeScore;
	public long invulnerabilityTimer;
	
	private long gameStartTime;

	public void init(){
		player = new GameObjectPlayer();
		gameobjects = new ArrayList<GameObject>();
		bullets = new ArrayList<GameObjectBullet>();
		killScore = timeScore = 0;
		invulnerabilityTimer = 0;

		//map = new GameMap(null); //TODO: later change null to something else
		player.setGun(new GameObjectGun("ak47", player));
		GameObjectItem.setPlayer(player);
		GameObject.state = this;
		GameObjectEnemy.player = player;
		
		this.spawn(new GameObjectItem("ak47", Vector.create(1000, 500)));
		this.spawn(new GameObjectItem("awp", Vector.create(1400, 700)));
		this.spawn(new GameObjectItem("m4a1s", Vector.create(700, 700)));
		//this.spawn(new GameObjectEnemyShooter(Vector.create(300, 300), 180*MathHelper.invPI));
		
		gameStartTime = System.currentTimeMillis();
	}

	@Override
	public void deinit() {
		GameObject.state = null;
	}

	@Override
	public void update() {
		timeScore = (int) (System.currentTimeMillis() - gameStartTime)/1000;
		player.update();
		
		for(int i = bullets.size() - 1; i >= 0; i--){
			GameObjectBullet b = bullets.get(i);
			b.update();
			for(GameObject go : gameobjects){
				if(b.collidesWith(go)){
					if(!b.getOrigin().equals(go))
					go.damage((float) b.getBulletDamage());
					if(!player.getGun().getName().equals("awp"))
						b.kill();
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
		
		if(rand.nextInt((120 - (int)(0.0005*(System.currentTimeMillis() - gameStartTime)))) == 0){
			spawn(new GameObjectEnemy(Vector.create(rand.nextInt(Main.WIDTH),rand.nextInt(Main.HEIGHT)), 0).setPos(Vector.random(player.getPos(), 500, 1000)));
		}
		if(rand.nextInt(130) == 0){
			spawn(new GameObjectItem(Vector.random(player.getPos(), 500, 1000)));
		}
		if(player.getHealth() <= 0){
			GameObject.R.changeState(new StateGameOver(killScore + timeScore));
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
