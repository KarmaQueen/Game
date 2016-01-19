package isom3320;

public class GameObjectGun extends GameObject {
	
	private String[] name =     new String[]{"ak47",  "awp", "m4a1"};
	private double[] damage =   new double[]{    30,    100,     30};
	private long[] delayPerShot = new long[]{   100,   1500,    100};
	
	private int index;
	
	public GameObjectGun(String s){
		for(int i = 0; i < name.length; i++){
			if(s.equals(name[i])){
				index = i;
				break;
			}
				
		}
	}
	
	@Override
	public void update(){
		
	}

	@Override
	public void render(double framestep) {
		// TODO Auto-generated method stub
		
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

}
