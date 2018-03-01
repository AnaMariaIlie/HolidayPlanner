package entities;

import java.util.ArrayList;
import java.util.Arrays;

public class Location {

	public String nameLocation;
	public City cityLocation;
	public County countyLocation;
	public Country countryLocation;
	private double averagePrice;

	public ArrayList<Activity> possibleActivities;
	public Period period;

	// TODO: tostring activities
	public String showDetails() {
		return nameLocation + " is situated in " + cityLocation.entityName + "\nThe price per day is: " + averagePrice
				+ "$" + "\nYou have the oportunity to do following activities: "
				+ Arrays.toString(possibleActivities.toArray()) + "\nYou may arrive here by : "
				+ period.showDate("start") + "\nAnd you may leave before : " + period.showDate("end");
	}

	public String getNameLocation() {
		return nameLocation;
	}

	public Location(String nameLocation, City cityLocation, County countyLocation, Country countryLocation,
			double averagePrice, ArrayList<Activity> possibleActivities, Period period) {
		super();
		this.nameLocation = nameLocation;
		this.cityLocation = cityLocation;
		this.countyLocation = countyLocation;
		this.countryLocation = countryLocation;
		this.averagePrice = averagePrice;
		this.possibleActivities = possibleActivities;
		this.period = period;
	}

	public Location() {

	}

	public double getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(double averagePrice) {
		this.averagePrice = averagePrice;
	}

}
