package ie.gmit.sw;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class provides the menu to the user and creates objects of all the
 * classes created e.g GoogleParser
 * 
 * @author Adil
 *
 */
public class Menu {
	boolean keepRunning;
	private Scanner s;
	private String text;

	private UserInputMap userInput;
	private Map<String, String> map;
	private Collection<String> googleWord;
	private FilePath filePath;

	public Menu() throws IOException {
		keepRunning = true;
		s = new Scanner(System.in);
		text = "Please follow the instructions.\n\t I. Type 1 to Proceed\n\tII. Type \"Quit\" to end the program\n";
		map = new ConcurrentHashMap<>();
		googleWord = new TreeSet<>();
		filePath = new FilePath();
		new GoogleParser(filePath.readFilePath("Choose Google File"), map, googleWord).parse();
		new ThesaurusParser(filePath.readFilePath("Choose Thesaurus File"), map, googleWord).parse();
		userInput = new UserInputMap(map);

	}

	/**
	 * This method provides the menu to its user and process the data after the user
	 * input.
	 */
	public void show() {
		while (keepRunning) {
			System.out.println(text);
			String option = s.next();
			process(option);
		}
	}

	/**
	 * This method takes the user input whether user wants to proceed or not it user
	 * wants to proceed with the programs it prompts the instructions to the user.
	 * 
	 * @param option
	 */
	private void process(String option) {
		Scanner console;
		try {
			if (option.equalsIgnoreCase("1")) {
				System.out.print("Enter Text :  ");
				console = new Scanner(System.in);
				String string = console.nextLine();
				System.out.println(userInput.swapping(string));
				System.out.println("\n\n");
			} else if (option.equalsIgnoreCase("Quit")) {
				System.exit(0);
				keepRunning = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}