package isom3320;

public class GameObjectGun extends GameObject {
	
	public static String[] name =     new String[]{"ak47",  "awp", "m4a1s"};
	public static double[] damage =   new double[]{    30,    100,     30};
	public static long[] delayPerShot = new long[]{   100,   1500,    100};
	public static Color[] colors = new Color[]{
			Color.create(255, 0, 0), 
			Color.create(0, 180, 0),
			Color.create(0, 0, 200)};
	private int index;
	
	public GameObjectGun(String s){
		for(int i = 0; i < name.length; i++){
			if(s.equals(name[i])){
				index = i;
				color = colors[i];
				break;
			}
				
		}
	}
	
	@Override
	public void update(){
		
	}
	
	

	@Override
	public void render(double framestep) {
		R.noStroke();
		setColorAsFill();
		switch(name[index]){
		
		case "ak47":
			R.rect(10, 5, 10, 3);
			break;
		case "awp":
			R.rect(10, 5, 30, 4);
		case "m4a1s":
			R.rect(10, 5, 20, 3);
		}	
	}
	
	public String getName(){
		return name[index];
	}
	
	public long getDelay(){
		return delayPerShot[index];
	}
	
	public double getDamage(){
		return damage[index];
	}
	
	public Color getColor(){
		return colors[index];
	}

}
