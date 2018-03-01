package entities;

import java.util.Comparator;
import java.util.PriorityQueue;

public class County extends Entity {

	private PriorityQueue<City> countyCities;

	public County(String entityName) {
		super(entityName);
		this.countyCities = new PriorityQueue<City>(new Comparator<City>() {

			public int compare(City w1, City w2) {

				double comp1 = w1.getCityLocations().poll().getAveragePrice();
				double comp2 = w2.getCityLocations().poll().getAveragePrice();

				return comp1 > comp2 ? 1 : (comp1 < comp2 ? -1 : 0);
			}
		});
	}

	public PriorityQueue<City> getCountyCities() {
		return countyCities;
	}

	public void addCity(City city) {
		countyCities.add(city);
	}

	public void setCountyCities(PriorityQueue<City> countyCities) {
		this.countyCities = countyCities;
	}

}
