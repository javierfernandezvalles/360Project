import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
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
				} catch (Exception e) {
					e.printStackTrace();
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
		btnHelp.setToolTipText("");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, 
						"1. Please select the text file that you wish to analyze. \n"
						+ "2. Select Read File, this will give you a break down of the selecteds file.\n"
						+ "The History Of All Files will provide a list of all the Text files analyzed. \n"
						+ "The Averages Of All Files will provide statistics of all files analyzed. ");
			}
		});
		btnHelp.setBounds(848, 16, 115, 29);
		frmTextFileAnalyzer.getContentPane().add(btnHelp);
		
		JFileChooser fileChooser = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("Text","txt"); //Filter only allows Text files to be selected
		fileChooser.setFileFilter(filter);
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		fileChooser.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		fileChooser.setBounds(37, 16, 796, 334);
		frmTextFileAnalyzer.getContentPane().add(fileChooser);
		
		JButton btnReadFile = new JButton("Read File");
		btnReadFile.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnReadFile.setBounds(32, 388, 205, 40);
		frmTextFileAnalyzer.getContentPane().add(btnReadFile);
		
		JButton btnHistory = new JButton("History Of All Files");
		btnHistory.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnHistory.setBounds(394, 388, 205, 40);
		frmTextFileAnalyzer.getContentPane().add(btnHistory);
		
		JButton btnAverages = new JButton("Averages Of All Files");
		btnAverages.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAverages.setBounds(744, 388, 205, 40);
		frmTextFileAnalyzer.getContentPane().add(btnAverages);
	}
}
