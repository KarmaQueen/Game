package isom3320;

import processing.core.PConstants;

public class GameObjectButton extends GameObject{
	
	private String text;
	
	private float halfWidth, halfHeight, yOffset; //internally generated
	
	public GameObjectButton(String text, float size, double x, double y) {
		this(text, size, Vector.create(x,y));
	}
	public GameObjectButton(String text, float size, Vector pos) {
		this.text = text;
		setSize(size);
		setPos(pos);
		
		halfWidth = size*text.length()*0.27F;
		halfHeight = size*0.5F;
		yOffset = size*0.3F;
	}
	
	@Override
	public void update(){
		super.update();
		
		if(isHovered())
			color = Color.create(255, 0, 0);
		else
			color = Color.create(255, 255, 255);
		
	}

	@Override
	public void render(double framestep) {
		
		//Style
		R.textAlign(PConstants.CENTER);
		R.textSize(size);
		setColorAsFill();
		
		//Render
		R.text(text, getXF(), getYF());
	}
	
	public boolean isHovered(){
		return getX() - halfWidth < R.mouseX && R.mouseX < getX() + halfWidth &&
			   getY() - halfHeight < R.mouseY + yOffset && R.mouseY + yOffset < getY() + halfHeight;
	}

}
