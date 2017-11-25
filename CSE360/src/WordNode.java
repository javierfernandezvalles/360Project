// used to store words and their frequency in a given file
public class WordNode {
	
	String word;
	int count = 0;
	
	public WordNode(String word, int count){
		this.word = word;
		this.count = count;
	}
	
	public void increment(){
		count++;
	}
	
	
	// prints out the data of a word node
	public static void nodePrint(WordNode w){
		System.out.println("Word: " + "'" + w.word + "'" + " | Frequency: " + w.count);
	}

}
