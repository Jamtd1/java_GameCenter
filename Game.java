import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Game {
  Player player;
  int pointsBet = 0;

  // constructor for the Player class
  public Game(Player newPlayer) {
    player = newPlayer;
  }

  // Bet setter
  protected void takeBet(int bet) {
    pointsBet = bet;
  }

  // reads input from the buffer
  protected int readAmount() throws IOException {
    return Integer.parseInt(readLine());
  }

  // reads the nest line of the input stream
  protected String readLine() throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    return reader.readLine();
  }

  // If there is an error in the amount print an error
  protected void printInvalidAmountError() {
    System.out.println("That doesn't seem to be a valid amount, please enter a single integer value, e.g. 1 or 10");
  }

  // if bet amount = 0 then error
  protected void printMustBetMoreThanZeroPoints() {
    System.out.println("You must bet more than 0 points to play");
  }


  protected void playerWon(Player player, int amountBet) {
    player.addPoints(amountBet);
  }

  protected void playerLost(Player player, int amountBet) {
    player.subtractPoints(amountBet);
    player.subtractContinue();
  }

  protected Boolean handleUnrecognizedInput() {
     System.out.println("Hmm, not sure what you mean there, do you want to back out? (y/n)");
     return getYesNo();
  }

  protected Boolean getYesNo() {
    try {
      String option = readLine();
      return option.toLowerCase().equals("y");
    } catch (IOException e) {
      return false;
    }
  }

  protected void printHowManyPointsToBet(Player player) {
    System.out.println("How many points would you like to bet? You have " + player.getPoints() + " to bet. Mind your amount must be an integer too!");
  }
}
