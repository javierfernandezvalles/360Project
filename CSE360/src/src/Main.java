import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Scanner;

import org.apache.commons.io.FileUtils; // used for copying the file's contents to a string

public class Main {

	// store history of files in an LL
	
	@SuppressWarnings({ "resource", "deprecation" })
	public static void main(String[] args) throws IOException {
		// use scanner to get input command
		Scanner scan = new Scanner(System.in);
		
		// use a linked list to store the data from every file processed
		LinkedList<FileData> history = new LinkedList<FileData>();
		
		// Calendar and DateFormat classes used for recording the time each
		// file was processed in the program. 
		Calendar cal;
		DateFormat fileDate = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
		String hist = new String();
		while (true) {
			System.out.println("Enter a command:");
			String command = scan.nextLine();
			
			
			if (command.equals("Read")) {
				System.out.println("Enter file name: ");

				// get file name
				String fileName = scan.nextLine();
				File h = new File(fileName);

				// if the file exists, process it. Else, print that it DNE
				if (h.exists()) {
					System.out.println("Processing data from: " + fileName);
					
					// read the file contents to a string 
					String fileContents = FileUtils.readFileToString(h);
					
					// make a LL of wordnodes of the fileContents that stores
					// every word in the file and the frequency at which it 
					// occurs in the file.
					// ***MUST DO THIS BEFORE THE OTHER FILE METRICS CAN BE USED***
					LinkedList<WordNode> L = FileMetrics.wordCount(fileContents);
					
					// get date of file processing, store in FileData node for the
					// history linked list
					cal = Calendar.getInstance();
					FileData newFileData = new FileData(fileName, fileDate.format(cal.getTime()));
					
					
					// Use the Linked List L and the String File Contents to calculate and store all the
					// file metrics in a FileData node
					newFileData.setAverageCharsPerLine((int) FileMetrics.averageCharsPerLine(L, fileContents));
					newFileData.setAverageWordLength((int) FileMetrics.averageWordLength(L));
					newFileData.setBlankLines(FileMetrics.totalEmptyLines(fileContents));
					newFileData.setLines(FileMetrics.totalLines(fileContents));
					newFileData.setMostCommon(FileMetrics.topWord(L));
					newFileData.setSpaces(FileMetrics.totalSpaces(fileContents));
					newFileData.setWords(FileMetrics.totalWords(L));

					System.out.println(newFileData.toString());
					// keep the data from the file stored in the history linked list
					// by storing a FileData data node in the history linked list
					history.add(newFileData);

				} else {
					System.out.println("File DNE");
				}

			}

			// traverse through the history linked list and
			// if nonempty, print each filename and the date
			// it was processed
			else if (command.equals("History")) {
				if (history.size() == 0) {
					System.out.println("No files have been processed.");
				} else {
					for (int i = 0; i < history.size(); i++) {
						
						hist.concat((history.get(i).getFileName() + " | " + history.get(i).getDate()));
					}
					System.out.println(hist);
				}
			}

			// displays all valid commands
			else if (command.equals("Help")) {
				System.out.println("\nThis is the help information, ");
				System.out.println("The following commands are valid: ");
				System.out.println("Read, History, Help, Exit");

			}

			// terminate the while loop when the user is finished
			else if (command.equals("Exit")) {
				break;
			}
			// invalid command entered
			else {
				System.out.println("Invalid command");
			}
		}
	}

}
