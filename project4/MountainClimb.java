package project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class contains the main method. It represents the hiker's descent by
 * parsing and validating the command line arguments, reading and parsing the
 * input file, producing any error messages, handling any exceptions thrown by
 * other classes, and producing output
 * 
 * @author Finn Fetherstonhaugh
 * @version 11/25/22
 */
public class MountainClimb {

	/**
	 * Main method of the program that reads the file and makes a BSTMountain object
	 * and a Hiker object to be tested
	 * 
	 * @param Args Command line arguments
	 * 
	 * @throws ArrayIndexOutOfBoundsExcpetion if no command line is provided
	 * @throws FileNotFoundException          If the file path is invalid or does
	 *                                        not exist
	 */
	public static void main(String[] Args) throws FileNotFoundException {
		
		
		
		
		/*
		// if no command line is provided, throw exception
		if (Args.length < 1) {
			throw new ArrayIndexOutOfBoundsException("Usage Error: must provide a command line to run the program");
		}

		// new file object using the command line argument
		File f = new File(Args[0]);

		// CITATION: Code from the colorlist example colorconverter class on ed
		if (!f.exists()) {
			System.err.println("Error: the file " + f.getAbsolutePath() + " does not exist.\n");
			System.exit(1);
		}
		if (!f.canRead()) {
			System.err.println("Error: the file " + f.getAbsolutePath() + " cannot be opened for reading.\n");
			System.exit(1);
		}

		// create new scanner object to scan the file
		Scanner scan = new Scanner(f);

		// creates an array of RestStops based on the file
		RestStop[] rs = parseLines(scan);

		// New BSTMountain object
		BSTMountain bst = new BSTMountain();

		// Adds the RestStops to the BSTMountain in the order that they were provided
		for (RestStop r : rs) {
			bst.add(r);
		}
		// find all valid paths
		bst.traverse();
		// print all valid paths
		bst.printValidPaths();
*/
	}

	/**
	 * Takes data from the input file and turns it into an array of RestStops to be
	 * added to the BSTMountain
	 * 
	 * @param scan intakes a scanner to get the RestStops from
	 * 
	 * @return reststops an array of RestStops
	 */
	public static RestStop[] parseLines(Scanner scan) {
		// make a new ArrayList to add data to
		ArrayList<RestStop> al = new ArrayList<RestStop>();

		while (scan.hasNextLine()) {
			// split the line using a space as a delimeter
			String[] split = scan.nextLine().split(" ");

			// make a new RestStop
			RestStop rs = new RestStop();

			if (split[0].length() == 1 && // check for valid label, otherwise skip line
					(split[0].charAt(0) >= 65 && split[0].charAt(0) <= 90)) {
				rs.setLabel(split[0]);
			} else {
				continue;
			}

			boolean hasObstacles = false; // this stays false unless an obstacle is found

			for (int i = 1; i < split.length; i++) {
				if (hasObstacles == false) { // if no obstacles, add every element into the RestStops data
					if (split[i].equals("food")) {
						rs.setFood(rs.getFood() + 1);
					} else if (split[i].equals("raft")) {
						rs.setRafts(rs.getRafts() + 1);
					} else if (split[i].equals("axe")) {
						rs.setAxes(rs.getAxes() + 1);
					} else if (split[i].equals("fallen")) {
						rs.setFallenTrees(rs.getFallenTrees() + 1);
						hasObstacles = true;
					} else if (split[i].equals("river")) {
						rs.setRivers(rs.getRivers() + 1);
						hasObstacles = true;
					} else {
						continue;
					}
				} else { // if obstacles are found, only add the remaining obstacles, and not the
							// supplies
					if (split[i].equals("fallen")) {
						rs.setFallenTrees(rs.getFallenTrees() + 1);
						hasObstacles = true;
					} else if (split[i].equals("river")) {
						rs.setRivers(rs.getRivers() + 1);
						hasObstacles = true;
					} else {
						continue;
					}
				}

			}
			// add the newly made RestStop object to the RestStop arrayList
			al.add(rs);
		}
		// make a new RestStop array and copy to the ArrayList to it, then return the
		// new array
		RestStop[] restStops = new RestStop[al.size()];
		al.toArray(restStops);
		return restStops;
	}
}
