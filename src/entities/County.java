package entities;

import java.util.ArrayList;

public class County extends Entity {

	/**
	 * A list of cities contained by county.
	 */
	private ArrayList<City> countyCities;

	public County(String entityName) {
		super(entityName);
		this.countyCities = new ArrayList<City>();
	}

	public County() {

	}

	public ArrayList<City> getCountyCities() {
		return countyCities;
	}

	public void addCity(City city) {
		countyCities.add(city);
	}

}
