import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

public class TextAnGUI {

	private JFrame frmTextFileAnalyzer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextAnGUI window = new TextAnGUI();
					window.frmTextFileAnalyzer.setVisible(true);
				} catch (Exception z) {
					z.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TextAnGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTextFileAnalyzer = new JFrame();
		frmTextFileAnalyzer.setTitle("Text File Analyzer");
		frmTextFileAnalyzer.setBounds(100, 100, 1000, 500);
		frmTextFileAnalyzer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTextFileAnalyzer.getContentPane().setLayout(null);
		
		JButton btnHelp = new JButton("Help?");
		btnHelp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnHelp.setToolTipText("");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent y) {
				JOptionPane.showMessageDialog(null, 
						"Please select the text file that you wish to analyze. \n"
						+ "File History will provide a list of all the Text files analyzed. \n"
						+ "File Averages will provide statistics of all files analyzed. ");
			}
		
		});
		btnHelp.setBounds(681, 389, 205, 40);
		frmTextFileAnalyzer.getContentPane().add(btnHelp);
		
		// use a linked list to store the data from every file processed
		LinkedList<FileData> history = new LinkedList<FileData>();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("");
		fileChooser.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		fileChooser.setApproveButtonText("Open");
		fileChooser.setFont(UIManager.getFont("EditorPane.font"));
		FileFilter filter = new FileNameExtensionFilter("Text","txt"); //Filter only allows Text files to be selected
		fileChooser.setFileFilter(filter);
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.addActionListener(new ActionListener() 
		
			{
			public void actionPerformed(ActionEvent y) {
				// Calendar and DateFormat classes used for recording the time each
				// file was processed in the program. 
				Calendar cal;
				DateFormat fileDate = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
				String fileName = fileChooser.getSelectedFile().getName();
				File h = fileChooser.getSelectedFile();
				if(h.exists()) 
				{
						try {
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
					        JOptionPane.showMessageDialog(null, newFileData.toString());
							// keep the data from the file stored in the history linked list
							// by storing a FileData data node in the history linked list
							history.add(newFileData);
						} 
						catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 					
				}	
				else {
			        JOptionPane.showMessageDialog(null, "Error: File Doesn't Exist");
				}
			}
		});
		fileChooser.setBounds(90, 16, 796, 334);
		frmTextFileAnalyzer.getContentPane().add(fileChooser);
			
		JButton btnHistory = new JButton("File History");// File History JButton
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent y) {
				if (history.size() == 0) {
			        JOptionPane.showMessageDialog(null, "No files have been processed.");
				} else {
					for (int i = 0; i < history.size(); i++) {
				        JOptionPane.showMessageDialog(null, history.get(i).getFileName() + " | " + history.get(i).getDate());
					}
				}
				
			}
		});
		btnHistory.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnHistory.setBounds(90, 389, 205, 40);
		frmTextFileAnalyzer.getContentPane().add(btnHistory);
		
		JButton btnFileAverages = new JButton("File Averages");
		btnFileAverages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent y) {
				int avgLines = 0, avgBlankLines = 0, avgSpaces = 0,avgWords = 0, avgAvgCharsPerLine = 0, avgAvgWordLength = 0;
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
				JOptionPane.showMessageDialog(null, "Over All Averages:\n\nAverage Lines = " + avgLines + "\nAverage Blank Lines = " + avgBlankLines + "\nAverage Spaces = " + avgSpaces
						+ "\nAverage Words = " + avgWords + "\nAverage Chars Per Line = " + avgAvgCharsPerLine + "\nAverage Word Length = "
						+ avgAvgWordLength); 

			}
		});
		btnFileAverages.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnFileAverages.setBounds(385, 389, 205, 40);
		frmTextFileAnalyzer.getContentPane().add(btnFileAverages);
	}
}
