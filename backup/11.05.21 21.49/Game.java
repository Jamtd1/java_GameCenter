package gameCenter;

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
}
