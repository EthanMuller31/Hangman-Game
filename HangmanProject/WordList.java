import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WordList
	{
		//private for getters and setters
		//Used list to make code more flexible this stores the words
		private List<Word> words;
		
		//Constructor that initializes the 'words' list
		public WordList() {
			words = new ArrayList<>();
		}
		//Fills with the words
		public void addWord(String word, int difficulty) {
			Word newWord = new Word(word, difficulty);
			words.add(newWord);
		}
		
		//Method to get word by specific difficulty
		//Uses java stream to organize list
		//Conveyer belt that goes through each word to create a list of words all same difficulty
		public Word getWordByDifficulty(int difficulty) {
			List<Word> filteredWords = words.stream()
					//checks if difficulty matches
					.filter(word -> word.getDifficulty() == difficulty)
					//collects the elements that make it past the filter into a list aka filteredWords
					.collect(Collectors.toList());
			
			if(filteredWords.isEmpty()) {
				//incorrect user input, put a 4 in or something
				return null;
			}
			//Gets a random word to add some randomization feel to the game
			Random random = new Random();
			int index = random.nextInt(filteredWords.size());
			return filteredWords.get(index);
			
		}

	}
