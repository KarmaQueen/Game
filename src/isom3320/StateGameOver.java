package isom3320;

import java.util.Collections;

import processing.core.PConstants;

public class StateGameOver extends State {
	
	private int killScore, timeScore;
	
	public StateGameOver(int killScore, int timeScore){
		this.killScore = killScore;
		this.timeScore = timeScore;
	}
	
	@Override
	public void init() {
		Main.highscores.add(killScore + timeScore);
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
		GameObject.R.text("Game Over", Main.WIDTH*0.5F, Main.HEIGHT/8);
		
		GameObject.R.textSize(60);
		GameObject.R.text(killScore +timeScore, Main.WIDTH/4 + 100, Main.HEIGHT/4 + 150);
		
		GameObject.R.textSize(20);
		GameObject.R.text("Your Score:", Main.WIDTH/4 + 100, Main.HEIGHT/4 + 150 - 70);
		
		GameObject.R.text("You survived for " + timeScore + " seconds!", Main.WIDTH/4 + 100, Main.HEIGHT/4 + 150 +60);
		
		float yval = Main.HEIGHT * 0.25F;
		
		GameObject.R.textSize(20);
		GameObject.R.textAlign(PConstants.CENTER);
		GameObject.R.text("P r e s s   S p a c e   t o   p l a y   a g a i n!", Main.WIDTH*0.5F, yval);
		
		yval += 40;
		
		GameObject.R.textAlign(PConstants.LEFT);
		for(int i = 0; i < Main.highscores.size(); i++){
			int score = Main.highscores.get(i);
			if(killScore + timeScore == score) {
				GameObject.R.textSize(40);
				GameObject.R.fill(255, 50, 50);
				yval += 60;
			}
			else {
				GameObject.R.textSize(20);
				GameObject.R.fill(255);
				yval += 40;
			}
			
			String str = (i+1) + ". " + score;
			GameObject.R.text(str, Main.WIDTH*0.5F + 200, yval);
			
			
		}
		
		
		
	}
	
}
