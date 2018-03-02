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

	public static HashMap<String, Location> asocNameLocation;
	public static HashMap<String, Activity> asocNameActivity;
	public static HashMap<String, Country> asocNameCountries;
	public static HashMap<String, County> asocNameCounties;
	public static HashMap<String, City> asocNameCities;

	public HandleRequests() {
		super();
		HandleRequests.asocNameLocation = new HashMap<String, Location>();
		HandleRequests.asocNameActivity = new HashMap<String, Activity>();
		HandleRequests.asocNameCities = new HashMap<String, City>();
		HandleRequests.asocNameCounties = new HashMap<String, County>();
		HandleRequests.asocNameCountries = new HashMap<String, Country>();
	}

	public static HandleRequests readDates() {

		HandleRequests handleRequests = new HandleRequests();
		BufferedReader br = null;
		try {

			br = new BufferedReader(new FileReader("dates.txt"));
			String line;

			while ((line = br.readLine()) != null) {

				boolean cityContainsLocation = false;
				Location resultLocation = new Location();
				String[] tokens = line.split(",");
				resultLocation.nameLocation = tokens[0];

				resultLocation.setAveragePrice(Double.parseDouble(tokens[4]));

				/******** Parse city ******/
				City tokCity;
				/* If the city already exists, we take it from the hashMap */
				if (asocNameCities.get(tokens[1]) != null) {
					tokCity = asocNameCities.get(tokens[1]);
				} else {
					tokCity = new City(tokens[1]);
					asocNameCities.put(tokens[1], tokCity);
				}

				/*
				 * If the location already exists in the city, we don't add it
				 * again.
				 */
				cityContainsLocation = tokCity.locations.contains(resultLocation);
				if (!cityContainsLocation) {
					tokCity.addLocation(resultLocation);
				}
				resultLocation.cityLocation = tokCity;

				/******** Parse county ******/
				County tokCounty;
				/* If the county already exists, we take it from the hashMap */
				if (asocNameCounties.get(tokens[2]) != null) {

					tokCounty = asocNameCounties.get(tokens[2]);
					/*
					 * If the city already exists in the county, we don't add it
					 * again.
					 */
					if (!tokCounty.countyCities.contains(tokCity)) {
						tokCounty.addCity(tokCity);
					}
				} else {
					tokCounty = new County(tokens[2]);
					asocNameCounties.put(tokens[2], tokCounty);
					tokCounty.addCity(tokCity);
				}

				/*
				 * We add the location in the county's locations only if it
				 * doesn't exists in the city.
				 */
				if (!cityContainsLocation) {
					tokCounty.addLocation(resultLocation);
				}
				resultLocation.countyLocation = tokCounty;

				/******** Parse country ******/
				Country tokCountry;
				/* If the country already exists, we take it from the hashMap */
				if (asocNameCountries.get(tokens[3]) != null) {
					tokCountry = asocNameCountries.get(tokens[3]);
					/*
					 * If the county already exists in the country, we don't add
					 * it again.
					 */
					if (!tokCountry.countryCounties.contains(tokCounty)) {
						tokCountry.addCounty(tokCounty);
					}
				} else {
					tokCountry = new Country(tokens[3]);
					asocNameCountries.put(tokens[3], tokCountry);
					tokCountry.addCounty(tokCounty);
				}

				/*
				 * We add the location in the country's locations only if it
				 * doesn't exists in the city.
				 */
				if (!cityContainsLocation) {
					tokCountry.addLocation(resultLocation);
				}
				resultLocation.countryLocation = tokCountry;

				/******** Parse activities **************/
				String[] activityTokens = tokens[5].split(";");
				ArrayList<Activity> activitiesList = new ArrayList<Activity>();
				int activityTokensLen = activityTokens.length;

				for (int i = 0; i < activityTokensLen; i++) {

					/*
					 * If the activity already exists, we take it from the
					 * hashMap
					 */
					if (asocNameActivity.get(activityTokens[i]) != null) {

						if (!cityContainsLocation) {
							asocNameActivity.get(activityTokens[i]).addLocation(resultLocation);
						}
						activitiesList.add(asocNameActivity.get(activityTokens[i]));
					} else {

						Activity varActivity = new Activity(activityTokens[i]);
						if (!cityContainsLocation) {
							varActivity.addLocation(resultLocation);
						}
						activitiesList.add(varActivity);
						asocNameActivity.put(activityTokens[i], varActivity);
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

				asocNameLocation.put(tokens[0], resultLocation);

			}

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

		return handleRequests;
	}

}
