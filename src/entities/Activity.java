package entities;

import java.util.ArrayList;

public class Activity {

	public String name;
	public ArrayList<Location> activityLocations;

	public Activity(String name) {
		super();
		this.name = name;
		this.activityLocations = new ArrayList<Location>();
	}

	public Activity() {

	}

	public void addLocation(Location location) {
		activityLocations.add(location);
	}

	@Override
	public String toString() {
		return name;
	}

}
