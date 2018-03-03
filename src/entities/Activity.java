package entities;

import java.util.ArrayList;

public class Activity {

	/**
	 * The name of a particular activity.
	 */
	protected String name;
	/**
	 * A list of locations where you can practice the activity.
	 */
	protected ArrayList<Location> activityLocations;

	public Activity(String name) {
		super();
		this.name = name;
		this.activityLocations = new ArrayList<Location>();
	}

	public Activity() {

	}

	public String getName() {
		return name;
	}

	public ArrayList<Location> getActivityLocations() {
		return activityLocations;
	}

	public void addLocation(Location location) {
		activityLocations.add(location);
	}

	@Override
	public String toString() {
		return name;
	}

}
