import static org.junit.Assert.*;

import java.io.File;
import java.util.LinkedList;

import org.junit.Test;
import org.apache.commons.io.FileUtils; // used for copying the file's contents to a string


public class FileManagerTests {

	@Test
	public void testCorrectNumberOfLines() {
		String fileName = "TestFile00.txt";
		String fileContents = FileUtils.readFileToString(fileName);
		assertEquals(2, FileMetrics.totalLines(fileContents));
	}
	
	@Test
	public void testCorrectNumberOfBlankLines() {
		String fileName = "TestFile01.txt";
		String fileContents = FileUtils.readFileToString(fileName);
		assertEquals(2, FileMetrics.totalEmptyLines(fileContents));
	}
	 	 	
	@Test
	public void testCorrectNumberOfSpaces() {
		String fileName = "TestFile02.txt";
		String fileContents = FileUtils.readFileToString(fileName);
		assertEquals(6, FileMetrics.totalSpaces(fileContents));
	}
	
	@Test
	public void testCorrectNumberOfWords() {
		String fileName = "TestFile03.txt";
		String fileContents = FileUtils.readFileToString(fileName);
		assertEquals(7, FileMetrics.totalSpaces(fileContents));
	}
	
	@Test
	public void testAverageCharactersPerLineInTheFile() {
		String fileName = "TestFile04.txt";
		String fileContents = FileUtils.readFileToString(fileName);
		LinkedList<WordNode> L = FileMetrics.wordCount(fileContents);
		assertEquals(12, (int)FileMetrics.averageCharsPerLine(L, fileContents));
	}
	
	@Test
	public void testAverageWordLength() {
		String fileName = "TestFile05.txt";
		String fileContents = FileUtils.readFileToString(fileName);
		LinkedList<WordNode> L = FileMetrics.wordCount(fileContents);
		assertEquals(3.7, FileMetrics.averageWordLength(L));
	}
	
	@Test
	public void testMostCommonWords() {
		String fileName = "TestFile06.txt";
		String fileContents = FileUtils.readFileToString(fileName);
		LinkedList<WordNode> L = FileMetrics.wordCount(fileContents);
		assertEquals("Cloud", FileMetrics.topWord(L));
	}
}
