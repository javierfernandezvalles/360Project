// used to store the data of files that have already been processed 
public class FileData {
	private String fileName, date;
	private int lines, blankLines, spaces, words,
				averageCharsPerLine, averageWordLength;
	private WordNode mostCommon = null;
	
	
	public FileData (String fileName, String date){
		this.fileName = fileName;
		this.date = date;
	}


	public int getLines() {
		return lines;
	}


	public void setLines(int lines) {
		this.lines = lines;
	}


	public int getBlankLines() {
		return blankLines;
	}


	public void setBlankLines(int blankLines) {
		this.blankLines = blankLines;
	}


	public int getSpaces() {
		return spaces;
	}


	public void setSpaces(int spaces) {
		this.spaces = spaces;
	}


	public int getWords() {
		return words;
	}


	public void setWords(int words) {
		this.words = words;
	}


	public int getAverageCharsPerLine() {
		return averageCharsPerLine;
	}


	public void setAverageCharsPerLine(int averageCharsPerLine) {
		this.averageCharsPerLine = averageCharsPerLine;
	}


	public int getAverageWordLength() {
		return averageWordLength;
	}


	public void setAverageWordLength(int averageWordLength) {
		this.averageWordLength = averageWordLength;
	}


	public WordNode getMostCommon() {
		return mostCommon;
	}


	public void setMostCommon(WordNode mostCommon) {
		this.mostCommon = mostCommon;
	}


	public String getFileName() {
		return fileName;
	}
	
	public String getDate(){
		return date;
	}


	@Override
	public String toString() {
		return "\nFile Name = " + fileName + " | " + date + "\n Number Of Lines = " + lines + "\n Number Of Blank Lines = " + blankLines + "\n Number Of Spaces = "
				+ spaces + "\n Number Of Words = " + words + "\n Average Chars Per Line = " + averageCharsPerLine + "\n Average Word Length = "
				+ averageWordLength + "\n Most Common Word = " + mostCommon.word
				+ "\n Most Common Word Frequency = " + mostCommon.count;
	}
}
