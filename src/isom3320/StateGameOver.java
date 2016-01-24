package isom3320;

import java.util.Collections;

public class StateGameOver extends State {
	
	int yourScore;
	int yourScoreIndex;
	
	public StateGameOver(int score){
		yourScore = score;
		yourScoreIndex = -1;
	}
	
	@Override
	public void init() {
		Collections.sort(Main.highscores);
		for(int i = Main.highscores.size() - 1; i >= 0; i--){
			if(yourScore > Main.highscores.get(i)){
				yourScoreIndex = i;
				if(yourScoreIndex == 2){
					Main.highscores.set(0, Main.highscores.get(1));
					Main.highscores.set(1, Main.highscores.get(2));
				}
				else if(yourScoreIndex == 1)
					Main.highscores.set(0, Main.highscores.get(1));
				Main.highscores.set(i, yourScore);
				break;
			}
		}	
	}

	@Override
	public void deinit() {

	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(double framestep) {
		GameObject.R.textSize(50);
		GameObject.R.text("Game Over", Main.WIDTH/2 - 50, Main.HEIGHT/8);
		GameObject.R.textSize(20);
		
		int counter = 30;
		int index = 1;
		for(int i = 2; i >= 0; i--){
			if(yourScoreIndex == i)
				GameObject.R.textSize(30);
			else
				GameObject.R.textSize(20);
			GameObject.R.text(index + ". " + Main.highscores.get(i), Main.WIDTH/2, Main.HEIGHT/4 + counter);
			counter += 30;
			index++;
		}
		
		GameObject.R.text("Press G to play again!", Main.WIDTH/2 - 50, (float)(Main.HEIGHT/1.5));
		
		if(GameObject.R.keyPressed)
			if(GameObject.R.key == 'g')
				GameObject.R.changeState(new StateGame());
	}
	
}
