package project4;

/**
 * This class represents the hiker that will be descending the mountain, keeping
 * track of their supplies. It will be updated at the hiker descends the
 * mountain.
 * 
 * @author Finn Fetherstonhaugh
 * @version 11/25/22
 */
public class Hiker {

	private int food;
	private int rafts;
	private int axes;

	/**
	 * Constructs a new instance of the Hiker class with all data fields set to 0
	 */
	public Hiker() {
		food = 0;
		rafts = 0;
		axes = 0;
	}

	/**
	 * Constructs a new instance of the Hiker class and sets its variables according
	 * to the parameters
	 * 
	 * @param _food  amount of food a hiker has
	 * @param _rafts amount of rafts a hiker has
	 * @param _axes  amount of axes a hiker has
	 */
	public Hiker(int _food, int _rafts, int _axes) {
		food = _food;
		rafts = _rafts;
		axes = _axes;
	}

	/**
	 * Evaluates if a hiker is able travel to a rest stop based on supplies and
	 * obstacles
	 * 
	 * @param rs RestStop to be evaluated
	 * 
	 * @return true if hiker can go and false if not
	 */
	public boolean canGo(RestStop rs) {
		if (rs == null) {
			return false;
		}
		if (food < 1) { // hiker must have food to travel
			return false;
		}
		if (rs.getRivers() > rafts + rs.getRafts()) { // hiker must have 1 raft per river
			return false;
		}
		if (rs.getFallenTrees() > axes + rs.getAxes()) { // hiker must have 1 axe per fallen tree
			return false;
		}
		return true;
	}

	/**
	 * Changes the hiker's data fields based on the next RestStop they travel to
	 * 
	 * @param rs RestStop that the hiker has traveled to
	 */
	public void updateHiker(RestStop rs) {
		if (food > 0) {
			food--;
		}
		food += rs.getFood();
		rafts += rs.getRafts();
		axes += rs.getAxes();
		rafts = rafts - rs.getRivers();
		axes = axes - rs.getFallenTrees();
	}

	/**
	 * Creates a new hiker object with the same data values as the current hiker
	 * 
	 * @return newHiker new Hiker object
	 */
	public Hiker replicateHiker() {
		Hiker newHiker = new Hiker(food, rafts, axes); // copy the current hiker
		return newHiker;
	}
}
