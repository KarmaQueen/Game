package isom3320;

import java.util.ArrayList;

import processing.core.PConstants;

public class StateGameOver extends State {
	
	private ArrayList<GameObjectButton> buttons;
	
	public StateGameOver(int killScore, int timeScore){	
		Highscore.readFromFile();
		Highscore.addScore(killScore, timeScore);
		Highscore.writeToFile();
	}
	
	@Override
	public void init() {
		Main.playSound("victory");
		buttons = new ArrayList<GameObjectButton>();
		
		buttons.add(new GameObjectButton("Play Again?", 30, WIDTH*0.5F, HEIGHT - 50));
		buttons.add(new GameObjectButton("Back to Menu", 30, WIDTH - 170, HEIGHT - 50));
	}

	@Override
	public void deinit() {
		buttons.clear();
	}

	@Override
	public void update() {
		if(GameObject.R.keyPressed && GameObject.R.key == ' ')
				GameObject.R.changeState(new StateGame());
		
		for(int i = 0; i < buttons.size(); i++){
			GameObjectButton b = buttons.get(i);
			b.update();
			if(b.isHovered() && R.mousePressed){
				R.changeState(i == 0? new StateGame() : new StateMenu());
			}
		}
	}

	@Override
	public void render(double framestep) {
		GameObject.R.fill(255);
		GameObject.R.textFont(Main.font);
		GameObject.R.textAlign(PConstants.CENTER);
		GameObject.R.textSize(50);
		GameObject.R.text("Game Over", Main.WIDTH*0.5F, Main.HEIGHT/8);
		
		GameObject.R.textSize(60);
		GameObject.R.text(Highscore.getScore(), Main.WIDTH/4 + 100, Main.HEIGHT/4 + 150);
		
		GameObject.R.textSize(20);
		GameObject.R.text("Your Score:", Main.WIDTH/4 + 100, Main.HEIGHT/4 + 150 - 70);
		
		GameObject.R.text("You survived for " + Highscore.timeScore + " seconds!", Main.WIDTH/4 + 100, Main.HEIGHT/4 + 150 +60);
		
		float yval = Main.HEIGHT * 0.25F;
		
		GameObject.R.textSize(20);
		GameObject.R.textAlign(PConstants.CENTER);
		GameObject.R.text("P r e s s   S p a c e   t o   p l a y   a g a i n!", Main.WIDTH*0.5F, yval);
		
		yval += 40;
		
		//Ranking
		GameObject.R.textAlign(PConstants.LEFT);
		for(int i = 0; i < (int)Math.min(10, Highscore.getNumScores()); i++){
			int score = Highscore.get(i);
			if(Highscore.getScore() == score) {
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
		
		for(GameObjectButton b : buttons){
			b.render(framestep);
		}
	}
	
}
