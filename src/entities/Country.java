package entities;

import java.util.ArrayList;

public class Country extends Entity {

	/**
	 * A list of counties contained by country.
	 */
	private ArrayList<County> countryCounties;

	public Country(String entityName) {
		super(entityName);
		this.countryCounties = new ArrayList<County>();
	}

	public Country() {

	}

	public ArrayList<County> getCountryCounties() {
		return countryCounties;
	}

	public void addCounty(County county) {
		countryCounties.add(county);
	}

}
