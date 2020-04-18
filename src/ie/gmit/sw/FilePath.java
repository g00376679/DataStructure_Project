package ie.gmit.sw;

import java.awt.FileDialog;
import java.awt.Frame;

/**
 * This class is used to choose the file from the system and read the path of
 * the file instead of typing the file path manually.
 * 
 * @author Adil
 *
 */
public class FilePath {
	String filePath;

	public String readFilePath(String message) {
		FileDialog dialog = new FileDialog(new Frame(), message, FileDialog.LOAD);
		dialog.setVisible(true);
		String fileName = dialog.getFile();
		String path = dialog.getDirectory();
		if (path == null) {
			System.out.println("No Path Chosen...!!!\nProgram is terminated");
			System.exit(0);
		} else {
			filePath = path + fileName;
		}
		return filePath;
	}
}
