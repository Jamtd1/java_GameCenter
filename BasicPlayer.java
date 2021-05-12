package gameCenter;

//Define the basic player class, most users will be
//basic players as the "admin" player is meant for
//debugging issues with the arcade machine
public class BasicPlayer extends Player {
	public PlayerType type = PlayerType.Basic;
	
	public BasicPlayer(String playerName) {
		super(playerName);
	}
}
