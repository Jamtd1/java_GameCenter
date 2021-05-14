package gameCenter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class CointossGame extends Game {
  public CointossGame(Player newPlayer) {
    super(newPlayer);
  }

  public void play() {
    // Print the instructions on how to play the game
    printInstructions();
    while(player.continues > 0) {
      // Ask user how many points they wish to bet
      // printHowManyPointsToBet();

      try {
        // Get the number of points bet
        int amountBet = readAmount();

        if (amountBet <= 0) {
          printMustBetMoreThanZeroPoints();
          System.out.println("You must bet more than 0 points to play");
        } else if (player.hasNumberOfPoints(amountBet)) {
          // Player has enough points to play
          // Ask the user for heads or tails
          // Flip the coin and print the result
          // Tell the user how many points they won/lost
          // Ask if they want to play again
        } else {
          System.out.println("You only have " + player.getPoints() + " to bet");
        }


      } catch (IOException e) {
        // Error reading user input, ask to try again

      } catch (NumberFormatException e) {
        // Error converting input to integer, ask user to try again with an int
        printInvalidAmountError();
      }
    }
  }

  private void printInstructions() {

  }
}


