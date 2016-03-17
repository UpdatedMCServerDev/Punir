package ban.util;

import java.text.SimpleDateFormat;

public class DateUtil {

	private static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.S");
	
	public static String format(long date) {
		return format.format(date);
	}

}
