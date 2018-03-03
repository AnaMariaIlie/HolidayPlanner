package entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

public class Period {

	/**
	 * The beginning of the period you can stay in a location.
	 */
	private Date start;
	/**
	 * The end of the period you can stay in a location.
	 */
	private Date end;

	public Period(Date start, Date end) {
		super();
		this.start = start;
		this.end = end;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	/**
	 * This method is used to show the date in an appropriated format.
	 * 
	 * @param date
	 * @return formatted date
	 */
	public String showDate(String string) {

		DateFormat outputFormatter = new SimpleDateFormat("EEEEEEEEE, dd MM yyyy", Locale.ENGLISH);
		String output = "";
		if (string.equals("start")) {
			output = outputFormatter.format(start);
		} else if (string.equals("end")) {
			output = outputFormatter.format(end);
		}

		return output;
	}

	/**
	 * This method calculates the days between the start date and the end date.
	 * 
	 * @return number of days in a period.
	 */
	public int noAvailableDays() {

		int days = (int) ChronoUnit.DAYS.between(start.toInstant(), end.toInstant());
		return days;
	}
}
