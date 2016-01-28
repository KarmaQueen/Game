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

	private boolean flag;

	public void init(){
		player = new GameObjectPlayer();
		player.cantShootFor(1000);
		gameobjects = new ArrayList<GameObject>();
		bullets = new ArrayList<GameObjectBullet>();
		killScore = timeScore = 0;
		invulnerabilityTimer = 0;

		//setting player as static targets for other objects
		GameObjectItem.setPlayer(player);
		GameObject.state = this;
		GameObjectEnemy.player = player;

		gameStartTime = System.currentTimeMillis();

		Main.stopMusic();
		Main.music("music.wav");
	}

	@Override
	public void deinit() {
		GameObject.state = null;
		Main.stopMusic();
	}

	@Override
	public void update() {
		timeScore = (int) (System.currentTimeMillis() - gameStartTime)/1000;
		player.update();

		if(Main.isPressed('m')){
			if(flag){

				if(Main.musicPlaying) Main.stopMusic();
				else Main.music("music.wav");

				flag = false;
			}
		} else flag = true;
		
		//Bullet collision
		for(int i = bullets.size() - 1; i >= 0; i--){
			GameObjectBullet b = bullets.get(i);
			b.update();
			for(GameObject go : gameobjects){

				if(b.collidesWith(go)){

					if(!(go instanceof GameObjectEnemy)) continue;

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

		//Spawn Enemies
		if(rand.nextInt((120 - (int)(0.0005*(System.currentTimeMillis() - gameStartTime)))) == 0){
			Vector random = Vector.create(rand.nextInt(Main.WIDTH),rand.nextInt(Main.HEIGHT));
			spawn(new GameObjectEnemy(random, 0).setPos(Vector.randomWithBounds(player.getPos(), 500, 1000)));
		}

		//Spawn Items
		if(rand.nextInt(130) == 0){
			spawn(new GameObjectItem(Vector.randomWithBounds(player.getPos(), 50, 1000)));
		}

		//Game Over
		if(player.isDead()){
			GameObject.R.changeState(new StateGameOver(killScore, timeScore));
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
