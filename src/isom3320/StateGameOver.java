package isom3320;

public class StateGameOver extends State {
	
	int yourScore;
	
	public StateGameOver(int score){
		yourScore = score;
	}
	
	@Override
	public void init() {

	}

	@Override
	public void deinit() {

	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(double framestep) {
		GameObject.R.text(yourScore, Main.WIDTH/2, Main.HEIGHT/2);
	}
	
}
