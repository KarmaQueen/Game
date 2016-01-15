package hkust;

import processing.core.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main extends PApplet{

	public static int WIDTH, HEIGHT;
	
	public static final double MS_PER_UPDATE = 7;
	private static double previous = System.currentTimeMillis();
	private static double lag = 0.0;
	private State currentState;
	
	public static Map<Character, Boolean> inputs; 
	
	public static Random rand;
	
	public static void main(String args[]) {
		WIDTH = 1600;
		HEIGHT = 900;
		rand = new Random();
		
		PApplet.main(new String[] {"hkust.Main"});
	}

	public void settings(){
		size(WIDTH, HEIGHT);
	}
	public void setup(){
		smooth();
		changeState(new StateGame());
		GameObject.R = this;
		initInputs();
	}

	public void draw(){
		double current = System.currentTimeMillis();
		double elapsed = current - previous;
		previous = current;
		lag += elapsed;

		while (lag >= MS_PER_UPDATE) {
			update();
			lag -= MS_PER_UPDATE;
		}
		
		render(lag/MS_PER_UPDATE);
	}
	
	public void update(){
		currentState.update();
	}
	public void render(double framestep){
		background(0);
		currentState.render(framestep);
	}
	
	public void changeState(State state){
		if(currentState != null)
			currentState.deinit();
		currentState = state;
		currentState.init();
	}
	
	public void initInputs(){
		
		inputs = new HashMap<Character, Boolean>();
		
		inputs.put('w', false);
		inputs.put('W', false);
		inputs.put('a', false);
		inputs.put('A', false);
		inputs.put('s', false);
		inputs.put('S', false);
		inputs.put('d', false);
		inputs.put('D', false);
		
		inputs.put('r', false);
		inputs.put('R', false);
	}
	
	@Override
	public void keyPressed(){
		for(char c : inputs.keySet()){
			if(key == c || keyCode == c){
				inputs.put(c, true);
			}
		}
	}
	
	@Override
	public void keyReleased(){
		for(char c : inputs.keySet()){
			if(key == c || keyCode == c)
				inputs.put(c, false);
		}
	}
	
	public static boolean isPressed(char c){
		return inputs.get(c) || inputs.get(Character.toUpperCase(c));
	}
}
