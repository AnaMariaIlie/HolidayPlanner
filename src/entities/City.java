package entities;

import java.util.Comparator;
import java.util.PriorityQueue;

public class City extends Entity {

	public PriorityQueue<Location> cityLocations;

	public City(String entityName) {
		super(entityName);
		this.cityLocations = new PriorityQueue<Location>(new Comparator<Location>() {

			public int compare(Location w1, Location w2) {
				return w1.getAveragePrice() > w2.getAveragePrice() ? 1
						: (w1.getAveragePrice() < w2.getAveragePrice() ? -1 : 0);
			}
		});
	}

	public void addLocation(Location location) {
		cityLocations.add(location);
	}

}
