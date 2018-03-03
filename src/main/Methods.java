package main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Activity;
import entities.City;
import entities.Country;
import entities.County;
import entities.Location;

public class Methods {

	private static final int NO_DAYS = 10;
	private static final int START_INDEX_SHOW = 10;
	private static final int START_INDEX_TOP = 6;
	private static final int START_INDEX_CHEAP = 12;

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
			if (HandleRequests.asocNameCity.get(name) != null) {

				city = HandleRequests.asocNameCity.get(name);
				city.topFiveLocations(A, B);
			} else {
				System.out.println("\nWe're sorry, but " + name + " does not exist in our database.");
			}

		} else if (type.equals("county")) {

			County county = null;
			if (HandleRequests.asocNameCounty.get(name) != null) {

				county = HandleRequests.asocNameCounty.get(name);
				county.topFiveLocations(A, B);
			} else {
				System.out.println("\nWe're sorry, but " + name + " does not exist in our database.");
			}

		} else if (type.equals("country")) {

			Country country = null;
			if (HandleRequests.asocNameCountry.get(name) != null) {

				country = HandleRequests.asocNameCountry.get(name);
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
			ArrayList<Location> locations = activity.getActivityLocations();
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
							+ loc.getNameLocation() + " from " + loc.getCityLocation().getEntityName() + " for just "
							+ loc.getAveragePrice() + "$.");
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
	 * the first introduced location from file(it doesn't allow duplicates)
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

		Scanner scanner = new Scanner(System.in);
		String input = "";
		System.out.println("Hy! I'm your holiday planner and I'll help you"
				+ " find the best places to spend your holiday. All you have to do is to relax."
				+ "\nHere are the available commands: " + "\nShow info <location name>"
				+ "\nTop 5 <type of the place(city, county, country)>,<name of the place>"
				+ ",<start of  the period(month day.year)>,<end of the period(month day.year)>"
				+ "\nCheapest 10 <activity name>\n");

		while (true) {

			System.out.print("> ");
			input = scanner.nextLine();
			if (input.contentEquals("exit")) {

				System.out.println("Thank you! We hope you've found your dream location.");
				break;
			} else if (input.startsWith("Show info ")) {

				String locationName = input.substring(START_INDEX_SHOW);
				showInfoLocation(locationName);
			} else if (input.startsWith("Top 5 ")) {
				try {
					String topInput = input.substring(START_INDEX_TOP);
					String[] tokens = topInput.split(",");
					String placeType = tokens[0];
					String placeName = tokens[1];
					DateFormat format = new SimpleDateFormat("MMMM dd.yyyy", Locale.ENGLISH);
					Date A = format.parse(tokens[2]);
					Date B = format.parse(tokens[3]);

					showTopLocations(placeType, placeName, A, B);
				} catch (ParseException e) {
					System.out.println("invalid dates");
					e.printStackTrace();
				}
			} else if (input.startsWith("Cheapest 10")) {

				String activity = input.substring(START_INDEX_CHEAP);
				showBestLocationForActivity(activity);
			} else {
				System.out.println("Invalid command. Please, review the top section.");
			}
		}

		scanner.close();

	}

}
