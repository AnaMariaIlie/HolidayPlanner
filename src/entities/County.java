package entities;

import java.util.Comparator;
import java.util.PriorityQueue;

public class County extends Entity {

	public PriorityQueue<City> countyCities;

	public County(String entityName) {
		super(entityName);
		this.countyCities = new PriorityQueue<City>(new Comparator<City>() {

			public int compare(City w1, City w2) {

				double comp1 = w1.cityLocations.peek().getAveragePrice();
				double comp2 = w2.cityLocations.peek().getAveragePrice();

				return comp1 > comp2 ? 1 : (comp1 < comp2 ? -1 : 0);
			}
		});
	}

	public void addCity(City city) {
		countyCities.add(city);
	}

}
