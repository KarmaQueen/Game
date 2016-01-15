package hkust;

public class GameObjectButton extends GameObject {
	
	private int width, height;

	public GameObjectButton() {
		// TODO Auto-generated constructor stub
		width = 100;
		height = 50;
	}

	@Override
	public void render(double framestep) {
		// TODO Auto-generated method stub
		R.rect(getXF(), getYF(), width, height);
	}

}
