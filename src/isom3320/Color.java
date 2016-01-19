package isom3320;

public class Color {
	
	public static Color create(int r, int g, int b){
		return new Color(r,g,b);
	}
	
	private int[] color;
	public Color(int r, int g, int b){
		color = new int[3];
		color[0] = r;
		color[1] = g;
		color[2] = b;
	}
	
	public int[] getColorArr(){
		return color.clone();
	}
}
