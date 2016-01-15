package hkust;

import java.util.ArrayList;

public class StateGame extends State{
	
	private GameObject player;
	private ArrayList<GameObject> gameobjects;
	private long shootTimer;
	
	public void init(){
		player = new GameObjectPlayer();
		gameobjects = new ArrayList<GameObject>();
		shootTimer = 0;
	}

	@Override
	public void deinit() {
		
	}

	@Override
	public void update() {
		player.update();
		
		if(((GameObjectPlayer)player).isShooting()){
			if(shootTimer <= System.currentTimeMillis()){
				gameobjects.add(
					new GameObjectBullet(
							player.getPos().add(Vector.createFromAngle(player.getAngle(), 5)), //spawn point
									Vector.createFromAngle(player.getAngle(), 15),
									!player.isMoving()? 8D * MathHelper.toRad : 0)); //last param is speed of bullet
				shootTimer = System.currentTimeMillis() + 100;
			}
		}
		
		for(int i = gameobjects.size() - 1; i >= 0; i--){
			gameobjects.get(i).update();
			if(gameobjects.get(i).isDead())
				gameobjects.remove(i);
		}
		
	}

	@Override
	public void render(double framestep) {
		player.render(framestep);
		
		for(GameObject go : gameobjects){
			go.render(framestep);
		}
	}

}
