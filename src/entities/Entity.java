package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Entity {

	public String entityName;
	public ArrayList<Location> locations;
	public static final int FIVE = 5;

	public Entity(String entityName) {
		super();
		this.entityName = entityName;
		this.locations = new ArrayList<Location>();
	}

	public void addLocation(Location location) {
		this.locations.add(location);
	}

	public void topFiveLocations(Date A, Date B) {

		ArrayList<Location> resultList = new ArrayList<Location>();

		/*
		 * The list must be sorted in order to retrieve top 5 locations.
		 */

		Collections.sort(this.locations, new Comparator<Location>() {

			public int compare(Location w1, Location w2) {
				return w1.getAveragePrice() > w2.getAveragePrice() ? 1
						: (w1.getAveragePrice() < w2.getAveragePrice() ? -1 : 0);
			}
		});

		int k = 0;
		for (Location location : this.locations) {

			Date startDate = location.period.start;
			Date endDate = location.period.end;

			/* The desired period must fit in the location period */
			if ((!startDate.after(A) && !endDate.before(A)) && (!startDate.after(B) && !endDate.before(B))) {

				if (k < FIVE) {
					resultList.add(location);
					k++;
				} else {
					break;
				}
			}
		}

		if (!(k >= FIVE)) {

			System.out.println("\nThere are no 5 locations." + "\nAvailable locations are: "
					+ Arrays.toString(resultList.toArray()));
		} else {
			System.out.println("\nThe best 5 available locations are : " + Arrays.toString(resultList.toArray()));
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entityName == null) ? 0 : entityName.hashCode());
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
		Entity other = (Entity) obj;
		if (entityName == null) {
			if (other.entityName != null)
				return false;
		} else if (!entityName.equals(other.entityName))
			return false;
		return true;
	}

}
