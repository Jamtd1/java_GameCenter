package gameCenter;

//the Player class
public class Player {

	// The players name, this is protected instead of private
	// because we need to access it in the AdminPlayers getter
	protected String name;

	// The number of points they start with
	protected int points = 100;

	// The number of times they can continue if they
	// lose a game
	protected int continues = 3;

	// Constructor for the Player class which assigns a name
	public Player(String playerName) {
		name = playerName;
	}

	// getter to retrieve the amount of points belonging to a player
	public int getPoints() {
		return points;
	}

  public void addPoints(int pointsBet) {
    points += pointsBet;
  }

  public void subtractPoints(int pointsBet) {
    points -= pointsBet;
  }

  public void subtractContinue() {
    continues -= 1;
  }

	// Get the players name
	public String getName() {
		return name;
	}

	// does the player have a request number of points or more?
	public boolean hasNumberOfPoints(int numberOfPoints) {
		return points >= numberOfPoints;
	}
}
