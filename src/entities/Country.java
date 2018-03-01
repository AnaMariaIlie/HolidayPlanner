package entities;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Country extends Entity {

	public PriorityQueue<County> countryCounties;

	public Country(String entityName) {
		super(entityName);
		this.entityParent = null;
		this.countryCounties = new PriorityQueue<County>(new Comparator<County>() {

			public int compare(County w1, County w2) {

				double comp1 = w1.countyCities.peek().cityLocations.peek().getAveragePrice();
				double comp2 = w2.countyCities.peek().cityLocations.peek().getAveragePrice();

				return comp1 > comp2 ? 1 : (comp1 < comp2 ? -1 : 0);
			}
		});

	}

	public void addCounty(County county) {
		countryCounties.add(county);
	}

}
