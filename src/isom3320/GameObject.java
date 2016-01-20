package isom3320;
/**
 * The GameObject class. Used to create any object visible on the screen.
 * @author David
 *
 */
public abstract class GameObject {
	
	public static Main R;
	public static StateGame state;
	
	protected Vector pos, pPos, motion; 
	
	protected double angle, motionMult;
	protected float size;
	private boolean isDead, isMoving;
	protected Color color;

	public GameObject(){
		this(0, 0, 0);
	}
	public GameObject(double x, double y){
		this(x, y, 0);
	}
	public GameObject(Vector v){
		this(v, 0);
	}
	public GameObject(double x, double y, double angle){
		this(Vector.create(x, y), angle);
	}
	public GameObject(Vector v, double angle){
		pos = v.clone();
		pPos = pos.clone();
		this.angle = angle;
		isDead = false;
		motion = Vector.ZERO.clone();
		motionMult = 0.95D;
		color = Color.create(255, 255, 255);
		size = 25;
		init();
	}
	
	public void init(){}

	public void update() {
		
		pos.setVec(pos.add(motion));
		//motion.setVec(Math.max(0, motion.getX() - motionMult), Math.max(0, motion.getY() - motionMult));
		motion.scalar(motionMult);
		if(motion.getLengthSq() <= 0.00001D){
			motion.setVec(0, 0);
			isMoving = true;
		} else isMoving = false;
		
		pPos.setVec(pos);
	}

	public abstract void render(double framestep);

	//Get the partial position of the game object's current position usually for rendering
	protected Vector getPartialPos(double framestep) {
		return getPartialPos(pos, pPos, framestep);
	}
	protected Vector getPartialPos(Vector vec, Vector pVec, double framestep) {
		return getPartialPos(vec.getX(), vec.getY(), pVec.getX(), pVec.getY(), framestep);
	}
	protected Vector getPartialPos(double x, double y, double px, double py, double framestep) {
		double dx = x - px, dy = y - py;
		return new Vector(px + dx * framestep, py + dy * framestep);
	}

	//Setters
	public GameObject setPos(double x, double y) {
		pos.setVec(x, y);
		return this;
	}
	public GameObject setPos(Vector v){
		return setPos(v.getX(), v.getY());
	}
	public GameObject setPPos(double x, double y){
		pPos.setVec(x,y);
		return this;
	}
	public GameObject setX(double x){
		pos.setVec(x, getY());
		return this;
	}
	public GameObject setY(double y){
		pos.setVec(getX(), y);
		return this;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public void lookAt(Vector v){
		lookAt(v.getX(), v.getY());
	}
	public void lookAt(double x, double y){
		this.angle = new Vector(x, y).sub(pos).getAngle();
	}
	
	protected void setColorAsFill(){
		int[] arr = color.getColorArr();
		R.fill(arr[0], arr[1], arr[2]);
	}
	
	//Getters
	public double getX(){ return pos.getX(); }
	public double getY(){ return pos.getY(); }
	public float getXF(){ return (float)pos.getX(); }
	public float getYF(){ return (float)pos.getY(); } 
	public Vector getPos() { return pos; }
	public double getAngle() { return angle;}
	public boolean isDead() {
		return isDead;
	}
	public void setDead() {
		this.isDead = true;
	}
	public Vector getMotion() {
		return motion;
	}
	public boolean isMoving() {
		return isMoving;
	}
	
	public void setSize(float size){
		this.size = size;
	}
	public double getSize(){
		return size;
	}
	public boolean collidesWith(GameObject go){
		//TODO: fak should've picked number 4
		return false;
	}
}
