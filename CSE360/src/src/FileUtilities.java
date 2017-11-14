
public class FileUtilities {
	private FileUtilities(){
	}
	
	// removes punctuation from the analysis
	public static String removePunctuation(String fileText){
		String noPunctuation = "";
		for(char c : fileText.toCharArray()){
			// if c is alphanumeric or a space, keep it in the string
			if((c >= 48 && c <= 57) || 
					(c >= 65 && c <= 90) || 
					(c >= 97 && c <= 122) ||
					(c == 32)){
				noPunctuation = noPunctuation + c;

			}
		}
		return noPunctuation;
	}
	
			
}
