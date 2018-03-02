package entities;

import java.util.ArrayList;

public class Country extends Entity {

	public ArrayList<County> countryCounties;

	public Country(String entityName) {
		super(entityName);
		this.countryCounties = new ArrayList<County>();

	}

	public void addCounty(County county) {
		countryCounties.add(county);
	}

}
