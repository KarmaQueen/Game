package isom3320;

import java.util.ArrayList;

import processing.core.PConstants;
import processing.core.PImage;

public class StateHowToPlay extends State {

	private ArrayList<GameObjectButton> buttons;
	
	private GameObjectEnemy enemy;
	
	private float yVal;
	
	private PImage[] imgs;
	private PImage controls, score;
	
	@Override
	public void init() {
		//Initialize buttons
		buttons = new ArrayList<GameObjectButton>();
		buttons.add(new GameObjectButton("Back", 30, WIDTH - 100, HEIGHT - 40));
		yVal += 100;
		enemy = new GameObjectEnemy(Vector.create(100, 100), 0);
		imgs = new PImage[4];
		controls = R.loadImage("controls.png");
		controls.resize(600, 150);
		score = R.loadImage("score.png");
		score.resize(960/2, 830/2);
		for(int i = 0 ; i < imgs.length; i++){
			imgs[i] = R.loadImage(GameObjectItem.effects[i] + ".png");
			imgs[i].resize(40, 40);
		}
	}

	@Override
	public void deinit() {
		
	}

	@Override
	public void update() {
		for(int i = 0; i < buttons.size(); i++){
			GameObjectButton b = buttons.get(i);
			b.update();
			if(b.isHovered() && R.mousePressed){
				switch(i){
				case 0: 
					R.changeState(new StateMenu());
					break;
				}
			}
		}
		
	}

	@Override
	public void render(double framestep) {
		for(GameObjectButton b : buttons){
			b.render(framestep);
		}
		
		enemy.render(framestep);
		//Style
		R.textAlign(PConstants.LEFT);
		R.textSize(20);
		R.fill(255);
		yVal = 100;
		
		R.text("This is an enemy. Shoot at it until it dies.", 150, yVal);
		yVal+= 100;
		
		R.imageMode(PConstants.CENTER);
		for(int i = 0 ; i < imgs.length; i++){
			R.image(imgs[i], 100, yVal);
			yVal += 100;
		}
		R.imageMode(PConstants.CORNER);
		R.image(controls, 100, yVal);
		R.image(score, WIDTH*0.58F, 100);
		
		R.text("Max Ammo: Replenishes your current magazine.", 150, 200);
		R.text("Max Health: Replenishes 50% of your health.", 150, 300);
		R.text("Invulnerability: You cannot be damaged for 5 seconds.", 150, 400);
		R.text("Nuke: Blasts everything including 75% of your health.", 150, 500);
		
		
	}

}
