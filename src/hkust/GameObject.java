package hkust;
/**
 * The GameObject class. Used to create any object visible on the screen.
 * @author David
 *
 */
public abstract class GameObject {
	
	public static Main R;
	
	protected Vector pos, pPos, motion; 
	
	protected double angle, motionMult;
	private boolean isDead;

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
		angle = 0;
		isDead = false;
		motion = Vector.ZERO.clone();
		motionMult = 0.95D;
		init();
	}
	
	public void init(){}

	public void update() {
		
		pos.setVec(pos.add(motion));
		//motion.setVec(Math.max(0, motion.getX() - motionMult), Math.max(0, motion.getY() - motionMult));
		motion.scalar(motionMult);
		if(motion.getLengthSq() <= 0.00001D) motion.setVec(0, 0);
		
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
}
