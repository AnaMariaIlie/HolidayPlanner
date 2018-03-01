package entities;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Activity {

	public String name;
	public PriorityQueue<Location> activityLocations;

	public Activity(String name) {
		super();
		this.name = name;
		this.activityLocations = new PriorityQueue<Location>(new Comparator<Location>() {

			public int compare(Location w1, Location w2) {
				return w1.getAveragePrice() > w2.getAveragePrice() ? 1
						: (w1.getAveragePrice() < w2.getAveragePrice() ? -1 : 0);
			}
		});
	}

	public Activity() {

	}

	public void addLocation(Location location) {
		activityLocations.add(location);
	}

	public PriorityQueue<Location> getLocationsForAnActivity() {
		return this.activityLocations;
	}

	@Override
	public String toString() {
		return name;
	}

}
