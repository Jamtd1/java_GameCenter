package gameCenter;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;

public class Leaderboard {
	
	public Leaderboard () {
		
		// for advanced marks this needs to check for a file  first
		
		try {
		      File leaderboard = new File("leaderboard.txt");
		      if (leaderboard.createNewFile()) {
		        System.out.println("File created.");
		      } else {
		        System.out.println("File was not created.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	private String loadFile(String fileName) {
		
		try {
			
			File file = new File(fileName);
			
			if (!file.exists()) {
				return "Error: that file does not exist. Please try again.";
			} else {
			
				BufferedReader reader = new BufferedReader(new FileReader(fileName));
				StringBuilder stringBuilder = new StringBuilder();
				String line = null;
				String ls = System.getProperty("line.separator");
				int countLines = 0;
				
				while ((line = reader.readLine()) != null) {
					countLines ++;
					stringBuilder.append(line);
					stringBuilder.append(ls);
				}
				// delete the last new line separator
				stringBuilder.deleteCharAt(stringBuilder.length() - 1);
				reader.close();
				
				// Create a string from the file information
				String board = stringBuilder.toString();
				
				// return the string created 
				return board;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "Error";
		}
	}

	public String getLeaderboard() {
		// load the file
		String leaderboardFile = loadFile("leaderboard.txt");
		// Read the entire file;
		return leaderboardFile;
		
	}
	
	public int getIndexForPlayerScore() {
		// get number of points player has
		
		// compare with each line
		
		// if points greater than points at line
		
			// return the line index
		
		// else 
		
			// move onto the next line
		return 1;
	}
	
	public void putPlayerScoreInIndex(int index) {
		
	}
	
	public void newPosition(Player player) {
		// create a new leaderboard position
		String name = player.getName();
		int points = player.getPoints();
		
		// Get the leaderboard
	    getLeaderboard();
		// figure out the position of the player in the leaderboard
		int index = getIndexForPlayerScore();
		// Insert player at that position
		putPlayerScoreInIndex(index);
		
	}
//	
	// add new position to leaderboard
	// pass the position to a leaderboard file
	
	
		
		
	// create a leaderboard file if none exist
	
	// read in the leaderboard file 
	
}


