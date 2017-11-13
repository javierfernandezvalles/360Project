import java.util.LinkedList;

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


	@Override
	public String toString() {
		return "Averages [avgLines=" + avgLines + ", avgBlankLines=" + avgBlankLines + ", avgSpaces=" + avgSpaces
				+ ", avgWords=" + avgWords + ", avgAvgCharsPerLine=" + avgAvgCharsPerLine + ", avgAvgWordLength="
				+ avgAvgWordLength + "]";
	}
}
