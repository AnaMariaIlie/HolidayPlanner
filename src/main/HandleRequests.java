package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import entities.Activity;
import entities.City;
import entities.Country;
import entities.County;
import entities.Location;
import entities.Period;

public class HandleRequests {

	public ArrayList<Location> possibleLocations;
	public HashMap<String, Location> asocNameLocation;
	public HashMap<String, Activity> asocNameActivity;
	public HashMap<String, Country> asocNameCountries;
	public HashMap<String, County> asocNameCounties;
	public HashMap<String, City> asocNameCities;

	public HandleRequests() {
		super();
		this.possibleLocations = new ArrayList<Location>();
		this.asocNameLocation = new HashMap<String, Location>();
		this.asocNameActivity = new HashMap<String, Activity>();
		this.asocNameCities = new HashMap<String, City>();
		this.asocNameCounties = new HashMap<String, County>();
		this.asocNameCountries = new HashMap<String, Country>();
	}

	public static void main(String[] args) {

		HandleRequests handleRequests = new HandleRequests();
		BufferedReader br = null;
		try {

			br = new BufferedReader(new FileReader("dates.txt"));
			String line;

			while ((line = br.readLine()) != null) {

				Location resultLocation = new Location();
				String[] tokens = line.split(",");
				resultLocation.nameLocation = tokens[0];

				resultLocation.setAveragePrice(Double.parseDouble(tokens[4]));

				City tokCity;
				if (handleRequests.asocNameCities.get(tokens[1]) != null) {
					tokCity = handleRequests.asocNameCities.get(tokens[1]);
				} else {
					tokCity = new City(tokens[1]);
					handleRequests.asocNameCities.put(tokens[1], tokCity);
				}

				if (!tokCity.cityLocations.contains(resultLocation)) {
					tokCity.addLocation(resultLocation);
				}
				resultLocation.cityLocation = tokCity;

				County tokCounty;
				if (handleRequests.asocNameCounties.get(tokens[2]) != null) {
					tokCounty = handleRequests.asocNameCounties.get(tokens[2]);
				} else {
					tokCounty = new County(tokens[2]);
					handleRequests.asocNameCounties.put(tokens[2], tokCounty);
					tokCounty.addCity(tokCity);
				}

				if (!tokCounty.countyCities.contains(tokCity)) {
					tokCounty.addCity(tokCity);
				}
				resultLocation.countyLocation = tokCounty;

				Country tokCountry;
				if (handleRequests.asocNameCountries.get(tokens[3]) != null) {
					tokCountry = handleRequests.asocNameCountries.get(tokens[3]);
				} else {
					tokCountry = new Country(tokens[3]);
					handleRequests.asocNameCountries.put(tokens[3], tokCountry);
				}

				if (!tokCountry.countryCounties.contains(tokCounty)) {
					tokCountry.addCounty(tokCounty);
				}

				resultLocation.countryLocation = tokCountry;

				/******** Parse activities **************/
				String[] activityTokens = tokens[5].split(";");
				ArrayList<Activity> activitiesList = new ArrayList<Activity>();
				int activityTokensLen = activityTokens.length;

				for (int i = 0; i < activityTokensLen; i++) {

					if (handleRequests.asocNameActivity.get(activityTokens[i]) != null) {

						handleRequests.asocNameActivity.get(activityTokens[i]).addLocation(resultLocation);
						activitiesList.add(handleRequests.asocNameActivity.get(activityTokens[i]));
					} else {

						Activity varActivity = new Activity(activityTokens[i]);
						varActivity.addLocation(resultLocation);
						activitiesList.add(varActivity);
						handleRequests.asocNameActivity.put(activityTokens[i], varActivity);
					}

				}

				resultLocation.possibleActivities = activitiesList;

				/******** Parse date ********************/
				String[] dateTokens = tokens[6].split("-");
				DateFormat format = new SimpleDateFormat("MMMM dd.yyyy", Locale.ENGLISH);
				Date startDate = format.parse(dateTokens[0]);
				Date endDate = format.parse(dateTokens[1]);
				Period holidayPeriod = new Period(startDate, endDate);
				resultLocation.period = holidayPeriod;

				handleRequests.possibleLocations.add(resultLocation);
				handleRequests.asocNameLocation.put(tokens[0], resultLocation);

			}

			System.out.println("****Print county****");

			for (String name : handleRequests.asocNameCities.keySet()) {

				String key = name.toString();
				String value = handleRequests.asocNameCities.get(name).entityName;
				System.out.println(key + " " + value);

				System.out.println(handleRequests.asocNameCities.get(name).cityLocations.peek().nameLocation + " "
						+ handleRequests.asocNameCities.get(name).cityLocations.size());

			}

			// for (String name : handleRequests.asocNameCounties.keySet()) {
			//
			// String key = name.toString();
			// String value =
			// handleRequests.asocNameCounties.get(name).entityName;
			// System.out.println(key + " " + value);
			//
			// System.out.println(handleRequests.asocNameCounties.get(name).countyCities.peek().entityName
			// + " "
			// + handleRequests.asocNameCounties.get(name).countyCities.size());
			//
			// }

			/*
			 * for (String name : handleRequests.asocNameCountries.keySet()) {
			 * 
			 * String key = name.toString(); String value =
			 * handleRequests.asocNameCountries.get(name).entityName;
			 * System.out.println(key + " " + value);
			 * 
			 * System.out.println(handleRequests.asocNameCountries.get(name).
			 * countryCounties.peek().entityName + " " +
			 * handleRequests.asocNameCountries.get(name).countryCounties.size()
			 * );
			 * 
			 * }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
