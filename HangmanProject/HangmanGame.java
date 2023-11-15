import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HangmanGame {
    private Word currentWord;
    private StringBuilder currentGuessState;
    private int incorrectGuesses;
    private int maxIncorrectGuesses;
    private boolean isGameWon;
    private List<Character> incorrectGuessesList;
    private Set<Character> guessedCharacters;


    public HangmanGame(int maxIncorrectGuesses) {
        this.maxIncorrectGuesses = maxIncorrectGuesses;
        this.incorrectGuessesList = new ArrayList<>();
        this.guessedCharacters = new HashSet<>();
    }


    public void startNewGame(WordList wordList, int difficulty) {
        this.currentWord = wordList.getWordByDifficulty(difficulty);
        this.currentGuessState = new StringBuilder("_".repeat(currentWord.getWord().length()));
        this.incorrectGuesses = 0;
        this.isGameWon = false;
        this.incorrectGuessesList.clear();
        this.guessedCharacters.clear();

    }

    public void makeGuess(char guess) {
        guess = Character.toLowerCase(guess); // Convert guess to lowercase

        // Check if the guess has already been made
        if (guessedCharacters.contains(guess)) {
            // Already guessed character handling
            return;
        }
        guessedCharacters.add(guess);

        String word = currentWord.getWord().toLowerCase(); // Convert word to lowercase
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


    public boolean isGameOver() {
        return isGameWon || incorrectGuesses >= maxIncorrectGuesses;
    }

    public String getCurrentGuessState() {
        return currentGuessState.toString();
    }

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
    
    public Word getCurrentWord() {
        return currentWord;
    }
    
    public boolean isGameWon() {
        return isGameWon;
    }
    
    public String getIncorrectGuesses() {
        return incorrectGuessesList.stream()
                                   .map(String::valueOf)
                                   .collect(Collectors.joining(" "));
    }



    }
    



