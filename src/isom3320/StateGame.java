package isom3320;

import java.util.ArrayList;

public class StateGame extends State{
	
	private GameObjectPlayer player;
	private GameMap map;
	private ArrayList<GameObject> gameobjects;
	private long shootTimer;
	private double randIncreaser;

	public void init(){
		player = new GameObjectPlayer();
		gameobjects = new ArrayList<GameObject>();
		shootTimer = 0;
		randIncreaser = 4;
		map = new GameMap(null); //TODO: later change null to something else
		player.setGun(new GameObjectGun("m4a1s"));
	}

	@Override
	public void deinit() {
		
	}

	@Override
	public void update() {
		player.update();
		map.update();
		
		if(player.isShooting()){
			if(shootTimer <= System.currentTimeMillis()){
				
				Vector spawnPoint = player.getPos().add(Vector.createFromAngle(player.getAngle(), 5));
				Vector velocity = Vector.createFromAngle(player.getAngle(), 15);
				double randomness = !player.isMoving()? randIncreaser * MathHelper.toRad : 0;
				double damage = player.getGun().getDamage();
				gameobjects.add(new GameObjectBullet(spawnPoint, velocity, randomness, damage));
				
				shootTimer = System.currentTimeMillis() + player.getGun().getDelay(); //The timer
				Main.playSound(player.getGun().getName() + ".wav");
				
				randIncreaser = Math.min(randIncreaser + 4D, 16D);
			}
		} else {
			randIncreaser = 4;
		}
		
		for(int i = gameobjects.size() - 1; i >= 0; i--){
			gameobjects.get(i).update();
			if(gameobjects.get(i).isDead())
				gameobjects.remove(i);
		}
		
	}

	@Override
	public void render(double framestep) {
		GameObject.R.translate(Main.WIDTH/2-player.getXF(), Main.HEIGHT/2-player.getYF());
		for(GameObject go : gameobjects)
			go.render(framestep);
		map.render(framestep);
		player.render(framestep);
	}

}
