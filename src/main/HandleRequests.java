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

	public HandleRequests() {
		super();
		this.possibleLocations = new ArrayList<Location>();
		this.asocNameLocation = new HashMap<String, Location>();
		this.asocNameActivity = new HashMap<String, Activity>();
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
				resultLocation.cityLocation = new City(tokens[1]);
				resultLocation.countyLocation = new County(tokens[2]);
				resultLocation.countryLocation = new Country(tokens[3]);
				resultLocation.setAveragePrice(Double.parseDouble(tokens[4]));

				/******** Parse activities **************/
				String[] activityTokens = tokens[5].split(";");
				ArrayList<Activity> activitiesList = new ArrayList<Activity>();

				/* Add the first and the last strings separately */
				String firstActivity = activityTokens[0].substring(1);
				int activityTokensLen = activityTokens.length;
				String str = activityTokens[activityTokensLen - 1];
				String lastActivity = str.substring(0, str.length() - 1);

				/* Add activities in their hashMap */
				/* For all activities add the their list the current location */

				Activity varActivity1 = new Activity(firstActivity);
				activitiesList.add(varActivity1);
				varActivity1.addLocation(resultLocation);
				handleRequests.asocNameActivity.put(firstActivity, varActivity1);

				for (int i = 1; i < activityTokensLen - 1; i++) {

					Activity varActivity = new Activity(activityTokens[i]);
					varActivity.addLocation(resultLocation);
					activitiesList.add(varActivity);
					handleRequests.asocNameActivity.put(activityTokens[i], new Activity(activityTokens[i]));
				}

				Activity varActivity2 = new Activity(lastActivity);
				activitiesList.add(varActivity2);
				varActivity2.addLocation(resultLocation);
				handleRequests.asocNameActivity.put(lastActivity, varActivity2);

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

				System.out.println(resultLocation.showDetails());

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
	}

}
