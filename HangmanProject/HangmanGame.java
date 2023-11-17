import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HangmanGame {
	//variables etc.
    private Word currentWord;
    private StringBuilder currentGuessState;
    private int incorrectGuesses;
    private int maxIncorrectGuesses;
    private boolean isGameWon;
    private List<Character> incorrectGuessesList;
    
    //wont allow duplicates in a set which is perfect for this case
    private Set<Character> guessedCharacters;

//Initializes here
    public HangmanGame(int maxIncorrectGuesses) {
        this.maxIncorrectGuesses = maxIncorrectGuesses;
        this.incorrectGuessesList = new ArrayList<>();
        this.guessedCharacters = new HashSet<>();
    }

//Setting up game and secret word also hides the word with '_'
    public void startNewGame(WordList wordList, int difficulty) {
        this.currentWord = wordList.getWordByDifficulty(difficulty);
        this.currentGuessState = new StringBuilder("-".repeat(currentWord.getWord().length()));
        this.incorrectGuesses = 0;
        this.isGameWon = false;
        this.incorrectGuessesList.clear();
        this.guessedCharacters.clear();

    }

//Checks the guess also handles case sensitivity, not allowing number guesses Example:('3'), and not allowing repeated guesses
    public void makeGuess(char guess) {
        guess = Character.toLowerCase(guess); // Convert guess to lower case

        // Check if the guess has already been made
        if (guessedCharacters.contains(guess)) {
            // Already guessed character handling
            return;
        }
        guessedCharacters.add(guess);

        String word = currentWord.getWord().toLowerCase(); // Convert word to lower case
        boolean isCorrect = false;

        // Check each character of the word
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                currentGuessState.setCharAt(i, currentWord.getWord().charAt(i));
                isCorrect = true;
            }
        }

        // If the guess is incorrect, increment incorrect guesses and add to the list
        if (!isCorrect) {
            incorrectGuesses++;
            incorrectGuessesList.add(guess);
        }

        // Check if the current guess state matches the word (case-insensitive)
        isGameWon = currentGuessState.toString().equalsIgnoreCase(currentWord.getWord());
    }

//boolean to check if game is over
    public boolean isGameOver() {
        return isGameWon || incorrectGuesses >= maxIncorrectGuesses;
    }
//getter for currentGuessState
    public String getCurrentGuessState() {
        return currentGuessState.toString();
    }
//Switch statements containing the ASCII art for the game display. Uses incorrectGuesses
    public String getHangmanDrawing() {
        switch (incorrectGuesses) {
            case 0:
                return "+---+\n" +
                       "    |\n" +
                       "    |\n" +
                       "    |\n" +
                       "   ===\n";
            case 1:
                return "+---+\n" +
                       "O   |\n" +
                       "    |\n" +
                       "    |\n" +
                       "   ===\n";
            case 2:
                return "+---+\n" +
                       "O   |\n" +
                       "|   |\n" +
                       "    |\n" +
                       "   ===\n";
            case 3:
                return "+---+\n" +
                       "O   |\n" +
                       "|   |\n" +
                       "|   |\n" +
                       "   ===\n";
            case 4:
                return "+---+\n" +
                       " O  |\n" +
                       "/|  |\n" +
                       "    |\n" +
                       "   ===\n";
            case 5:
                return "+---+\n" +
                       " O  |\n" +
                       "/|\\ |\n" +
                       "    |\n" +
                       "   ===\n";
            case 6:
                return "+---+\n" +
                       " O  |\n" +
                       "/|\\ |\n" +
                       "/   |\n" +
                       "   ===\n";
            case 7:
                return "+---+\n" +
                       " O  |\n" +
                       "/|\\ |\n" +
                       "/ \\ |\n" +
                       "   ===\n";
            default:
                return "Invalid state";
        }
    }
  
//Getter for currentWord
    public Word getCurrentWord() {
        return currentWord;
    }

//Boolean for win situations
    public boolean isGameWon() {
        return isGameWon;
    }
  
//Uses stream to get the String value of previous incorrect guesses and collects them into a list to be displayed
//Will also use to not allow user to guess the same thing twice. being able to guess twice brought up issues
    public String getIncorrectGuesses() {
        return incorrectGuessesList.stream()
                                   .map(String::valueOf)
                                   .collect(Collectors.joining(" "));
    }



    }
    



