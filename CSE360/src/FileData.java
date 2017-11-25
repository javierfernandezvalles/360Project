import java.lang.StringBuilder;



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
	

	
	public String stringtext(int a, int b, int c, int d, int e, int f, int g, int h) {	//Displays info depending on check marks selected
		StringBuilder builder = new StringBuilder();
		builder.append(" File Name: " + fileName + " | " + date) ;
		if(a == 1){builder.append("\n Number Of Lines = " + lines) ; } 
		if(b == 1){builder.append("\n Number Of Blank Lines = " + blankLines); } 
		if(c == 1){builder.append("\n Number Of Spaces = " + spaces); } 
		if(d == 1){builder.append("\n Number Of Words = " + words); }
		if(e == 1){builder.append("\n Average Characters Per Line = " + averageCharsPerLine); } 
		if(f == 1){builder.append("\n Average Word Length = " + averageWordLength); } 
		if(g == 1){builder.append("\n Most Common Word = " + mostCommon.word); } 
		if(h == 1){builder.append("\n Most Common Word Frequency = " + mostCommon.count); }
		return builder.toString();
	}
		
}
