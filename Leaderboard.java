package gameCenter;

import java.util.ArrayList;
import java.io.*;
import java.io.IOException;

public class Leaderboard {
	
	public Leaderboard () throws IOException {
		
		// for advanced marks this needs to check for a file  first
		File file = new File("leaderboard.txt");
		if (!file.exists()) {
			if (file.createNewFile()) {
			System.out.println("File created.");
			} else {
			System.out.println("File was not created.");
			}
		}
	}
	
	// printLeaderBoard
	public void print() throws IOException {
		// get the leaderboard list
		ArrayList<String> leaderboard = this.getLeaderboard();
		// print each index of the leaderboard
		System.out.println("Leader Board");
		for (int i = 0; i < leaderboard.size(); i++) {
			System.out.println(leaderboard.get(i));
		}
	}
	
	// addPlayer		
	public void addPlayer(Player player) throws IOException {
		String name = player.getName();
		int points = player.getPoints();
		
		// addPlayerData
		ArrayList<String> leaderboard = this.addPlayerData(name, points);
		// write leaderboard to file
		this.writeLeaderboardtoFile(leaderboard);
		
	}

	private ArrayList<String> getLeaderboard() throws IOException {
		// load the file
		File leaderboardFile = new File("leaderboard.txt");
		
		// Read the entire file;
		BufferedReader reader = new BufferedReader(new FileReader(leaderboardFile));
		String line = null;
		
		ArrayList<String> leaderboard = new ArrayList<String>();
		
		while ((line = reader.readLine()) != null) {
			leaderboard.add(line);
		}

		reader.close();
		
		// return leaderboard array;
		return leaderboard;
	
	}
	
	private ArrayList<String> addPlayerData(String name, int points) throws IOException {
		
		// get the player data
		String playerInfo = name + " , " + Integer.toString(points);
		
		// get the leaderboard
		ArrayList<String> leaderboard = this.getLeaderboard();
		
		// get number of points player has
		// for each index in the array assign string to temporary variable
		if (leaderboard.isEmpty()) {
			leaderboard.add(0,playerInfo);
		} else {
			for (int i = 0; i<leaderboard.size(); i++) {
				String temp = leaderboard.get(i);
				temp = temp.substring(temp.lastIndexOf(',') + 1).trim();
				int leaderPoints = Integer.parseInt(temp);
				// compare with each line
				// if points greater than points at line
				if (leaderPoints <= points) {
					// add player info string the line index
					leaderboard.add(i, playerInfo);
					break;
				} else if (i == (leaderboard.size()-1)) {
					leaderboard.add(playerInfo);
					break;
				}
			}
		}
		return leaderboard;
	}
	
	private void writeLeaderboardtoFile (ArrayList<String> leaderboard) throws IOException {
		
		File f = new File ("leaderboard.txt");
		PrintWriter out = new PrintWriter (new FileOutputStream(f));
		if (!f.exists()) {
			f.createNewFile();
		}
		for (String s : leaderboard) {
			out.println(s);
		}
		out.close();
	}
	
	
}



