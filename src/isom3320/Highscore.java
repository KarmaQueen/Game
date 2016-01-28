package isom3320;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Highscore {
	
	public static ArrayList<Integer> scores = new ArrayList<Integer>(10);
	
	public static int killScore, timeScore;
	
	private static URL url;
	private static File file;

	public static void addScore(int killScore, int timeScore){
		Highscore.killScore = killScore;
		Highscore.timeScore = timeScore;
		
		if(Collections.min(scores) < getScore()){
			int minIndex = scores.indexOf(Collections.min(scores));
			scores.set(minIndex, getScore());
		}
		process();
	}
	
	public static int getScore(){
		return killScore + timeScore;
	}
	
	public static void process(){
		Collections.sort(scores);
		Collections.reverse(scores);
	}
	
	public static int get(int i){
		return scores.get(i);
	}
	
	public static void readFromFile(){
		try {
			url = Main.class.getResource("/data/scores.txt");
			file = new File(url.getPath());
			Scanner sc = new Scanner(file);
			
			while(sc.hasNextLine())
				Highscore.scores.add(Integer.parseInt(sc.nextLine()));
			
			sc.close();
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeToFile(){
		try {
			url = Main.class.getResource("/data/scores.txt");
			file = new File(url.getPath());
			file.delete();
			
			file = new File(url.getPath());
			try{
				file.createNewFile();
			}
			catch(IOException ex){
		        System.out.println (ex.toString());
		        System.out.println("Could not find file");
		    }
			
			PrintWriter pw = new PrintWriter(file);
			for(int i = 0; i < scores.size(); i++)
				pw.println(scores.get(i));
			
			pw.close();
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static int getNumScores() {
		return scores.size();
	}
}
