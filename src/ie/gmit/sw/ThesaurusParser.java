package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Map;

/**
 * This class parse the Thesaurus file and adds it to the map.
 * 
 * @author Adil
 *
 */
public class ThesaurusParser {
	private String fileName;
	private Map<String, String> map;
	private Collection<String> googleWord;

	public ThesaurusParser(String fileName, Map<String, String> map, Collection<String> googleWord) {
		super();
		this.fileName = fileName;
		this.map = map;
		this.googleWord = googleWord;
	}

	/**
	 * 
	 * This method called parse which reads the file line by line and split the line
	 * by "," and search each word against the collection of words in the map of
	 * Google words if the word is found in the list this method will use each word
	 * of the line as Key in the map and found Google word would be the Value in the
	 * map.
	 * 
	 * @Big_O_Notation This method runs in Quadratic time when the file is being
	 *                 read it has to go through each and every single line. And
	 *                 after reading the each line it has to go through each and
	 *                 every single word to check if there is a match with the any
	 *                 word in Google word list.
	 * 
	 * @return Returns the map where Key is each word of the line in Therasus and
	 *         Value is the found word in the Google word which is Synonym to each
	 *         Key in the map.
	 * @throws IOException Input Output Exception
	 */
	public Map<String, String> parse() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
		String line = null;
		while ((line = br.readLine()) != null) {
			String[] words = line.split(",");
			String wordFound = getGoogleWord(words, googleWord);
			if (wordFound != null) {
				for (String word : words) {
					map.put(word.toLowerCase().replaceAll("[^A-Za-z0-9 ]", ""), wordFound);
				}
			}
		}
		br.close();
		return map;
	}

	/**
	 * This method finds the match between the words in the array of words
	 * (Thesaurus) and list of Google words.
	 * 
	 * @Big_O_Notation This method runs in linear time because loop has to go over
	 *                 each word one by one.
	 * 
	 * @param words
	 * @param googleWord
	 * @return It return the word if there is a match otherwise it returns null.
	 */
	public String getGoogleWord(String[] words, Collection<String> googleWord) {
		for (String word : words) {
			if (googleWord.contains(word)) {
				return word.toLowerCase().replaceAll("[^A-Za-z0-9 ]", "");
			}
		}
		return null;
	}
}