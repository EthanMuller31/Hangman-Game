
public class Word
	{
		private String word;
		private int difficulty;
		
		public Word(String word, int difficulty) {
			this.word = word;
			this.difficulty = difficulty;
		}
		//Getters and Setters here
		
		// Getter for the word
	    public String getWord() {
	        return word;
	    }

	    // Setter for the word
	    public void setWord(String word) {
	        this.word = word;
	    }

	    // Getter for the difficulty
	    public int getDifficulty() {
	        return difficulty;
	    }

	    // Setter for the difficulty
	    public void setDifficulty(int difficulty) {
	        this.difficulty = difficulty;
	    }

	}
