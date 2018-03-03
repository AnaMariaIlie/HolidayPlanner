package entities;

import java.util.ArrayList;
import java.util.Arrays;

public class Location {

	/**
	 * The name of the location.
	 */
	private String nameLocation;
	/**
	 * The city in which the location is situated.
	 */
	private City cityLocation;
	/**
	 * The county in which the location is situated.
	 */
	private County countyLocation;
	/**
	 * The country in which the location is situated.
	 */
	private Country countryLocation;
	/**
	 * The average price per day.
	 */
	private double averagePrice;
	/**
	 * A list of activities that can be practiced.
	 */
	public ArrayList<Activity> possibleActivities;
	/**
	 * The available period for visits.
	 */
	public Period period;

	public Location(String nameLocation) {
		super();
		this.nameLocation = nameLocation;
		this.possibleActivities = new ArrayList<Activity>();
	}

	public Location() {

	}

	/**
	 * 
	 * @return details about a location.
	 */
	public String showDetails() {
		return "\n" + nameLocation + " is situated in " + cityLocation.entityName + "\nThe average price per day is: "
				+ averagePrice + "$." + "\nYou have the oportunity to do following activities: "
				+ Arrays.toString(possibleActivities.toArray()) + "\nYou may arrive here by : "
				+ period.showDate("start") + "\nAnd you may leave before : " + period.showDate("end");
	}

	@Override
	public String toString() {
		return nameLocation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nameLocation == null) ? 0 : nameLocation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (nameLocation == null) {
			if (other.nameLocation != null)
				return false;
		} else if (!nameLocation.equals(other.nameLocation))
			return false;
		return true;
	}

	public String getNameLocation() {
		return nameLocation;
	}

	public void setNameLocation(String nameLocation) {
		this.nameLocation = nameLocation;
	}

	public City getCityLocation() {
		return cityLocation;
	}

	public void setCityLocation(City cityLocation) {
		this.cityLocation = cityLocation;
	}

	public County getCountyLocation() {
		return countyLocation;
	}

	public void setCountyLocation(County countyLocation) {
		this.countyLocation = countyLocation;
	}

	public Country getCountryLocation() {
		return countryLocation;
	}

	public void setCountryLocation(Country countryLocation) {
		this.countryLocation = countryLocation;
	}

	public double getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(double averagePrice) {
		this.averagePrice = averagePrice;
	}

	public ArrayList<Activity> getPossibleActivities() {
		return possibleActivities;
	}

	public void setPossibleActivities(ArrayList<Activity> possibleActivities) {
		this.possibleActivities = possibleActivities;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

}
