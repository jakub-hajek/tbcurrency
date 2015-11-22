package org.jakubhajek.tbcurrency;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jakubhajek.tbcurrency.exception.FormatException;

/**
 * Application main class
 * 
 * 
 * @author jakub.hajek
 *
 */
public class App {
	private static final String QUIT_COMMAND = "quit";
	private static final long PERIOD = 60000;
	private LineParser lineParser = new LineParser();
	private State state = new State();
	
	/**
	 * Starts the "printing thread" and processes console input
	 */
	private void run() {
		new PrinterThread(PERIOD, state).start();
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
		String line;
		try {
			while ((line = bi.readLine()) != null) {
				if (line == null || line.isEmpty()) continue;
				if (line.trim().toLowerCase().equals(QUIT_COMMAND)) {
					System.exit(0);
				}
				try {
					state.addPayment(lineParser.parse(line));
				} catch (FormatException e) {
					System.err.printf("Ingoring input line %s\n", line);
				}
			}
		} catch (IOException e) {
			System.err.println("Unexcpted error: ");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Parse the file identified by given path and calls run()
	 * 
	 * @param path
	 */
	private void run(final String path) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(path));
				for(String line; (line = br.readLine()) != null; ) {
					try {
						state.addPayment(lineParser.parse(line));
					} catch (FormatException e) {
						System.err.printf("Ingoring input line %s\n", line);
					}
				}
			} catch (FileNotFoundException e1) {
				System.err.println("File " + " not found. Continuing without it.");
			} catch (IOException e) {
				System.err.println("Error reading input file. Continuing without it.");
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
					}
				}
			}
			run();
	}
	
	public static void main(String[] args) {
		final App app = new App();
		if (args.length > 0) {
			app.run(args[0]);
		} else {
			app.run();
		}
	}
}
