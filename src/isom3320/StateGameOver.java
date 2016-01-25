package isom3320;

import java.util.Collections;

import processing.core.PConstants;

public class StateGameOver extends State {
	
	private int yourScore;
	
	public StateGameOver(int score){
		yourScore = score;
	}
	
	@Override
	public void init() {
		Main.highscores.add(yourScore);
		Collections.sort(Main.highscores);
		Collections.reverse(Main.highscores);
		Main.playSound("victory");
	}

	@Override
	public void deinit() {

	}

	@Override
	public void update() {
		if(GameObject.R.keyPressed)
			if(GameObject.R.key == ' ')
				GameObject.R.changeState(new StateGame());
	}

	@Override
	public void render(double framestep) {
		GameObject.R.fill(255);
		GameObject.R.textFont(Main.font);
		GameObject.R.textAlign(PConstants.CENTER);
		GameObject.R.textSize(50);
		GameObject.R.text("Game Over", Main.WIDTH/2, Main.HEIGHT/8);
		GameObject.R.textSize(20);
		
		float yval = Main.HEIGHT * 0.25F;
		
		GameObject.R.textSize(20);
		GameObject.R.textAlign(PConstants.CENTER);
		GameObject.R.text("P r e s s   S p a c e   t o   p l a y   a g a i n!", Main.WIDTH/2, yval);
		
		yval += 40;
		
		GameObject.R.textAlign(PConstants.LEFT);
		for(int i = 0; i < Main.highscores.size(); i++){
			int score = Main.highscores.get(i);
			if(yourScore == score) {
				GameObject.R.textSize(40);
				yval += 60;
			}
			else {
				GameObject.R.textSize(20);
				yval += 40;
			}
			
			String str = (i+1) + ". " + score;
			GameObject.R.text(str, Main.WIDTH*0.5F-100, yval);
			
			
		}
		
		
		
	}
	
}
