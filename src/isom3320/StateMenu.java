package isom3320;

import processing.core.PConstants;

public class StateMenu extends State {

	@Override
	public void init() {
		Main.music("intro");
	}

	@Override
	public void deinit() {
		Main.stopMusic();
	}

	@Override
	public void update() {
		if(R.keyPressed){
			if(R.key == ' ')
				R.changeState(new StateGame());
		}
	}

	@Override
	public void render(double framestep) {
		R.textAlign(PConstants.CENTER);
		R.textFont(Main.font);
		R.textSize(180);
		R.fill(100, 0, 255);
		R.text("GAME MASTER", Main.WIDTH*0.5F, Main.HEIGHT*0.3F);
		
		R.textSize(30);
		R.fill(255);
		R.text("Press Space To Start", Main.WIDTH*0.5F, Main.HEIGHT*0.8F);
	}

}
