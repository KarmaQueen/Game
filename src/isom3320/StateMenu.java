package isom3320;

import java.util.ArrayList;

import processing.core.PConstants;

public class StateMenu extends State {
	
	private ArrayList<GameObjectButton> buttons;

	@Override
	public void init() {
		if(Main.clip == null || !Main.clip.isActive())
			Main.music("intro.wav");
		
		//Initialize buttons
		buttons = new ArrayList<GameObjectButton>();
		buttons.add(new GameObjectButton("Start", 50, Vector.create(Main.WIDTH*0.5F, Main.HEIGHT*0.5F)));
		buttons.add(new GameObjectButton("How to Play", 50, Vector.create(Main.WIDTH*0.5F, Main.HEIGHT*0.5F + 125)));
	}

	@Override
	public void deinit() {
		buttons.clear();
	}

	@Override
	public void update() {
		if(R.keyPressed){
			if(R.key == ' ')
				R.changeState(new StateGame());
		}
		
		for(int i = 0; i < buttons.size(); i++){
			GameObjectButton b = buttons.get(i);
			b.update();
			if(b.isHovered() && R.mousePressed){
				switch(i){
				case 0: 
					R.changeState(new StateGame());
					break;
				case 1:
					R.changeState(new StateHowToPlay());
				}
			}
		}
	}

	@Override
	public void render(double framestep) {
		R.textAlign(PConstants.CENTER);
		R.textFont(Main.font);
		R.textSize(130);
		R.fill(100, 0, 255);
		R.text("Dead Center", WIDTH*0.5F, HEIGHT*0.3F);
		
		R.textSize(30);
		R.fill(255);
		R.text("Press Space To Start", WIDTH*0.5F, HEIGHT - 100);
		
		R.textSize(15);
		R.text("Project Group 13", WIDTH*0.5F, HEIGHT - 50);
		
		for(GameObjectButton b : buttons){
			b.render(framestep);
		}
	}

}
