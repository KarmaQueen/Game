package isom3320;

import java.util.Random;

public class MathHelper {
	
	public static final double inv2PI = 0.15915494309189533576888376337251;
	public static final double invPI = 0.31830988618379067153776752674503D;
	public static final double toRad = 0.03490658503988659153847381536977;
	public static double randomWithRange(double min, double max)
	{
	   double range = (max - min);     
	   return (double)(Math.random() * range) + min;
	}
	public static Random rand;
}
