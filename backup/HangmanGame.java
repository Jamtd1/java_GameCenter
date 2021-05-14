import java.io.IOException;

public class HangmanGame extends Game {
  String phrase;
  String hiddenPhrase;

  public HangmanGame(Player player) {
    super(player);
  }

  public void play() {
    // Print the instructions for the game
    this.printInstructions();

    while(this.shouldContinue) {
      // Ask user how many points they wish to bet
      this.printHowManyPointsToBet(this.player);

      try {
        // Get the number of points bet
        int amountBet = this.readAmount();

        if (amountBet <= 0) {
          this.printMustBetMoreThanZeroPoints();
          System.out.println("You must bet more than 0 points to play");

        } else if (player.hasNumberOfPoints(amountBet)) {
          // Get a phrase for this game
          this.phrase = this.getPhrase().toLowerCase();
          // Blank out the phrase for the hidden phrase the user will see
          this.hiddenPhrase = this.phrase.replaceAll("[a-zA-Z]", "_");

          // Set the users number of lives
          int lives = 3;

          while (lives >= 1) {
            this.printPhrase();
            String guess = this.readLine().toLowerCase();

            if (this.phrase.indexOf(guess) == -1) {
              lives--;
              this.wrongGuess(lives);
            } else {
              this.rightGuess(lives);

              for (
                int index = this.phrase.indexOf(guess); // set index to the first occurrance
                index >= 0; // as long as it's not -1 there is another of the same character
                index = this.phrase.indexOf(guess, index + 1) // set the index to the next index of the char
              ) {
                // Convert the string to a list of characters so we can replace at index
                char[] chars = this.hiddenPhrase.toCharArray();

                // Change the character at the index it was found in the original phrase
                chars[index] = guess.charAt(0);

                // Re-create the string from the char list with the guess values revealed
                this.hiddenPhrase = String.valueOf(chars);
              }
            }

            // Check if there are any hidden characters left
            // if not the player won
            if (this.hiddenPhrase.indexOf("_") < 0) {
              this.playerWon(this.player, amountBet);
              break;
            }

            // Check if the users has lost the game and if they want to go again
            if (lives <= 0) {
              this.playerLost(this.player, amountBet);
              if (!getYesNo()) {
                this.shouldContinue = false;
              }
            }
          }

          System.out.println("Would you like to continue? (y/n)");
          if (!getYesNo()) {
            this.shouldContinue = false;
          }
        }

      } catch (IOException e) {
        // Error reading user input, ask to try again
        if (this.handleUnrecognizedInput()) {
          continue;
        } else {
          this.shouldContinue = false;
          break;
        }
      } catch (NumberFormatException e) {
        // Error converting input to integer, ask user to try again with an int
        this.printInvalidAmountError();
      }

      // Check if the player wishes to go again
      if (player.continues <= 0) {
        this.shouldContinue = false;
      }
    }
  }

  private void rightGuess(int lives) {
    System.out.println("Correct! You have " + lives + " left");
  }

  private void wrongGuess(int lives) {
    System.out.println("Ohh not quite! You have " + lives + " left");
  }

  protected void playerWon(Player player, int amountBet) {
    super.playerWon(player, amountBet);
    System.out.println("You got it! Your total points are now: " + player.getPoints());
  }

  protected void playerLost(Player player, int amountBet) {
    super.playerLost(player, amountBet);
    System.out.println("Good try but your out of lives! The phrase was " + this.phrase);
    System.out.println("You now have " + player.continues + " continues and " + player.getPoints() + " points");
    System.out.println("Would you like to go again? (y/n)");
  }

  private void printPhrase() {
    System.out.println("The phrase is " + this.hiddenPhrase);
  }

  private void printInstructions() {
    System.out.println("The easiest game in the world, I've got a phrase in mind, you have to guess it.");
    System.out.println("Each time you correctly guess a letter I'll reveal it. Each time you fail you'll loose a limb!");
    System.out.println(".... or probably just a life. But lose all 3 of your lives and it's game over. Guess the entire phrase and you win!");
  }

  private String getPhrase() {
    // Generate a random number between 0 & 9 (i.e. 1 - 10)
    int randomIndex = (int) (Math.random() * 10);

    // The list of phrases that can be returned
    String[] phrases = {
      "Test Phrase",
      "Sunny sky in morning shepards warning",
      "Sunny sky at night shepards delight",
      "A dime a dozen",
      "Chip on the shoulder",
      "A piece of cake",
      "An arm and a leg",
      "Back to sqaure one",
      "Barking mad",
      "With a grain of salt"
    };

    // Return a phrase at a random index for this game
    return phrases[randomIndex];
  }
}
