package main;

import java.util.ArrayList;
import java.util.HashMap;

import entities.Location;

public class Database {

	public ArrayList<Location> possibleLocations;
	public HashMap<String, Location> asocNameLocation;

	public Database() {
		super();
		this.possibleLocations = new ArrayList<Location>();
		this.asocNameLocation = new HashMap<String, Location>();
	}

}
