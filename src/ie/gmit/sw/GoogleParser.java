package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Map;

/**
 * This class is used to parse the Google 1000 word file.
 * 
 * @author Adil
 *
 */
public class GoogleParser {
	private String fileName;
	private Map<String, String> map;
	private Collection<String> googleWord;

	public GoogleParser(String fileName, Map<String, String> map, Collection<String> googleWord) {
		super();
		this.fileName = fileName;
		this.map = map;
		this.googleWord = googleWord;
	}

	/**
	 * This method reads the file from system and adds it to the map and the list.
	 * 
	 * @Big_O_Notation This method runs in linear time because while loop has to go
	 *                 through each line one by one till the end of file.
	 * 
	 * @return Map where each word is Key and Value to itself.
	 * @throws IOException
	 */
	public Map<String, String> parse() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
		String word = null;
		while ((word = br.readLine()) != null) {
			googleWord.add(word.toLowerCase().replaceAll("[^A-Za-z0-9 ]", ""));
			map.put(word.toLowerCase().replaceAll("[^A-Za-z0-9 ]", ""),
					word.toLowerCase().replaceAll("[^A-Za-z0-9 ]", ""));
		}
		br.close();
		return map;
	}
}
