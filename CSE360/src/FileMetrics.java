import java.util.LinkedList;

public final class FileMetrics {
	private FileMetrics() {
	}

	// creates a linked list containing all the words in the file
	// and their frequency
	// ****Any new file must have its contents passed through here first!!*****
	public static LinkedList<WordNode> wordCount(String fileText) {

		// create a LL to store words and their frequency of occurrence
		LinkedList<WordNode> list = new LinkedList<WordNode>();
		WordNode tempNode = null; // used to iterate through list
		boolean isInList = false;
		int index = 0;

		// iterate through each word in the file
		for (String tW : fileText.split(" ")) {

			// reset values for the new word
			isInList = false;
			tempNode = null;
			index = 0;

			// remove any punctuation from the word
			String tempWord = FileUtilities.removePunctuation(tW);

			// search list for word tempWord in LL
			for (index = 0; index < list.size(); index++) {
				tempNode = list.get(index);

				// if word is in the list, make the boolean true
				// and break the loop
				if (tempNode.word.equals(tempWord)) {
					isInList = true;
					break;
				}

			}

			// if the word is already in the list, increment the count
			if (isInList) {
				tempNode.increment();
				list.set(index, tempNode);
			}

			// else, add it to the end of the list, initialize count to 1
			else {
				tempNode = new WordNode(tempWord, 1);
				list.addLast(tempNode);
			}

		}
		
		// return the list of all words and their frequency
		return list;
	}
	
	// counts and returns the number of empty lines
	public static int totalEmptyLines(String fileText){
		String lines[] = fileText.split("\\n");
		int blanks = 0;
		
		// iterate through each line of the code
		for(int i = 0; i < lines.length; i++){
			// check for vert tab
			if(lines[i].equals(Character.toString((char) 13))){
				blanks++;
			}
		}	
		// adjust for one-off error if there are blank lines
		if(blanks > 0){
			blanks++;
		}
		return blanks;
	
	}

	// counts the total lines in the program
	public static int totalLines(String fileText) {
		int lines = 1;
		for (char c : fileText.toCharArray()) {
			// if c line break, increment line count
			if (c == '\n') {
				lines++;
			}
		}
		return lines;
	}

	// sums up total amount of characters in the file, not including spaces
	public static int totalCharacters(LinkedList<WordNode> list) {
		int sum = 0;
		// sum up word length*frequency and divide by total words
		for (int i = 0; i < list.size(); i++) {
			sum += FileUtilities.removePunctuation(list.get(i).word).length() * list.get(i).count;
		}
		return sum;
	}

	// returns total words in the file by summing the frequencies in the ll
	public static int totalWords(LinkedList<WordNode> list) {
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i).count;
		}

		return sum;

	}
	
	// counts total spaces in the file
	public static int totalSpaces(String fileText) {
		int spaces = 0;
		for (char c : fileText.toCharArray()) {
			// if c linebreak, increment line count
			if (c == ' ') {
				spaces++;
			}
		}
		return spaces;
	}

	// returns most common word
	public static WordNode topWord(LinkedList<WordNode> list) {
		WordNode temp = null;
		WordNode MAX = new WordNode("", 0);
		for (int i = 0; i < list.size(); i++) {
			temp = list.get(i);
			if (temp.count > MAX.count) {
				MAX = temp;
			}
		}
		return MAX;
	}

	// returns average word length
	public static float averageWordLength(LinkedList<WordNode> list) {
		return (float) totalCharacters(list) / totalWords(list);

	}
	
	public static float averageCharsPerLine(LinkedList<WordNode> list, String fileText){
		return (float) totalCharacters(list)/totalLines(fileText);
	}
	

}
