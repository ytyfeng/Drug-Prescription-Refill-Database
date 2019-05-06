import java.io.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

public class UI {

	public static String lines = "-----------------------------";
	private Scanner keyboard = null;
	
	public UI() {
		keyboard = new Scanner(System.in);
	}

	
	public int getCommand(int numOfCommands, String infoDialog) {
		int choice = -2;

		System.out.println(lines);
		System.out.println(infoDialog);
		System.out.println(lines);

		while (choice != -1) {
			System.out.print("---> Select an option : ");
			try {
				choice = keyboard.nextInt();
				keyboard.nextLine(); 

				if (choice >= 0 && choice < numOfCommands)
					return choice;
				else if (choice != -1)
					System.out.println("*** Invalid option. Please select again.");

			} catch (InputMismatchException ex) {
				System.out.println("*** Bad input! "
						+ "Enter an integer from 0 to " + (numOfCommands - 1));
				keyboard.nextLine(); // get rid of bad input 
			} catch (NoSuchElementException e) {
				keyboard.close(); 
				System.exit(-1); 
			}
		}

		return -1;
	}

	/* get info from the user */ 
	public String getInfo (String prompt) {
		
		String result;
		
		System.out.println(prompt + " ");
                System.out.print("--> ");
		result = keyboard.nextLine();
		//if the user wants return with no input, he should be able to 
		if (result.equals("-1"))
			return null; 
		return result;
	}

	public void sendMessage(String message) {
		System.out.println("*** " + message);
		System.out.print("Press ENTER to continue.");
		keyboard.nextLine(); 
	}

	public void printResults(ResultSet rset) {

		int noOfCols;
		int noOfRows;

		try {
			System.out.println();

			ResultSetMetaData data = rset.getMetaData();
			noOfCols = data.getColumnCount();


			// get column names
			for (int c = 0; c < noOfCols; c++)
				// getColumnName needs to start at 1
				System.out.printf("%-25s ", data.getColumnName(c + 1));
			System.out.println();
			while (rset.next()) {
				// process each row, rows/cols start at 1 for SQL but 0 for java
				for (int i = 1; i <= noOfCols; i++) {
					String element = rset.getString(i);
					if (element != null)
						// print an element in the row if we have data
						System.out.printf("%-25s ", rset.getString(i));
					else
						System.out.printf("%-25s ", "N/A");
				}
				System.out.println(); // move to next line
			}

			System.out.print("Press ENTER to return.");
			keyboard.nextLine(); // wait for user to press enter before next prompt

		} catch (Exception e) {
			e.printStackTrace();
		}

		return;

	}
}
