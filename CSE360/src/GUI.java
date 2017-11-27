import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;
import javax.swing.border.SoftBevelBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class GUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
			    { 
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); // Sets over all theme
					GUI window = new GUI();
					window.frame.setVisible(true);
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public static class Global{
		static String[] fileName;
		static File[] h;
		static FileData newFileData;
		static int NumLinesStat = 1, NumBlankStat = 1, NumSpacesStat = 1, NumWordsStat = 1, //Sets the status of check marks to 1 (checked) by default
				AvgCharStat = 1, AvgWordLthStat = 1, MostCmnStat = 1, MstCmnWrdFrqStat = 1;
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1050, 498);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Text File Analyzer");

		
		// use a linked list to store the data from every file processed
		// history linked list
		LinkedList<FileData> history = new LinkedList<FileData>();
		// Calendar and DateFormat classes used for recording the time each
		// file was processed in the program. 
		DateFormat fileDate = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
		
		JFileChooser fileChooser = new JFileChooser();					//Creates JfileChooser
		fileChooser.setDialogTitle("");
		fileChooser.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		fileChooser.setApproveButtonText("Open");
		fileChooser.setFont(UIManager.getFont("EditorPane.font"));
		FileFilter filter = new FileNameExtensionFilter("Text","txt");	 //Filter only allows Text files to be selected
		fileChooser.setFileFilter(filter);
		fileChooser.setMultiSelectionEnabled(true);

		JTextArea textArea = new JTextArea();							//JTextAea OutPut Text Area
		textArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textArea.setEditable(false);
		textArea.setText(" Please Select File(s)\n ");
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(15, 16, 665, 353);
		frame.getContentPane().add(scrollPane);

		
		JCheckBox NumLines = new JCheckBox("Number Of Lines");			 //Number Of Lines Check box
		NumLines.setSelected(true);
		NumLines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (NumLines.isSelected() == true) { Global.NumLinesStat = 1; }
				else { Global.NumLinesStat = 0;  }
			}
		});
		NumLines.setBackground(Color.LIGHT_GRAY);
		NumLines.setFont(new Font("Times New Roman", Font.BOLD, 20));
		NumLines.setBounds(702, 16, 303, 29);
		frame.getContentPane().add(NumLines);
		
		JCheckBox NumBlank = new JCheckBox("Number of Blank Lines"); 	//Number Of Blank Lines Check Box
		NumBlank.setSelected(true);	
		NumBlank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (NumBlank.isSelected() == true) { Global.NumBlankStat = 1; }
				else { Global.NumBlankStat = 0; }
			}
		});
		NumBlank.setFont(new Font("Times New Roman", Font.BOLD, 20));
		NumBlank.setBackground(Color.LIGHT_GRAY);
		NumBlank.setBounds(702, 62, 303, 29);
		frame.getContentPane().add(NumBlank);
		
		JCheckBox NumSpaces = new JCheckBox("Number Of Spaces");		//Number Of Spaces Check Box 
		NumSpaces.setSelected(true);
		NumSpaces.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (NumSpaces.isSelected() == true) { Global.NumSpacesStat = 1; }
				else { Global.NumSpacesStat = 0; }
			}
		});
		NumSpaces.setFont(new Font("Times New Roman", Font.BOLD, 20));
		NumSpaces.setBackground(Color.LIGHT_GRAY);
		NumSpaces.setBounds(702, 108, 303, 29);
		frame.getContentPane().add(NumSpaces);
		
		JCheckBox NumWords = new JCheckBox("Number Of Words");			//Number Of Words Check Box
		NumWords.setSelected(true);
		NumWords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (NumWords.isSelected() == true) { Global.NumWordsStat = 1; }
				else { Global.NumWordsStat = 0; }
			}
		});
		NumWords.setFont(new Font("Times New Roman", Font.BOLD, 20));
		NumWords.setBackground(Color.LIGHT_GRAY);
		NumWords.setBounds(702, 156, 303, 29);
		frame.getContentPane().add(NumWords);
		
		JCheckBox AvgChar = new JCheckBox("Average Characters Per Line");//Average Characters Per Line Check Box
		AvgChar.setSelected(true);
		AvgChar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (AvgChar.isSelected() == true){ Global.AvgCharStat = 1; }
				else { Global.AvgCharStat = 0; }
			}
		});
		AvgChar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		AvgChar.setBackground(Color.LIGHT_GRAY);
		AvgChar.setBounds(702, 203, 303, 29);
		frame.getContentPane().add(AvgChar);
		
		JCheckBox AvgWordLth = new JCheckBox("Average Word Length");	//Average Word Length Check Box
		AvgWordLth.setSelected(true);
		AvgWordLth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (AvgWordLth.isSelected() == true) { Global.AvgWordLthStat = 1; }
				else { Global.AvgWordLthStat = 0; }
			}
		});
		AvgWordLth.setFont(new Font("Times New Roman", Font.BOLD, 20));
		AvgWordLth.setBackground(Color.LIGHT_GRAY);
		AvgWordLth.setBounds(702, 251, 303, 29);
		frame.getContentPane().add(AvgWordLth);
		
		JCheckBox MostCmn = new JCheckBox("Most Common Word");			//Most Common Word Check Box
		MostCmn.setSelected(true);
		MostCmn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (MostCmn.isSelected() == true) { Global.MostCmnStat = 1; }
				else { Global.MostCmnStat = 0; }
			}
		});
		MostCmn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		MostCmn.setBackground(Color.LIGHT_GRAY);
		MostCmn.setBounds(702, 296, 303, 29);
		frame.getContentPane().add(MostCmn);
		
		JCheckBox MstCmnWrdFrq = new JCheckBox("Most Common Word Frequency"); //Most Common Word Frequency Check Box
		MstCmnWrdFrq.setSelected(true);
		MstCmnWrdFrq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (MstCmnWrdFrq.isSelected() == true) { Global.MstCmnWrdFrqStat = 1; }
				else { Global.MstCmnWrdFrqStat = 0; }
			}
		});
		MstCmnWrdFrq.setFont(new Font("Times New Roman", Font.BOLD, 20));
		MstCmnWrdFrq.setBackground(Color.LIGHT_GRAY);
		MstCmnWrdFrq.setBounds(702, 340, 303, 29);
		frame.getContentPane().add(MstCmnWrdFrq);
		
		JButton btnNewButton = new JButton("Select File");			//Selects file to be analyzed
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.setMultiSelectionEnabled(true);
				fileChooser.showOpenDialog(frame);					//File selector window
				textArea.setText(" Please Select File(s)\n ");
				Global.h = fileChooser.getSelectedFiles();
				if(Global.h.length != 0){ 							//Checks to see if a file had been selected
					textArea.setText(" File(s) Selected: \n ");
					for (int x = 0; x < Global.h.length; x++) {		//Selects all of the file(s) and displays the file name(s)               	 	
						textArea.append(Global.h[x].getName()+"\n ");
					}
				}
				else { textArea.setText(" Please Select File(s)\n ");}
			} 
		});
		
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));	
		btnNewButton.setBounds(15, 391, 200, 35);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnFileHistory = new JButton("File History");		//File History Button
		btnFileHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (history.size() == 0) {							//Error if file has not been selected
			        textArea.setText(" No File History, Please Run File Analyzer First");
				} else {											//Verifies that a file had been selected and prints history
					textArea.setText(" File(s) Selected\n ");
					for (int i = 0; i < history.size(); i++) {
				        textArea.append(history.get(i).getFileName() + " | Date: " + history.get(i).getDate() + "\n ");
					}
				}
			}
		});
		
				
		btnFileHistory.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnFileHistory.setBounds(445, 391, 200, 35);
		frame.getContentPane().add(btnFileHistory);
		
		JButton btnFileAverages = new JButton("File Averages");		//File Averages Button
		btnFileAverages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Global.h == null ) {							//Error if no file was selected	
					textArea.setText(" No File Averages, Please Run File Analyzer First");
				}
				else {												//Verifies that a file had been selected
					Averages Ave = new Averages();
					Ave.compute(history);		
					textArea.setText(Ave.stringAvgtext(
							Global.NumLinesStat, Global.NumBlankStat, Global.NumSpacesStat, Global.NumWordsStat, 
							Global.AvgCharStat, Global.AvgWordLthStat) + Global.newFileData.stringAvg(Global.MostCmnStat, Global.MstCmnWrdFrqStat)); 
				}
			}
		});
		btnFileAverages.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnFileAverages.setBounds(654, 391, 200, 35);
		frame.getContentPane().add(btnFileAverages);
		
		JButton btnRunAnalyzer = new JButton("Run Analyzer");		//Runs Analyzer and Displays the information
		btnRunAnalyzer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Global.h  == null) {								//Error if no file was selected before running analyzer	
					textArea.setText(" Please Select a File Before Running Analyzer ");
				}
				
				else {												//Runs all the file(s) through the analyzer		
					try {
						textArea.setText("");
						for (int x = 0; x < Global.h.length; x++) {	
						String fileContents = FileUtils.readFileToString(Global.h[x]);
						// make a LL of wordnodes of the fileContents that stores
						// every word in the file and the frequency at which it 
						// occurs in the file.
						// ***MUST DO THIS BEFORE THE OTHER FILE METRICS CAN BE USED***
						LinkedList<WordNode> L = FileMetrics.wordCount(fileContents);
						// get date of file processing, store in FileData node for the
						Calendar cal;
						cal = Calendar.getInstance();
						Global.newFileData = new FileData(Global.h[x].getName(), fileDate.format(cal.getTime()));
						// Use the Linked List L and the String File Contents to calculate and store all the
						// file metrics in a FileData node
						Global.newFileData.setAverageCharsPerLine((int) FileMetrics.averageCharsPerLine(L, fileContents));
						Global.newFileData.setAverageWordLength((int) FileMetrics.averageWordLength(L));
						Global.newFileData.setBlankLines(FileMetrics.totalEmptyLines(fileContents));
						Global.newFileData.setLines(FileMetrics.totalLines(fileContents));
						Global.newFileData.setMostCommon(FileMetrics.topWord(L));
						Global.newFileData.setSpaces(FileMetrics.totalSpaces(fileContents));
						Global.newFileData.setWords(FileMetrics.totalWords(L));
				        textArea.append(Global.newFileData.stringtext(				//Checks to see what which check marks have been selected (out put)
				        		Global.NumLinesStat, Global.NumBlankStat, Global.NumSpacesStat, Global.NumWordsStat, Global.AvgCharStat, 
				        		Global.AvgWordLthStat, Global.MostCmnStat, Global.MstCmnWrdFrqStat)+"\n\n");
						// keep the data from the file stored in the history linked list
						// by storing a FileData data node in the history linked list
						history.add(Global.newFileData);
						}
					} 
					catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 					
					
				}
			} 
		});
				
	
		btnRunAnalyzer.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnRunAnalyzer.setBounds(230, 391, 200, 35);
		frame.getContentPane().add(btnRunAnalyzer);
		
		JButton btnHelp = new JButton("Help?");						 //Help Button and text
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("Instructions:\n\n" + " 1. Please select the information (check marks) you wish to be displayed. \n\n"
						+" 2. Then select the text file that is to be analyzed. \n\n" 
						+ " 3. Then select Run Analyzer, which will  display the results. \n\n" 
						+ " 4. The File History button will provide a list of all the Text files analyzed. \n\n"
						+ " 5. The File Averages button will provide statistics of all files analyzed. \n\n");
			}
		});
		btnHelp.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnHelp.setBounds(890, 391, 111, 35);
		frame.getContentPane().add(btnHelp);
		
	}
}
