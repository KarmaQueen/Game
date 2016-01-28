package isom3320;

public class Vector {

	//Static Methods
	public static final Vector ZERO = new Vector(0,0);
	public static Vector create(double x, double y) {
		return new Vector(x, y);
	}
	public static double getDistance(Vector vec1, Vector vec2) {
		return Math.sqrt(getDistanceSq(vec1, vec2));
	}
	public static double getDistanceSq(Vector vec1, Vector vec2) {
		double dx = vec1.xCoord - vec2.xCoord;
		double dy = vec1.yCoord - vec2.yCoord;
		return dx * dx + dy * dy;
	}
	public static Vector createFromAngle(double angle){
		return create(Math.cos(angle), Math.sin(angle)).normalize();
	}
	public static Vector createFromAngle(double angle, double scalar){
		return create(Math.cos(angle), Math.sin(angle)).normalize(scalar);
	}
	public static Vector random(double scalar){
		return create(Math.random() - 0.5D, Math.random() - 0.5D).normalize(scalar);
	}
	public static Vector random(){
		return create(Math.random() - 0.5D, Math.random() - 0.5D).normalize(1);
	}
	public static Vector random(Vector pos, double innerBound, double outerBound){
		return Vector.createFromAngle(2*Math.PI*Math.random(), MathHelper.randomWithRange(innerBound, outerBound)).add(pos);
	}

	//Class Definition
	private double xCoord, yCoord;

	//Constructors
	public Vector() {
		this(0D, 0D);
	}
	public Vector(double x, double y) {
		xCoord = x;
		yCoord = y;
	}
	public Vector(Vector v){
		xCoord = v.xCoord;
		yCoord = v.yCoord;
	}
	public Vector normalize(){
		double len = getDistance(ZERO, this);
		xCoord /= len;
		yCoord /= len;
		return this;
	}
	public Vector normalize(double d){
		return normalize().scalar(d);
	}

	public Vector scalar(double d){
		xCoord *= d;
		yCoord *= d;
		return this;
	}
	public Vector clone(){
		return new Vector(this);
	}
	public Vector add(Vector v){
		return create(xCoord + v.xCoord, yCoord + v.yCoord);
	}
	public Vector add(double x, double y){
		return create(xCoord + x, yCoord + y);
	}
	public Vector sub(Vector v){
		return create(xCoord - v.xCoord, yCoord - v.yCoord);
	}
	public double getAngle() {
	    return Math.atan2(getY(), getX());
	}

	//Setters
	public Vector setVec(double x, double y) {
		xCoord = x;
		yCoord = y;
		return this;
	}
	public Vector setVec(Vector vec) {
		xCoord = vec.getX();
		yCoord = vec.getY();
		return this;
	}
	public Vector setX(double x) {
		xCoord = x;
		return this;
	}
	public Vector setY(double y) {
		yCoord = y;
		return this;
	}
	public Vector addX(double x) {
		xCoord += x;
		return this;
	}
	public Vector addY(double y) {
		yCoord += y;
		return this;
	}
	
	//Getters
	public double getX() {
		return xCoord;
	}
	public double getY() {
		return yCoord;
	}
	public float getXF() {
		return (float)xCoord;
	}
	public float getYF() {
		return (float)yCoord;
	}
	public double getLength() {
		return Math.sqrt(xCoord * xCoord + yCoord * yCoord);
	}
	public double getLengthSq() {
		return xCoord * xCoord + yCoord * yCoord;
	}
	
	public String toString(){
		return "[" + xCoord + "," + yCoord + "]"; 
	}
	public void print(){
		System.out.print("[");
		System.out.printf("%-6.3f", xCoord);
		System.out.print(",");
		System.out.printf("%-6.3f", yCoord);
		System.out.print("]");
	}
	
	public boolean equals(Vector v){
		return xCoord == v.xCoord && yCoord == v.yCoord;
	}
	

}



