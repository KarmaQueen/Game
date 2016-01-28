package isom3320;

import java.util.Random;

public abstract class State {
	
	protected static Random rand;
	protected State nextState;
	protected static Main R;
	public static int WIDTH, HEIGHT;

	public abstract void init();
	
	public abstract void deinit();

	public abstract void update();
	
	public abstract void render(double framestep);

	//method that can be overridden when needed for states that will pause the game; used for smooth movements, etc
	public boolean getPaused() {
		return false;
	}
	
}
