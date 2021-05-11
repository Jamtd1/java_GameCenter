import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

enum PlayerChoice {
  Heads,
  Tails,
  UnrecognizedInput
}

public class CointossGame extends Game {
  public CointossGame(Player newPlayer) {
    super(newPlayer);
  }

  public void play() {
    // Check if we should continue playing or exit
    Boolean shouldContinue = true;

    // Print the instructions on how to play the game
    this.printInstructions();

    while(shouldContinue) {
      // Ask user how many points they wish to bet
      this.printHowManyPointsToBet(player);

      try {
        // Get the number of points bet
        int amountBet = this.readAmount();

        if (amountBet <= 0) {
          this.printMustBetMoreThanZeroPoints();
          System.out.println("You must bet more than 0 points to play");

        } else if (player.hasNumberOfPoints(amountBet)) {
          this.printOptions();

          PlayerChoice choice = this.getPlayerChoice();

          if (choice == PlayerChoice.UnrecognizedInput) {
            // Invalid input, check if user wants to continue, if yes then break
            if (this.handleUnrecognizedInput())  {
              shouldContinue = false;
            } else {
              break;
            }
          }

          PlayerChoice result = this.flipCoin();

          if (result == choice) {
            this.playerWon(player, amountBet);
          } else {
            this.playerLost(player, amountBet);
          }

          if (player.continues > 0) {
            System.out.println("Want to give it another shot? (y/n)");
          }

          if (!getYesNo()) {
            shouldContinue = false;
          }
        } else {
          System.out.println("You only have " + player.getPoints() + " to bet");
        }


      } catch (IOException e) {
        // Error reading user input, ask to try again
        if (this.handleUnrecognizedInput()) {
         shouldContinue = false;
        } else {
          break;
        }
      } catch (NumberFormatException e) {
        // Error converting input to integer, ask user to try again with an int
        this.printInvalidAmountError();
      }

      if (player.continues <= 0) {
        shouldContinue = false;
      }
    }
  }

  private PlayerChoice getPlayerChoice() throws IOException {
    String option = readLine();

    if (option.equals("1")) {
      return PlayerChoice.Heads;
    } else if (option.equals("2")) {
      return PlayerChoice.Tails;
    } else {
      return PlayerChoice.UnrecognizedInput;
    }
  }

  private PlayerChoice flipCoin() {
    if (Math.random() > 0.5) {
      return PlayerChoice.Heads;
    } else {
      return PlayerChoice.Tails;
    }
  }

  protected void playerWon(Player player, int amountBet) {
    super.playerWon(player, amountBet);
    System.out.println("You WON! Your total points are now: " + player.getPoints());
  }

  protected void playerLost(Player player, int amountBet) {
    super.playerLost(player, amountBet);
    System.out.println("Ahhhh too bad, better luck next time! Your current points are: " + player.getPoints());
  }

  private void printInstructions() {
    System.out.println("Ok ok, so it couldn't be simpler, you have two options, put your points down on one and roll the dice! .... or flip a coin I guess...");
  }

  private void printOptions() {
    System.out.println("Ok then, heads or tails?");
    System.out.println("1). Heads, it's always heads!");
    System.out.println("2). Tails, that's not how probablity works!");
  }
}
