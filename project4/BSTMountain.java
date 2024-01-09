package project4;

import java.util.ArrayList;

/**
 * This class inherits from the BST Class and represents the mountain itself.
 * Its nodes store data of type RestStop
 * 
 * @author Finn Fetherstonhaugh
 * @version 11/25/22
 */
public class BSTMountain extends BST<RestStop> {

	Hiker hiker = new Hiker();
	ArrayList<ArrayList<RestStop>> validPaths = new ArrayList<ArrayList<RestStop>>();

	/**
	 * Constructs a new empty BSTMountain Object
	 */
	public BSTMountain() {
		super();
	}

	/**
	 * Constructs a new balanced BSTMountain object based on the parameter
	 * 
	 * @param collection RestStop array to be sorted and added to the BST
	 */
	public BSTMountain(RestStop[] collection) {
		super(collection);
	}

	/**
	 * Prints out the validPaths ArrayList to the console
	 */
	public void printValidPaths() {
		for (int i = 0; i < validPaths.size(); i++) {
			for (RestStop r : validPaths.get(i)) {
				System.out.print(r + " ");
			}
			if (i != validPaths.size() - 1) {
				System.out.println();
			}
		}
	}

	/**
	 * Returns an ArrayList full of the RestStops along the path to the given
	 * RestStop, including the given RestStop
	 * 
	 * @param rs RestStop that should be found
	 * 
	 * @return ArrayList of RestStops that represents the path to the given node
	 */
	public ArrayList<RestStop> findPath(RestStop rs) {
		ArrayList<RestStop> path = new ArrayList<RestStop>();
		return recFindPath(rs, root, path);
	}

	/**
	 * Recursive method for findPath
	 * 
	 * @param RestStop to be found
	 * @param current  current node
	 * @param path     ArrayList to hold RestStops
	 * 
	 * @return ArrayList full of RestStops
	 */
	public ArrayList<RestStop> recFindPath(RestStop rs, Node current, ArrayList<RestStop> path) {
		path.add(current.data); // adds the current node's data to the path
		int c = current.data.compareTo(rs);
		if (c > 0) { // if current node is greater than target, move left
			return recFindPath(rs, current.left, path);
		} else if (c < 0) { // if current node is greater than target, move right
			return recFindPath(rs, current.right, path);
		} else { // if current node equals target, return the path
			return path;
		}
	}

	/**
	 * Finds all the valid paths down to the leaf nodes of a BSTMountain based on
	 * the resources and obstacles, and adds them to the validPaths data field
	 */
	public void traverse() {
		recTraverse(hiker, root, new ArrayList<RestStop>());
	}

	/**
	 * Recursive method for the traverse method
	 * 
	 * @param hiker       Hiker object that keeps track of the hiker's resources
	 * @param current     current node in traversal
	 * @param currentPath the current path that the method is exploring
	 */
	public void recTraverse(Hiker hiker, Node current, ArrayList<RestStop> currentPath) {
		// check if the ArrayList is empty (indicating that traversal has started
		// exploring a new path) adds the data in the new path to the empty ArrayList
		// (up to the current node)
		if (currentPath.size() == root.height + 1) {
			currentPath = findPath(current.data);
		}
		// if not empty, adds current to the ArrayList
		else {
			currentPath.add(current.data);
		}
		// if current is a leaf node, return the whole ArrayList (representing one safe
		// path) and set the ArrayList to empty so its ready for a new path
		if (current.height == 0) {
			validPaths.add(currentPath);
			return;
		}

		Hiker currentHiker = hiker.replicateHiker(); // makes a new hiker object with the previous hiker's data
		currentHiker.updateHiker(current.data); // updates the new hiker object according to the current restStop

		// if the left RestStop has valid resources and obstacles and is along a path
		// that has leaf nodes(indicated by the height), move left
		if (current.left != null && current.height == current.left.height + 1
				&& currentHiker.canGo(current.left.data)) {
			recTraverse(currentHiker, current.left, currentPath);
		}
		// if the right RestStop has valid resources and obstacles and is along a path
		// that has leaf nodes(indicated by the height)
		if (current.right != null && current.height == current.right.height + 1
				&& currentHiker.canGo(current.right.data)) {
			recTraverse(currentHiker, current.right, currentPath);
		}
	}

}
