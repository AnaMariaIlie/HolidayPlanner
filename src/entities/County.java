package entities;

import java.util.ArrayList;

public class County extends Entity {

	public ArrayList<City> countyCities;

	public County(String entityName) {
		super(entityName);
		this.countyCities = new ArrayList<City>();
	}

	public void addCity(City city) {
		countyCities.add(city);
	}

}
