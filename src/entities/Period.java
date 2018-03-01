package entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Period {

	public Date start;
	public Date end;

	public Period(Date start, Date end) {
		super();
		this.start = start;
		this.end = end;
	}

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
}
