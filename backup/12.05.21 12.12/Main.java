package gameCenter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

enum MenuOption {
	Hangman,
	Cointoss,
	Leaderboard,
	Return,
	UnrecognizedInput
}

enum MainMenu {
	NewPlayer,
	Quit,
	UnrecognizedInput
}

public class Main {
	public static void main(String[] args) throws IOException {
		// Get a Leaderboard class so we can show the player on demand
		Leaderboard leaderboard = new Leaderboard();

		String mainMenu = "continue";
		while (mainMenu.equals("continue")) {
			
			// print the main menu
			printMainMenu();
			
			MainMenu mainOption = readMainOption();
			
			if (mainOption == MainMenu.NewPlayer) {
				// print the starting message
				printWelcome();
		
				try {
				// Create a new player
				Player player = getPlayer();
				System.out.println("player type: " + player.getClass().getSimpleName());
		
				// Once created print a message
				userCreated(player);
		
					while(player.continues > 0) {
					// Now that we have our player tell them the options
					printOptions();
		
					// Read in the option
					MenuOption option = readOption();
		
						// Depending on what the user selected start a game,
						// retire or show the leaderboard
						if (option == MenuOption.Return) {
							// Setting continues to 0 will trigger the if statement below
							// and exit the while loop
							System.out.println("Thanks for playing, goodbye");
							player.continues = 0;
						} else if (option == MenuOption.Leaderboard) {
							System.out.println("Leaderboard");
							leaderboard.print();
						} else if (option == MenuOption.Hangman) {
							// start the hangman game
							// HangmanGame hangman = new HangmanGame(player);
							System.out.println("Lets play hangman");
						} else if (option == MenuOption.Cointoss) {
							// start the hangman game
							CointossGame cointoss = new CointossGame(player);
							cointoss.play();
							System.out.println("Lets play coin toss");
						} else if (option == MenuOption.UnrecognizedInput) {
							printUnrecognisedMenuOption();
						}
		
						if (player.continues == 0) {
							System.out.println("Your out of continues!! Let's see how you did!'");
							// user has run out of continues
							// Add their name to the leaderboard
							// Display the leaderbaord
							// Say goodbye
							//
							leaderboard.addPlayer(player);
							leaderboard.print();
							// printGoodbye();
						}
					}
				} catch(Exception e) {
					System.out.println("An error was thrown!");
					System.out.println(e);
				}
			} else if (mainOption == MainMenu.Quit){
				mainMenu = "quit";
				leaderboard.print();
			} else if (mainOption == MainMenu.UnrecognizedInput) {
				printUnrecognisedMenuOption();
			}
			
		}
	}

	private static String readLine() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		return reader.readLine();
	}

	private static MainMenu readMainOption() throws IOException {
		String mainOption = readLine();

		System.out.println("Option just read is:");
		System.out.println(mainOption);

		if (mainOption.equals("1")) {
			return MainMenu.NewPlayer;
		} else if (mainOption.equals("q")) {
			return MainMenu.Quit;
		} else {
			return MainMenu.UnrecognizedInput;
		}
	}
	
	private static MenuOption readOption() throws IOException {
		String option = readLine();

		System.out.println("Option just read is:");
		System.out.println(option);

		if (option.equals("1")) {
			return MenuOption.Hangman;
		} else if (option.equals("2")) {
			return MenuOption.Cointoss;
		} else if (option.equals("l")) {
			return MenuOption.Leaderboard;
		} else if (option.equals("r")) {
			return MenuOption.Return;
		} else {
			return MenuOption.UnrecognizedInput;
		}
	}
	

	private static Player getPlayer() throws IOException {
		// Create a buffered reader to
		String name = readLine();

		System.out.println("And are you a an Admin (y/n)?");

		String type = readLine();
		type = type.toLowerCase();
		System.out.println(type);
		// change the below to look for a passcode if admin
		if(type.equals("y")){
			System.out.println("Player is admin");
			return new AdminPlayer(name);
		} else {
			System.out.println("Player is a basic player");
			return new BasicPlayer(name);
		}
	}

	private static void printUnrecognisedMenuOption() {
	System.out.println("Sorry I don't recognise that input, can you try another from the list?");
	}

	private static void userCreated(Player player) {
		System.out.println("Great welcome to the fun " + player.getName() + "!");
		System.out.println("Now in order to play our games you will need to bet points, the more points you bet the greater the reward!");
		System.out.println("However if you loose all your points OR loose at 3 games you'll be out!'");
		System.out.println("Then your score will be recorded and the you'll see how you match up on the leaderboard!'");
		System.out.println("Good Luck!");
	}

	private static void printWelcome() {
		System.out.println("Welcome to Mischief Managed Arcade!");
		System.out.println("Firstly let's get your name so you can start playing!");
		System.out.println("So what is your name?");
	}

	private static void printOptions() {
		System.out.println("What do you want to do?");
		System.out.println("1). Good gusser? Try your brain on hangman!");
		System.out.println("2). True luck, bet your points on a coin toss!");
		System.out.println("l). View the leaderboard");
		System.out.println("r). Return");
	}
	
	private static void printMainMenu() {
		System.out.println("Welcome to Mischief Managed Arcade!");
		System.out.println("Please select from the options below");
		System.out.println("how you wish to proceed!");
		System.out.println("Main Menu");
		System.out.println("1). New Player");
		System.out.println("q). Quit");
		
	}
}

// class Game {
//   Player player;
//   int pointsBet;
//
//   public Game(Player newPlayer, int bet) {
//     player = newPlayer;
//     pointsBet = bet;
//   }
// }


// class Leaderboard {
//    File file;
//
//    private Leaderboard() {}
//
//    public void addPlayersScore(Player player) {}
//    public void print() {}
// }
