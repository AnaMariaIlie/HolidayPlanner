package main;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import entities.Activity;
import entities.City;
import entities.Country;
import entities.County;
import entities.Location;

public class Test {

	City city;
	County county;
	Country country;
	Location location;

	@Before
	public void setUp() {

		city = new City("Targoviste");
		county = new County("Dambovita");
		country = new Country("Romania");
		location = new Location("Old Center");
	}

	@org.junit.Test
	public void testCountry() {

		county.getCountyCities().add(city);
		country.addCounty(county);
		Assert.assertTrue(country.getCountryCounties().size() == 1);
	}

	@org.junit.Test
	public void testCounty() {

		city.addLocation(location);
		city.addLocation(new Location("Park"));
		city.addLocation(new Location("Theater"));
		city.addLocation(new Location("Museum"));
		Assert.assertTrue(city.getLocations().size() == 4);
	}

	@org.junit.Test
	public void test1HandleRequests() {

		HandleRequests.readDates();
		Assert.assertTrue(HandleRequests.asocNameCity.get("Targoviste").getEntityName().equals("Targoviste"));
	}

	@org.junit.Test
	public void test2HandleRequests() {

		HandleRequests.readDates();
		Assert.assertTrue(HandleRequests.asocNameCounty.get("Braila") == null);
	}

	@org.junit.Test
	public void testActivities() {

		HandleRequests.readDates();
		Activity activity = HandleRequests.asocNameActivity.get("walks");
		Assert.assertTrue(activity.getActivityLocations().get(0).getNameLocation().equals("Chindia park"));
	}

	@org.junit.Test
	public void testPeriod() {

		HandleRequests.readDates();
		Location location = HandleRequests.asocNameLocation.get("Auschwitz");
		Assert.assertTrue(location.period.noAvailableDays() == 14);
	}

	@After
	public void tearDown() {
		country = null;
		county = null;
		city = null;
	}

}