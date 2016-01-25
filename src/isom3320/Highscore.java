package isom3320;

import java.util.ArrayList;
import java.util.Collections;

public class Highscore {
	
	public static ArrayList<Integer> scores = new ArrayList<Integer>();
	public static int killScore, timeScore;

	public static void addScore(int killScore, int timeScore){
		Highscore.killScore = killScore;
		Highscore.timeScore = timeScore;
		scores.add(getScore());
		process();
	}
	
	public static int getScore(){
		return killScore + timeScore;
	}
	
	public static void process(){
		Collections.sort(scores);
		Collections.reverse(scores);
	}
	
	public static int getNumScores(){
		return scores.size();
	}
	
	public static int get(int i){
		return scores.get(i);
	}
}
