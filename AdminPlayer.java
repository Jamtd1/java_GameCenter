package gameCenter;

//This is the admin player class. An admin player
//should only be debugging the arcade machine and
//as such has near infinite continues
public class AdminPlayer extends Player {
	private int continues = 1000;
	public PlayerType type = PlayerType.Admin;

	public AdminPlayer(String playerName) {
		super(playerName);
	}

	// Return the players name with the admin
	// trailing text, used for the leaderboard
	public String getName() {
		return name + " (Admin)";
	}
}