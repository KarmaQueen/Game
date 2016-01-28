package isom3320;

public class GameObjectGun extends GameObject {
	
	public static String[] name =     new String[]{"ak47",  "awp","m4a1s", "mag7"};
	public static float[] damage =     new float[]{    34,   1000,     50,     34};
	public static long[] delayPerShot = new long[]{   100,   1500,    130,    450};
	public static double[] speed =    new double[]{    15,     30,     15,     15};
	public static int[] maxAmmo =        new int[]{    40,     10,     20,      7};
	public static long[] reloadTimer =  new long[]{  1500,   1900,   1500,   1900};
	public static int[] numBullets =     new int[]{     1,      1,      1,      6};
	public static Color[] colors = new Color[]{
			Color.create(121, 67, 54), 
			Color.create(0, 140, 0),
			Color.create(0, 0, 200),
			Color.create(100, 120, 160)};
	private int index, currentAmmo;
	private Shooter user;
	private long cantShootFor;
	
	protected boolean isShooting; //checks whether gun should be shooting or not
	
	public GameObjectGun(String s, Shooter user){
		for(int i = 0; i < name.length; i++){
			if(s.equals(name[i])){
				index = i;
				color = colors[i];
				currentAmmo = maxAmmo[i];
				break;
			}
				
		}
		this.user = user;
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
			R.fill(82, 80, 94);
			R.rect(15, 5, 30, 4);
			R.fill(121, 67, 54);
			R.rect(15, 5, 3, 5);
			R.rect(0, 5, 4, 5);
			break;
		case "awp":
			R.rect(25, 5, 50, 5);
			break;
		case "m4a1s":
			R.rect(15, 5, 30, 4);
			break;
		case "mag7":
			R.rect(15, 5, 25, 6);
			break;
		}	
	}
	
	public String getName(){
		return name[index];
	}
	
	public long getDelay(){
		return delayPerShot[index];
	}
	
	public float getDamage(){
		return damage[index];
	}
	
	public Color getColor(){
		return colors[index];
	}
	
	public double getSpeed(){
		return speed[index];
	}
	
	public boolean shoot(){
		if(currentAmmo <= 0){
			return false;
		}
		if(!gunCanShoot()) return false;
		currentAmmo--;
		Main.playSound(name[index]);
		cantShootFor(delayPerShot[index]);
		user.cantShootFor(getDelay());
		return true;
	}
	
	public boolean shootPreview(){
		return currentAmmo > 0;
	}
	
	public void reload(){
		Main.playSound(name[index]+"_reload");
		currentAmmo = maxAmmo[index];
		user.cantShootFor(getReloadTime());
	}

	public long getReloadTime() {
		return reloadTimer[index];
	}
	
	public int getCurrentAmmo(){
		return currentAmmo;
	}
	
	public int getMaxAmmo(){
		return maxAmmo[this.index];
	}
	
	public void cantShootFor(long time){
		cantShootFor = System.currentTimeMillis() + time;
	}
	
	public boolean gunCanShoot(){
		return cantShootFor <= System.currentTimeMillis();
	}

	public void setAmmo(int ammo){
		currentAmmo = ammo;
	}
	
	public int getNumBullets(){
		return numBullets[this.index];
	}

}
