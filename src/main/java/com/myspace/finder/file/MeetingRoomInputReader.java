package com.myspace.finder.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MeetingRoomInputReader {

	FileReader fileReader = null;
	BufferedReader bufferReader = null;

	private boolean getBuffereReader(String filePath) {
		boolean result = false;
		try {
			File file = new File(filePath); // creates a new file instance
			if (file.exists()) {
				fileReader = new FileReader(file);
				bufferReader = new BufferedReader(fileReader); // creates a buffering character input stream
				result = true;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean setupInput(String[] args) {
		boolean result = false;
		if (args.length == 1 && this.getBuffereReader(args[0])) // check for input arguments
		{
			result = true;

		}
		return result;
	}

	public String readFileInputByLine() {
		String line = null;
		try {
			line = bufferReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}

	public void closeFile() {
		try {
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
