
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WordList wordList = new WordList();
        populateWordList(wordList);
        HangmanGame game = new HangmanGame(6); // Assuming max 6 incorrect guesses

        while (true) {
            System.out.println("\nWelcome to my Hangman Game!\nThe rules are simple...\n");
            System.out.println("1.You have 6 incorrect guesses allowed");
            System.out.println("2.Youare trying to guess the scret word");
            System.out.println("3.You can only guess one letter at a time\nGood Luck!\n_________________________________________\n");
            System.out.print("Please choose difficulty (1-Easy, 2-Medium, 3-Hard): ");
            int difficulty = scanner.nextInt();
            
            game.startNewGame(wordList, difficulty);
            
            //While the game isn't over gets the game display
            while (!game.isGameOver()) {
                System.out.println(game.getHangmanDrawing());
                System.out.println("Current word: " + game.getCurrentGuessState());
                System.out.println("Wrong guesses: " + game.getIncorrectGuesses());
                System.out.print("Enter your guess: ");
                String input = scanner.next();

                // Check if the input is a single letter
                if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                    char guess = input.charAt(0);
                    game.makeGuess(guess);
                } else {
                    System.out.println("Invalid input. Please enter a single letter.");
                    continue; // Skip to the next iteration of the loop
                }
            }

            //if statement for which case the game ended with and displays result
            if (game.isGameWon()) {
            	System.out.println("");        
                System.out.println("Congratulations, you won!");
                System.out.println(game.getHangmanDrawing());
                System.out.println("Current word: " + game.getCurrentGuessState());
                System.out.println("Wrong guesses: " + game.getIncorrectGuesses());
                System.out.print("Enter your guess: ");
                System.out.println("");
            } else {
                System.out.println("Game over! The correct word was: " + game.getCurrentWord().getWord());
            }


            // Display the result of the game
            // ...

            System.out.print("Do you want to play again? (y/n): ");
            String playAgain = scanner.next();
            System.out.println("_________________________________________");
            if (!playAgain.equalsIgnoreCase("y")) {
                break;
            }
        }
        scanner.close();
    }
//This contains all the possible words that you could have to guess can add more or remove some
    private static void populateWordList(WordList wordList) {
        // Add words to the wordList
    	wordList.addWord("CAT", 1);
		wordList.addWord("OCEAN", 2);
		wordList.addWord("JUXTAPOSE", 3);
		wordList.addWord("SUN", 1);
		wordList.addWord("GUITAR", 2);
		wordList.addWord("QUIZZICAL", 3);
		wordList.addWord("BALL", 1);
		wordList.addWord("CASTLE", 2);
		wordList.addWord("ZEPHYR", 3);
		wordList.addWord("TREE", 1);
		wordList.addWord("PUZZLE", 2);
		wordList.addWord("HYPHENATE", 3);
		wordList.addWord("BOOK", 1);
		wordList.addWord("JUNGLE", 2);
		wordList.addWord("GOSSAMER", 3);
        // ... add more words
    }
}
