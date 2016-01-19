package isom3320;

public abstract class State {
	
	protected State nextState;

	public abstract void init();
	
	public abstract void deinit();

	public abstract void update();
	
	public abstract void render(double framestep);

	//method that can be overridden when needed for states that will pause the game; used for smooth movements, etc
	public boolean getPaused() {
		return false;
	}
	
}
