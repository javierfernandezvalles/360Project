import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import org.junit.Test;
import org.apache.commons.io.FileUtils; // used for copying the file's contents to a string


public class FileManagerTests {

	@Test
	public void testCorrectNumberOfLines() throws IOException {
		String fileName = "TestFile00.txt";
		String fileContents = FileUtils.readFileToString(new File(fileName));
		assertEquals(2, FileMetrics.totalLines(fileContents));
	}
	
	@Test
	public void testCorrectNumberOfBlankLines() throws IOException {
		String fileName = "TestFile01.txt";
		String fileContents = FileUtils.readFileToString(new File(fileName));
		assertEquals(3, FileMetrics.totalEmptyLines(fileContents));
	}
	 	 	
	@Test
	public void testCorrectNumberOfSpaces() throws IOException {
		String fileName = "TestFile02.txt";
		String fileContents = FileUtils.readFileToString(new File(fileName));
		assertEquals(6, FileMetrics.totalSpaces(fileContents));
	}
	
	@Test
	public void testCorrectNumberOfWords() throws IOException {
		String fileName = "TestFile03.txt";
		String fileContents = FileUtils.readFileToString(new File(fileName));
		assertEquals(6, FileMetrics.totalSpaces(fileContents));
	}
	
	@Test
	public void testAverageCharactersPerLineInTheFile() throws IOException {
		String fileName = "TestFile04.txt";
		String fileContents = FileUtils.readFileToString(new File(fileName));
		LinkedList<WordNode> L = FileMetrics.wordCount(fileContents);
		assertTrue(9.8 - FileMetrics.averageCharsPerLine(L, fileContents) <= .001);
	}
	
	@Test
	public void testAverageWordLength() throws IOException {
		String fileName = "TestFile05.txt";
		String fileContents = FileUtils.readFileToString(new File(fileName));
		LinkedList<WordNode> L = FileMetrics.wordCount(fileContents);
		assertTrue(3.7 - FileMetrics.averageWordLength(L) < .001);
	}
	
	@Test
	public void testMostCommonWords() throws IOException {
		String fileName = "TestFile06.txt";
		String fileContents = FileUtils.readFileToString(new File(fileName));
		LinkedList<WordNode> L = FileMetrics.wordCount(fileContents);
		assertEquals("Cloud", FileMetrics.topWord(L).word);
	}
}
