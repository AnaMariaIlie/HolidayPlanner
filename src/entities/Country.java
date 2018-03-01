package entities;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Country extends Entity {

	private PriorityQueue<County> countryCounties;

	public Country(String entityName) {
		super(entityName);
		this.entityParent = null;
		this.countryCounties = new PriorityQueue<County>(new Comparator<County>() {

			public int compare(County w1, County w2) {

				double comp1 = w1.getCountyCities().peek().getCityLocations().peek().getAveragePrice();
				double comp2 = w2.getCountyCities().peek().getCityLocations().peek().getAveragePrice();

				return comp1 > comp2 ? 1 : (comp1 < comp2 ? -1 : 0);
			}
		});

	}

	public void addCounty(County county) {
		countryCounties.add(county);
	}

	public PriorityQueue<County> getCountryCounties() {
		return countryCounties;
	}

}
