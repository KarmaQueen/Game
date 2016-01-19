package isom3320;

import java.util.ArrayList;

public class StateGame extends State{
	
	private GameObjectPlayer player;
	private GameMap map;
	private ArrayList<GameObject> gameobjects;
	private long shootTimer;


	public void init(){
		player = new GameObjectPlayer();
		gameobjects = new ArrayList<GameObject>();
		shootTimer = 0;

		map = new GameMap(null); //TODO: later change null to something else
		player.setGun(new GameObjectGun("m4a1s"));
		GameObjectItem.setPlayer(player);
		GameObject.state = this;
		
		this.spawn(new GameObjectItem("ak47", Vector.create(1000, 500)));
		this.spawn(new GameObjectItem("awp", Vector.create(1400, 700)));
		this.spawn(new GameObjectItem("m4a1s", Vector.create(700, 700)));
	}

	@Override
	public void deinit() {
		GameObject.state = null;
	}

	@Override
	public void update() {
		player.update();
		map.update();
		
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
	
	public void spawn(GameObject go){
		gameobjects.add(go);
	}
}
