package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import entities.Activity;
import entities.City;
import entities.Country;
import entities.County;
import entities.Location;

public class Methods {

	public static final int NO_DAYS = 10;
	public static final int START_INDEX_SHOW = 10;
	public static final int START_INDEX_TOP = 6;

	/**
	 * This method show top 5 cheapest locations to visit in the A-B period;
	 * 
	 * @param place
	 *            type
	 * @param place
	 *            name
	 * @param start
	 *            date
	 * @param end
	 *            date
	 */
	public static void showTopLocations(String type, String name, Date A, Date B) {

		if (type.equals("city")) {

			City city = null;
			if (HandleRequests.asocNameCities.get(name) != null) {

				city = HandleRequests.asocNameCities.get(name);
				city.topFiveLocations(A, B);
			} else {
				System.out.println("\nWe're sorry, but " + name + " does not exist in our database.");
			}

		} else if (type.equals("county")) {

			County county = null;
			if (HandleRequests.asocNameCounties.get(name) != null) {

				county = HandleRequests.asocNameCounties.get(name);
				county.topFiveLocations(A, B);
			} else {
				System.out.println("\nWe're sorry, but " + name + " does not exist in our database.");
			}

		} else if (type.equals("country")) {

			Country country = null;
			if (HandleRequests.asocNameCountries.get(name) != null) {

				country = HandleRequests.asocNameCountries.get(name);
				country.topFiveLocations(A, B);
			} else {
				System.out.println("\nWe're sorry, but " + name + " does not exist in our database.");
			}

		}

	}

	/**
	 * This method show the cheapest location where an activity can be practiced
	 * for 10 days.
	 * 
	 * @param activity
	 *            name
	 */
	public static void showBestLocationForActivity(String name) {

		Activity activity = null;
		if (HandleRequests.asocNameActivity.get(name) != null) {

			activity = HandleRequests.asocNameActivity.get(name);
			ArrayList<Location> locations = activity.activityLocations;
			Collections.sort(locations, new Comparator<Location>() {

				public int compare(Location w1, Location w2) {
					return w1.getAveragePrice() > w2.getAveragePrice() ? 1
							: (w1.getAveragePrice() < w2.getAveragePrice() ? -1 : 0);
				}
			});

			boolean foundLocation = false;
			for (Location loc : locations) {

				if (loc.period.noAvailableDays() > NO_DAYS) {
					System.out.println("\nThe best location to do " + name + " for " + NO_DAYS + " days is "
							+ loc.nameLocation + " from " + loc.cityLocation.entityName + " for just "
							+ loc.getAveragePrice() + "$");
					foundLocation = true;
					break;
				}
			}

			if (!foundLocation) {
				System.out.println("\nWe're sorry, but this activity is not available for " + NO_DAYS + " days.");
			}

		} else {
			System.out.println("\nWe're sorry, but this activity can not be practiced.");
		}

	}

	/**
	 * This method show informations about a specified location. It will show
	 * the last introduced location from file(doesn't allow duplicates)
	 * 
	 * @param location
	 *            name
	 */
	public static void showInfoLocation(String name) {

		Location location = null;
		if (HandleRequests.asocNameLocation.get(name) != null) {

			location = HandleRequests.asocNameLocation.get(name);
			System.out.println(location.showDetails());

		} else {
			System.out.println("\nWe're sorry, but " + name + " does not exist in our database.");
		}
	}

	public static void main(String[] args) {

		HandleRequests.readDates();

	}

}
