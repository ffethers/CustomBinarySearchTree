package project4;

/**
* This represents a RestStop. The nodes in the BST will store data of type RestStop to represent the mountain.
* Depending on what supplies or obstacles are contained in the mountain's RestStops, the program will 
* determine what paths are safe for the hiker to descend
* 
* @author Finn Fetherstonhaugh
* @version 11/25/22
*/
public class RestStop implements Comparable<RestStop> {
	
	private String label;
	private int food;
	private int rafts; 
	private int axes;
	private int fallenTrees;
	private int rivers;
	
	public RestStop() {
		label = null;
		food = 0;
		rafts = 0;
		axes = 0;
		fallenTrees = 0;
		rivers = 0;
	}
	
	/**
	 * Returns the label of this rest stop
	 * @return label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * sets the label of this rest stop to the parameter value
	 * @param label
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * Returns the current value of food at this rest stop
	 * @return food
	 */
	public int getFood() {
		return food;
	}
	/**
	 * sets the current value of food at this rest stop to the parameter value
	 * @param food
	 */
	public void setFood(int food) {
		this.food = food;
	}
	/**
	 * Returns the current value of rafts at this rest stop
	 * @return rafts
	 */
	public int getRafts() {
		return rafts;
	}
	/**
	 * sets the current value of rafts at this rest stop to the parameter value
	 * @param rafts
	 */
	public void setRafts(int rafts) {
		this.rafts = rafts;
	}
	/**
	 * Returns the current value of axes at this rest stop
	 * @return axes
	 */
	public int getAxes() {
		return axes;
	}
	/**
	 * sets the current value of axes at this rest stop to the parameter value
	 * @param axes
	 */
	public void setAxes(int axes) {
		this.axes = axes;
	}
	/**
	 * Returns the current value of fallen trees at this rest stop
	 * @return fallen trees
	 */
	public int getFallenTrees() {
		return fallenTrees;
	}
	/**
	 * sets the current value of fallen trees at this rest stop to the parameter value
	 * @param fallen trees
	 */
	public void setFallenTrees(int fallenTrees) {
		this.fallenTrees = fallenTrees;
	}
	/**
	 * Returns the current value of rivers at this rest stop
	 * @return rivers
	 */
	public int getRivers() {
		return rivers;
	}
	/**
	 * sets the current value of rivers at this rest stop to the parameter value
	 * @param rivers
	 */
	public void setRivers(int rivers) {
		this.rivers = rivers;
	}
	/**
	 * compares two rest stops using their labels based on alphabetical order 
	 * 
	 * @param o RestStop to be compared to
	 * 
	 * @return -1 if this is less than o
	 * @return 0 if this equals o
	 * @return 1 if this is greater than o
	 * 
	 * @throws IllegalArgumentException if argument is null or not of type RestStop
	 */
	@Override
	public int compareTo(RestStop o) {
		if (this == o ) { //true if the same memory address
            return 0;
        }
        if (o == null) {
            throw new IllegalArgumentException("Argument cannot be null"); 
        }
        if ( ! (o instanceof RestStop) ) {
        	throw new IllegalArgumentException("Argument must be of type RestStop");
        }
        RestStop r = (RestStop) o; 
        if (r.getLabel().compareTo(label) < 0) { // if greater than
        	return 1;
        }
        else if (r.getLabel().compareTo(label) > 0) { //if less than
        	return -1;
        }
        else { //if equal
        	return 0;
        }
	}
	
	/**
	 * determines if two RestStops are equal based on all of its data fields
	 * 
	 * @param obj RestStop to be compared to
	 * 
	 * @return true if all data field are equal, false if not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj ) { //true if same memory address
            return true;
        }

        if (obj == null ) { //false if null
            return false; 
        }
 
        if ( ! (obj instanceof RestStop) ) { //false if not correct type
            return false; 
        }
        RestStop e = (RestStop) obj; 

        if (e.label.equals(this.label) && e.food == this.food && e.rafts == this.rafts && //true if all data fields are equal
        		e.axes == this.axes && e.fallenTrees == this.fallenTrees && e.rivers == this.rivers) {
        	return true;
        }
		return false;
	}

	/**
	 * returns the string representation of this RestStop using the label
	 * 
	 * @return label of rest stop
	 */
	public String toString() {
		return this.label;
	}




}
