import static org.junit.Assert.*;

import java.io.File;

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
		assertEquals(7, FileMetrics.totalSpaces(fileContents));
	}
	
	@Test
	public void testCorrectNumberOfWords() {
		String fileName = "TestFile03.txt";
		String fileContents = FileUtils.readFileToString(fileName);
		assertEquals(6, FileMetrics.totalSpaces(fileContents));
	}
	
	@Test
	public void testAverageCharactersPerLineInTheFile() {
		String fileName = "TestFile04.txt";
		String fileContents = FileUtils.readFileToString(fileName);
		assertEquals(6, FileMetrics.averageCharsPerLine(list, fileContents));
	}
}
