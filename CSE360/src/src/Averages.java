import java.util.LinkedList;
import java.lang.StringBuilder;

// Used for computation of average metrics across all files
public class Averages {
	int avgLines = 0, avgBlankLines = 0, avgSpaces = 0,
	avgWords = 0, avgAvgCharsPerLine = 0, avgAvgWordLength = 0;
	
	
	public Averages() {
		// TODO Auto-generated constructor stub
	}


	public void compute(LinkedList<FileData> history){
		int s = history.size();
		int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0;
		for(int i = 0; i < s; i++){
			a += history.get(i).getLines();
			b += history.get(i).getBlankLines();
			c += history.get(i).getSpaces();
			d += history.get(i).getWords();
			e += history.get(i).getAverageCharsPerLine();
			f += history.get(i).getAverageWordLength();

		}
		if(s != 0){
			avgLines = a/s;
			avgBlankLines = b/s;
			avgSpaces = c/s;
			avgWords = d/s;
			avgAvgCharsPerLine = e/s;
			avgAvgWordLength = f/s;
		}
	}

	
	public String stringAvgtext(int a, int b, int c, int d, int e, int f) {
		StringBuilder builder = new StringBuilder();
		builder.append("Overall File Averages:");
		if(a == 1){builder.append("\nAverage Lines = " + avgLines) ; } 
		if(b == 1){builder.append("\nAverage Blank Lines =" + avgBlankLines ); } 
		if(c == 1){builder.append("\nAverage Spaces = " + avgSpaces); } 
		if(d == 1){builder.append("\nAverage Words = " + avgWords); }
		if(e == 1){builder.append("\nAverage Characters Per Line = " + avgAvgCharsPerLine); } 
		if(f == 1){builder.append("\nAverage Word Length = "+ avgAvgWordLength ); } 
		return builder.toString();
	}	
	
}